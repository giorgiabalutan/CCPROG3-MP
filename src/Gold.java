/**
 * Represents the gold drops of {@link Creature Creatures}.
 * <p>
 * Extends the {@link Loot} class.
 * Gold gives a set amount of gold equal to what it is initialized with.
 */

public class Gold extends Loot
{
    /**
     * The amount of gold it will give.
     */
    private int gold;

    /**
     * Constructs a Gold loot via the {@link Loot#Loot(LootType) Loot constructor}.
     * <p>
     * Sets the {@code gold} variable equal to the input parameter.
     * 
     * @param gold the amount of gold a {@code Creature} dropped.
     */
    public Gold(int gold)
    {
        super(LootType.GOLD);
        this.gold = gold;
    }

    /**
     * Gives the {@link Player} its gold amount.
     * 
     * @param floor the {@code Floor} the Gold is on.
     */
    @Override
    public void pickUpLoot(Floor floor)
    {
        floor.getPlayer().gainGold(gold);
    }
}