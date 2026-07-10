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
        int table = rand.nextInt(2);
        // System.out.println(table);
        switch(table)
        {
            case 0:
                floor.getPlayer().gainGold(rand.nextInt(91) + 10);
                break;
            case 1:
                floor.getPlayer().pickUpItem(new Item(11));
                break;
        }
        return true;
    }
}