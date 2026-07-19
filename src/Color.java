/**
 * Shortcut for ANSI Escape Sequences to change the foreground colors of text.
 * <p>
 * Used for printing colored text for the menu and map in console view. 
 */
public class Color
{
    /**
     * Private Constructor to prevent Instantiation. This class is meant to provide static data.
     */
    /**
     * Resets the text to the default console style
     */
    public static final String RESET = "\033[0m";
    /**
     * Changes the text to blue, used for Water Tiles
     */
    public static final String BLUE = "\033[38:5:87m";
    /**
     * Changes the text to orange, used for Heat Tiles
     */
    public static final String ORANGE = "\033[38:5:202m";
    /**
     * Changes the text to yellow, used for Loot and Exit
     */
    public static final String YELLOW = "\033[38:5:226m";
    /**
     * Changes the text to red, used for Bats
     */
    public static final String RED = "\033[38:5:196m";
    /**
     * Changes the text to dark red, used for Game Over
     */
    public static final String DARK_RED = "\033[38:5:88m";
    /**
     * Changes the text to green, used for Treasure
     */
    public static final String GREEN = "\033[38:5:40m";
    /**
     * Changes the text to purple, used for Yohane
     */
    public static final String PURPLE = "\033[38:5:13m";
}