public class Item
{
    private String itemName;
    private boolean isEquipped;
    private int quantity;
    private int price;
    private boolean isAvailable;

    public Item()
    {
        this.itemName = "N/A";
        this.isEquipped = false;
        this.quantity = 0;
    }

    public Item(int idolNumber)
    {
        switch(idolNumber)
        {
            case 1: 
                this.itemName = "Mikan Mochi";
                this.price = 1000;
                break;
            case 2:
                this.itemName = "Air Shoes";
                this.price = 500;
                break;
            case 3:
                this.itemName = "Bat Tamer";
                this.price = 400;
                break;
            case 5:
                this.itemName = "Choco-Mint Ice Cream";
                this.price = 2000;
                break;
            case 6:
                this.itemName = "Kurosawa Macha";
                this.price = 1000;
                break;
            case 7:
                this.itemName = "Shovel Upgrade";
                this.price = 300;
                break;
            case 8:
                this.itemName = "Stewshine";
                this.price = 1000;
                break;
        }
    }

    public String getItemName()
    {
        return this.itemName;
    }

    public boolean isEquipped()
    {
        return this.isEquipped;
    }

    public int getQuantity()
    {
        return this.quantity;
    }

    public int getPrice()
    {
        return this.price;
    }

    public boolean isAvailable()
    {
        return this.isAvailable();
    }
}