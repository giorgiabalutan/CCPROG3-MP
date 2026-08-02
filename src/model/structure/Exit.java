package model.structure;

import model.creature.Creature;
import model.dungeon.Dungeon;
import model.dungeon.Floor;
/**
 * Represents the Exit of the floor.
 * <p>
 * Extends the {@link Structure} class.
 * Exit serves as the main goal of a floor.
 */
public class Exit extends Structure
{
    /**
     * Indicates if the Exit should be hidden of not.
     */
    private Boolean isHidden;
    /**
     * Constructs an Exit via the {@link Structure#Structure(StructureType) Structure constructor}.
     * <p>
     * Sets {@code isHidden} to false by default.
     */
    public Exit()
    {
        super(StructureType.EXIT);
        this.isHidden = false;
    }
    /**
     * Sets the ExitReached flag to {@code true} to allow the {@link Dungeon} to progress to the next {@link Floor}.
     * <p>
     * Does not set the flag if this Exit {@code isHidden}.
     * 
     * @param floor the {@code Floor} the Exit is on.
     * 
     * @return {@code false} as the Exit can not be destroyed.
     */
    @Override
    public boolean interact(Floor floor)
    {
        if(!isHidden)
        {
            floor.setExitReached();
        }
        return false;
    }
    /**
     * Exits don't block movement.
     * 
     * @param floor the {@code Floor} the Exit is on.
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
     * @param floor the {@code Floor} the Exit is on.
     * 
     * @return {@code false} as the Exit can not be destroyed.
     */
    @Override
    public boolean idle(Floor floor)
    {
        return false;
    }

    /**
     * Nothing happens on interaction.
     * 
     * @param floor the {@code Floor} the Exit is on.
     * @param creature the {@link Creature} interacting with the Exit.
     * 
     * @return {@code false} as the Exit can not be destroyed.
     */
    @Override
    public boolean creatureInteract(Floor floor, Creature creature)
    {
        return false;
    }
    /**
     * Exits don't block movement.
     * 
     * @param floor the {@code Floor} the Exit is on.
     * @param creature the {@code Creature} attempting to pass the Exit.
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
     * @param floor the {@code Floor} the Exit is on.
     * @param creature the {@code Creature} idling on the Exit.
     * 
     * @return {@code false} as the Exit can not be destroyed.
     */
    @Override
    public boolean creatureIdle(Floor floor, Creature creature)
    {
        return false;
    }
    /**
     * Set whether this exit should be hidden.
     * 
     * @param bool whether this exit should be hidden.
     */
    public void setHidden(Boolean bool)
    {
        this.isHidden = bool;
    }
    /**
     * Returns if this exit is currently hidden.
     * 
     * @return {@code true} if this exit is currently hidden.
     */
    public Boolean isHidden()
    {
        return this.isHidden;
    }
}