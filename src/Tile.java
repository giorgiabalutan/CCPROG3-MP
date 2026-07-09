import java.util.ArrayList;

public class Tile
{
    private ArrayList<Structure> structures;
    private ArrayList<Loot> loots;
    private ArrayList<Creature> creatures;
    private char displayChar;

    public Tile()
    {
        displayChar = '.';
        this.structures = new ArrayList<>();
        this.creatures = new ArrayList<>();
        this.loots = new ArrayList<>();
    }

    public void addStructure(Structure struct)
    {
        this.structures.add(struct);
    }

    public void addLoot(Loot loot)
    {
        this.loots.add(loot);
    }

    public void addCreature(Creature creature)
    {
        this.creatures.add(creature);
    }

    public ArrayList<Structure> getStructures()
    {
        return this.structures;
    }

    public ArrayList<Loot> getLoots()
    {
        return this.loots;
    }

    public ArrayList<Creature> getCreatures()
    {
        return this.creatures;
    }

    public char getTileChar()
    {
        for(Creature creature : creatures)
        {
            return creature.getDisplayChar();
        }
        for(Loot loot : loots)
        {
            return loot.getDisplayChar();
        }
        for(Structure structure : structures)
        {
            return structure.getDisplayChar();
        }
        return '.';
    }
}