package model.dungeon;

import java.io.Serializable;
import java.util.ArrayList;
import model.Idol;
import model.Player;
/**
 * Represents one of the Dungeons that must be completed to rescue an {@link Idol}.
 * <p>
 * Contains the list of {@link Floor Floors} in the dungeon and tracks which one the {@link Player} is in.
 */
public class Dungeon implements Serializable
{
    /**
     * The {@code Idol} trapped in this dungeon.
     */
    private Idol idol;
    /**
     * The name of this dungeon.
     */
    private String dungeonName;
    /**
     * The {@link DungeonCode} of this dungeon.
     */
    private DungeonCode dungeonCode;
    /**
     * The list of {@code Floors} that this dungeon has.
     */
    private ArrayList<Floor> floors;
    /**
     * A reference to the {@code Player} object.
     * <p>
     * It is used for the Dungeon to modify the {@code Player} state.
     */
    private Player player;
    /**
     * The number of the floor that the {@code Player} is currently on.
     */
    private int floorNum;
    /**
     * The number of floors that this dungeon has.
     */
    private int maxFloor;
    /**
     * The order in which the player challenged this dungeon.
     */
    private int order;

    //Constructors
    /**
     * Constructs the dungeon with the player reference.
     * Initializes as the first dungeon being challenged.
     * 
     * @param player object holding the {@code Player}'s data.
     */
    public Dungeon(Player player)
    {
        this.floors = new ArrayList<>();
        this.player = player;
        this.order = 1;
    }

    //Methods
    /**
     * Generates the dungeon based on which {@code Idol} the player decided to save first.
     * Gets the corresponding {@code DungeonCode} from the {@code Idol} and generates {@code floors} from that dungeon.
     * The number of {@code floors} generated is based on the challenge order of the dungeon.
     * <p>
     * After generating the {@code floors}, it spawns the {@code Player} in the first one.
     * 
     * @param idol the {@code Idol} that the {@code Player} chose to save.
     */
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

    /**
     * Generates a number of {@code floors} equal to the max number of floors for the dungeon.
     * It initializes each {@code floor} and runs the {@link Floor#generateFloor(DungeonCode) Floor.generateFloor} method on it.
     * It passes the {@code DungeonCode} to generate the corresponding type of {@code Floor}.
     * 
     * @param dungeonCode the type of dungeon this is.
     * @param maxFloor the number of floors to generate.
     */
    private void generateFloors(DungeonCode dungeonCode, int maxFloor)
    {
        this.floors = new ArrayList<>();
        for(int i = 0; i < maxFloor; i++)
        {
            this.floors.add(new Floor(player, this.order));
            this.floors.get(i).generateFloor(dungeonCode);
        }
    }

    /**
     * Sets the {@link Position} of the {@code Player} to the {@link Spawn} of the {@code Floor}.
     */
    private void spawnPlayer()
    {
        Floor floor = this.floors.get(floorNum);
        this.player.setPosition(floor.getSpawnY(), floor.getSpawnX());
        floor.resetExitCondition();
    }

    /**
     * Increments the order of the dungeon by 1.
     * When the dungeon is finished, the next dungeon overwrites the floors here.
     * In order to reflect the challenge order of the dungeons, the order is incremented by 1 as this is the next dungeon to be challenged.
     */
    private void finishDungeon()
    {
        order += 1;
    }

    /**
     * Processes one cycle of player input.
     * <p>
     * Calls the {@link Floor#tick(char) Floor.tick} method to process the choice and changes there.
     * If that method returns true, that indicates that the {@code Floor} is finished.
     * If the {@code Floor} has been finished and there is another {@code Floor}
     * this increments the current floor by 1 and spawns the {@code Player} at the next {@code Floor}.
     * Otherwise, if there are no remaining {@code Floors}, this calls {@code finishDungeon}
     * and returns {@code true} to signal the {@link Controller} that the current {@code Dungeon} was completed.
     * 
     * @param choice the input character representing the player's choice.
     * 
     * @return {@code true} if the {@code Dungeon} has been finished, {@code false} if not. This notifies the {@code Controller} if the dungeon is finished.
     */
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
    /**
     * Returns the {@code Idol} trapped in this dungeon.
     * 
     * @return the {@code Idol} of this dungeon.
     */
    public Idol getIdol(){
        return this.idol;
    }
    /**
     * Returns the current {@code Floor} of the dungeon.
     * 
     * @return the {@code Floor} matching the current index in the {@code floors} list.
     */
    public Floor getFloor(){
        return this.floors.get(floorNum);
    }
    /**
     * Returns the name of the dungeon.
     * 
     * @return the name of the dungeon.
     */
    public String getName(){
        return this.dungeonName;
    }
    /**
     * Returns the number of the current {@code Floor}.
     * 
     * @return the number of the current {@code Floor}.
     */
    public int getFloorNum(){
        return this.floorNum;
    }
    /**
     * Returns the number of {@code Floors} this dungeon has.
     * 
     * @return the number of {@code Floors} in the dungeon.
     */
    public int getMaxFloor(){
        return this.maxFloor;
    }
    /**
     * Returns the challenge order of the dungeon.
     * 
     * @return the challenge order of the dungeon.
     */
    public int getOrder(){
        return this.order;
    }
}