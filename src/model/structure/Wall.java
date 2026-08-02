package model.structure;

import java.util.HashSet;
import model.CombatLogType;
import model.creature.Creature;
import model.dungeon.DungeonModifier;
import model.dungeon.Floor;
/**
 * Represents a Wall on the floor.
 * <p>
 * Extends the {@link Structure} class.
 * Walls can get dug up by the {@link Player} when interacted with.
 */
public class Wall extends Structure
{
    /**
     * Indicates if the Wall is strong enough to survive another hit.
     */
    boolean strength;
    /**
     * Constructs a Wall via the {@link Structure#Structure(StructureType) Structure constructor}.
     * 
     * Sets {@code strength} to true if the floor has the {@link DungeonModifier#STRONGER_WALLS Stronger Walls Modifier}.
     */
    public Wall(HashSet<DungeonModifier> dungeonModifiers)
    {
        super(StructureType.WALL);
        if(dungeonModifiers.contains(DungeonModifier.STRONGER_WALLS))
        {
            strength = true;
        }else{
            strength = false;
        }
    }
    /**
     * The wall gets dug up by the {@code Player}.
     * It does not get dug up if it has {@code strength},
     * It loses the {@code strength} status once an attempt has been made.
     * 
     * @param floor the {@link Floor} the Wall is on.
     * 
     * @return {@code true} as the Wall gets dug up, {@code false} if it resisted with {@code strength}.
     */
    @Override
    public boolean interact(Floor floor)
    {
        if(strength && !floor.getPlayer().hasShovelUpgrade())
        {
            strength = false;
            floor.addCombatLog("The lingering magic protected the Wall!", CombatLogType.PLAYER_ACTION);
            return false;
        }
        return true;
    }
    /**
     * Walls always block movement.
     * 
     * @param floor the {@code Floor} the Wall is on.
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
     * @param floor the {@code Floor} the Wall is on.
     * 
     * @return {@code false} as the Wall can not be destroyed in this way.
     */
    @Override
    public boolean idle(Floor floor)
    {
        return false;
    }

    /**
     * Nothing happens on interaction.
     * 
     * @param floor the {@code Floor} the Wall is on.
     * @param creature the {@link Creature} interacting with the Wall.
     * 
     * @return {@code false} as the Wall can not be destroyed in this way.
     */
    @Override
    public boolean creatureInteract(Floor floor, Creature creature)
    {
        return false;
    }
    /**
     * Walls always block movement.
     * 
     * @param floor the {@code Floor} the Wall is on.
     * @param creature the {@code Creature} attempting to pass the Wall.
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
     * @param floor the {@code Floor} the Wall is on.
     * @param creature the {@code Creature} idling on the Wall.
     * 
     * @return {@code false} as the Wall can not be destroyed in this way.
     */
    @Override
    public boolean creatureIdle(Floor floor, Creature creature)
    {
        return false;
    }
}