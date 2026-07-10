/**
 * Represents the Spawn Location of the floor.
 * <p>
 * Extends the {@link Structure} class.
 * Spawn is where the {@link Player} is placed when entering a {@link Floor}.
 */
public class Spawn extends Structure
{
    /**
     * Constructs a Spawn via the {@link Structure#Structure(StructureType) Structure constructor}.
     */
    public Spawn()
    {
        super(StructureType.SPAWN);
    }
    /**
     * Nothing happens on interaction.
     * 
     * @param floor the {@code Floor} the Spawn is on.
     * 
     * @return {@code false} as the Spawn can not be destroyed.
     */
    @Override
    public boolean interact(Floor floor)
    {
        return false;
    }
    /**
     * Spawns don't block movement.
     * 
     * @param floor the {@code Floor} the Spawn is on.
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
     * @param floor the {@code Floor} the Spawn is on.
     * 
     * @return {@code false} as the Spawn can not be destroyed.
     */
    @Override
    public boolean idle(Floor floor)
    {
        return false;
    }

    /**
     * Nothing happens on interaction.
     * 
     * @param floor the {@code Floor} the Spawn is on.
     * @param creature the {@link Creature} interacting with the Spawn.
     * 
     * @return {@code false} as the Spawn can not be destroyed.
     */
    @Override
    public boolean creatureInteract(Floor floor, Creature creature)
    {
        return false;
    }
    /**
     * Spawns don't block movement.
     * 
     * @param floor the {@code Floor} the Spawn is on.
     * @param creature the {@code Creature} attempting to pass the Spawn.
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
     * @param floor the {@code Floor} the Spawn is on.
     * @param creature the {@code Creature} idling on the Spawn.
     * 
     * @return {@code false} as the Spawn can not be destroyed.
     */
    @Override
    public boolean creatureIdle(Floor floor, Creature creature)
    {
        return false;
    }
}