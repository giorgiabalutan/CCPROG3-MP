public class Heat extends Structure
{
    public Heat()
    {
        super(StructureType.HEAT);
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
        floor.damagePlayer(1);
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
        if(creature.canFly())
        {
            //Its a hotspring so the heat can reach into flight fr fr
            if(floor.getDungeonCode() == DungeonCode.YASUDAYA_RYOKAN)
            {
                creature.damageCreature(1);
            }
        }else{
            creature.damageCreature(1);
        }
        return false;
    }
}