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

                    break;
                case SHOP:

                    break;
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
                
                this.model.setGameState(GameState.DUNGEON);
                break;
            case 'I':
                this.view.printInventory();
                waitForContinue();
                break;
            case 'S':
                //Save
                this.model.quit();
        }
    }

    //Cutscenes
    private void startIntroSequence()
    {
        for(int i=0;i<2;i++)
        {
            this.view.printIntro(i);
            waitForContinue();
        }
    }

    //Generic Character Input
    private char input()
    {
        String line = sc.nextLine().trim().toUpperCase();
        return line.isEmpty() ? ' ' : line.charAt(0);
    }
    //Stalls until the user presses enter, drops whatever the line may have contained
    private void waitForContinue()
    {
        this.view.printContinuePrompt();
        sc.nextLine();
    }
}