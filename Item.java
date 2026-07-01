public class Item
{
    private String itemName;
    private boolean isEquipped;
    private int quantity;

    public Item()
    {
        this.itemName = "N/A";
        this.isEquipped = false;
        this.quantity = 0;
    }

    public String getItemName()
    {
        return this.itemName;
    }

    public int getQuantity()
    {
        return this.quantity;
    }
}