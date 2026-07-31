package model.structure;
/**
 * Represents the type of the Structure.
 * <p>
 * Used to identify the statistics and behaviors of a structure.
 */
public enum StructureType{
    /**
     * Marks the {@link Player} spawn location.
     */
    SPAWN,
    /**
     * Marks the exit of the current {@link Floor}.
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
     * Tiles that damage the {@code Player} and {@link Creature Creatures} when idle.
     */
    HEAT,

    SWITCH,
}