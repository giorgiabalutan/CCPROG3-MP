public class Spike extends Structure
{
    public Spike()
    {
        super(StructureType.SPIKE);
    }

    @Override
    public boolean interact(Floor floor)
    {
        //Don't deal damage if Shovel upgraded
        floor.damagePlayer(0.5, "Spike");
        return true;
    }

    @Override
    public boolean isBlocking(Floor floor)
    {
        return true;
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
        return true;
    }

    @Override
    public boolean creatureIdle(Floor floor, Creature creature)
    {
        return false;
    }
}