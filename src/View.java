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
                System.out.println("Hanamaru: Yoshiko! Have you heard?!?");
                System.out.println("Yoshiko: What's the matter, Hana-chan?");
                System.out.println("Hanamaru: Some of the idols have started to lose their voice!");
                System.out.println("Chika: Everyone!! Dia-chan and Mari-chan have lost their voice already too!");
                System.out.println("Yoshiko: Oh my! This is terrible. We have to do something!");
                System.out.println("Yoshiko: I vow to unleash my inner fallen angel, come forth Yohane-chan.");
                break;
            case 2:
                System.out.println("----------------------------------");
                System.out.println("During a peaceful moment of slumber");
                System.out.println("----------------------------------");
                System.out.println("Lailaps: Yohane-chan! Wake up, we seem to be in an mirror world.");
                System.out.println("Yohane: Lailaps? What is this mirror world you speak of?");
                System.out.println("Lailaps: It seems that this world is the reason why idols' voices are disappearing.");
                System.out.println("Lailaps: There is a siren lurking and stealing the voices!");
                System.out.println("Yohane: Why is the siren doing that?");
                System.out.println("Lailaps: To have the perfect singing voice Yohane-chan.");
                System.out.println("Lailaps: We have to venture off to the dungeons where the voices are hidden!");
                System.out.println("Lailaps: This way, we can retrieve the voices and end this madness!!");
                System.out.println("Yohane: I got it. Lets go, Lailaps!!");

                ArrayList<Idol> idolList = this.model.getIdolList();
                int size = idolList.size();

                System.out.println("\n----------------------------------");
                System.out.println("Voices to be retrieved");
                System.out.println("----------------------------------");
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

        System.out.printf("HP: %.1f / %.1f", player.getCurrHP() , player.getTotalHP());
        System.out.println("                  Total Gold: " + player.getTotalGold() + " GP");
        if(player.getInventory().getItemCount() > 0)
        {
            System.out.print("Item on hand: " + player.getItemOnHand().getItemName());
            System.out.print(" (" + player.getItemOnHand().getQuantity() + ")");
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
        int size = inventory.getItemCount();

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
    public void printDungeon()
    {
        //Get data from model
        Dungeon dungeon = this.model.getDungeon();
        Floor floor = dungeon.getFloor();
        int sizeY = floor.getSizeY();
        int sizeX = floor.getSizeX();
        Tile[][] grid = floor.getGrid();
        Player player = this.model.getPlayer();

        clearWithANSICodes();

        //Print Status
        System.out.println("Dungeon #" + dungeon.getOrder() + ": " + dungeon.getName());
        System.out.println("Floor " + (dungeon.getFloorNum()+1) + " of " + dungeon.getMaxFloor());
        System.out.println();

        //Old?
        // System.out.print("HP: " + player.getCurrHP() + " / " + player.getTotalHP());
        // System.out.println("                  Total Gold: " + player.getTotalGold() + " GP");
        // if(player.getInventory().getItemCount() > 0)
        // {
        //     System.out.println("Item on hand: " + player.getInventory().getItems().get(0).getItemName());
        // }else{
        //     System.out.println("Item on hand: None");
        // }

        System.out.printf("HP: %.1f / %.1f", player.getCurrHP() , player.getTotalHP());
        System.out.println("                  Total Gold: " + player.getTotalGold() + " GP");
        if(player.getInventory().getItemCount() > 0)
        {
            System.out.print("Item on hand: " + player.getItemOnHand().getItemName());
            System.out.print(" (" + player.getItemOnHand().getQuantity() + ")");
        }else{
            System.out.print("Item on hand: None");
        }

        //Print Floor
        System.out.println();
        for(int i = 0; i < sizeY; i++)
        {
            // System.err.println("test");
            for(int j = 0; j < sizeX; j++)
            {
                if(player.getPosition().getPosY() == i && player.getPosition().getPosX() == j)
                {
                    System.out.print("Y");
                }else{
                    System.out.print(grid[i][j].getTileChar());
                }
            }
            System.out.println();
        }

        //Debug Print Player Position
        // System.out.println(player.getPosition().getPosY()+" "+player.getPosition().getPosX());
    }

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