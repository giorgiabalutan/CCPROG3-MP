import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Represents one of the floors of a {@link Dungeon}.
 * <p>
 * Contains the map for the gameplay, tracking the {@link Player}, grid, and entities on it.
 * It generates a map by reading from the {@link Layouts} and populating the grid.
 * It processes {@code Player} actions and manages creature actions.
 */

public class Floor
{
    /**
     * Tracks how many spaces high the {@code grid} is, or how many rows it has.
     */
    private int sizeY;
    /**
     * Tracks how many spaces wide the {@code grid} is, or how many columns it has.
     */
    private int sizeX;
    /**
     * Tracks how many spaces from the top edge the {@link Spawn} is located.
     */
    private int spawnY;
    /**
     * Tracks how many spaces from the left edge the {@code Spawn} is located.
     */
    private int spawnX;
    /**
     * Tracks if the {@code Player} has interacted with the {@link Exit}.
     */
    private boolean exitReached;
    /**
     * The order in which the {@code Player} has challenged the {@code Dungeon} of this floor.
     */
    private int order;
    /**
     * The map of the Floor.
     * <p>
     * It is used to check interactions between a {@code Player} or {@link Creature} with nearby {@link Tile Tiles}.
     */
    private Tile[][] grid;
    /**
     * A list of {@link Structure Structures} on the floor.
     */
    private ArrayList<Structure> structures;
    /**
     * A list of {@code Creatures} on the floor.
     * <p>
     * It is used to trigger the turns of every {@code Creature} after the {@code Player} moves.
     */
    private ArrayList<Creature> creatures;
    /**
     * A list of {@link Loot loots} on the floor.
     */
    private ArrayList<Loot> loots;
    /**
     * A reference to the {@code Player} object.
     * <p>
     * It is used for the Floor to modify the {@code Player} state.
     */
    private Player player;
    /**
     * A {@code Random} object used to randomize gameplay.
     */
    private Random rand;
    /**
     * The type of dungeon the floor is a part of.
     */
    private DungeonCode dungeonCode;

    //Constructors
    /**
     * Constructs the Floor object.
     * <p>
     * Initializes all of the variables and stores a reference to the {@code Player}.
     * 
     * @param player object holding the {@code Player}'s data.
     * @param order the order that the {@code Player} challenged the dungeon.
     */
    public Floor(Player player, int order)
    {
        this.player = player;
        this.order = order;
        this.structures = new ArrayList<Structure>();
        this.creatures = new ArrayList<Creature>();
        this.loots = new ArrayList<Loot>();
        rand = new Random(System.currentTimeMillis());
    }

    //Methods
    //Gameplay
    /**
     * Processes one cycle of player input.
     * <p>
     * If a direction is chosen, an interaction and movement are attempted on the corresponding {@code Tile}.
     * If the {@code Tile} interacted with is the {@code Exit}, the exit would have been set to {@code true} by the tile,
     * allowing the player to proceed to the next floor.
     * <p>
     * The usage and switching of held {@link Item Items} is handled by the {@link Controller#processDungeonInput() Controller.processDungeonInput}.
     * If the player fails to move during this cycle, they will idle and trigger any idle effects on their current {@code Tile}.
     * <p>
     * After these, the turn of every {@code Creature} on the floor is processed.
     * 
     * @param choice the input character representing the player's choice.
     * 
     * @return {@code true} if the exit has been reached, {@code false} if not. This notifies the {@code Dungeon} if the floor is finished.
     */
    public boolean tick(char choice)
    {
        int y = this.player.getPosition().getPosY();
        int x = this.player.getPosition().getPosX();
        switch(choice)
        {
            case 'W':
                interact(y, x, -1, 0);
                break;
            case 'S':
                interact(y,x, 1, 0);
                break;
            case 'A':
                interact(y,x, 0, -1);
                break;
            case 'D':
                interact(y,x, 0, 1);
                break;
            case '[':
                // this.player.previousItem(); Being called in Controller for ErrorMessage
                idle(y,x);
                break;
            case ']':
                // this.player.nextItem(); Being called in Controller for ErrorMessage
                idle(y,x);
                break;
            case ' ':
                // this.player.useItem(); Being called in Controller for ErrorMessage
                idle(y,x);
                break;
            default:
                //Skip Turn
                idle(y,x);
                break;
        }
        if(exitReached){
            return true;
        }
        tickEnemies();
        return false;
    }

    /**
     * Processes an interaction between the {@code Player} with a nearby {@code Tile}.
     * <p>
     * Triggers any {@link Structure#interact(Floor) interactions} on the target {@code Tile}.
     * Also damages {@code Creature} on the target {@code Tile}.
     * If after both of these, no blocking entities were detected, moves the {@code Player} onto the target {@code Tile}.
     * Otherwise, triggers the idle effects on their current {@code Tile}.
     * <p>
     * This also deletes the {@code Structure} from the {@code grid} and {@code structures} if the structure should be destroyed as a result of the interaction.
     * <p>
     * This also drops the {@code Loot} of and deletes the {@code Creature} from the {@code grid} and {@code creatures} if the creature should die as a result of the interaction.
     * 
     * @param y distance of the current position from the top edge.
     * @param x distance of the current position from the left edge.
     * @param offY how many spaces downwards the {@code Tile} that the {@code Player} is interacting with is.
     * @param offX how many spaces to the right the {@code Tile} that the {@code Player} is interacting with is.
     */
    private void interact(int y, int x, int offY, int offX)
    {
        boolean blocked = false;
        Iterator<Structure> iter1 = this.grid[y + offY][x + offX].getStructures().iterator();
        while(iter1.hasNext())
        {
            Structure struct = iter1.next();
            if(struct.interact(this))
            {
                structures.remove(struct);
                iter1.remove();
            }
            blocked = blocked || struct.isBlocking(this);
        }
        Iterator<Creature> iter2 = this.grid[y + offY][x + offX].getCreatures().iterator();
        while(iter2.hasNext())
        {
            Creature creature = iter2.next();
            creature.damageCreature(player.getAttack());
            if(creature.isDead())
            {
                creature.dropLoot(this);
                this.creatures.remove(creature);
                iter2.remove();
            }
            blocked = true;
        }
        if(!blocked)
        {
            this.player.setPosition(y + offY, x + offX);
            step(y + offY,x + offX);
        }else{
            idle(y,x);
        }
    }

    /**
     * Picks up loot when moving the {@code Player}.
     * <p>
     * Picks up all of the loot on the {@code Tile} that the {@code Player} moved to.
     * After the {@code Loot} is obtained, this also deletes it from the {@code grid} and {@code loots}.
     * 
     * @param y distance of the destination position from the top edge.
     * @param x distance of the destination position from the left edge.
     */
    private void step(int y, int x)
    {
        Iterator<Loot> iter = this.grid[y][x].getLoots().iterator();
        while(iter.hasNext())
        {
            Loot loot = iter.next();
            loot.pickUpLoot(this);
            loots.remove(loot);
            iter.remove();
        }
    }

    /**
     * Processes any idle effects on the {@code Tile} that the {@code Player} is idling on.
     * <p>
     * This also deletes the {@code Structure} from the {@code grid} and {@code structures} if the structure should be destroyed as a result of idling.
     * 
     * @param y distance of the current position from the top edge.
     * @param x distance of the current position from the left edge.
     */
    private void idle(int y, int x)
    {
        Iterator<Structure> iter = this.grid[y][x].getStructures().iterator();
        while(iter.hasNext())
        {
            Structure struct = iter.next();
            if(struct.idle(this))
            {
                structures.remove(struct);
                iter.remove();
            }
        }
    }

    /**
     * Processes any idle effects on the {@code Tile} that the {@code Creature} is idling on.
     * <p>
     * This also deletes the {@code Structure} from the {@code grid} and {@code structures} if the structure should be destroyed as a result of idling.
     * 
     * @param creature the creature that is idling.
     */
    public void creatureIdle(Creature creature)
    {
        int y = creature.getPosition().getPosY();
        int x = creature.getPosition().getPosX();
        Iterator<Structure> iter = this.grid[y][x].getStructures().iterator();
        while(iter.hasNext())
        {
            Structure struct = iter.next();
            if(struct.creatureIdle(this, creature))
            {
                structures.remove(struct);
                iter.remove();
            }
        }
    }

    /**
     * Triggers every {@code Creature} on the map to take their turn.
     * <p>
     * This also drops the {@code Loot} of and deletes the {@code Creature} from the {@code grid} and {@code creatures} if the creature should die as a result of the interaction.
     */
    private void tickEnemies()
    {
        Iterator<Creature> iter = this.creatures.iterator();
        while(iter.hasNext())
        {
            Creature creature = iter.next();
            if(creature.tick(this))
            {
                int y = creature.getPosition().getPosY();
                int x = creature.getPosition().getPosX();
                creature.dropLoot(this);
                grid[y][x].getCreatures().remove(creature);
                iter.remove();
            }
        }
    }

    /**
     * Moves the {@code Position} of the creature,
     * as well as changing the {@code Tile} it is stored in to reflect the new {@code Position}.
     * Movement validation is handled by {@link Creature#tick(Floor) Creature.tick} when they take their turn.
     * 
     * @param creature the creature being moved.
     * @param moveY how many spaces down to move it.
     * @param moveX how many spaces right to move it.
     */
    public void moveCreature(Creature creature, int moveY, int moveX)
    {
        int initialY = creature.getPosition().getPosY();
        int initialX = creature.getPosition().getPosX();
        grid[initialY][initialX].getCreatures().remove(creature);
        grid[initialY + moveY][initialX + moveX].getCreatures().add(creature);
        creature.move(moveY, moveX);
    }

    //Generation
    /**
     * Generates the floor map depending on which {@link DungeonCode} the floor is a part of.
     * It calls the corresponding generate function.
     * 
     * @param dungeonCode the code identifying which {@code DungeonCode} this floor is in.
     */
    public void generateFloor(DungeonCode dungeonCode)
    {
        this.dungeonCode = dungeonCode;
        switch(dungeonCode)
        {
            case DungeonCode.YASUDAYA_RYOKAN:
                generateYasudayaRyokan();
                break;
        }
    }
    /**
     * Generates a floor for the Yasudaya Ryokan dungeon.
     * Randomly selects one of the {@link Layouts} under the dungeon and converts it into useable data.
     */
    private void generateYasudayaRyokan()
    {
        int i = this.rand.nextInt(2);
        switch(i)
        {
            case 0:
                convertLayout(Layouts.REFERENCE);
                break;
            case 1:
                convertLayout(Layouts.BAT_WATER_TEST);
                break; 
        }
    }

    /**
     * Converts the {@code Layout} by scanning each character and initializing their corresponding {@code Tiles} in the {@code grid}.
     * The grid is initialized with sizes {@code sizeY} and {@code sizeX},
     * which are obtained from getting the number of strings and the length of the strings from the {@code Layout}.
     * <p>
     * This also adds the reference to each generated {@code Structure}, {@code Creature}, and {@code Loot} to the
     * {@code structures}, {@code creatures}, and {@code loots} lists as well.
     * <p>
     * Once the {@code Spawn} is found, it stores the location as {@code spawnY} and {@code spawnX}.
     * 
     * @param layout the layout data to convert into data.
     */
    private void convertLayout(String[] layout)
    {
        this.sizeY = layout.length;
        this.sizeX = layout[0].length();
        this.grid = new Tile[sizeY][sizeX];
        for(int i = 0; i < sizeY; i++)
        {
            for (int j = 0; j < sizeX; j++)
            {
                grid[i][j] = new Tile();
                char c = layout[i].charAt(j);
                switch(c)
                {
                    //Structures
                    case '*':
                        Border border = new Border();
                        border.setPosition(i, j);
                        this.grid[i][j].addStructure(border);
                        this.structures.add(border);
                        break;
                    case 'v':
                        Wall wall = new Wall();
                        wall.setPosition(i, j);
                        this.grid[i][j].addStructure(wall);
                        this.structures.add(wall);
                        break;
                    case 'x':
                        Spike spike = new Spike();
                        spike.setPosition(i, j);
                        this.grid[i][j].addStructure(spike);
                        this.structures.add(spike);
                        break;
                    case 'w':
                        Water water = new Water();
                        water.setPosition(i, j);
                        this.grid[i][j].addStructure(water);
                        this.structures.add(water);
                        break;
                    case 'h':
                        Heat heat = new Heat();
                        heat.setPosition(i, j);
                        this.grid[i][j].addStructure(heat);
                        this.structures.add(heat);
                        break;
                    //Loot
                    case 'T':
                        Treasure treasure = new Treasure();
                        treasure.setPosition(i, j);
                        this.grid[i][j].addLoot(treasure);
                        this.loots.add(treasure);
                        break;
                    //Creatures
                    case 'b':
                        Bat bat = new Bat(this.order);
                        bat.setPosition(i, j);
                        grid[i][j].addCreature(bat);
                        this.creatures.add(bat);
                        break;
                    //Spawn and Exit
                    case 'S':
                        Spawn spawn = new Spawn();
                        spawn.setPosition(i, j);
                        this.grid[i][j].addStructure(spawn);
                        this.structures.add(spawn);
                        this.spawnY = i;
                        this.spawnX = j;
                        break;
                    case 'E':
                        Exit exit = new Exit();
                        exit.setPosition(i, j);
                        this.grid[i][j].addStructure(exit);
                        this.structures.add(exit);
                        break;
                }
            }
        }
    }

    /**
     * Processes any damage being dealt to the {@code Player}.
     * If the player dies due to this damage, it also sets the cause of death as the source of it.
     * 
     * @param dmg the amount of damage being dealt.
     * @param source the source of the damage.
     */
    public void damagePlayer(double dmg, String source){
        this.player.damage(dmg);
        if(this.player.isDead())
        {
            this.player.setCauseOfDeath(source);
        }
    }

    //Check for Yohane at given coordinates
    /**
     * Checks if the {@code Player} is at the given coordinates.
     * Used by {@code Creatures} to check nearby {@code Tiles}.
     * 
     * @param y distance of the target position from the top edge.
     * @param x distance of the target position from the left edge.
     * 
     * @return {@code true} if the {@code Player} was found at the target {@code Tile}, {@code false} if not.
     */
    public boolean checkForPlayer(int y, int x)
    {
        if(player.getPosition().getPosY() == y && player.getPosition().getPosX() == x)
        {
            return true;
        }
        return false;
    }

    //Check for creature passable
    /**
     * Checks if the {@code Creature} can move to the target {@code Tile}.
     * 
     * @param creature the creature attempting to move.
     * @param y distance of the target position from the top edge.
     * @param x distance of the target position from the left edge.
     * 
     * @return {@code false} if no entities block the movement, {@code true} otherwise.
     */
    public boolean isCreatureBlocked(Creature creature, int y, int x)
    {
        boolean blocked = false;
        if (isInBounds(y, x))
        {
            Iterator<Structure> iter1 = this.grid[y][x].getStructures().iterator();
            while(iter1.hasNext())
            {
                Structure struct = iter1.next();
                blocked = blocked || struct.creatureIsBlocking(this, creature);
            }
        }else{
            blocked = true;
        }
        return blocked;
    }

    //Check if Coordinates are in bounds
    /**
     * Checks if the target {@code Tile} is in bounds.
     * Tiles can not have a negative position,
     * and can not have a position greater than or equal to the size of the corresponding {@code grid} axis.
     * 
     * @param y distance of the target position from the top edge.
     * @param x distance of the target position from the left edge.
     * 
     * @return {@code true} if the position is within bounds, {@code false} otherwise.
     */
    public boolean isInBounds(int y, int x)
    {
        return y >= 0 && x >= 0 && y < sizeY && x < sizeX;
    }

    //Getters and Setters
    /**
     * Returns how many rows the map has.
     * 
     * @return size of the Y axis.
     */
    public int getSizeY(){
        return this.sizeY;
    }
    /**
     * Returns how many columns the map has.
     * 
     * @return size of the X axis.
     */
    public int getSizeX(){
        return this.sizeX;
    }
    /**
     * Returns the Y coordinate of {@code Spawn}.
     * 
     * @return the distance of {@code Spawn} from the top edge of the map.
     */
    public int getSpawnY(){
        return this.spawnY;
    }
    /**
     * Returns the X coordinate of {@code Spawn}.
     * 
     * @return the distance of {@code Spawn} from the left edge of the map.
     */
    public int getSpawnX(){
        return this.spawnX;
    }
    /**
     * Resets the exit to not being found.
     */
    public void resetExitCondition(){
        this.exitReached = false;
    }
    /**
     * Sets the exit to be found, allowing the {@code Player} to leave the current {@code Floor}.
     */
    public void setExitReached(){
        this.exitReached = true;
    }
    /**
     * Returns the challenge order of the {@code Dungeon} that this {@code Floor} belongs to.
     * 
     * @return challenge order of this {@code Floor}'s {@code Dungeon}.
     */
    public int getOrder(){
        return this.order;
    }
    /**
     * Returns the {@code grid} of the map.
     * 
     * @return the {@code grid} of the map
     */
    public Tile[][] getGrid(){
        return this.grid;
    }
    /**
     * Returns the list of {@code Structures} in the {@code Floor}.
     * 
     * @return the list of {@code Structures}.
     */
    public ArrayList<Structure> getStructures(){
        return this.structures;
    }
    /**
     * Returns the list of {@code Creatures} in the {@code Floor}.
     * 
     * @return the list of {@code Creatures}.
     */
    public ArrayList<Creature> getCreatures(){
        return this.creatures;
    }
    /**
     * Returns the list of {@code Loot} in the {@code Floor}.
     * 
     * @return the list of {@code Loot}.
     */
    public ArrayList<Loot> getLoot(){
        return this.loots;
    }
    /**
     * Returns the reference to the {@code Player} stored by the {@code Floor}.
     * 
     * @return the {@code Player} reference.
     */
    public Player getPlayer(){
        return this.player;
    }
    /**
     * Returns a reference to the {@code rand} variable of the {@code Floor}.
     * Allows other classes to use the same seed for {@code Random}.
     * 
     * @return the {@code Random} object being used by the {@code Floor}.
     */
    public Random getRand(){
        return this.rand;
    }
    /**
     * Returns the code representing the type of {@code Dungeon} this {@code Floor} is a part of.
     * 
     * @return the {@code dungeonCode} of the {@code Floor}.
     */
    public DungeonCode getDungeonCode()
    {
        return this.dungeonCode;
    }
}