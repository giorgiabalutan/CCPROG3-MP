import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.*;
import model.Model;
import model.Player;
import model.dungeon.Dungeon;
import model.dungeon.Floor;
import model.dungeon.Tile;
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
    /**
     * How wide the frame should be.
     */
    private int frameWidth = 1100;
    /**
     * How tall the frame should be.
     */
    private int frameHeight = 540;
    /**
     * Layout type for the JFrame.
     */
    private CardLayout cardLayout;
    /**
     * Container to hold all the content.
     */
    private Container contentPane;
    /**
     * The Panel to show Main Menu options.
     */
    private MainMenuPanel mainMenuPanel;
    /**
     * The Panel to show Overworld options.
     */
    private OverworldPanel overworldPanel;
    /**
     * The Panel to show the current state of the Dungeon.
     */
    private DungeonPanel dungeonPanel;
    /**
     * The Panel to show Shop Options.
     */
    private ShopPanel shopPanel;
    
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
        dungeonPanel = new DungeonPanel(this.model, this.frameWidth, this.frameHeight);
        shopPanel = new ShopPanel(this.model, this.frameWidth, this.frameHeight);
        
        contentPane.add(mainMenuPanel, "MAIN_MENU");
        contentPane.add(overworldPanel, "OVERWORLD");
        contentPane.add(dungeonPanel, "DUNGEON");
        contentPane.add(shopPanel, "SHOP");
        
        this.setTitle("Yohane the Parhelion: The Siren in the Mirror World!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        // System.out.println("Constructor contentPane: " + System.identityHashCode(contentPane));

    }
    /**
     * Gives Main Menu access to the Action Listener.
     * 
     * @param listener the Action Listener
     */
    public void setActionListener(ActionListener listener)
    {
        this.mainMenuPanel.setButtonActionListener(listener);
    }
    /**
     * Adds a Key Listener to this JFrame.
     * 
     * @param keyListener the Key Listener.
     */
    public void setKeyListener(KeyListener keyListener)
    {
        this.addKeyListener(keyListener);
    }
    
    // public void setKeyListener(KeyListener keyListener)
    // {
    //     this.overworldPanel.setKeyListener(keyListener);
    //     this.shopPanel.setKeyListener(keyListener);
    // }
    /**
     * Switches which panel should be shown using the CardLayout layout.
     * 
     * @param panelName which panel should be shown.
     */
    public void showPanel(String panelName)
    {
        cardLayout.show(contentPane, panelName);
        // contentPane.revalidate();
        // SwingUtilities.invokeLater(() -> this.requestFocusInWindow());
        // this.requestFocusInWindow();
        // System.out.println(panelName);
        // System.out.println("showPanel contentPane: " + System.identityHashCode(contentPane));
        // for (Component c : contentPane.getComponents()) {
        //     System.out.println(c.getClass().getSimpleName() + " visible: " + c.isVisible());
        // }
    }
    /**
     * Repaints the Main Menu depending on the command from the Action Listener.
     * 
     * @param command indicating which button was pressed.
     */
    public void repaintMainMenu(String command)
    {
        switch(command)
        {
            case "H":
                this.mainMenuPanel.setIsManualShowing(true);
                break;
            case "S":
                this.mainMenuPanel.setIsStatsShowing(true);
                break;
            case "R":
                this.mainMenuPanel.setIsManualShowing(false);
                this.mainMenuPanel.setIsStatsShowing(false);
                break;
        }
        this.mainMenuPanel.repaint();
    }
    /**
     * A call to repaint the Overworld after something was changed.
     */
    public void repaintOverworld()
    {
        this.overworldPanel.repaint();
    }
    /**
     * A call to repaint the Dungeon after something was changed.
     */
    public void repaintDungeon()
    {
        this.dungeonPanel.repaint();
    }
    /**
     * A call to repaint the Shop after something was changed.
     */
    public void repaintShop()
    {
        this.shopPanel.repaint();
    }
    //Methods
    //Main Menu
    /**
     * Prints the graphics and choices for the Main Menu.
     * <p>
     * Prints the choice to check Status, How to Play, and Quit.
     * Also prints the choices to start a New Game, a New Game Plus, or to Continue depending on availability.
     */
    
    

    //Overworld
    /**
     * Sets the dialogue Lailaps should be saying.
     * 
     * @param lailapsText the dialogue Lailaps should be saying.
     */
    public void setOverworldLailapsText(String lailapsText)
    {
        this.overworldPanel.setLailapsText(lailapsText);
    }
    /**
     * Sets the dialogue Hanamaru should be saying.
     * 
     * @param hanamaruText the dialogue Hanamaru should be saying.
     */
    public void setShopHanamaruText(String hanamaruText)
    {
        this.shopPanel.setHanamaruText(hanamaruText);
    }
    //Dungeon
    /**
     * Displays the current {@link Floor} of the {@code Dungeon}.
     * <p>
     * Prints the {@code Dungeon} information, {@code Dungeon} information, and the current {@code Floor}.
     * Deprecated method used for Command Interface.
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

    //Generic
    
    /**
     * Loads the new floor onto {@link DungeonPanel}
     */
    public void loadFloor()
    {
        this.dungeonPanel.loadFloor();
    }
    /**
     * Ticks the animation one frame in {@code DungeonPanel}
     */
    public void tickAnimation()
    {
        this.dungeonPanel.tickAnimation();
    }
}