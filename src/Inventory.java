import java.util.ArrayList;
/**
 * Tracks what and how many of each {@link Item} the {@link Player} has.
 */
public class Inventory
{
    /**
     * A list of {@code Items} the {@code Player} has.
     */
    private ArrayList<Item> itemList;
    /**
     * A count of how many unique items the {@code Player} has.
     */
    private int itemCount;

    /**
     * Constructs a {@code Player}'s inventory.
     * <p>
     * Initializes the Inventory containing a {@code Tears of a Fallen Angel} and a {@code Noppo Bread}.
     */
    public Inventory()
    {
        itemList = new ArrayList<Item>();
        itemList.add(new Item(10));
        itemList.add(new Item(11));
        itemCount = itemList.size();
    }
    /**
     * Adds the item to the inventory.
     * If a copy already exists in the inventory, it adds the quantities together,
     * else it adds the item directly.
     * 
     * @param item the item being added to the inventory via looting or shopping.
     */
    public void addItem(Item item)
    {
        boolean inItemList = false;
        for(Item listItem: this.itemList)
        {
            if(listItem.getItemCode() == item.getItemCode())
            {
                inItemList = true;
                listItem.addQuantity(item.getQuantity());
            }
        }
        if(!inItemList)
        {
            itemList.add(item);
            this.itemCount++;
        }
    }

    /**
     * Returns the list of {@code Items} in the inventory.
     * 
     * @return the list of {@code Items}.
     */
    public ArrayList<Item> getItems()
    {
        return this.itemList;
    }
    /**
     * Returns how many unique {@code Items} are in the inventory.
     * 
     * @return the count of unique {@code Items}.
     */
    public int getItemCount()
    {
        return this.itemCount;
    }
    /**
     * Removes a used {@code Item} from the inventory.
     * Called when the quantity of the item drops to 0.
     * 
     * @param itemOnHand the item that was used up by the {@code Player}.
     */
    public void removeUsedItem(Item itemOnHand)
    {
        int index = this.itemList.indexOf(itemOnHand);
        this.itemList.remove(index);
        this.itemList.trimToSize();
        this.itemCount--;
    }
}