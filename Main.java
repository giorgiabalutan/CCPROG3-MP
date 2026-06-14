public class Main
{
    public static void main(String args[])
    {
        
        Player p = new Player();
        int gameStatus = p.getGameStatus();
        MainMenu mm = new MainMenu(gameStatus);
        boolean quit = false;
        
        mm.displayMenu(gameStatus);
        do
        {
            String choice = p.pickChoice(mm.getMainMenuChoices());

            if (choice.compareTo("N") == 0)
                System.out.println("NEW GAME ALERT!");
            else if (choice.compareTo("S") == 0)
                System.out.println("PRINT STATUS");
            else if (choice.compareTo("H") == 0)
                mm.displayManual();
            else 
                quit = true;

        }while (!quit);


        

        
        
        
        
        
    }
}