package model.structure;

import model.creature.Creature;
import model.dungeon.DungeonModifier;
import model.dungeon.Floor;
/**
 * Represents a Water Tile on the floor.
 * <p>
 * Extends the {@link Structure} class.
 * Water tiles block non-flyers.
 */
public class Water extends Structure
{
    /**
     * Constructs a Water Tile via the {@link Structure#Structure(StructureType) Structure constructor}.
     */
    public Water()
    {
        super(StructureType.WATER);
    }
    /**
     * Nothing happens on interaction.
     * 
     * @param floor the {@link Floor} the Water Tile is on.
     * 
     * @return {@code false} as the Water Tile can not be destroyed.
     */
    @Override
    public boolean interact(Floor floor)
    {
        return false;
    }
    /**
     * Water Tiles block movement.
     * 
     * @param floor the {@code Floor} the Water Tile is on.
     * 
     * @return {@code true} if movement is blocked.
     */
    @Override
    public boolean isBlocking(Floor floor)
    {
        //False if Player has Air Shoes (to be implemented later...)
        if(floor.getDungeonModifiers().contains(DungeonModifier.REBREATHER) || floor.getPlayer().hasAirShoes())
        {
            return false;
        }
        return true;
    }
    /**
     * Nothing happens on idle.
     * 
     * @param floor the {@code Floor} the Water Tile is on.
     * 
     * @return {@code false} as the Water Tile can not be destroyed.
     */
    @Override
    public boolean idle(Floor floor)
    {
        return false;
    }

    /**
     * Nothing happens on interaction.
     * 
     * @param floor the {@code Floor} the Water Tile is on.
     * @param creature the {@link Creature} interacting with the Water Tile.
     * 
     * @return {@code false} as the Water Tile can not be destroyed.
     */
    @Override
    public boolean creatureInteract(Floor floor, Creature creature)
    {
        return false;
    }
    /**
     * Water Tiles block movement unless the {@code Creature} is flying.
     * 
     * @param floor the {@code Floor} the Water Tile is on.
     * @param creature the {@code Creature} attempting to pass the Water Tile.
     * 
     * @return {@code true} if movement is blocked, {@code false} if allowed.
     */
    @Override
    public boolean creatureIsBlocking(Floor floor, Creature creature)
    {
        if(creature.canFly())
        {
            return false;
        }
        if(floor.getDungeonModifiers().contains(DungeonModifier.REBREATHER))
        {
            return false;
        }
        return true;
    }
    /**
     * Nothing happens on idle.
     * 
     * @param floor the {@code Floor} the Water Tile is on.
     * @param creature the {@code Creature} idling on the Water Tile.
     * 
     * @return {@code false} as the Water Tile can not be destroyed.
     */
    @Override
    public boolean creatureIdle(Floor floor, Creature creature)
    {
        return false;
    }
}