import java.util.Scanner;

public class MainMenu
{
    boolean continueGame;

    public MainMenu(boolean continueGame)
    {
        this.continueGame = newGame;
    }

    public void displayMenu()
    {
        System.out.println("************************************************");
        System.out.println("*            Yohane The Parhelion!             *");
        System.out.println("*        The Siren in the Mirror World!        *");
        System.out.println("************************************************");

        if(this.continueGame)
            System.out.println("         [C]ontinue Game");
        
        System.out.println("         [N]ew Game");
        System.out.println("         [S]tatus");
        System.out.println("         [Q]uit");
    }

    public String choice(String choices)
    {
        Scanner sc = new Scanner(System.in);
        String choice;
        do
        {
            System.out.println("Your choice: ");
            choice = sc.nextLine();
        } while(!choices.contains(choice));
        
        return choice;
    }

}