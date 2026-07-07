import java.util.ArrayList;

public class Inventory
{
    private ArrayList<Item> itemList;
    private int itemCount;

    public Inventory()
    {
        itemList = new ArrayList<Item>();
        itemList.add(new Item());
    }

    public ArrayList<Item> getItems()
    {
        return this.itemList;
    }

    public int getItemCount()
    {
        return this.itemCount;
    }
}