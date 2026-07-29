package model.structure;

import model.creature.Creature;
import model.dungeon.Floor;
/**
 * Represents the Border of the map.
 * <p>
 * Extends the {@link Structure} class.
 * Borders serve as map boundaries.
 */
public class Border extends Structure
{
    /**
     * Constructs a Border via the {@link Structure#Structure(StructureType) Structure constructor}.
     */
    public Border()
    {
        super(StructureType.BORDER);
    }

    /**
     * Nothing happens on interaction.
     * 
     * @param floor the {@link Floor} the Border is on.
     * 
     * @return {@code false} as the Border can not be destroyed.
     */
    @Override
    public boolean interact(Floor floor)
    {
        return false;
    }
    /**
     * Borders always block movement.
     * 
     * @param floor the {@code Floor} the Border is on.
     * 
     * @return {@code true} to block movement.
     */
    @Override
    public boolean isBlocking(Floor floor)
    {
        return true;
    }
    /**
     * Nothing happens on idle.
     * 
     * @param floor the {@code Floor} the Border is on.
     * 
     * @return {@code false} as the Border can not be destroyed.
     */
    @Override
    public boolean idle(Floor floor)
    {
        return false;
    }

    /**
     * Nothing happens on interaction.
     * 
     * @param floor the {@code Floor} the Border is on.
     * @param creature the {@link Creature} interacting with the Border.
     * 
     * @return {@code false} as the Border can not be destroyed.
     */
    @Override
    public boolean creatureInteract(Floor floor, Creature creature)
    {
        return false;
    }
    /**
     * Borders always block movement.
     * 
     * @param floor the {@code Floor} the Border is on.
     * @param creature the {@code Creature} attempting to pass the Border.
     * 
     * @return {@code true} to block movement.
     */
    @Override
    public boolean creatureIsBlocking(Floor floor, Creature creature)
    {
        return true;
    }
    /**
     * Nothing happens on idle.
     * 
     * @param floor the {@code Floor} the Border is on.
     * @param creature the {@code Creature} idling on the Border.
     * 
     * @return {@code false} as the Border can not be destroyed.
     */
    @Override
    public boolean creatureIdle(Floor floor, Creature creature)
    {
        return false;
    }
}