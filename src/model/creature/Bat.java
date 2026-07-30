package model.creature;

import model.CombatLogType;
import model.dungeon.Floor;
import model.loot.Gold;
import model.loot.Loot;
/**
 * Represents the generic flying creature Bat.
 * <p>
 * Extends the {@link Creature} class.
 * Bats have varying statistics depending on which dungeon it is in.
 */
public class Bat extends Creature
{
    /**
     * The order of the {@link Dungeon} the bat spawned in.
     */
    private int order;
    /**
     * The amount of damage the bat deals on attack.
     */
    private double damage;
    /**
     * How many ticks it takes for the Bat to take its turn.
     */
    private int moveInterval;
    /**
     * The current number of remaining ticks until it takes its next turn.
     */
    private int curCooldown;
    /**
     * The amount of gold the Bat drops as {@link Loot}.
     */
    private int gold;

    /**
     * Constructs a bat depending on the current {@code Dungeon}.
     * <p>
     * Constructs a Bat Creature via the {@link Creature#Creature(CreatureType) Creature constructor}.
     * Its characteristics depend on the {@code order} of the {@code Dungeon}.
     * 
     * @param order the order of the dungeon currently being challenged.
     */
    public Bat(int order)
    {
        super(CreatureType.BAT);
        this.order = order;
        this.damage = 0;
        this.gold = 0;
        this.moveInterval = 2;
        switch(order)
        {
            default:
            case 3:
                this.damage += 0.5;
                this.gold += 5;
            case 2:
                this.damage += 0.5;
                this.moveInterval -= 1;
                this.gold += 5;
            case 1:
                this.damage += 0.5;
                this.gold += 5;
        }
        this.curCooldown = this.moveInterval;
    }

    /**
     * Reduces the Bat's move cooldown by one. Once it reaches 0, it resets the {@code curCooldown} to the {@code moveInterval} and takes its turn.
     * If the {@link Player} is adjacent to the Bat on its turn, the Bat attacks the {@code Player},
     * else the bat moves to a random valid adjacent space.
     * The bat is also able to move diagonally on the 3rd {@code Dungeon}.
     * The bat also triggers any Structure Idle effects if it does not move during its turn.
     * 
     * @param floor the {@link Floor} the Bat is on, allows the Bat to search and move on it.
     * @return {@code true} if the Bat died as a result of its turn.
     */
    @Override
    public boolean tick(Floor floor)
    {
        this.curCooldown -= 1;
        if(this.curCooldown <= 0)
        {
            int y = this.getPosition().getPosY();
            int x = this.getPosition().getPosX();
            if(floor.checkForPlayer(y-1, x) || floor.checkForPlayer(y+1, x) || floor.checkForPlayer(y, x-1) || floor.checkForPlayer(y, x+1))
            {
                //Attack
                floor.damagePlayer(damage, "Bat");
                floor.addCombatLog("Yohane was hit by a bat for " + damage + " damage!", CombatLogType.DAMAGE);
            }else{
                //Move
                int directions[][] = new int[8][2];
                int directionsSize;
                int validDirections[][] = new int[8][2];
                int j = 0;
                if(order < 3)
                {
                    directions = new int[][]{
                                {-1, 0},
                        {0, -1},        {0, 1},
                                {1, 0}
                    };
                    directionsSize = 4;
                }else{
                    directions = new int[][]{
                        {-1, -1}, {-1, 0}, {-1, 1},
                        {0, -1},           {0, 1},
                        {1, -1},  {1, 0},  {1, 1}
                    };
                    directionsSize = 8;
                }
                for(int i = 0; i < directionsSize; i++)
                {
                    int checkY = directions[i][0] + y;
                    int checkX = directions[i][1] + x;
                    if(!floor.isCreatureBlocked(this, checkY, checkX))
                    {
                        validDirections[j] = directions[i];
                        j++;
                    }
                }
                if(j > 0)
                {
                    int direction[] = validDirections[floor.getRand().nextInt(j)];
                    int moveY = direction[0];
                    int moveX = direction[1];
                    floor.moveCreature(this, moveY, moveX);
                }else{
                    floor.creatureIdle(this);
                }
            }
            this.curCooldown = this.moveInterval;
        }
        if(this.isDead()){
            return true;
        }
        return false;
    }

    /**
     * Drops a small amount of gold on death.
     * 
     * @param floor the {@code Floor} the Bat is on, allows the Bat to create {@link Gold} there.
     */
    @Override
    public void dropLoot(Floor floor){
        Loot goldDrop = new Gold(this.gold);
        int curY = this.getPosition().getPosY();
        int curX = this.getPosition().getPosX();
        floor.getLoot().add(goldDrop);
        floor.getGrid()[curY][curX].addLoot(goldDrop);
    }
}