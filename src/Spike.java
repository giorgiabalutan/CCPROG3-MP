/**
 * Represents a Spike on the floor.
 * <p>
 * Extends the {@link Structure} class.
 * Spikes damage the {@link Player} when interacted with.
 * Spikes can get dug up by the {@code Player} when interacted with.
 */
public class Spike extends Structure
{
    /**
     * Constructs a Spike via the {@link Structure#Structure(StructureType) Structure constructor}.
     */
    public Spike()
    {
        super(StructureType.SPIKE);
    }
    /**
     * The spike damages the {@code Player} while being dug up.
     * 
     * @param floor the {@link Floor} the Spike is on.
     * 
     * @return {@code true} as the Spike gets dug up.
     */
    @Override
    public boolean interact(Floor floor)
    {
        //Don't deal damage if Shovel upgraded (To implement later...)
        floor.damagePlayer(0.5, "Spike");
        return true;
    }
    /**
     * Spikes always block movement.
     * 
     * @param floor the {@code Floor} the Spike is on.
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
     * @param floor the {@code Floor} the Spike is on.
     * 
     * @return {@code false} as the Spike can not be destroyed in this way.
     */
    @Override
    public boolean idle(Floor floor)
    {
        return false;
    }

    /**
     * Nothing happens on interaction.
     * 
     * @param floor the {@code Floor} the Spike is on.
     * @param creature the {@link Creature} interacting with the Spike.
     * 
     * @return {@code false} as the Spike can not be destroyed in this way.
     */
    @Override
    public boolean creatureInteract(Floor floor, Creature creature)
    {
        return false;
    }
    /**
     * Spikes always block movement.
     * 
     * @param floor the {@code Floor} the Spike is on.
     * @param creature the {@code Creature} attempting to pass the Spike.
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
     * @param floor the {@code Floor} the Spike is on.
     * @param creature the {@code Creature} idling on the Spike.
     * 
     * @return {@code false} as the Spike can not be destroyed in this way.
     */
    @Override
    public boolean creatureIdle(Floor floor, Creature creature)
    {
        return false;
    }
}