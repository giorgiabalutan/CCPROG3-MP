import java.util.ArrayList;
import java.util.Scanner;

public class Controller
{
    //Components
    private Model model;
    private View view;
    private Scanner sc;

    //Constructors
    public Controller(Model model, View view)
    {
        this.model = model;
        this.view = view;
        sc = new Scanner(System.in);
    }

    //Main Loop
    public void run()
    {
        while(this.model.isGameActive())
        {
            switch(model.getGameState())
            {
                case MAIN_MENU:
                    this.view.printMainMenu();
                    this.view.printChoicePrompt();
                    processMenuInput();
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
    }

    //Methods
    //Process Menu Input
    private void processMenuInput()
    {
        char choice = input();
        switch(choice)
        {
            case 'N':
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
            case 'C':
                if(this.model.isPlaythroughExists())
                {
                    //Continue Game
                }else{
                    this.model.setErrorMessage("No Save Found");
                }
                break;
            case 'S':
                this.view.printStatus();
                waitForContinue();
                break;
            case 'H':
                this.view.printManual();
                waitForContinue();
                break;
            case 'Q':
                this.model.quit();
                break;
            default:
                this.model.setErrorMessage("Command not Found");
                break;
        }
    }
    //Process Overworld Input
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
            this.view.deathMessage(this.model.getPlayer().getCauseOfDeath());
            waitForContinue();
        }
    }

    //Cutscenes
    private void startIntroSequence()
    {
        for(int i=0;i<3;i++)
        {
            this.view.printIntro(i);
            waitForContinue();
        }
    }

    //Generic Character Input
    private char input()
    {
        String line = sc.nextLine().toUpperCase();
        if(line.trim().isEmpty())
            return ' ';
        else
            return line.isEmpty() ? ' ' : line.charAt(0);
    }
    //Stalls until the user presses enter, drops whatever the line may have contained
    private void waitForContinue()
    {
        this.view.printContinuePrompt();
        sc.nextLine();
    }
}