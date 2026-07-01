import java.util.Scanner;
import java.util.ArrayList;

public class Display
{
    private String mainMenuChoices;

    public Display(int gameStatus)
    {
        if (gameStatus == 2)
            this.mainMenuChoices = "CNSHQ";
        else
            this.mainMenuChoices = "NSHQ"; 
    }

    public String getMMChoices()
    {
        return this.mainMenuChoices;
    }

    public void displayMainMenu(int gameStatus)
    {
        clearWithANSICodes();
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

        System.out.println();
        System.out.println("Your choice: ");
    }
    
    public void displayIntro(ArrayList<Idol> idolList)
    {
        int i, size = idolList.size();
        Scanner sc = new Scanner(System.in);

        clearWithANSICodes();

        System.out.println("----------------------------------");
        System.out.println("Numazu, Shizouka prefecture, Japan");
        System.out.println("----------------------------------");
        System.out.println("Press ENTER to continue...");
        sc.nextLine();

        clearWithANSICodes();
        System.out.println("Lailaps: Yohane! Have you heard?!?");
        System.out.println("Yoshiko: What's the matter, Lailaps?");
        System.out.println("Lailaps: Some of the members have started to lose their voice!");
        System.out.println("Yoshiko: Oh my! This is terrible. We have to do something!");
        //dialogue to be continued

        System.out.println("\nTo save: ");
        for (i = 0; i < size; i++)
            System.out.println("" + idolList.get(i).getIdolName());

        System.out.println("\nPress ENTER to continue...");
        sc.nextLine();
    }

    public void displayGamePlay(ArrayList<Idol> idolList, int currHP, int totalHP, int totalGold)
    {
        int i;
        int size = idolList.size();

        clearWithANSICodes();

        System.out.println("Lailaps: Yohane! Where should we go to now?");

        System.out.print("HP: " + currHP + " / " + totalHP);
        System.out.println("                  Total Gold: " + totalGold + " GP");
        System.out.print("Item on hand: ");
        System.out.print("            [I]nventory");
        System.out.println("    [S]ave and Quit");

        System.out.println();
        for (i = 0; i < size; i++)
        {
            System.out.println("[" + i + "] " + idolList.get(i).getDungeonName());
        }

        System.out.println();
        System.out.println("Your choice: ");
    }

    public void displayManual()
    {
        System.out.println("\f");
        System.out.println("Instructions");
    }

    public static void clearWithANSICodes() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}