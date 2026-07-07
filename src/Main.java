public class Main
{
    public static void main(String args[])
    {
        //Initialize Model, View, Controller Architecture
        Model game = new Model();
        View display = new View(game);
        Controller player = new Controller(game, display);
        
        //Starts taking inputs through the controller (which also has an initial print)
        player.run();
    }
}