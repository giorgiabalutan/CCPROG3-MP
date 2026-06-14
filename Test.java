public class Test
{
    public static void main(String args[])
    {
        MainMenu mm = new MainMenu();
        Player p = new Player();
        boolean quit = false;
        int gameStatus = p.getGameStatus();
        mm.displayMenu(gameStatus);
        do
        {
            String choice = p.pickChoice(mm.getMainMenuChoices(gameStatus));

            if (choice.compareTo("N") == 0)
                System.out.println("NEW GAME ALERT!");
            else if (choice.compareTo("S") == 0)
                System.out.println("PRINT STATUS");
            else 
                quit = true;

        }while (!quit);


        

        
        
        
        
        
    }
}