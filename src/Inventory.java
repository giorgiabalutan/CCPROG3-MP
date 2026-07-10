import java.util.ArrayList;

public class Inventory
{
    private ArrayList<Item> itemList;
    private int itemCount;

    public Inventory()
    {
        itemList = new ArrayList<Item>();
        itemList.add(new Item(10));
        itemList.add(new Item(11));
        itemCount = itemList.size();
    }

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
        }
    }

    public ArrayList<Item> getItems()
    {
        return this.itemList;
    }

    public int getItemCount()
    {
        return this.itemCount;
    }

    public void removeUsedItem(Item itemOnHand)
    {
        int index = this.itemList.indexOf(itemOnHand);
        this.itemList.remove(index);
        this.itemList.trimToSize();
        this.itemCount--;
    }
}