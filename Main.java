import java.util.ArrayList;

public class Main
{
    public static void main(String args[])
    {
        
        Player player = new Player();
        int gameStatus = player.getGameStatus();

        Display UI = new Display(gameStatus);
        boolean quit = false;

        ArrayList<Idol> idolList = player.whoToSave();
        
        UI.displayMainMenu(gameStatus);
        
        do
        {
            String choice = player.pickChoice(UI.getChoices());
            if (choice.compareTo("N") == 0)
                new Gameplay(player, UI, idolList);
            else if (choice.compareTo("S") == 0)
                System.out.println("PRINT STATUS");
            else if (choice.compareTo("H") == 0)
                UI.displayManual();
            else 
                quit = true;
        }while(!quit);
            

    }

}