public class Spawn extends Structure
{
    public Spawn()
    {
        super(StructureType.SPAWN);
    }

    @Override
    public boolean interact(Floor floor)
    {
        return false;
    }

    @Override
    public boolean isBlocking(Floor floor)
    {
        return false;
    }

    @Override
    public boolean idle(Floor floor)
    {
        return false;
    }

    @Override
    public boolean creatureInteract(Floor floor, Creature creature)
    {
        return false;
    }

    @Override
    public boolean creatureIsBlocking(Floor floor, Creature creature)
    {
        return false;
    }

    @Override
    public boolean creatureIdle(Floor floor, Creature creature)
    {
        return false;
    }
}