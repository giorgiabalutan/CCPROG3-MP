
public class Bat extends Creature
{
    int order;
    double damage;
    int moveInterval;
    int curCooldown;
    int gold;

    Bat(int order)
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

    @Override
    public boolean tick(Floor floor)
    {
        // System.out.println("TEST " + this.getPosition().getPosY() + " " + this.getPosition().getPosX());
        this.curCooldown -= 1;
        if(this.curCooldown <= 0)
        {
            //Check if Yohane is adjacent
            int y = this.getPosition().getPosY();
            int x = this.getPosition().getPosX();
            if(floor.checkForPlayer(y-1, x) || floor.checkForPlayer(y+1, x) || floor.checkForPlayer(y, x-1) || floor.checkForPlayer(y, x+1))
            {
                //Attack
                floor.damagePlayer(damage);
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

    @Override
    public boolean dropLoot(Floor floor){
        Loot goldDrop = new Gold(this.gold);
        int curY = this.getPosition().getPosY();
        int curX = this.getPosition().getPosX();
        floor.getLoot().add(goldDrop);
        floor.getGrid()[curY][curX].addLoot(goldDrop);
        return true;
    }
}