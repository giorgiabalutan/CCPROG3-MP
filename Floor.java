import java.util.Random;

public class Floor
{
    private Room[][] floorLayout;
    private int sizeX;
    private int sizeY;
    private int spawnRoomX;
    private int spawnRoomY;
    private int exitRoomX;
    private int exitRoomY;


    public Floor()
    {

    }

    public Floor(int sizeX,int sizeY)
    {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public void generateRooms(int type)
    {
        Random rand = new Random(System.currentTimeMillis());
        switch (type)
        {
            //Yasudaya Ryokan
            case 1:
                //I might just hand make floors bruh im not sure
                dungeon1preset1();

                // //Choose spawn room location
                // this.spawnRoomX = rand.nextInt(this.sizeX);
                // this.spawnRoomY = rand.nextInt(this.sizeY);
                // //Choose exit room location
                // do{
                // this.exitRoomX = rand.nextInt(this.sizeX);
                // this.exitRoomY = rand.nextInt(this.sizeY);
                // }while(this.spawnRoomX==this.exitRoomX && this.spawnRoomY==this.exitRoomY);
                // //Generate rooms between
                // //Generate auxiliary rooms
                // this.floorLayout[spawnRoomX][spawnRoomY] = new Room("spawn");
                // this.floorLayout[exitRoomX][exitRoomY] = new Room("exit");
                break;
            default:
                break;
        }
    }

    private void dungeon1preset1()
    {
        //Set Floor Size
        this.sizeX = 5;
        this.sizeY = 5;
        this.floorLayout = new Room[this.sizeX][this.sizeY];
        //Set Spawn Room Location
        this.spawnRoomX = 1;
        this.spawnRoomY = 1;
        boolean[] sAccess = {true,true,false,true};
        //Set Exit Room Location
        this.exitRoomX = 3;
        this.exitRoomY = 3;
        boolean[] eAccess = {true,true,true,false};



        this.floorLayout[spawnRoomX][spawnRoomY] = new Room("spawn",sAccess);
        this.floorLayout[exitRoomX][exitRoomY] = new Room("exit",eAccess);
    }

    public int[][][] generateRoom(int type)
    {
        switch (type)
        {
            //Yasudaya Ryokan
            case 1:
                
                break;
            default:
                break;
        }
        return new int[1][1][1];
    }
}