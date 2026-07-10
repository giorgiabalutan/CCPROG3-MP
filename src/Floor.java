import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Floor
{
    private int sizeY;
    private int sizeX;
    private int spawnY;
    private int spawnX;
    private boolean exitReached;
    private int order;
    private Tile[][] grid;
    private ArrayList<Structure> structures;
    private ArrayList<Creature> creatures;
    private ArrayList<Loot> loots;
    private Player player;
    private Random rand;
    private DungeonCode dungeonCode;

    //Constructors
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
                grid[y][x].getCreatures().remove(creature);
                iter.remove();
            }
        }
    }

    public void moveCreature(Creature creature, int moveY, int moveX)
    {
        int initialY = creature.getPosition().getPosY();
        int initialX = creature.getPosition().getPosX();
        grid[initialY][initialX].getCreatures().remove(creature);
        grid[initialY + moveY][initialX + moveX].getCreatures().add(creature);
        creature.move(moveY, moveX);
    }

    //Generation
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

    private void generateYasudayaRyokan()
    {
        int i = this.rand.nextInt(2);
        switch(i)
        {
            case 0:
                convertLayout(Layouts.Reference);
                break;
            case 1:
                convertLayout(Layouts.BatWaterTest);
                break; 
        }
    }

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

    public void damagePlayer(double dmg, String source){
        this.player.damage(dmg);
        if(this.player.isDead())
        {
            this.player.setCauseOfDeath(source);
        }
    }

    //Check for Yohane at given coordinates
    public boolean checkForPlayer(int y, int x)
    {
        if(player.getPosition().getPosY() == y && player.getPosition().getPosX() == x)
        {
            return true;
        }
        return false;
    }

    //Check for creature passable
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
    public boolean isInBounds(int y, int x)
    {
        return y >= 0 && x >= 0 && y < sizeY && x < sizeX;
    }

    //Getters and Setters
    public int getSizeY(){
        return this.sizeY;
    }
    public int getSizeX(){
        return this.sizeX;
    }
    public int getSpawnY(){
        return this.spawnY;
    }
    public int getSpawnX(){
        return this.spawnX;
    }
    public void resetExitCondition(){
        this.exitReached = false;
    }
    public void setExitReached(){
        this.exitReached = true;
    }
    public int getOrder(){
        return this.order;
    }
    public Tile[][] getGrid(){
        return this.grid;
    }
    public ArrayList<Structure> getStructures(){
        return this.structures;
    }
    public ArrayList<Creature> getCreatures(){
        return this.creatures;
    }
    public ArrayList<Loot> getLoot(){
        return this.loots;
    }
    public Player getPlayer(){
        return this.player;
    }
    public Random getRand(){
        return this.rand;
    }
    public DungeonCode getDungeonCode()
    {
        return this.dungeonCode;
    }
}