public class Item
{
    private String itemName;
    private int quantity = 0;
    private int price;
    private boolean isAvailable;
    private int itemCode;

    public Item(int itemCode)
    {
        this.quantity += 1;
        this.itemCode = itemCode;
        switch(itemCode)
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
            case 10:
                this.itemName = "Tears of a fallen angel";
                this.price = 30;
                break;
            case 11:
                this.itemName = "Noppo Bread";
                this.price = 100;
                break;
        }
    }

    public String getItemName()
    {
        return this.itemName;
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

    public int getItemCode()
    {
        return this.itemCode;
    }

    public void reduceQuantity()
    {
        this.quantity -= 1;
    }
}