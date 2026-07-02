import java.util.Scanner;
import java.util.ArrayList;

public class Display
{
    private String choices;

    public Display(int gameStatus)
    {
        if (gameStatus == 2)
            this.choices = "CNSHQ";
        else
            this.choices = "NSHQ"; 
    }

    public String getChoices()
    {
        return this.choices;
    }

    public void displayMainMenu(int gameStatus)
    {
        if (gameStatus == 2)
            this.choices = "CNSHQ";
        else
            this.choices = "NSHQ";

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

    public void displayGamePlay(Player player, ArrayList<Idol> idolList)
    {
        int i;
        int size = idolList.size();

        clearWithANSICodes();

        System.out.println("Lailaps: Yohane! Where should we go to now?");

        System.out.print("HP: " + player.getCurrHP() + " / " + player.getTotalHP());
        System.out.println("                  Total Gold: " + player.getTotalGold() + " GP");
        System.out.print("Item on hand: " + player.getInventory().getItems().get(0).getItemName());
        System.out.print("            [I]nventory");
        System.out.println("    [S]ave and Quit");

        System.out.println();
        for (i = 1; i <= size; i++)
        {
            System.out.println("[" + i + "] " + idolList.get(i-1).getDungeonName());
        }

        this.choices = "123IS";
    }

    public void displayInventory(Player player, Inventory inventory)
    {
        int size = inventory.getItems().size();
        int i;

        clearWithANSICodes();
        System.out.println("Lailaps: These are the items you have, Yohane!");

        System.out.print("HP: " + player.getCurrHP() + " / " + player.getTotalHP());
        System.out.println("                  Total Gold: " + player.getTotalGold() + " GP");
        System.out.print("Items Available \n");

        for (i = 1; i <= size; i++)
        {
            System.out.println("" + i + ". " + inventory.getItems().get(i-1).getItemName() 
            + "        " + inventory.getItems().get(i-1).getQuantity());
        }

        System.out.println("\n[R]eturn");
        this.choices = "R"; 
    }

    public void displayManual()
    {
        System.out.println("Instructions");
    }

    public static void clearWithANSICodes() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}