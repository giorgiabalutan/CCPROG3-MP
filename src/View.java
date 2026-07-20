import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * Contains all of the Console Print methods.
 * <p>
 * Contains the printing methods for the different parts of the game.
 */
public class View extends JFrame
{
    /**
     * The access to the data in {@link Model} for printing information from it.
     */
    private Model model;
   
    private int frameWidth = 1100;
    private int frameHeight = 540;
    
    private CardLayout cardLayout;
    private Container contentPane;
    
    private MainMenuPanel mainMenuPanel;
    private OverworldPanel overworldPanel;
    //Constructors
    /**
     * Constructs the View with access to the {@code Model} object.
     * 
     * @param model the model of the program.
     */
    public View(Model model)
    {
        this.model = model;
        this.cardLayout = new CardLayout();
        this.contentPane = getContentPane();
        this.contentPane.setLayout(cardLayout);
        
        mainMenuPanel = new MainMenuPanel(this.model, this.frameWidth, this.frameHeight);
        overworldPanel = new OverworldPanel(this.model, this.frameWidth, this.frameHeight);
        
        contentPane.add(mainMenuPanel, "MAIN_MENU");
        contentPane.add(overworldPanel, "OVERWORLD");
        
        this.setTitle("Yohane the Parhelion: The Siren in the Mirror World!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
   
    public void setActionListener(ActionListener listener)
    {
        this.mainMenuPanel.setButtonActionListener(listener);
    }
    
    public void setKeyListener(KeyListener keyListener)
    {
        this.overworldPanel.setKeyListener(keyListener);
    }
    
    public void showPanel(String panelName)
    {
        cardLayout.show(contentPane, panelName);
    }
    
    public void repaintOverworld()
    {
        this.overworldPanel.repaint();
    }
    //Methods
    //Main Menu
    /**
     * Prints the graphics and choices for the Main Menu.
     * <p>
     * Prints the choice to check Status, How to Play, and Quit.
     * Also prints the choices to start a New Game, a New Game Plus, or to Continue depending on availability.
     */
   
    /**
     * Prints the lifetime stats of the {@link Player}.
     */
    public void printStatus()
    {
        System.out.println("PRINT STATUS");
    }
    /**
     * Prints a manual on how to play the game.
     */
    public void printManual()
    {
        System.out.println("Instructions");
    }

    //Overworld
    /**
     * Prints the Intro sequence of the overworld.
     * <p>
     * Called by {@link Controller#startIntroSequence() Controller.startIntroSequence} in order to print only parts of the output.
     * It iterates through each of the cases here, with a {@link Controller#waitForContinue() Controller.waitForContinue} call between prints.
     * 
     * @param i indicates which set of prints to do.
     */
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

    //Dungeon
    /**
     * Displays the current {@link Floor} of the {@code Dungeon}.
     * <p>
     * Prints the {@code Dungeon} information, {@code Dungeon} information, and the current {@code Floor}.
     */
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

        System.out.printf("HP: %.1f / %.1f", player.getCurrHP() , player.getTotalHP());
        System.out.println("                  Total Gold: " + Color.YELLOW + player.getTotalGold() + " GP");
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
                    System.out.print("Y" );
                }else{
                    System.out.print(grid[i][j].getTileChar());
                }
            }
        }

        //Debug Print Player Position
        // System.out.println(player.getPosition().getPosY()+" "+player.getPosition().getPosX());
    }

    /**
     * Prints a death message if the {@code Player} dies.
     * 
     * @param cause a string holding the name of the entity that caused the death.
     */
    public void deathMessage(String cause)
    {
        clearWithANSICodes();
        System.out.println("GAME OVER");
        System.out.println("Yohane has fallen due to " + cause);
    }

    /**
     * Prints a victory message if the {@code Player} clears a {@code Dungeon}.
     * 
     * @param idol the {@link Idol} that the player rescued.
     */
    public void finishedFloor(Idol idol)
    {
        clearWithANSICodes();
        System.out.println("************************************************************");
        System.out.println("                     Dungeon Cleared!                       ");
        System.out.println("           "+idol.getDungeonName()+" Completed!          ");
        System.out.println("                "+idol.getIdolName()+" rescued!                 ");
        System.out.println("************************************************************");

    }

    //Generic
    /**
     * Prints the prompt asking for the player's next choice.
     * <p>
     * Also prints an error message or list of error messages grabbed from {@code Model} if any exist.
     */
    public void printChoicePrompt()
    {
        System.out.println();
        String errorMessage = this.model.getErrorMessage();
        if(!errorMessage.equals(""))
        {
            System.out.println(errorMessage);
            this.model.setErrorMessage("");
        }
        String[] errorMessages = this.model.getErrorMessages();
        if(errorMessages != null && errorMessages.length > 0)
        {
            for(String msg : errorMessages)
            {
                System.out.println(msg);
            }
            this.model.setErrorMessages(new String[0]);
        }
        System.out.print("Your choice: ");
    }
    /**
     * Prints a prompt asking for the player to enter anything to continue.
     * Used for cutscenes where the player's input does not matter.
     */
    public void printContinuePrompt()
    {
        System.out.println();
        System.out.print("Press ENTER to continue: ");
    }
    /**
     * Clears the current screen using ANSI codes.
     */
    private static void clearWithANSICodes() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}