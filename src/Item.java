
import java.io.Serializable;

/**
 * Represents Items that the {@link Player} can have.
 * <p>
 * Tracks the availability and price of the item, and tracks how many copies of itself the {@code Player} has.
 */
public class Item implements Serializable
{
    /**
     * The name of the item for displaying.
     */
    private String itemName;
    /**
     * How many copies of the item the {@code Player} has.
     */
    private int quantity = 0;
    /**
     * The price of the item in the Shop.
     */
    private int price;
    /**
     * The availability of the item in the Shop.
     */
    private boolean isAvailable;
    /**
     * A code representing which item this is.
     * The following item codes represent:
     * <ul>
     * <li>1 - Mikan Mochi
     * <li>2 - Air Shoes
     * <li>3 - Bat Tamer
     * <li>5 - Choco-Mint Ice Cream
     * <li>6 - Kurosawa Macha
     * <li>7 - Shovel Upgrade
     * <li>8 - Stewshine
     * <li>10 - Tears of a Fallen Angel
     * <li>11 - Noppo Bread
     * </ul>
     */
    private int itemCode;
    private boolean isBought;
    /**
     * Constructs the item of the given code.
     * <p>
     * Sets the quantity and price of this item.
     * 
     * @param itemCode identifies which item this is.
     */
    public Item(int itemCode)
    {
        this.quantity += 1;
        this.itemCode = itemCode;
        switch(itemCode)
        {
            case 1: 
                this.itemName = "Mikan Mochi";
                this.price = 1000;
                this.isAvailable = false;
                break;
            case 2:
                this.itemName = "Air Shoes";
                this.price = 500;
                this.isAvailable = false;
                break;
            case 3:
                this.itemName = "Bat Tamer";
                this.price = 400;
                this.isAvailable = false;
                break;
            case 5:
                this.itemName = "Choco-Mint Ice Cream";
                this.price = 2000;
                this.isAvailable = false;
                break;
            case 6:
                this.itemName = "Kurosawa Macha";
                this.price = 1000;
                this.isAvailable = false;
                break;
            case 7:
                this.itemName = "Shovel Upgrade";
                this.price = 300;
                this.isAvailable = false;
                break;
            case 8:
                this.itemName = "Stewshine";
                this.price = 1000;
                this.isAvailable = false;
                break;
            case 10:
                this.itemName = "Tears of a Fallen Angel";
                this.price = 30;
                this.isAvailable = true;
                break;
            case 11:
                this.itemName = "Noppo Bread";
                this.price = 100;
                this.isAvailable = true;
                break;
        }
    }

    /**
     * Returns the name of the item for displaying.
     * 
     * @return the name of the item.
     */
    public String getItemName()
    {
        return this.itemName;
    }
    /**
     * Returns how many copies of the item the {@code Player} has.
     * 
     * @return the quantity of this item.
     */
    public int getQuantity()
    {
        return this.quantity;
    }
    /**
     * Returns the price of the item in the Shop.
     * 
     * @return the price of this item.
     */
    public int getPrice()
    {
        return this.price;
    }
    /**
     * Checks if the item is available in the Shop.
     * 
     * @return {@code true} if the item is available, {@code false} if not.
     */
    public boolean isAvailable()
    {
        return this.isAvailable;
    }
    /**
     * Returns the code identifying this item.
     * 
     * @return the itemCode of this item.
     */
    public int getItemCode()
    {
        return this.itemCode;
    }
    /**
     * Reduces the quantity of this item by 1.
     * Used when the {@code Player} consumes an item.
     */
    public void reduceQuantity()
    {
        this.quantity -= 1;
    }
    /**
     * Increases the quantity of this item by the given amount.
     * Used when the {@code Player} picks items up as loot, or when they buy items from the shop.
     * 
     * @param qt the amount of items being added.
     */
    public void addQuantity(int qt)
    {
        this.quantity += qt;
    }
    
    public boolean isBought()
    {
        return this.isBought;
    }
    
    public void setBought(boolean isBought)
    {
        this.isBought = isBought;
    }
}