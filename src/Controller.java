import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Manages all of the player's inputs and interactions.
 */
public class Controller implements ActionListener
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
            switch(model.getGameState())
            {
                case MAIN_MENU:
                    this.view.printMainMenu();
                    //waitForContinue();
                    break;  
                case OVERWORLD:
                    this.view.printOverworldOptions();
                    this.view.printChoicePrompt();
                    processOverworldInput();
                    break;
                case DUNGEON:
                    this.view.printDungeon();
                    this.view.printChoicePrompt();
                    processDungeonInput();
                    break;
                /** SHOP NOT YET NEEDED FOR PHASE 1
                case SHOP:

                    break;
                */
            }
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
                    //Start New Game
                    this.model.generateSaveList();
                    startIntroSequence();
                    this.model.setGameState(GameState.OVERWORLD);
                }
                break;
            case "C":
                if(this.model.isPlaythroughExists())
                {
                    //Continue Game
                }else{
                    this.model.setErrorMessage("No Save Found");
                }
                break;
            case "S":
                this.view.printStatus();
                waitForContinue();
                break;
            case "H":
                this.view.printManual();
                waitForContinue();
                break;
            case "Q":
                this.model.quit();
                break;
        }
    }
    //Process Overworld Input
    /**
     * Processes the player's inputs in the Overworld.
     * <p>
     * It can start a {@link Dungeon}, open the {@link Inventory}, manage held {@link Item Items}, or save and quit the game.
     */
    private void processOverworldInput()
    {
        char choice = input();
        switch(choice)
        {
            case '1':
            case '2':
            case '3':
                //Start dungeon
                int index = choice-'1';
                ArrayList<Idol> idolList = this.model.getIdolList();
                if(index >= 0 && index < idolList.size())
                {
                    this.model.getDungeon().generateDungeon(idolList.get(index));
                    this.model.setGameState(GameState.DUNGEON);
                }else{
                    this.model.setErrorMessage("Idol already saved");
                }
                break;
            case 'I':
                this.view.printInventory();
                waitForContinue();
                break;
            case ' ':
                String[] msg = this.model.getPlayer().useItem();
                this.model.setErrorMessages(msg);
                break;
            case '[':
                String message1 = this.model.getPlayer().previousItem();
                this.model.setErrorMessage(message1);
                break;
            case ']':
                String message2 = this.model.getPlayer().nextItem();
                this.model.setErrorMessage(message2);
                break;
            case 'S':
                //Save
                this.model.quit();
            default:
                this.model.setErrorMessage("Command not Found");
                break;
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
    private void processDungeonInput()
    {
        char choice = input();
        switch(choice){
            case ' ':
                String[] msg = this.model.getPlayer().useItem();
                this.model.setErrorMessages(msg);
                break;
            case '[':
                String message1 = this.model.getPlayer().previousItem();
                this.model.setErrorMessage(message1);
                break;
            case ']':
                String message2 = this.model.getPlayer().nextItem();
                this.model.setErrorMessage(message2);
                break;
        }
        if(this.model.tickDungeon(choice))
        {
            //Cutscene
            Idol savedIdol = this.model.getDungeon().getIdol();
            this.model.getIdolList().remove(savedIdol);
            
            this.view.finishedFloor(savedIdol);
            waitForContinue();
            this.model.setGameState(GameState.OVERWORLD);
        }
        if(this.model.getPlayer().isDead())
        {
            this.model.incGameOvers();
            this.view.deathMessage(this.model.getPlayer().getCauseOfDeath());
            waitForContinue();
            this.model.setGameState(GameState.MAIN_MENU);
            //To be expounded
        }
    }

    //Cutscenes
    /**
     * Calls the intro sequence in parts.
     * Waits for a player input before showing the next part.
     */
    private void startIntroSequence()
    {
        for(int i=0;i<3;i++)
        {
            this.view.printIntro(i);
            waitForContinue();
        }
    }

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
}