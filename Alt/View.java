import java.util.ArrayList;

public class View
{
    private Model model;

    //Constructors
    public View(Model model)
    {
        this.model = model;
    }

    //Methods
    //Main Menu
    public void printMainMenu()
    {
        clearWithANSICodes();
        System.out.println("************************************************");
        System.out.println("*            Yohane The Parhelion!             *");
        System.out.println("*        The Siren in the Mirror World!        *");
        System.out.println("************************************************");

        if(model.isPlaythroughExists())
        {
            System.out.println("         [C]ontinue Game");
        }
        if(model.isNgPlusAvailable())
        {
            System.out.println("         [N]ew Game+");
        }else{
            System.out.println("         [N]ew Game");
        }
        System.out.println("         [S]tatus");
        System.out.println("         [H]ow to play");
        System.out.println("         [Q]uit");
    }
    public void printStatus()
    {
        System.out.println("PRINT STATUS");
    }
    public void printManual()
    {
        System.out.println("Instructions");
    }

    //Overworld
    public void printIntro(int i)
    {
        clearWithANSICodes();

        //Switch Case is being iterated through in startIntroSequence() in Controller.java
        //Update the loop there if adding more cases here
        switch(i)
        {
            case 0:
                System.out.println("----------------------------------");
                System.out.println("Numazu, Shizouka prefecture, Japan");
                System.out.println("----------------------------------");
                break;
            case 1:
                System.out.println("Lailaps: Yohane! Have you heard?!?");
                System.out.println("Yoshiko: What's the matter, Lailaps?");
                System.out.println("Lailaps: Some of the members have started to lose their voice!");
                System.out.println("Yoshiko: Oh my! This is terrible. We have to do something!");

                //dialogue to be continued

                ArrayList<Idol> idolList = this.model.getIdolList();
                int size = idolList.size();

                System.out.println("\nTo save: ");
                for (int j = 0; j < size; j++)
                {
                    System.out.println("" + idolList.get(j).getIdolName());
                }
                break;
        }
    }
    
    public void printOverworldOptions()
    {
        int i;
        Player player = this.model.getPlayer();
        ArrayList<Idol> idolList = this.model.getIdolList();
        int size = idolList.size();

        clearWithANSICodes();

        System.out.println("Lailaps: Yohane! Where should we go to now?");

        System.out.print("HP: " + player.getCurrHP() + " / " + player.getTotalHP());
        System.out.println("                  Total Gold: " + player.getTotalGold() + " GP");
        if(player.getInventory().getItemCount() > 0)
        {
            System.out.print("Item on hand: " + player.getInventory().getItems().get(0).getItemName());
        }else{
            System.out.print("Item on hand: None");
        }
        System.out.print("            [I]nventory");
        System.out.println("    [S]ave and Quit");

        System.out.println();
        for (i = 1; i <= size; i++)
        {
            System.out.println("[" + i + "] " + idolList.get(i-1).getDungeonName());
        }
    }

    public void printInventory()
    {
        int i;
        Player player = this.model.getPlayer();
        Inventory inventory = player.getInventory();
        int size = inventory.getItems().size(); //getItemCount()?

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
    }

    //Dungeon

    //Generic
    public void printChoicePrompt()
    {
        System.out.println();
        String errorMessage = this.model.getErrorMessage();
        if(!errorMessage.equals(""))
        {
            System.out.println(errorMessage);
            this.model.setErrorMessage("");
        }
        System.out.print("Your choice: ");
    }
    public void printContinuePrompt()
    {
        System.out.println();
        System.out.print("Press ENTER to continue: ");
    }
    private static void clearWithANSICodes() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}