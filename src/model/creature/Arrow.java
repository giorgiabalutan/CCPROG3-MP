package model.creature;

import java.util.ArrayList;
import model.Direction;
import model.Player;
import model.dungeon.DungeonModifier;
import model.dungeon.Floor;
import model.structure.Water;

/**
 * Represents the projectiles shot by {@link Skeleton}
 * <p>
 * Extends the {@link Creature} class.
 */
public class Arrow extends Creature{
    /**
     * The amount of damage this creature deals.
     */
    private double damage;
    /**
     * The direction this creature moves in.
     */
    private Direction direction;
    /**
     * How many ticks it takes for the Arrow to take its turn.
     */
    private int moveInterval;
    /**
     * The current number of remaining ticks until it takes its next turn.
     */
    private int curCooldown;

    /**
     * Constructs an Arrow.
     * <p>
     * Constructs an Arrow Creature via the {@link Creature#Creature(CreatureType) Creature constructor}.
     * Its characteristics depend on the damage of the {@code Skeleton} that shot it.
     * 
     * @param damage the amount of damage this deals.
     * @param direction the direction the arrow moves in.
     */
    public Arrow(double damage, Direction direction)
    {
        super(CreatureType.ARROW);
        this.damage = damage;
        this.direction = direction;
        this.setDirection(direction);
        this.moveInterval = 1;
        this.curCooldown = this.moveInterval;
        this.setIdle(false);
    }

    /**
     * Reduces the Arrow's move cooldown by one. Once it reaches 0, it resets the {@code curCooldown} to the {@code moveInterval} and takes its turn.
     * If the arrow lands on a {@link Player} or {@code Creature} on its turn, the Arrow attacks the {@code Player} or {@code Creature},
     * and kills itself.
     * It moves twice in its direction every turn, unless it is in {@link Water}.
     * 
     * @param floor the {@link Floor} the Arrow is on, allows the Bat to search and move on it.
     * @return {@code true} if the Arrow died as a result of its turn.
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
            int offY = 0;
            int offX = 0;

            int movements = 2;

            if(floor.getDungeonModifiers().contains(DungeonModifier.REBREATHER))
            {
                if(floor.checkForWater(y, x))
                {
                    movements--;
                }
            }

            for(int i = 0; i < movements; i++)
            {
                switch(this.direction)
                {
                    case DOWN:
                        offY = 1;
                        break;
                    case UP:
                        offY = -1;
                        break;
                    case RIGHT:
                        offX = 1;
                        break;
                    case LEFT:
                        offX = -1;
                        break;
                }

                if(!floor.isCreatureBlocked(this, y+offY, x+offX))
                {
                    floor.moveCreature(this, offY, offX);
                    y = this.getPosition().getPosY();
                    x = this.getPosition().getPosX();
                    if (floor.checkForPlayer(y, x))
                    {
                        floor.damagePlayer(this.damage, "Arrow");
                        super.damageCreature(999);
                    }
                    ArrayList<Creature> creaturesFound = floor.checkForCreatures(y, x);
                    if(!creaturesFound.isEmpty())
                    {
                        for(Creature creature: creaturesFound)
                        {
                            if(creature.getCreatureType() != CreatureType.ARROW)
                            {
                                // System.out.println(creature.getHp());
                                creature.damageCreature(damage*2);
                                super.damageCreature(999);
                            }
                        }
                    }
                }else{
                    super.damageCreature(999);
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
     * Drops nothing on death.
     * 
     * @param floor the {@code Floor} the Arrow is on.
     */
    @Override
    public void dropLoot(Floor floor){}
}
