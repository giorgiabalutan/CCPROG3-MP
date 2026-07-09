import java.util.ArrayList;

public class Dungeon
{
    private Idol idol;
    private String dungeonName;
    private DungeonCode dungeonCode;
    private ArrayList<Floor> floors;
    private Player player;
    private int floorNum;
    private int maxFloor;
    private int order;

    //Constructors
    public Dungeon(Player player)
    {
        this.floors = new ArrayList<>();
        this.player = player;
        this.order = 1;
    }

    //Methods
    public void generateDungeon(Idol idol)
    {
        this.idol = idol;
        this.dungeonName = idol.getDungeonName();
        floorNum = 0;
        maxFloor = 0;
        switch(order)
        {
            default:
            case 3:
                maxFloor += 1;
            case 2:
                maxFloor += 2;
            case 1:
                maxFloor += 1;
        }
        generateFloors(idol.getDungeonCode(), maxFloor);
        spawnPlayer();
    }

    private void generateFloors(DungeonCode dungeonCode, int maxFloor)
    {
        this.floors = new ArrayList<>();
        for(int i = 0; i < maxFloor; i++)
        {
            this.floors.add(new Floor(player, this.order));
            this.floors.get(i).generateFloor(dungeonCode);
        }
    }

    private void spawnPlayer()
    {
        Floor floor = this.floors.get(floorNum);
        this.player.setPosition(floor.getSpawnY(), floor.getSpawnX());
        floor.resetExitCondition();
    }

    private void finishDungeon()
    {
        order += 1;
    }

    public boolean tick(char choice)
    {
        if(this.floors.get(floorNum).tick(choice))
        {
            floorNum++;
            if(floorNum>=maxFloor){
                finishDungeon();
                return true;
            }else{
                spawnPlayer();
            }
        }
        return false;
    }

    //Getters and Setters
    public Idol getIdol(){
        return this.idol;
    }

    public Floor getFloor(){
        return this.floors.get(floorNum);
    }

    public String getName(){
        return this.dungeonName;
    }

    public int getFloorNum(){
        return this.floorNum;
    }

    public int getMaxFloor(){
        return this.maxFloor;
    }

    public int getOrder(){
        return this.order;
    }
}