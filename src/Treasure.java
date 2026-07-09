import java.util.Random;

public class Treasure extends Loot
{
    Treasure()
    {
        super(LootType.TREASURE);
    }

    @Override
    public boolean pickUpLoot(Floor floor)
    {
        Random rand = floor.getRand();
        if(rand.nextInt(2) == 0)
        {
            floor.getPlayer().gainGold(rand.nextInt(91) + 10);
        }else{
            //Get Item
        }
        return true;
    }
}