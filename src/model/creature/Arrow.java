package model.creature;

import java.util.ArrayList;
import model.Direction;
import model.dungeon.DungeonModifier;
import model.dungeon.Floor;


public class Arrow extends Creature{
    private double damage;
    private Direction direction;
    private int moveInterval;
    private int curCooldown;

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

    @Override
    public void dropLoot(Floor floor){}
}
