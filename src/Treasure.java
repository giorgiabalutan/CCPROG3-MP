import java.util.Random;

/**
 * Represents the random floor loot Treasure.
 * <p>
 * Extends the {@link Loot} class.
 * Treasure gives either an {@link Item} or a random amount of gold.
 */

public class Treasure extends Loot
{
    /**
     * Constructs a Treasure loot via the {@link Loot#Loot(LootType) Loot constructor}.
     */
    public Treasure()
    {
        super(LootType.TREASURE);
    }
    /**
     * Gives either a random {@code Item} or a random amount of gold (10-100) to the {@link Player} at equal odds.
     * 
     * @param floor the {@code Floor} the Treasure is on, allows it to use the floor's {@code Random} seed to generate loot.
     */
    @Override
    public void pickUpLoot(Floor floor)
    {
        Random rand = floor.getRand();
        int table = rand.nextInt(2);
        switch(table)
        {
            case 0:
                floor.getPlayer().gainGold(rand.nextInt(91) + 10);
                break;
            case 1:
                floor.getPlayer().pickUpItem(new Item(11));
                break;
        }
    }
}