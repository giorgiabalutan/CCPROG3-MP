import java.util.ArrayList;
import java.util.Arrays;

public class MainMenu
{   
    private String mainMenuChoices;

    public MainMenu(int gameStatus)
    {
        if (gameStatus == 2)
            this.mainMenuChoices = "CNSHQ";
        else
            this.mainMenuChoices = "NSHQ"; 
    }

    public void displayMenu(int gameStatus)
    {
        System.out.println("************************************************");
        System.out.println("*            Yohane The Parhelion!             *");
        System.out.println("*        The Siren in the Mirror World!        *");
        System.out.println("************************************************");

        if(gameStatus == 1)
            System.out.println("         [N]ew Game");
        else if (gameStatus == 2)
        {
            System.out.println("         [C]ontinue Game");
            System.out.println("         [N]ew Game");
        }
        else
            System.out.println("         [N]ew Game+");
            
        System.out.println("         [S]tatus");
        System.out.println("         [H]tow to play");
        System.out.println("         [Q]uit");
    }

    public String getMainMenuChoices()
    {
        return mainMenuChoices;
    }

    public void displayManual()
    {
        boolean quit = false;
        ArrayList<String> P1 = new ArrayList<String>(Arrays.asList(
            "Page 1: About the Game",
            "In this game, players will take on the role of Yoshiko Tsushima (otherwise known as “Yohane”),",
            "one of the nine members of the school idol group Aqours,",
            "to solve mysterious phenomena involving idols losing their voices everywhere."
           

        ));

        P1.forEach(line -> System.out.println(line));
    }
}