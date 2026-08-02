package model.dungeon;
/**
 * Represents what modifiers a dungeon has.
 */
public enum DungeonModifier {
    /**
     * Heat Tiles can damage Fliers
     * TO DO: Implement Item Interaction, Air Shoes
     */
    STRONGER_HEAT,
    /**
     * Healing Items heal Twice as much
     */
    STRONGER_HEALS,
    /**
     * Bat damage is increased by 0.5
     * Bat movement interval is increased by 1
     */
    STRONGER_BATS,
    /**
     * Skeleton damage is increased by 0.5
     * Skeleton movement interval is increased by 1
     * Skeleton hp is increased by 1
     */
    STRONGER_SKELETONS,
    /**
     * Walls take two actions to destroy unless the Shovel Upgrade has been bought
     */
    STRONGER_WALLS,
    /**
     * Bat damage is decreased by 1 to a minimum of 0.5
     * Bats can either move Twice in a turn or Attack once.
     */
    FASTER_BATS,
    /**
     * Skeleton damage is decreased by 1 to a minimum of 0.5
     * Skeletons can also attack after moving
     */
    FASTER_SKELETONS,
    /**
     * Bat damage is set to 0.5
     * Bat movement interval is increased by 2
     */
    CRIPPLED_BATS,
    /**
     * Non fliers can traverse water, projectile speed is halved
     * TO DO: Skeletons.
     */
    REBREATHER,
    /**
     * Waters spawn with Heat Tiles
     */
    HOT_WATERS,
    /**
     * Gold gain is cut in half
     */
    GOLD_TAX,
    
}
