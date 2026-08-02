package model.structure;

import model.creature.Creature;
import model.dungeon.Floor;
/**
 * Represents the Switches of the final boss floor.
 * <p>
 * Extends the {@link Structure} class.
 */
public class Switch extends Structure
{
    /**
     * Constructs a Switch via the {@link Structure#Structure(StructureType) Structure constructor}.
     */
    public Switch()
    {
        super(StructureType.SWITCH);
    }
    /**
     * Nothing happens on interaction.
     * Pressed status is checked each turn by the {@code Floor}.
     * 
     * @param floor the {@code Floor} the Switch is on.
     * 
     * @return {@code false} as the Switch should not be destroyed normally.
     */
    @Override
    public boolean interact(Floor floor)
    {
        return false;
    }
    /**
     * Switches don't block movement.
     * 
     * @param floor the {@code Floor} the Switch is on.
     * 
     * @return {@code false} to allow movement.
     */
    @Override
    public boolean isBlocking(Floor floor)
    {
        return false;
    }
    /**
     * Nothing happens on idle.
     * 
     * @param floor the {@code Floor} the Switch is on.
     * 
     * @return {@code false} as the Switch should not be destroyed normally.
     */
    @Override
    public boolean idle(Floor floor)
    {
        return false;
    }

    /**
     * Nothing happens on interaction.
     * 
     * @param floor the {@code Floor} the Switch is on.
     * @param creature the {@link Creature} interacting with the Switch.
     * 
     * @return {@code false} as the Switch should not be destroyed normally.
     */
    @Override
    public boolean creatureInteract(Floor floor, Creature creature)
    {
        return false;
    }
    /**
     * Switches don't block movement.
     * 
     * @param floor the {@code Floor} the Switch is on.
     * @param creature the {@code Creature} attempting to pass the Switch.
     * 
     * @return {@code false} to allow movement.
     */
    @Override
    public boolean creatureIsBlocking(Floor floor, Creature creature)
    {
        return false;
    }
    /**
     * Nothing happens on idle.
     * 
     * @param floor the {@code Floor} the Switch is on.
     * @param creature the {@code Creature} idling on the Switch.
     * 
     * @return {@code false} as the Switch should not be destroyed normally.
     */
    @Override
    public boolean creatureIdle(Floor floor, Creature creature)
    {
        return false;
    }
}