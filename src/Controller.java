import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.Timer;
import model.GameState;
import model.Idol;
import model.Inventory;
import model.Item;
import model.Model;
import model.creature.Creature;
import model.dungeon.Dungeon;
import model.dungeon.Floor;
import model.loot.Loot;
import model.structure.Structure;
/**
 * Manages all of the player's inputs and interactions.
 */
public class Controller implements ActionListener, KeyListener
{
    //Components
    /**
     * The access to the data in {@link Model} for getting and setting information in it.
     */
    private Model model;
    /**
     * Access to {@link View} to call its print methods.
     */
    private View view;
    /**
     * A {@code Scanner} to take player inputs.
     */
    private Scanner sc;
    

    //Constructors
    /**
     * Constructs the Controller with access to the {@code Model} and {@code View} objects.
     * 
     * @param model the model of the program.
     * @param view the view object of the program.
     */
    public Controller(Model model, View view)
    {
        this.model = model;
        this.view = view;
        this.view.setActionListener(this);
        this.view.addKeyListener(this);
        sc = new Scanner(System.in);
    }

    //Main Loop
    /**
     * Runs the program, getting inputs and processing its outputs.
     * <p>
     * Handles the Print, Input, Process loops for Main Menu, Overworld, and Dungeon depending on the current {@link GameState}.
     * Ends processing once the game is no longer active from a {@link Model#quit() Model.quit} call.
     */
    public void run()
    {
        updateView();
        startAnimationTimer();
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch(model.getGameState())
        {
            case MAIN_MENU:
                processMenuInput(e.getActionCommand());
                break;
        }
        
        if (this.model.isGameActive())
            updateView();
        else
            this.view.dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent e)
    {
        // System.out.println(e);
        switch(model.getGameState())
        {
            case OVERWORLD:
                processOverworldInput(KeyEvent.getKeyText(e.getKeyCode()));
                break;
            case DUNGEON:
                processDungeonInput(KeyEvent.getKeyText(e.getKeyCode()));
                break;
            case SHOP:
                processShopInput(KeyEvent.getKeyText(e.getKeyCode()));
                break;
        }

        // System.out.println(model.getGameState());
        
        if(this.model.isGameActive())
            updateView();
        else
            this.view.dispose();
    }
    
    
    public void updateView()
    {
        switch(model.getGameState())
            {
                case MAIN_MENU:
                    this.view.showPanel("MAIN_MENU");
                    break;  
                case OVERWORLD:
                    this.view.showPanel("OVERWORLD");
                    this.view.repaintOverworld();
                    // System.out.println("TEST");
                    break;
                case DUNGEON:
                    this.view.showPanel("DUNGEON");
                    this.view.repaintDungeon();
                    break;
                case SHOP:
                    this.view.showPanel("SHOP");
                    break;
            }
        
    }
    
    //Methods
    //Process Menu Input
    /**
     * Processes player inputs in the Menu.
     * <p>
     * It can Start a New Game, New Game Plus, or Continue a Game.
     * It can also print the player's Status, a Help Manual, or Quit the game.
     */
    private void processMenuInput(String command)
    {
        switch(command)
        {
            case "N":
                if(this.model.isNgPlusAvailable())
                {
                    //Start New Game +
                }else{
                    //Start New Games
                    this.model.generateSaveList();
                    this.model.setIsIntroPlaying(true);
                    this.model.setGameState(GameState.OVERWORLD);
                    
                }
                break;
            case "C":
                if(this.model.isPlaythroughExists())
                {
                    this.model.load();
                    this.model.setGameState(GameState.OVERWORLD);
                }else{
                    this.model.setErrorMessage("No Save Found");
                }
                break;
            case "S":
                this.view.repaintMainMenu(command);
                break;
            case "H":
                this.view.repaintMainMenu(command);
                break;
            case "Q":
                this.model.quit();
                break;
            case "R":
                this.view.repaintMainMenu(command);
                break;
        }
    }
    //Process Overworld Input
    /**
     * Processes the player's inputs in the Overworld.
     * <p>
     * It can start a {@link Dungeon}, open the {@link Inventory}, manage held {@link Item Items}, or save and quit the game.
     */
    private void processOverworldInput(String command)
    {
        if(this.model.isIntroPlaying())
        {
            int currentIndex = this.model.getIntroIndex();
            if("Enter".equals(command) && this.model.getIntroIndex() < 6)
            {
                currentIndex++;
                this.model.setIntroIndex(currentIndex);
            }
            else if (this.model.getIntroIndex() == 6)
                this.model.setIsIntroPlaying(false);
        }
        else
        {
            switch(command)
            {
                case "1":
                case "2":
                case "3":
                    //Start dungeon
                    int index = Integer.parseInt(command) - 1;
                    ArrayList<Idol> idolList = this.model.getIdolList();
                    if(index >= 0 && index < idolList.size())
                    {
                        this.model.getDungeon().generateDungeon(idolList.get(index));
                        this.view.loadFloor();
                        this.model.setGameState(GameState.DUNGEON);
                    }else{
                        this.model.setErrorMessage("Idol already saved");
                    }
                    if(idolList.size() == 0)
                    {
                        this.model.getDungeon().generateFinalDungeon();
                        this.view.loadFloor();
                        this.model.setGameState(GameState.DUNGEON);
                    }
                    break;
                case "I":
                    this.model.getPlayer().setIsInventoryOpen(true);
                    break;
                case "R":
                    if(this.model.getPlayer().isInventoryOpen())
                        this.model.getPlayer().setIsInventoryOpen(false);
                    break;
                case "H":
                    this.model.setGameState(GameState.SHOP);
                    break;
                case "Space":
                    System.out.println("Command: " + command);
    //                String[] msg = this.model.getPlayer().useItem();
    //                this.model.setErrorMessages(msg);
                    break;
                case "Open Bracket":
                    System.out.println("Command: " + command);
    //                String message1 = this.model.getPlayer().previousItem();
    //                this.model.setErrorMessage(message1);
                    break;
                case "Close Bracket":
                    System.out.println("Command: " + command);
    //                String message2 = this.model.getPlayer().nextItem();
    //                this.model.setErrorMessage(message2);
                    break;
                case "S":
                    //Save
                    this.model.setPlaythroughExists(true);
                    this.model.save();
                    this.model.quit();
                default:
                    this.model.setErrorMessage("Command not Found");
                    break;
            }
        }
        
    }

    //Process Dungeon Input
    /**
     * Processes the player's input in the Dungeon
     * <p>
     * It can make the player manage and use {@code Items} or move around and interact with
     * {@link Structure Structures}, {@link Creature Creatures}, or {@link Loot} in the {@code Dungeon}.
     * Interactions are handled at the {@link Floor#tick(char) Floor.tick} call.
     * <p>
     * This also manages the output for either beating or dying in the {@code Dungeon} and sending the player back to the Overworld or Menu after.
     */
    private void processDungeonInput(String command)
    {
        char choice = command.charAt(0);
        // System.out.println(command);
        switch(command)
        {
            case "Space":
                choice = ' ';
                break;
            case "Open Bracket":
                choice = '[';
                break;
            case "Close Bracket":
                choice = ']';
                break;
        }
        switch(choice){
            case ' ':
                // String[] msg = this.model.getPlayer().useItem();
                // this.model.setErrorMessages(msg);
                break;
            case '[':
                // String message1 = this.model.getPlayer().previousItem();
                // this.model.setErrorMessage(message1);
                break;
            case ']':
                // String message2 = this.model.getPlayer().nextItem();
                // this.model.setErrorMessage(message2);
                break;
        }
        if(this.model.tickDungeon(choice))
        {
            //Cutscene
            Idol savedIdol = this.model.getDungeon().getIdol();
            this.model.getIdolList().remove(savedIdol);
            
            // this.view.finishedFloor(savedIdol);
            // waitForContinue();
            this.model.setGameState(GameState.OVERWORLD);
            updateView();
            // System.out.println(this.model.getGameState());
        }
        if(this.model.getPlayer().isDead())
        {
            this.model.incGameOvers();
            this.view.deathMessage(this.model.getPlayer().getCauseOfDeath());
            // waitForContinue();
            this.model.setGameState(GameState.MAIN_MENU);
            updateView();
            //To be expounded
        }
    }

    public void SkipToSiren()
    {
        processMenuInput("N");
        processOverworldInput("1");
        this.model.getDungeon().finishDungeonHacks();
        Idol savedIdol = this.model.getDungeon().getIdol();
        this.model.getIdolList().remove(savedIdol);
        this.model.setGameState(GameState.OVERWORLD);
        processOverworldInput("1");
        this.model.getDungeon().finishDungeonHacks();
        savedIdol = this.model.getDungeon().getIdol();
        this.model.getIdolList().remove(savedIdol);
        this.model.setGameState(GameState.OVERWORLD);
        processOverworldInput("1");
        this.model.getDungeon().finishDungeonHacks();
        savedIdol = this.model.getDungeon().getIdol();
        this.model.getIdolList().remove(savedIdol);
        this.model.setGameState(GameState.OVERWORLD);
        processOverworldInput("1");
        updateView();
    }

    private void startAnimationTimer()
    {
        Timer animationTimer = new Timer(300, e -> {
            this.view.tickAnimation();
        });
        animationTimer.start();
    }
    
    private void processShopInput(String command)
    {
        switch(command)
        {
            case "R":
                this.model.setGameState(GameState.OVERWORLD);
                break;
        }
    }
    //Cutscenes
    /**
     * Calls the intro sequence in parts.
     * Waits for a player input before showing the next part.
     */

    //Generic Character Input
    /**
     * Waits for the player to input into the console.
     * Only grabs the first character of the input.
     * If the Input is empty, it returns a space instead.
     * 
     * @return a character representing the player's choice.
     */
    private char input()
    {
        String line = sc.nextLine().toUpperCase();
        if(line.trim().isEmpty())
            return ' ';
        else
            return line.isEmpty() ? ' ' : line.charAt(0);
    }
    //Stalls until the user presses enter, drops whatever the line may have contained
    /**
     * Waits for the player to input anything into the console.
     * Drops whatever input was accepted.
     */
    private void waitForContinue()
    {
        this.view.printContinuePrompt();
        sc.nextLine();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    //For Testing Only
    public void skipIntroNew(){
        processMenuInput("N");
        // processOverworldInput(" ");
        processOverworldInput("1");
        updateView();
    }
}