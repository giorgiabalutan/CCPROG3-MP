package model.creature;

import java.util.HashSet;
import model.Direction;
import model.Player;
import model.dungeon.Dungeon;
import model.dungeon.DungeonModifier;
import model.dungeon.Floor;
import model.loot.Gold;
import model.loot.Loot;

/**
 * Represents the generic shooting creature Skeleton.
 * <p>
 * Extends the {@link Creature} class.
 * Skeletons have varying statistics depending on which dungeon it is in.
 */
public class Skeleton extends Creature{
    /**
     * The order of the {@link Dungeon} the Skeleton spawned in.
     */
    private int order;
    /**
     * The amount of damage the Skeleton deals on attack.
     */
    private double damage;
    /**
     * How many ticks it takes for the Skeleton to take its turn.
     */
    private int moveInterval;
    /**
     * The current number of remaining ticks until it takes its next turn.
     */
    private int curCooldown;
    /**
     * The amount of gold the Skeleton drops as {@link Loot}.
     */
    private int gold;

    /**
     * Constructs a Skeleton depending on the current {@code Dungeon} and {@link DungeonModifier}s.
     * <p>
     * Constructs a Skeleton Creature via the {@link Creature#Creature(CreatureType) Creature constructor}.
     * Its characteristics depend on the {@code order} of the {@code Dungeon}.
     * 
     * @param order the order of the dungeon currently being challenged.
     * @param dungeonModifiers the list of {@code DungeonModifier}s of the current {@code Floor}.
     */
    public Skeleton(int order, HashSet<DungeonModifier> dungeonModifiers)
    {
        super(CreatureType.SKELETON);
        this.order = order;
        this.damage = 0;
        this.gold = 0;
        this.moveInterval = 3;
        switch(this.order)
        {
            default:
            case 3:
                this.damage += 0.5;
                this.gold += 10;
            case 2:
                this.damage += 0.5;
                this.moveInterval -= 1;
                this.gold += 10;
            case 1:
                this.damage += 0.5;
                this.gold += 10;
        }
        
        if(dungeonModifiers.contains(DungeonModifier.STRONGER_SKELETONS))
        {
            this.damage += 0.5;
            this.moveInterval += 1;
            super.incMaxHp(1);
            this.gold += 20;
        }
        if(dungeonModifiers.contains(DungeonModifier.FASTER_SKELETONS))
        {
            this.damage -= 1;
        }

        if(this.damage < 0.5)
        {
            this.damage = 0.5;
        }
        if(this.moveInterval < 1)
        {
            this.moveInterval = 1;
        }
        
        this.curCooldown = this.moveInterval;
    }

    /**
     * Reduces the Skeleton's move cooldown by one. Once it reaches 0, it resets the {@code curCooldown} to the {@code moveInterval} and takes its turn.
     * If the {@link Player} is adjacent to the Skeleton on its turn, the Skeleton skips and holds its turn,
     * else, if the {@code Player} is aligned orthogonally, the Skeleton fires an {@link Arrow} through the {@link Floor.createArrow} method.
     * else, the Skeleton moves to a valid adjacent space closer to the {@code Player}.
     * The Skeleton also triggers any Structure Idle effects if it does not move during its turn.
     * With the {@code DungeonModifier}, {@code FASTER_SKELETONS}, on, it can fire an arrow after moving as well.
     * 
     * @param floor the {@link Floor} the Skeleton is on, allows the Skeleton to search and move on it.
     * @return {@code true} if the Skeleton died as a result of its turn.
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
            
            int playerY = floor.getPlayer().getPosition().getPosY();
            int playerX = floor.getPlayer().getPosition().getPosX();

            boolean preserveTurn = false;

            int distY = Math.abs(y - playerY);
            int distX = Math.abs(x - playerX);
            if(floor.checkForPlayer(y-1, x) || floor.checkForPlayer(y+1, x) || floor.checkForPlayer(y, x-1) || floor.checkForPlayer(y, x+1))
            {
                // Do Nothing
                // floor.damagePlayer(damage, "Skeleton");
                // floor.addCombatLog("An adjacent skeleton is paralyzed by fear.", CombatLogType.CREATURE);
                floor.creatureIdle(this);
            }else if (floor.attackLailaps(y-1, x, 0) || floor.attackLailaps(y+1, x, 0) || floor.attackLailaps(y, x-1, 0) || floor.attackLailaps(y, x+1, 0)) {
                // floor.addCombatLog("An adjacent skeleton is paralyzed by fear.", CombatLogType.CREATURE);
                floor.creatureIdle(this);
            }else if(distY == 0){
                if(x < playerX)
                {
                    floor.createArrow(y, x+1, this.damage, Direction.RIGHT);
                }
                if(x > playerX)
                {
                    floor.createArrow(y, x-1, this.damage, Direction.LEFT);
                }
            }else if (distX == 0) {
                if(y < playerY)
                {
                    floor.createArrow(y+1, x, this.damage, Direction.DOWN);
                }
                if(y > playerY)
                {
                    floor.createArrow(y-1, x, this.damage, Direction.UP);
                }
            }else{
                if(floor.isFrozenSkeletons())
                {
                    floor.creatureIdle(this);
                    preserveTurn = true;
                }else{
                    int offY = 0;
                    int offX = 0;

                    if(distY > distX)
                    {
                        if(x < playerX)
                        {
                            offX = 1;
                        }
                        if(x > playerX)
                        {
                            offX = -1;
                        }
                    }else{
                        if(y < playerY)
                        {
                            offY = 1;
                        }
                        if(y > playerY)
                        {
                            offY = -1;
                        }
                    }

                    if(offY > 0)
                    {
                        this.setDirection(Direction.DOWN);
                        if(!floor.isCreatureBlocked(this, y+offY, x))
                        {
                            floor.moveCreature(this, offY, 0);
                        }else{
                            floor.creatureIdle(this);
                            preserveTurn = true;
                        }
                    }else if(offY<0)
                    {
                        this.setDirection(Direction.UP);
                        if(!floor.isCreatureBlocked(this, y+offY, x))
                        {
                            floor.moveCreature(this, offY, 0);
                        }else{
                            floor.creatureIdle(this);
                            preserveTurn = true;
                        }
                    }else if(offX>0)
                    {
                        this.setDirection(Direction.RIGHT);
                        if(!floor.isCreatureBlocked(this, y, x+offX))
                        {
                            floor.moveCreature(this, 0, offX);
                        }else{
                            floor.creatureIdle(this);
                            preserveTurn = true;
                        }
                    }else{
                        this.setDirection(Direction.LEFT);
                        if(!floor.isCreatureBlocked(this, y, x+offX))
                        {
                            floor.moveCreature(this, 0, offX);
                        }else{
                            floor.creatureIdle(this);
                            preserveTurn = true;
                        }
                    }
                    if(floor.getDungeonModifiers().contains(DungeonModifier.FASTER_SKELETONS))
                    {
                        //Attack
                        if(distY == 0){
                            if(x < playerX)
                            {
                                floor.createArrow(y, x+1, this.damage, Direction.RIGHT);
                            }
                            if(x > playerX)
                            {
                                floor.createArrow(y, x-1, this.damage, Direction.LEFT);
                            }
                        }else if (distX == 0){
                            if(y < playerY)
                            {
                                floor.createArrow(y+1, x, this.damage, Direction.DOWN);
                            }
                            if(y > playerY)
                            {
                                floor.createArrow(y-1, x, this.damage, Direction.UP);
                            }
                        }
                    }
                }
            }
            this.curCooldown = this.moveInterval;
            if(preserveTurn)
            {
                this.curCooldown = 0;
            }
        }
        if(this.isDead()){
            return true;
        }
        return false;
    }

    /**
     * Drops a small amount of gold on death.
     * 
     * @param floor the {@code Floor} the Skeleton is on, allows the Skeleton to create {@link Gold} there.
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
