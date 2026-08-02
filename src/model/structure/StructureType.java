package model.structure;
/**
 * Represents the type of the Structure.
 * <p>
 * Used to identify the statistics and behaviors of a structure.
 */
public enum StructureType{
    /**
     * Marks the {@link model.Player} spawn location.
     */
    SPAWN,
    /**
     * Marks the exit of the current {@link model.dungeon.Floor}.
     */
    EXIT,
    /**
     * Impassable borders for the floor.
     */
    BORDER,
    /**
     * Diggable obstacles.
     */
    WALL,
    /**
     * Diggable obstacles that damage the {@code Player}.
     */
    SPIKE,
    /**
     * Impassable tiles unless flying.
     */
    WATER,
    /**
     * Tiles that damage the {@code Player} and {@link model.creature.Creature Creatures} when idle.
     */
    HEAT,
    /**
     * Tiles that must be pressed to progress the Final Boss Floor.
     */
    SWITCH,
}