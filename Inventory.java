import java.util.ArrayList;

public class Inventory
{
    private ArrayList<Item> itemList;

    public Inventory()
    {
        itemList = new ArrayList<Item>();
    }

    public ArrayList<Item> getItems()
    {
        return this.itemList;
    }
}