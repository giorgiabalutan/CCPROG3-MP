public class Exit extends Structure
{
    public Exit()
    {
        super(StructureType.EXIT);
    }

    @Override
    public boolean interact(Floor floor)
    {
        floor.setExitReached();
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