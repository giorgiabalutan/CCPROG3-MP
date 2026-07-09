import java.util.ArrayList;
import java.util.Random;

public class Model
{
    //State Info
    private boolean gameActive;
    private GameState gameState;
    private String errorMessage;

    //Save Info
    private boolean ngPlusAvailable;
    private boolean playthroughExists;
    private int gameOvers;
    private ArrayList<Idol> idolList;

    //Save-Dungeon Info
    private Player player;
    //private Dungeon dungeon;

    //Constructors

    public Model()
    {
        //Initial Program State
        this.gameActive = true;
        this.gameState = GameState.MAIN_MENU;
        this.errorMessage = "";

        //To be changed when save files are actually implemented
        this.ngPlusAvailable = false;
        this.playthroughExists = false;
        this.player = new Player();
        //this.dungeon = new Dungeon();
    }

    //Methods
    //Generate the list of 3 idols to save
    public void generateSaveList()
    {
        int i, nIdols = 8, chosenIdols = 3;
        int randIndex;
        ArrayList<Integer> numList = new ArrayList<Integer>();
        this.idolList = new ArrayList<Idol>();
        
        for (i = 1; i <= nIdols; i++)
        {
            numList.add(i);
        }

        Random r = new Random();
        for (i = 0; i < chosenIdols; i++)
        {
            if (i == 0) //Temporary force first idol
            {
                idolList.add(new Idol(numList.remove(0)));
            }else{
                randIndex = r.nextInt(numList.size());
                idolList.add(new Idol(numList.remove(randIndex)));
            }

        }
    }

    //Getters and Setters
    public boolean isGameActive()
    {
        return this.gameActive;
    }
    public void quit()
    {
        this.gameActive = false;
    }

    public GameState getGameState()
    {
        return this.gameState;
    }
    public void setGameState(GameState gameState)
    {
        this.gameState = gameState;
    }

    public String getErrorMessage()
    {
        return this.errorMessage;
    }
    public void setErrorMessage(String msg)
    {
        this.errorMessage = msg;
    }

    public boolean isNgPlusAvailable()
    {
        return this.ngPlusAvailable;
    }
    public void setNGPlusAvailable(boolean ngPlusAvailable)
    {
        this.ngPlusAvailable = ngPlusAvailable;
    }

    public boolean isPlaythroughExists()
    {
        return this.playthroughExists;
    }
    public void setPlaythroughExists(boolean playthroughExists)
    {
        this.playthroughExists = playthroughExists;
    }

    public int getGameOvers()
    {
        return this.gameOvers;
    }
    public void setGameOvers(int gameOvers)
    {
        this.gameOvers = gameOvers;
    }
    public void incGameOvers()
    {
        this.gameOvers++;
    }

    public ArrayList<Idol> getIdolList()
    {
        return this.idolList;
    }

    public Player getPlayer()
    {
        return this.player;
    }

    // public Dungeon getDungeon()
    // {
    //     return this.dungeon;
    // }
}