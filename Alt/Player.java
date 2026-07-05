
public class Player
{
    private int currHP;
    private int totalHP;
    private int totalGold;
    private int goldSpent;
    private Item itemOnHand;
    private Inventory inventory;

    public Player()
    {
        currHP = 3;
        totalHP = 3;
        totalGold = 0;
        goldSpent = 0;
        inventory = new Inventory();
    }

    public int getCurrHP()
    {
        return this.currHP;
    }

    public int getTotalHP()
    {
        return this.totalHP;
    }

    public int getTotalGold()
    {
        return this.totalGold;
    }

    public int getGoldSpent()
    {
        return this.goldSpent;
    }

    public Inventory getInventory()
    {
        return this.inventory;
    }

    public void useItem()
    {
        
    }
}