package model.dungeon;

import java.lang.ModuleLayer.Controller;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import model.CombatLogEntry;
import model.CombatLogType;
import model.Direction;
import model.Item;
import model.Player;
import model.creature.*;
import model.loot.*;
import model.structure.*;
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
    private HashSet<DungeonModifier> dungeonModifiers;
    private Boolean isFinal;
    private Boolean isFinalPhase1Done;
    private ArrayList<Lailaps> lailapses;
    private ArrayList<Switch> switches;
    private int switchSetsPressed;
    private ArrayList<Arrow> arrows;

    private ArrayList<CombatLogEntry> combatLogs;
    private int turnNumber;

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
        this.rand = new Random(System.currentTimeMillis());
        this.combatLogs = new ArrayList<>();
        this.turnNumber = 1;
        this.isFinal = false;
        this.isFinalPhase1Done = false;
        this.switchSetsPressed = 0;
        this.switches = new ArrayList<Switch>();
        this.lailapses = new ArrayList<Lailaps>();
        this.arrows = new ArrayList<Arrow>();
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
        addCombatLog("Turn " + this.turnNumber, CombatLogType.TURN_INDICATOR);
        this.player.setIdle(false);
        switch(choice)
        {
            case 'W':
                interact(y, x, -1, 0);
                moveLailaps(-1, 0);
                this.player.setDirection(Direction.UP);
                break;
            case 'S':
                interact(y,x, 1, 0);
                moveLailaps(1, 0);
                this.player.setDirection(Direction.DOWN);
                break;
            case 'A':
                interact(y,x, 0, -1);
                moveLailaps(0, -1);
                this.player.setDirection(Direction.LEFT);
                break;
            case 'D':
                interact(y,x, 0, 1);
                moveLailaps(0, 1);
                this.player.setDirection(Direction.RIGHT);
                break;
            case '[':
                String message1 = this.player.previousItem();
                if(!message1.equals(""))
                {
                    addCombatLog("Attempted to switch Items but none were found", CombatLogType.PLAYER_ACTION);
                }
                idle(y,x);
                break;
            case ']':
                String message2 = this.player.nextItem();
                if(!message2.equals(""))
                {
                    addCombatLog("Attempted to switch Items but none were found", CombatLogType.PLAYER_ACTION);
                }
                idle(y,x);
                break;
            case ' ':
                String[] messages = this.player.useItem();
                if(messages[0].charAt(0) == 'L')
                {
                    // System.out.println(messages[0]);
                    if(this.player.getItemOnHand().getQuantity() > 0)
                    {
                        addCombatLog("Attempted to use " + this.player.getItemOnHand().getItemName() + " but HP is full!", CombatLogType.PLAYER_ACTION);
                    }else{
                        addCombatLog("Attempted to use an Item but none were found.", CombatLogType.PLAYER_ACTION);
                    }
                }else{
                    addCombatLog(messages[0], CombatLogType.ITEM_USE);
                    if(this.dungeonModifiers.contains(DungeonModifier.STRONGER_HEALS))
                    {
                        addCombatLog("The lingering magic in the dungeon healed an additional 0.5 hp!", CombatLogType.HEAL);
                    }
                }
                idle(y,x);
                break;
            default:
                //Skip Turn
                idle(y,x);
                break;
        }
        if(exitReached){
            addCombatLog("Yohane has reached the Exit!", CombatLogType.PLAYER_ACTION);
            return true;
        }
        tickEnemies();
        this.turnNumber++;
        // for(CombatLogEntry entry : this.combatLogs)
        // {
        //     // System.out.println(entry.getMessage() + "---" + entry.getType().toString());
        // }
        Iterator<Lailaps> iter = this.lailapses.iterator();
        while(iter.hasNext())
        {
            Lailaps lailaps = iter.next();
            if(lailaps.isDead())
            {
                addCombatLog("Lailaps has perished", CombatLogType.DEATH);
                grid[y][x].getCreatures().remove(lailaps);
                iter.remove();
            }
        }
        if (isFinal())
        {
            switchCheck();
            sirenCheck();
        }
        if((isFinal && turnNumber%8 == 0))
        {
            Boolean spawned = false;
            while(!spawned)
            {
                int posY = rand.nextInt(sizeY);
                int posX = rand.nextInt(sizeX);
                Tile tile = grid[posY][posX];
                if(tile.getStructures().isEmpty() && tile.getCreatures().isEmpty() && !(posY < 5 && posX > 11 && posX < 28) && !(posY == player.getPosition().getPosY() && posX == player.getPosition().getPosX()))
                {
                    Bat bat = new Bat(this.switchSetsPressed + 1, this.dungeonModifiers);
                    bat.setPosition(posY, posX);
                    grid[posY][posX].addCreature(bat);
                    this.creatures.add(bat);
                    spawned = true;
                }
            }
        }
        if(isFinal && lailapses.isEmpty())
        {
            damagePlayer(99999, "Life Link");
        }
        return false;
    }

    public void switchCheck()
    {
        if(isFinal)
        {
            Boolean setPressed = false;
            if(!switches.isEmpty())
            {
                setPressed = true;
            }
            for(Switch switchN: switches)
            {
                int y = switchN.getPosition().getPosY();
                int x = switchN.getPosition().getPosX();
                boolean pressed = false;
                for(Lailaps lailaps: lailapses)
                {
                    if(y == lailaps.getPosition().getPosY() && x == lailaps.getPosition().getPosX())
                    {
                        pressed = true;
                    }
                }
                if((y == this.player.getPosition().getPosY() && x == this.player.getPosition().getPosX()))
                {
                    pressed = true;
                }
                if(!pressed)
                {
                    setPressed = false;
                }
            }

            if(setPressed)
            {
                this.switchSetsPressed++;
                for(Switch switchN: switches)
                {
                    int y = switchN.getPosition().getPosY();
                    int x = switchN.getPosition().getPosX();
                    this.structures.remove(switchN);
                    this.grid[y][x].getStructures().remove(switchN);
                }
                for(Creature creature : creatures)
                {
                    if(creature.getCreatureType() == CreatureType.BAT)
                    {
                        Bat bat = (Bat) creature;
                        bat.setPower(switchSetsPressed + 1);
                    }
                }
                switches = new ArrayList<Switch>();
            }

            //Siren Area bounds are row 1 column 11 to row 3 column 28
            while(switches.isEmpty() && switchSetsPressed < 3)
            {
                int y = rand.nextInt(sizeY);
                int x = rand.nextInt(sizeX);
                Boolean invalid = false;
                Tile tile = grid[y][x];
                if(tile.getStructures().isEmpty() && tile.getCreatures().isEmpty() && !(y < 5 && x > 11 && x < 28) && !(y == player.getPosition().getPosY() && x == player.getPosition().getPosX()))
                {
                    ArrayList<int[]> possible = new ArrayList<>();
                    for(int offY=-2; offY<3; offY++)
                    {
                        for(int offX = -5; offX<6; offX++)
                        {
                            if(!(offY == 0 && offX == 0) && isInBounds(y+offY, x+offX))
                            {
                                Tile tile2 = grid[y+offY][x+offX];
                                if(tile2.getStructures().isEmpty() && tile2.getCreatures().isEmpty() && !(y+offY < 5 && x+offX > 11 && x+offX < 28) && !(y+offY == player.getPosition().getPosY() && x+offX == player.getPosition().getPosX()))
                                {
                                    possible.add(new int[]{y+offY,x+offX});
                                }
                            }
                        }
                    }
                    if(!possible.isEmpty())
                    {
                        int i = rand.nextInt(possible.size());
                        Switch switch1 = new Switch();
                        switch1.setPosition(y, x);
                        this.grid[y][x].addStructure(switch1);
                        this.structures.add(switch1);
                        Switch switch2 = new Switch();
                        switch2.setPosition(possible.get(i)[0], possible.get(i)[1]);
                        this.grid[possible.get(i)[0]][possible.get(i)[1]].addStructure(switch2);
                        this.structures.add(switch2);
                        switches.add(switch1);
                        switches.add(switch2);
                    }
                }
            }

            if(!isFinalPhase1Done && switchSetsPressed > 2)
            {
                this.isFinalPhase1Done = true;
                Iterator<Structure> iter = this.structures.iterator();
                while(iter.hasNext())
                {
                    Structure structure = iter.next();
                    if(structure.getType() == StructureType.BORDER)
                    {
                        Border border = (Border) structure;
                        if(border.isSwitchReactive())
                        {
                            int y = structure.getPosition().getPosY();
                            int x = structure.getPosition().getPosX();
                            this.grid[y][x].getStructures().remove(structure);
                            iter.remove();
                        }
                    }
                }
            }
        }
    }

    private void sirenCheck()
    {
        Boolean sirenAlive = false;
        for(Creature creature: creatures)
        {
            if(creature.getCreatureType() == CreatureType.SIREN)
            {
                sirenAlive = true;
            }
        }
        if(!sirenAlive)
        {
            for(Structure structure: structures)
            {
                if(structure.getType() == StructureType.EXIT && ((Exit) structure).isHidden())
                {
                    ((Exit) structure).setHidden(false);
                }
            }
        }
    }

    public void addCombatLog(String message, CombatLogType type){
        this.combatLogs.add(new CombatLogEntry(message, type));
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
            switch(struct.getType())
            {
                case WALL:
                case SPIKE:
                    addCombatLog("Yohane dug up a " + struct.getName() + "!", CombatLogType.PLAYER_ACTION);
                    break;
            }
            blocked = blocked || struct.isBlocking(this);
        }
        Iterator<Creature> iter2 = this.grid[y + offY][x + offX].getCreatures().iterator();
        while(iter2.hasNext())
        {
            Creature creature = iter2.next();
            if (creature.getCreatureType() != CreatureType.LAILAPS)
            {
                creature.damageCreature(player.getAttack());
                addCombatLog("Yohane hit a " +creature.getName()+ " for " + player.getAttack() + " damage.", CombatLogType.DAMAGE);
                if(creature.isDead())
                {
                    addCombatLog("Yohane has killed a " + creature.getName() + "!", CombatLogType.DEATH);
                    creature.dropLoot(this);
                    this.creatures.remove(creature);
                    iter2.remove();
                }
            }
            blocked = true;
        }
        if(!blocked)
        {
            this.player.setPosition(y + offY, x + offX);
            step(y + offY,x + offX);
            String direction;
            if(offY > 0)
            {
                direction = "down";
                this.player.setDirection(Direction.DOWN);
            }else if(offY<0)
            {
                direction = "up";
                this.player.setDirection(Direction.UP);
            }else if(offX>0)
            {
                direction = "right";
                this.player.setDirection(Direction.RIGHT);
            }else{
                direction = "left";
                this.player.setDirection(Direction.LEFT);
            }
            addCombatLog("Yohane moved " + direction + ".", CombatLogType.PLAYER_ACTION);
        }else{
            idle(y,x);
        }
    }

    private void moveLailaps(int offY, int offX)
    {
        for(Lailaps lailaps: lailapses)
        {
            boolean blocked = false;
            int y = lailaps.getPosition().getPosY();
            int x = lailaps.getPosition().getPosX();
            Iterator<Structure> iter1 = this.grid[y + offY][x + offX].getStructures().iterator();
            while(iter1.hasNext())
            {
                Structure struct = iter1.next();
                blocked = blocked || struct.isBlocking(this);
            }
            Iterator<Creature> iter2 = this.grid[y + offY][x + offX].getCreatures().iterator();
            while(iter2.hasNext())
            {
                Creature creature = iter2.next();
                blocked = true;
            }
            int playerY = this.player.getPosition().getPosY();
            int playerX = this.player.getPosition().getPosX();
            if(y+offY == playerY && x + offX == playerX)
            {
                blocked = true;
            }
            if(!blocked)
            {
                moveCreature(lailaps, offY, offX);
                String direction;
                if(offY > 0)
                {
                    direction = "down";
                    lailaps.setDirection(Direction.DOWN);
                }else if(offY<0)
                {
                    direction = "up";
                    lailaps.setDirection(Direction.UP);
                }else if(offX>0)
                {
                    direction = "right";
                    lailaps.setDirection(Direction.RIGHT);
                }else{
                    direction = "left";
                    lailaps.setDirection(Direction.LEFT);
                }
                addCombatLog("Lailaps moved " + direction + ".", CombatLogType.PLAYER_ACTION);
            }else{
                creatureIdle(lailaps);
            }
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
        this.player.setIdle(true);
        addCombatLog("Yohane waits.", CombatLogType.PLAYER_ACTION);
        for(Lailaps lailaps : lailapses)
        {
            lailaps.setIdle(true);
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
        if(creature.getCreatureType() != CreatureType.BAT)
        {
            creature.setIdle(true);
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
                if(creature.getCreatureType() != CreatureType.ARROW)
                {
                    addCombatLog(creature.getName()+" has perished due to natural causes.", CombatLogType.DEATH);
                }
                int y = creature.getPosition().getPosY();
                int x = creature.getPosition().getPosX();
                creature.dropLoot(this);
                grid[y][x].getCreatures().remove(creature);
                iter.remove();
            }
        }
        Iterator<Arrow> iter2 = this.arrows.iterator();
        while(iter2.hasNext())
        {
            Arrow arrow = iter2.next();
            int y = arrow.getPosition().getPosY();
            int x = arrow.getPosition().getPosX();
            this.grid[y][x].getCreatures().add(arrow);
            this.creatures.add(arrow);
            iter2.remove();
        }
        //To kill off any creatures that took damage from arrows
        Iterator<Creature> iter3 = this.creatures.iterator();
        while(iter3.hasNext())
        {
            Creature creature = iter3.next();
            if(creature.isDead())
            {
                addCombatLog(creature.getName()+" has perished due to natural causes.", CombatLogType.DEATH);
                int y = creature.getPosition().getPosY();
                int x = creature.getPosition().getPosX();
                creature.dropLoot(this);
                grid[y][x].getCreatures().remove(creature);
                iter3.remove();
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
        creature.setIdle(false);
    }

    //Generation
    // /**
    //  * Generates a floor for the Yasudaya Ryokan dungeon.
    //  * Randomly selects one of the {@link Layouts} under the dungeon and converts it into useable data.
    //  */
    /**
     * Generates the floor map depending on which {@link DungeonCode} the floor is a part of.
     * It calls the corresponding generate function.
     * 
     * @param dungeonCode the code identifying which {@code DungeonCode} this floor is in.
     */
    public void generateFloor(DungeonCode dungeonCode, HashSet<DungeonModifier> dungeonModifiers)
    {
        this.dungeonCode = dungeonCode;
        this.dungeonModifiers = dungeonModifiers;
        int i;
        switch(dungeonCode)
        {
            case YASUDAYA_RYOKAN:
                i = this.rand.nextInt(1000);
                if(i < 100)
                {
                    convertLayout(Layouts.FOUR_POOLS);
                }else if(i < 200)
                {
                    convertLayout(Layouts.PISKEL_ART_POOL);
                }else{
                    convertLayout(Layouts.YASUDAYA_RYOKAN_TREASURE_ROOM);
                }
                break;
            case IZU_MITO_SEA_PARADISE:
                i = this.rand.nextInt(2);
                switch(i)
                {
                    case 0:
                        convertLayout(Layouts.REFERENCE);
                        break;
                    case 1:
                        convertLayout(Layouts.BAT_WATER_TEST);
                        break; 
                }
                break;
            case NUMAZU_DEEP_SEA_AQUARIUM:
                i = this.rand.nextInt(2);
                switch(i)
                {
                    case 0:
                        convertLayout(Layouts.SKELETON_TEST);
                        break;
                    case 1:
                        convertLayout(Layouts.SKELETON_TEST);
                        break; 
                }
                break;
            case SHOUGETSU_CONFECTIONARY:
                i = this.rand.nextInt(2);
                switch(i)
                {
                    case 0:
                        convertLayout(Layouts.REFERENCE);
                        break;
                    case 1:
                        convertLayout(Layouts.BAT_WATER_TEST);
                        break; 
                }
                break;
            case NAGAHAMA_CASTLE_RUINS:
                i = this.rand.nextInt(2);
                switch(i)
                {
                    case 0:
                        convertLayout(Layouts.REFERENCE);
                        break;
                    case 1:
                        convertLayout(Layouts.BAT_WATER_TEST);
                        break; 
                }
                break;
            case NUMAZUGOYOTEI:
                i = this.rand.nextInt(2);
                switch(i)
                {
                    case 0:
                        convertLayout(Layouts.REFERENCE);
                        break;
                    case 1:
                        convertLayout(Layouts.BAT_WATER_TEST);
                        break; 
                }
                break;
            case UCHIURA_BAY_PIER:
                i = this.rand.nextInt(2);
                switch(i)
                {
                    case 0:
                        convertLayout(Layouts.REFERENCE);
                        break;
                    case 1:
                        convertLayout(Layouts.BAT_WATER_TEST);
                        break; 
                }
                break;
            case AWASHIMA_MARINE_PARK:
                i = this.rand.nextInt(2);
                switch(i)
                {
                    case 0:
                        convertLayout(Layouts.REFERENCE);
                        break;
                    case 1:
                        convertLayout(Layouts.BAT_WATER_TEST);
                        break; 
                }
                break;
            case SIRENS_LAIR:
                this.isFinal = true;
                convertLayout(Layouts.SIREN_LAIR);
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
        addLayout(layout);
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
    private void addLayout(String[] layout)
    {
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
                    case '#':
                        Border border2 = new Border();
                        border2.setPosition(i, j);
                        this.grid[i][j].addStructure(border2);
                        this.structures.add(border2);
                        border2.setSwitchReactivity(true);
                        break;
                    case 'v':
                        Wall wall = new Wall(this.dungeonModifiers);
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
                        if(dungeonModifiers.contains(DungeonModifier.HOT_WATERS))
                        {
                            Heat heat2 = new Heat();
                            heat2.setPosition(i, j);
                            this.grid[i][j].addStructure(heat2);
                            this.structures.add(heat2);
                        }
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
                        Bat bat = new Bat(this.order, this.dungeonModifiers);
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
                        // System.out.print(isFinal);
                        if(this.isFinal)
                        {
                            exit.setHidden(true);
                        }
                        break;
                    case 'L':
                        Lailaps lailaps = new Lailaps();
                        lailaps.setPosition(i, j);
                        grid[i][j].addCreature(lailaps);
                        this.creatures.add(lailaps);
                        this.lailapses.add(lailaps);
                        break;
                    case 'B':
                        Siren siren = new Siren();
                        siren.setPosition(i, j);
                        grid[i][j].addCreature(siren);
                        this.creatures.add(siren);
                        break;
                    case 's':
                        Skeleton skeleton = new Skeleton(this.order, this.dungeonModifiers);
                        skeleton.setPosition(i, j);
                        grid[i][j].addCreature(skeleton);
                        this.creatures.add(skeleton);
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
        // addCombatLog("Yohane was hit by " + source + " for " + dmg + " damage!", CombatLogType.DAMAGE);
        if(this.player.isDead())
        {
            this.player.setCauseOfDeath(source);
            addCombatLog("Yohane has fallen...", CombatLogType.DEATH);
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

    public ArrayList<Creature> checkForCreatures(int y, int x)
    {
        ArrayList<Creature> creaturesFound = new ArrayList<>();
        for(Creature creature: creatures)
        {
            if(creature.getPosition().getPosY() == y && creature.getPosition().getPosX() == x)
            {
                creaturesFound.add(creature);
            }
        }
        return creaturesFound;
    }

    public boolean checkForLailaps(int y, int x)
    {
        for(Lailaps lailaps: lailapses)
        {
            if(lailaps.getPosition().getPosY() == y && lailaps.getPosition().getPosX() == x)
            {
                return true;
            }
        }
        return false;
    }

    public boolean checkForWater(int y, int x)
    {
        for(Structure structure: structures)
        {
            if(structure.getPosition().getPosY() == y && structure.getPosition().getPosX() == x && structure.getType() == StructureType.WATER)
            {
                return true;
            }
        }
        return false;
    }

    public void createArrow(int y, int x, double damage, Direction direction)
    {
        Arrow arrow = new Arrow(damage, direction);
        arrow.setPosition(y, x);
        if(!this.isCreatureBlocked(arrow, y, x))
        {
            this.arrows.add(arrow);
        }
    }

    public double getPlayerDistance(int y, int x)
    {
        int playerY = this.player.getPosition().getPosY();
        int playerX = this.player.getPosition().getPosX();
        return Math.pow(Math.pow(y-playerY,2)+Math.pow(x-playerX,2),0.5);
    }

    public Lailaps findClosestLailaps(int y, int x)
    {
        Lailaps closestLailaps = null;
        double closestDist = Double.MAX_VALUE;
        for(Lailaps lailaps: lailapses)
        {
            int lailapsY = lailaps.getPosition().getPosY();
            int lailapsX = lailaps.getPosition().getPosX();
            double dist = Math.pow(Math.pow(y-lailapsY,2)+Math.pow(x-lailapsX,2),0.5);
            if(dist < closestDist)
            {
                closestLailaps = lailaps;
                closestDist = dist;
            }
        }
        return closestLailaps;
    }

    
    public boolean attackLailaps(int y, int x, double dmg)
    {
        for(Lailaps lailaps: lailapses)
        {
            if(lailaps.getPosition().getPosY() == y && lailaps.getPosition().getPosX() == x)
            {
                lailaps.damageCreature(dmg);
                return true;
            }
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
            for(Creature creatureN: this.grid[y][x].getCreatures())
            {
                if(creatureN.getCreatureType() == creature.getCreatureType() && creatureN.getCreatureType() != CreatureType.ARROW)
                {
                    blocked = true;
                }
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

    public ArrayList<CombatLogEntry> getCombatLogs()
    {
        return this.combatLogs;
    }

    public int getTurnNumber()
    {
        return this.turnNumber;
    }

    public boolean isFinal()
    {
        return this.isFinal;
    }

    public ArrayList<Lailaps> getLailapses()
    {
        return this.lailapses;
    }

    public HashSet<DungeonModifier> getDungeonModifiers()
    {
        return this.dungeonModifiers;
    }
}