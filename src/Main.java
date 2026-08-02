import model.Model;
/**
 * Starts up the game.
 * <p>
 * Initializes {@link Model}, {@link View}, and {@link Controller}.
 * Calls {@link Controller#run() Controller.run} and leaves the loop to the {@code Controller}.
 */
public class Main
{
    /**
     * Private Constructor to prevent Instantiation. This class exists to initialize the program from the console.
     */
    private Main(){}
    /**
     * Starts up the game.
     * 
     * @param args unused.
     */
    public static void main(String args[])
    {
        //Initialize Model, View, Controller Architecture
        /**
         * Initializes the {@code Model}.
         */
        Model game = new Model();
        /**
         * Initializes the {@code View} with a reference to the {@code Model}.
         */
        if (game.hasSavedGame())
            game.load();
        View display = new View(game);
        /**
         * Initializes the {@code Controller} with a reference to the {@code View} and {@code Model}.
         */
        Controller player = new Controller(game, display);
        
        //Starts taking inputs through the controller (which also has an initial print)
        player.run();

        //For Testing
        // player.skipIntroNew();
        // player.SkipToSiren();
    }
}