public class Gold extends Loot
{
    int gold;

    Gold(int gold)
    {
        super(LootType.GOLD);
        this.gold = gold;
    }

    @Override
    public boolean pickUpLoot(Floor floor)
    {
        floor.getPlayer().gainGold(gold);
        return true;
    }
}