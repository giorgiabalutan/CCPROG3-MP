package model.structure;

import model.creature.Creature;
import model.dungeon.Floor;
/**
 * Represents a Wall on the floor.
 * <p>
 * Extends the {@link Structure} class.
 * Walls can get dug up by the {@link Player} when interacted with.
 */
public class Wall extends Structure
{
    /**
     * Constructs a Wall via the {@link Structure#Structure(StructureType) Structure constructor}.
     */
    public Wall()
    {
        super(StructureType.WALL);
    }
    /**
     * The wall gets dug up by the {@code Player}.
     * 
     * @param floor the {@link Floor} the Wall is on.
     * 
     * @return {@code true} as the Wall gets dug up.
     */
    @Override
    public boolean interact(Floor floor)
    {
        return true;
    }
    /**
     * Walls always block movement.
     * 
     * @param floor the {@code Floor} the Wall is on.
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
     * @param floor the {@code Floor} the Wall is on.
     * 
     * @return {@code false} as the Wall can not be destroyed in this way.
     */
    @Override
    public boolean idle(Floor floor)
    {
        return false;
    }

    /**
     * Nothing happens on interaction.
     * 
     * @param floor the {@code Floor} the Wall is on.
     * @param creature the {@link Creature} interacting with the Wall.
     * 
     * @return {@code false} as the Wall can not be destroyed in this way.
     */
    @Override
    public boolean creatureInteract(Floor floor, Creature creature)
    {
        return false;
    }
    /**
     * Walls always block movement.
     * 
     * @param floor the {@code Floor} the Wall is on.
     * @param creature the {@code Creature} attempting to pass the Wall.
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
     * @param floor the {@code Floor} the Wall is on.
     * @param creature the {@code Creature} idling on the Wall.
     * 
     * @return {@code false} as the Wall can not be destroyed in this way.
     */
    @Override
    public boolean creatureIdle(Floor floor, Creature creature)
    {
        return false;
    }
}