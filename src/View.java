import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.*;
import model.Idol;
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
    
    private int frameWidth = 1100;
    private int frameHeight = 540;
    
    private CardLayout cardLayout;
    private Container contentPane;
    
    private MainMenuPanel mainMenuPanel;
    private OverworldPanel overworldPanel;
    private DungeonPanel dungeonPanel;
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
    
    public void setActionListener(ActionListener listener)
    {
        this.mainMenuPanel.setButtonActionListener(listener);
    }

    public void setKeyListener(KeyListener keyListener)
    {
        this.addKeyListener(keyListener);
    }
    
    // public void setKeyListener(KeyListener keyListener)
    // {
    //     this.overworldPanel.setKeyListener(keyListener);
    //     this.shopPanel.setKeyListener(keyListener);
    // }
    
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
    public void repaintOverworld()
    {
        this.overworldPanel.repaint();
    }
    public void repaintDungeon()
    {
        this.dungeonPanel.repaint();
    }
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
    public void setOverworldLailapsText(String lailapsText)
    {
        this.overworldPanel.setLailapsText(lailapsText);
    }
    
    public void setShopHanamaruText(String hanamaruText)
    {
        this.shopPanel.setHanamaruText(hanamaruText);
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
    
    
    public void loadFloor()
    {
        this.dungeonPanel.loadFloor();
    }

    public void tickAnimation()
    {
        this.dungeonPanel.tickAnimation();
    }
}