
public class Player
{
    private double currHP;
    private double totalHP;
    private int totalGold;
    private int goldSpent;
    private Item itemOnHand;
    private Inventory inventory;

    //Constructor
    public Player()
    {
        currHP = 3;
        totalHP = 3;
        totalGold = 0;
        goldSpent = 0;
        inventory = new Inventory();
        itemOnHand = inventory.getItems().get(0);
    }

    public void useItem()
    {
        int itemCode = this.itemOnHand.getItemCode();
        switch(itemCode)
        {
            case 1: 
                this.totalHP++;
                break;
            // case 2:
                
            //     break;
            // case 3:
                
            //     break;
            case 5:
                this.currHP = this.totalHP;
                break;
            case 6:
                this.totalHP++;
                break;
            // case 7:
                
            //     break;
            case 8:
                this.totalHP++;
                break;
            case 10:
                heal();
                break;
            case 11:
                heal();
                break;
        }
    }

    public void heal()
    {
        if (this.currHP < this.totalHP && this.itemOnHand.getQuantity() > 0)
        {
            System.out.println("Healing...");
            this.currHP += 0.5;
            itemOnHand.reduceQuantity();

            if (this.itemOnHand.getQuantity() == 0)
                updateItemOnHand();
        }
        else if (this.currHP == this.totalHP && this.itemOnHand.getQuantity() > 0)
        {
            System.out.println("Lailaps: You're still fully healed Yohane-chan");
            System.out.println("Lailaps: Stop being nervous eh");
        }
        else
            System.out.println("Lailaps: You've used up all your items Yohane-chan.");
            
    }

    //if one item is used up, remove that item and put the other one
    public void updateItemOnHand()
    {
        this.inventory.removeUsedItem(this.itemOnHand);
        if (this.inventory.getItemCount() > 0)
            this.itemOnHand = inventory.getItems().get(0);
    }

    public void previousItem()
    {
        int currentIndex = this.inventory.getItems().indexOf(this.itemOnHand);
        if (this.inventory.getItemCount() > 0 && currentIndex > 0)
            this.itemOnHand = inventory.getItems().get(currentIndex - 1);
        else if (this.inventory.getItemCount() == 0)
            System.out.println("Lailaps: You've used up all your items Yohane-chan.");
    }

    public void nextItem()
    {
        int currentIndex = this.inventory.getItems().indexOf(this.itemOnHand);
        if (this.inventory.getItemCount() > 0 && currentIndex + 1 < this.inventory.getItemCount())  
            this.itemOnHand = inventory.getItems().get(currentIndex + 1);
        else if (this.inventory.getItemCount() == 0)
            System.out.println("Lailaps: You've used up all your items Yohane-chan.");
    }

    //Getter methods
    public double getCurrHP()
    {
        return this.currHP;
    }

    public double getTotalHP()
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

    public Item getItemOnHand()
    {
        return this.itemOnHand;
    }
}