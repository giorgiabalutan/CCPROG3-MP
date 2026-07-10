public class Player
{
    private double currHP;
    private double totalHP;
    private int totalGold;
    private int goldSpent;
    private Item itemOnHand;
    private Inventory inventory;
    private double attack;
    private Position pos;
    private String causeOfDeath;

    //Constructor
    public Player()
    {
        currHP = 3;
        totalHP = 3;
        totalGold = 0;
        goldSpent = 0;
        attack = 1;
        causeOfDeath = "";
        inventory = new Inventory();
        itemOnHand = inventory.getItems().get(0);
        pos = new Position();
    }

    //Methods
    public void damage(double dmg){
        currHP -= dmg;
    }

    public String[] useItem()
    {
        int itemCode = this.itemOnHand.getItemCode();
        String[] messages = {};
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
                messages = heal();
                break;
            case 11:
                messages = heal();
                break;
        }
        return messages;
    }

    public String[] heal()
    {
        String[] messages;
        if (this.currHP < this.totalHP && this.itemOnHand.getQuantity() > 0)
        {
            System.out.println("Healing...");
            this.currHP += 0.5;
            itemOnHand.reduceQuantity();

            if (this.itemOnHand.getQuantity() == 0)
                updateItemOnHand();
            
            messages = new String[]{};
        }
        else if (this.currHP == this.totalHP && this.itemOnHand.getQuantity() > 0)
        {
            // System.out.println("Lailaps: You're still fully healed Yohane-chan");
            // System.out.println("Lailaps: Stop being nervous eh");
            messages = new String[]{
                "Lailaps: You're still fully healed Yohane-chan",
                "Lailaps: Stop being nervous eh"
            };
        }
        else
        {
            // System.out.println("Lailaps: You've used up all your items Yohane-chan.");
            messages = new String[]{"Lailaps: You've used up all your items Yohane-chan."};
        }
        return messages;
    }

    //if one item is used up, remove that item and put the other one
    public void updateItemOnHand()
    {
        this.inventory.removeUsedItem(this.itemOnHand);
        if (this.inventory.getItemCount() > 0)
            this.itemOnHand = inventory.getItems().get(0);
    }

    public String previousItem()
    {
        int currentIndex = this.inventory.getItems().indexOf(this.itemOnHand);
        String message = "";
        if (this.inventory.getItemCount() > 0 && currentIndex > 0)
            this.itemOnHand = inventory.getItems().get(currentIndex - 1);
        else if (this.inventory.getItemCount() == 0)
            message = "Lailaps: You've used up all your items Yohane-chan.";
        return message;
    }

    public void pickUpItem(Item item)
    {
        this.inventory.addItem(item);
    }

    public String nextItem()
    {
        int currentIndex = this.inventory.getItems().indexOf(this.itemOnHand);
        String message = "";
        if (this.inventory.getItemCount() > 0 && currentIndex + 1 < this.inventory.getItemCount())  
            this.itemOnHand = inventory.getItems().get(currentIndex + 1);
        else if (this.inventory.getItemCount() == 0)
            message = "Lailaps: You've used up all your items Yohane-chan.";
        return message;
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

    public boolean isDead()
    {
        return this.currHP <= 0;
    }

    public int getTotalGold()
    {
        return this.totalGold;
    }

    public void gainGold(int gold)
    {
        this.totalGold += gold;
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

    public double getAttack(){
        return this.attack;
    }

    public Position getPosition()
    {
        return this.pos;
    }

    public void setPosition(int y, int x)
    {
        this.pos.setPosition(y, x);
    }

    public void move(int y, int x)
    {
        this.pos.move(y, x);
    }

    public String getCauseOfDeath()
    {
        return this.causeOfDeath;
    }
    public void setCauseOfDeath(String cause)
    {
        this.causeOfDeath = cause;
    }
}