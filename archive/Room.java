
public class Room
{
    //Position of the room in the floor
    private int x;
    private int y;
    //Contents of the room
    private char[][][] room;
    //Boolean if N, S, E, W has a path in that order
    private boolean[] access;

    public Room(){}

    public Room(String type, boolean[] access)
    {
        switch(type){
            case "spawn":
                makeSpawn(access);
                break;
            case "exit":
                makeExit(access);
                break;
            default:
                break;
        }
    }

    // public Room(String type, Random rand)
    // {
    //     switch(type){
    //         case "spawn":
    //             makeSpawn();
    //             break;
    //         case "exit":
    //             break;
    //         case "normal":
    //             int r = rand.nextInt(3);
    //             switch(r){
    //                 case 0:
    //                     break;
    //                 case 1:
    //                     break;
    //                 case 2:
    //                     break;
    //             }
    //             break;
    //         default:
    //             break;
    //     }
    // }

    public Room(int sizex, int sizey)
    {
        room = new char[sizex][sizey][10];
    }

    private void makeSpawn(boolean[] access)
    {
        //the S character marks where Yohane spawns, hidden
        this.room = new char[][][]{
            {{'*'},{'*'},{'*'}    ,{'*'},{'*'}},
            {{'*'},{'.'},{'.'}    ,{'.'},{'*'}},
            {{'*'},{'.'},{'.','S'},{'.'},{'*'}},
            {{'*'},{'.'},{'.'}    ,{'.'},{'*'}},
            {{'*'},{'*'},{'*'}    ,{'*'},{'*'}}
        };
        if(access[0]){this.room[0][2] = new char[]{'.'};}
        if(access[1]){this.room[4][2] = new char[]{'.'};}
        if(access[2]){this.room[2][4] = new char[]{'.'};}
        if(access[3]){this.room[2][0] = new char[]{'.'};}
    }

    private void makeExit(boolean[] access)
    {
        this.room = new char[][][]{
            {{'*'},{'*'},{'*'},{'*'},{'*'}},
            {{'*'},{'.'},{'.'},{'.'},{'*'}},
            {{'*'},{'.'},{'E'},{'.'},{'*'}},
            {{'*'},{'.'},{'.'},{'.'},{'*'}},
            {{'*'},{'*'},{'*'},{'*'},{'*'}}
        };
        if(access[0]){this.room[0][2] = new char[]{'.'};}
        if(access[1]){this.room[4][2] = new char[]{'.'};}
        if(access[2]){this.room[2][4] = new char[]{'.'};}
        if(access[3]){this.room[2][0] = new char[]{'.'};}
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}