package model.loot;
/**
 * Represents the type of the Loot.
 * <p>
 * Used to identify the drops obtained on pick-up.
 */
public enum LootType{
    /**
     * Random {@link model.Item} or Random amount of Gold.
     */
    TREASURE,
    /**
     * Set amount of Gold.
     */
    GOLD,
}