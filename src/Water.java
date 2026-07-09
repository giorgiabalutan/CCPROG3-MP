public class Water extends Structure
{
    public Water()
    {
        super(StructureType.WATER);
    }

    @Override
    public boolean interact(Floor floor)
    {
        return false;
    }

    @Override
    public boolean isBlocking(Floor floor)
    {
        //False if Player has Air Shoes
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
        if(creature.canFly())
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean creatureIdle(Floor floor, Creature creature)
    {
        return false;
    }
}