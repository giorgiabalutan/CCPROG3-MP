package model.dungeon;

import java.io.Serializable;
import java.util.ArrayList;
import model.creature.Creature;
import model.loot.Loot;
import model.structure.Structure;
/**
 * Represents one of the Tiles on a {@link Floor}.
 * <p>
 * Contains a list of the entities currently occupying the same {@link Position}.
 * The tile itself does not have a {@code Position} as it's tracked by the {@link Floor#grid grid} variable of {@code Floor}.
 */
public class Tile implements Serializable
{
    /**
     * A list of {@link Structure Structures} in the same {@code Position}.
     */
    private ArrayList<Structure> structures;
    /**
     * A list of {@link Loot} in the same {@code Position}.
     */
    private ArrayList<Loot> loots;
    /**
     * A list of {@link Creature Creatures} in the same {@code Position}.
     */
    private ArrayList<Creature> creatures;

    /**
     * Constructs an empty Tile object.
     */
    public Tile()
    {
        this.structures = new ArrayList<>();
        this.creatures = new ArrayList<>();
        this.loots = new ArrayList<>();
    }

    /**
     * Adds a {@code Structure} to the Tile.
     * 
     * @param struct the structure being added to the Tile.
     */
    public void addStructure(Structure struct)
    {
        this.structures.add(struct);
    }
    /**
     * Adds a {@code Loot} to the Tile.
     * 
     * @param loot the loot being added to the Tile.
     */
    public void addLoot(Loot loot)
    {
        this.loots.add(loot);
    }
    /**
     * Adds a {@code Creature} to the Tile.
     * 
     * @param creature the creature being added to the Tile.
     */
    public void addCreature(Creature creature)
    {
        this.creatures.add(creature);
    }

    /**
     * Gets the {@code ArrayList} of {@code Structures} in the Tile.
     * 
     * @return the {@code ArrayList} of {@code Structures} stored in the Tile.
     */
    public ArrayList<Structure> getStructures()
    {
        return this.structures;
    }
    /**
     * Gets the {@code ArrayList} of {@code Loots} in the Tile.
     * 
     * @return the {@code ArrayList} of {@code Loots} stored in the Tile.
     */
    public ArrayList<Loot> getLoots()
    {
        return this.loots;
    }
    /**
     * Gets the {@code ArrayList} of {@code Creatures} in the Tile.
     * 
     * @return the {@code ArrayList} of {@code Creatures} stored in the Tile.
     */
    public ArrayList<Creature> getCreatures()
    {
        return this.creatures;
    }

    /**
     * Gets a character representation of the Tile.
     * Prioritizes the first entry in {@code creatures},
     * then the first entry in {@code loots},
     * then, the first entry in {@code structures}.
     * If there are no entities on the Tile, it returns the default tile character '.' with the default console color.
     * 
     * @return the character representation of the Tile with the {@link Color} of the Tile.
     */
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