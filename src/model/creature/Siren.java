package model.creature;

import model.CombatLogType;
import model.Direction;
import model.Player;
import model.dungeon.Floor;
import model.loot.Gold;
import model.loot.Loot;
/**
 * Represents the generic flying creature Bat.
 * <p>
 * Extends the {@link Creature} class.
 * Bats have varying statistics depending on which dungeon it is in.
 */
public class Siren extends Creature
{
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
    public Siren()
    {
        super(CreatureType.SIREN);
        this.damage = 999;
        this.gold = 750;
        this.moveInterval = 1;
        this.curCooldown = this.moveInterval;
        this.setIdle(false);
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
            this.setIdle(false);
            int y = this.getPosition().getPosY();
            int x = this.getPosition().getPosX();
            if(floor.checkForPlayer(y-1, x) || floor.checkForPlayer(y+1, x) || floor.checkForPlayer(y, x-1) || floor.checkForPlayer(y, x+1))
            {
                //Attack
                floor.damagePlayer(damage, "Siren");
                floor.addCombatLog("Yohane was hit by the Siren for " + damage + " damage!", CombatLogType.DAMAGE);
            }else if (floor.attackLailaps(y-1, x, damage) || floor.attackLailaps(y+1, x, damage) || floor.attackLailaps(y, x-1, damage) || floor.attackLailaps(y, x+1, damage)) {
                floor.addCombatLog("Lailaps was hit by the Siren for " + damage + " damage!", CombatLogType.DAMAGE);
            }else{
                Boolean playerIsCloser = true;

                int playerY = floor.getPlayer().getPosition().getPosY();
                int playerX = floor.getPlayer().getPosition().getPosX();
                double playerDist = floor.getPlayerDistance(y, x);

                int lailapsY = 0;
                int lailapsX = 0;
                double lailapsDist;

                Lailaps closestLailaps = floor.findClosestLailaps(y, x);
                if(closestLailaps != null)
                {
                    lailapsY = closestLailaps.getPosition().getPosY();
                    lailapsX = closestLailaps.getPosition().getPosX();
                    lailapsDist = Math.pow(Math.pow(y-lailapsY,2)+Math.pow(x-lailapsX,2),0.5);
                    if (lailapsDist < playerDist - 4)
                    {
                        playerIsCloser = false;
                    }
                }

                int offY = 0;
                int offX = 0;

                if(playerIsCloser)
                {
                    if(y < playerY)
                    {
                        offY = 1;
                    }
                    if(y > playerY)
                    {
                        offY = -1;
                    }
                    if(x < playerX)
                    {
                        offX = 1;
                    }
                    if(x > playerX)
                    {
                        offX = -1;
                    }
                }else{
                    if(y < lailapsY)
                    {
                        offY = 1;
                    }
                    if(y > lailapsY)
                    {
                        offY = -1;
                    }
                    if(x < lailapsX)
                    {
                        offX = 1;
                    }
                    if(x > lailapsX)
                    {
                        offX = -1;
                    }
                }

                if(offY > 0)
                {
                    this.setDirection(Direction.DOWN);
                }else if(offY<0)
                {
                    this.setDirection(Direction.UP);
                }else if(offX>0)
                {
                    this.setDirection(Direction.RIGHT);
                }else{
                    this.setDirection(Direction.LEFT);
                }

                if(floor.isCreatureBlocked(this,y+offY, x+offX))
                {
                    if(floor.isCreatureBlocked(this, y, x+offX))
                    {
                        if(floor.isCreatureBlocked(this, y+offY, x))
                        {
                            //Cant get closer oh well
                        }else{
                            floor.moveCreature(this, offY, 0);
                        }
                    }else{
                        floor.moveCreature(this, 0, offX);
                    }
                }else{
                    floor.moveCreature(this, offY, offX);
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

    public void setPower(int power)
    {
        this.damage = 0;
        this.gold = 0;
        this.moveInterval = 2;
        switch(power)
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
    }
    
}