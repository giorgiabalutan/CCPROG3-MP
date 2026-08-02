package model;

/**
 * The types of Combat Logs a Log can be.
 */
public enum CombatLogType {
    /**
     * Indicates the current turn number on the Floor.
     */
    TURN_INDICATOR,
    /**
     * Indicates what actions the player took this turn.
     */
    PLAYER_ACTION,
    /**
     * Indicates Loot gained by the player this turn.
     */
    LOOT_GAIN,
    /**
     * Indicates the Item Usage of the player this turn.
     */
    ITEM_USE,
    /**
     * Indicates damage taken by the Player or Creatures this turn.
     */
    DAMAGE,
    /**
     * Indicates creature or player deaths.
     */
    DEATH,
    /**
     * Indicates how much the Player healed.
     */
    HEAL,
    /**
     * Miscellaneous logs related to creatures.
     */
    CREATURE,
}
