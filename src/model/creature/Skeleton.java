package model.creature;

import java.util.HashSet;
import model.Direction;
import model.dungeon.DungeonModifier;
import model.dungeon.Floor;
import model.loot.Gold;
import model.loot.Loot;

public class Skeleton extends Creature{
    private int order;
    private double damage;
    private int moveInterval;
    private int curCooldown;
    private int gold;

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


    @Override
    public void dropLoot(Floor floor){
        Loot goldDrop = new Gold(this.gold);
        int curY = this.getPosition().getPosY();
        int curX = this.getPosition().getPosX();
        floor.getLoot().add(goldDrop);
        floor.getGrid()[curY][curX].addLoot(goldDrop);
    }
}
