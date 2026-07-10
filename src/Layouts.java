/**
 * A library to hold preset layouts for {@link Floor} maps.
 * <p>
 * Each layout is an array of Strings where each character represents a {@link Tile}.
 * Each character represents:
 * <ul>
 * <li>S - {@link Spawn}
 * <li>E - {@link Exit}
 * <li>* - {@link Border}
 * <li>v - {@link Wall}
 * <li>x - {@link Spike}
 * <li>w - {@link Water}
 * <li>h - {@link Heat}
 * <li>b - {@link Bat}
 * <li>T - {@link Treasure}
 * <li>. - Empty {@code Tile}
 * </ul>
 */
public class Layouts
{
    /**
     * Private Constructor to prevent Instantiation. This class is meant to provide static data.
     */
    private Layouts(){}
    /**
     * The reference level found in the specs.
     */
    public static final String[] REFERENCE = {
        "*******************************************************",
        "*.....x.............x.................................*",
        "*..T..x....h........x...............b.................*",
        "*.....x....hh.......x.......ww........xx..............*",
        "*.....xx.......b...xx.......ww........xx....x...b.....*",
        "*.....x....v.......xx..x....ww........xx.b..x.........*",
        "*.x...x....x.......vx..x....ww........xx....x.........*",
        "*.x..Sx....x.......vx.......ww........xx..vvv...xxxbxx*",
        "*..x..vvvvvvvvvvvvv....vvvvvww........xx.h.....vv.....*",
        "*..x....x.................vvv............h.....vv.....*",
        "*.......x..................v.......b.....h.....vv.b..E*",
        "*******************************************************"
    };
    /**
     * A level to test Bat movement on Water Tiles.
     */
    public static final String[] BAT_WATER_TEST = {
        "*******************************************************",
        "*.....x.............x.................................*",
        "*..T..x....h........x...............b.................*",
        "*.....x....hh.......x.......ww........xx..............*",
        "*.....xx.......b...xx.......ww........xx....x...b.....*",
        "*.....x....v.......xx..x....ww........xx.b..x.........*",
        "*.x...x....x.......vx..x....wwww......xx....x.........*",
        "*.x..Sx....x.......vx.......wwbw......xx..vvv...xxxbxx*",
        "*..x..vvvvvvvvvvvvv....vvvvvwwww......xx.h.....vv.....*",
        "*..x....x.................vvv............h.....vv.....*",
        "*.......x..................v.......b.....h.....vv.b..E*",
        "*******************************************************"
    };
}