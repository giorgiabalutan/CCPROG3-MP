package model.structure;

import model.CombatLogType;
import model.Player;
import model.creature.Creature;
import model.dungeon.DungeonCode;
import model.dungeon.DungeonModifier;
import model.dungeon.Floor;
/**
 * Represents a Heat Tile on the floor.
 * <p>
 * Extends the {@link Structure} class.
 * Heat Tiles damage an idling {@link Player} or {@link Creature}.
 */
public class Heat extends Structure
{
    /**
     * Constructs a Heat Tile via the {@link Structure#Structure(StructureType) Structure constructor}.
     */
    public Heat()
    {
        super(StructureType.HEAT);
    }
    /**
     * Nothing happens on interaction.
     * 
     * @param floor the {@link Floor} the Heat Tile is on.
     * 
     * @return {@code false} as the Heat Tile can not be destroyed.
     */
    @Override
    public boolean interact(Floor floor)
    {
        return false;
    }
    /**
     * Heat Tiles don't block movement.
     * 
     * @param floor the {@code Floor} the Heat Tile is on.
     * 
     * @return {@code false} to allow movement.
     */
    @Override
    public boolean isBlocking(Floor floor)
    {
        return false;
    }
    /**
     * Damages the {@code Player} on idle.
     * 
     * @param floor the {@code Floor} the Heat Tile is on.
     * 
     * @return {@code false} as the Heat Tile can not be destroyed.
     */
    @Override
    public boolean idle(Floor floor)
    {
        if(floor.getPlayer().hasAirShoes() && !floor.getDungeonModifiers().contains(DungeonModifier.STRONGER_HEAT))
        {
            return false;
        }
        floor.damagePlayer(1, "Heat");
        floor.addCombatLog("Yohane took 1 damage from the Heat!",CombatLogType.DAMAGE);
        return false;
    }

    /**
     * Nothing happens on interaction.
     * 
     * @param floor the {@code Floor} the Heat Tile is on.
     * @param creature the {@code Creature} interacting with the Heat Tile.
     * 
     * @return {@code false} as the Heat Tile can not be destroyed.
     */
    @Override
    public boolean creatureInteract(Floor floor, Creature creature)
    {
        return false;
    }
    /**
     * Heat Tiles don't block movement.
     * 
     * @param floor the {@code Floor} the Heat Tile is on.
     * @param creature the {@code Creature} attempting to pass the Heat Tile.
     * 
     * @return {@code false} to allow movement.
     */
    @Override
    public boolean creatureIsBlocking(Floor floor, Creature creature)
    {
        return false;
    }
    /**
     * Damages a {@code Creature} idling on the heat tile.
     * Does not damage a flying {@code Creature} unless the dungeon is {@link DungeonCode#YASUDAYA_RYOKAN Yasudaya Ryokan}.
     * 
     * @param floor the {@code Floor} the Heat Tile is on.
     * @param creature the {@code Creature} idling on the Heat Tile.
     * 
     * @return {@code false} as the Heat Tile can not be destroyed.
     */
    @Override
    public boolean creatureIdle(Floor floor, Creature creature)
    {
        System.out.println("TEST");
        if(creature.canFly())
        {
            if(floor.getDungeonModifiers().contains(DungeonModifier.STRONGER_HEAT))
            {
                creature.damageCreature(1);
                floor.addCombatLog(creature.getName()+" took 1 damage from the Heat!", CombatLogType.DAMAGE);
            }
        }else{
            creature.damageCreature(1);
            floor.addCombatLog(creature.getName()+" took 1 damage from the Heat!", CombatLogType.DAMAGE);
        }
        return false;
    }
}