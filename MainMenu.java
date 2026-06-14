public class MainMenu
{
    public MainMenu()
    {
        
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
        System.out.println("         [Q]uit");
    }

    public String getMainMenuChoices(int gameStatus)
    {
        if (gameStatus == 2)
            return "CNSQ";
        else
            return "NSQ";
    }
}