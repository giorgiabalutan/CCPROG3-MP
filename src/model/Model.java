package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ModuleLayer.Controller;
import java.util.ArrayList;
import java.util.Random;

import model.creature.Siren;
import model.dungeon.Dungeon;
/**
 * Tracks the current {@link GameState} and Save Information.
 * <p>
 * Source of all information regarding the state of the game, the progress of the {@link Player}, and the details of the {@link Dungeon Dungeons}.
 */
public class Model implements Serializable
{
    //State Info
    /**
     * Indicates if the game is still active or not.
     */
    private boolean gameActive;
    /**
     * Tracks what part of the game the {@code Player} is currently in.
     */
    private GameState gameState;
    /**
     * Holds a single error message to notify the Player.
     */
    private String errorMessage;
    /**
     * Holds an array of error messages for more verbose notifications.
     */
    private String[] errorMessages;

    //Save Info
    /**
     * Tracks whether a New Game Plus is available.
     */
    private boolean ngPlusAvailable;
    /**
     * Tracks whether the player currently has save data.
     */
    private boolean playthroughExists;
    /**
     * Tracks the number of game overs the player has gotten.
     */
    private int gameOvers;
    /**
     * Tracks the {@link Idol Idols} that the player needs to save.
     */
    private ArrayList<Idol> idolList;

    //Save-Dungeon Info
    /**
     * Tracks the data related to the Player's Character.
     */
    private Player player;
    /**
     * Tracks the data related to the {@code Dungeons} that the player needs to challenge.
     */
    private Dungeon dungeon;
    /**
     * The data storing mechanism for saving and loading.
     */
    private DataStorage dataStorage;
    /**
     * Indicates if the Intro Cutscene is Playing.
     */
    private boolean isIntroPlaying;
    /**
     * Indicates which screen of the Intro Cutscene this is on.
     */
    private int introIndex = 0;
    /**
     * How many times each {@code Idol} has been saved.
     */
    private ArrayList<Idol> savedIdols;
    /**
     * How many times the {@link Siren} has been defeated.
     */
    private int timesSirenDefeated;
    /**
     * Indicates which items are available in the shop.
     */
    private ArrayList<Item> availableShopItems;
    /**
     * Indicates if the current dungeon has been won.
     */
    private boolean dungeonWon;
    /**
     * Indicates if the Final Fight has been won.
     */
    private boolean finalFightWon;
    //Constructors
    /**
     * Initializes the Model in the Main Menu
     */
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
        this.dungeon = new Dungeon(player);
        this.dataStorage = new DataStorage();
        this.isIntroPlaying = false;
        this.savedIdols = new ArrayList<Idol>();
        this.timesSirenDefeated = 0;
        this.availableShopItems = new ArrayList<Item>();
        setAvailableShopItems();
    }
    /**
     * Resets the Dungeon, when a New Game (plus) is Started.
     */
    public void resetDungeon()
    {
        this.dungeon = new Dungeon(player);
    }
    
    //Methods
    //Generate the list of 3 idols to save
    /**
     * Generates a random list of 3 idols for the player to save.
     * Currently rigs the first idol to save to be Chika Takami in Yasudaya Ryokan.
     */
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
            randIndex = r.nextInt(numList.size());
            idolList.add(new Idol(numList.remove(randIndex)));
        }
    }
    /**
     * Save all data using the {@link DataStorage}.
     */
    public void save()
    {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            
            dataStorage.setNGPlusAvailable(this.ngPlusAvailable);
            dataStorage.setPlaythroughExists(this.playthroughExists);
            dataStorage.setGameOvers(this.gameOvers);
            dataStorage.setIdolList(this.idolList);
            dataStorage.setPlayer(this.player);
            dataStorage.setDungeon(this.dungeon);
            dataStorage.setSavedIdols(this.savedIdols);
            dataStorage.setTimesSirenDefeated(this.timesSirenDefeated);
            
            oos.writeObject(dataStorage);
            System.out.println("Successfully saved!");
            
        }
        catch(Exception e)
        {
            System.out.println("WARNING: COULD NOT SAVE GAME!");
            e.printStackTrace();
        }
    }
    /**
     * Loads all data using the {@link DataStorage} from save.dat.
     */
    public void load()
    {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
            DataStorage ds = (DataStorage)ois.readObject();
            
            this.ngPlusAvailable = ds.getNGPlusAvailable();
            this.playthroughExists = ds.getPlaythroughExists();
            this.gameOvers = ds.getGameOvers();
            this.idolList = ds.getIdolList();
            this.player = ds.getPlayer();
            this.dungeon = ds.getDungeon();
            this.savedIdols = ds.getSavedIdols();
            this.timesSirenDefeated = ds.getTimesSirenDefeated();
        }
        catch(Exception e )
        {
            System.out.println("WARNING: COULD NOT LOAD SAVED GAME!");
        }
    }
    /**
     * Indicates whether there is a savefile.
     * 
     * @return {@code true} if there is a savefile.
     */
    public boolean hasSavedGame()
    {
        File savedFile = new File("save.dat");
        
        return savedFile.exists() && !savedFile.isDirectory();
    }
    //Updates Dungeon Status
    /**
     * Processes one cycle of user input.
     * <p>
     * Calls the {@link Dungeon#tick(char) Dungeon.tick} method to process the choice and changes there.
     * If the method returns true, then the dungeon is finished.
     * Passes on the return value back to {@link Controller} for it to process.
     * 
     * @param choice the input character representing the player's choice.
     * 
     * @return {@code true} if the {@code Dungeon} has been finished, {@code false} if not. This notifies the {@code Controller} if the dungeon is finished.
     */
    public boolean tickDungeon(char choice){
        if(this.dungeon.tick(choice))
        {
            setAvailableShopItems();
            return true;
        }
        return false;
    }

    //Getters and Setters
    /**
     * Checks if the game is currently active.
     * 
     * @return {@code true} if the game is still active, {@code false} if not.
     */
    public boolean isGameActive()
    {
        return this.gameActive;
    }
    /**
     * Sets the game to be inactive.
     * This will cause the {@link Controller#run() Controller.run} method's main loop to end, thus ending the program.
     */
    public void quit()
    {
        this.gameActive = false;
    }

    /**
     * Checks what part of the game the player is currently in.
     * 
     * @return the current {@code GameState}.
     */
    public GameState getGameState()
    {
        return this.gameState;
    }
    /**
     * Sets the player to be in a different part of the game.
     * 
     * @param gameState the gameState representing the part of the game to send the player to.
     */
    public void setGameState(GameState gameState)
    {
        this.gameState = gameState;
    }

    /**
     * Gets the error messages from the model to notify the player.
     * 
     * @return the error message.
     */
    public String getErrorMessage()
    {
        return this.errorMessage;
    }
    /**
     * Sets an error message to notify the player.
     * 
     * @param msg the error message.
     */
    public void setErrorMessage(String msg)
    {
        this.errorMessage = msg;
    }

    /**
     * Gets the array of error messages from the model to notify the player.
     * 
     * @return the array of error messages.
     */
    public String[] getErrorMessages()
    {
        return this.errorMessages;
    }
    /**
     * Sets a list of error messages to notify the player.
     * 
     * @param msg the list of error messages.
     */
    public void setErrorMessages(String[] msg)
    {
        this.errorMessages = msg;
    }

    /**
     * Checks if New Game Plus is an available option.
     * 
     * @return {@code true} if New Game Plus is available, {@code false} if not.
     */
    public boolean isNgPlusAvailable()
    {
        return this.ngPlusAvailable;
    }
    /**
     * Sets the availability of New Game Plus.
     * 
     * @param ngPlusAvailable a boolean indicating whether New Game Plus should be available or not.
     */
    public void setNGPlusAvailable(boolean ngPlusAvailable)
    {
        this.ngPlusAvailable = ngPlusAvailable;
    }

    /**
     * Checks if a playthrough currently exists.
     * 
     * @return {@code true} if a playthrough exists, {@code false} if not.
     */
    public boolean isPlaythroughExists()
    {
        return this.playthroughExists;
    }
    /**
     * Sets the flag on if a playthrough exists or not.
     * 
     * @param playthroughExists a boolean indicating whether a playthrough should exist or not.
     */
    public void setPlaythroughExists(boolean playthroughExists)
    {
        this.playthroughExists = playthroughExists;
    }

    /**
     * Gets how many times the player has reached a Game Over.
     * 
     * @return the number of game overs reached.
     */
    public int getGameOvers()
    {
        return this.gameOvers;
    }
    /**
     * Sets the number of game overs the player has reached.
     * 
     * @param gameOvers the number of game overs to set it to.
     */
    public void setGameOvers(int gameOvers)
    {
        this.gameOvers = gameOvers;
    }
    /**
     * Increments the number of game overs reached by one.
     * Called whenever the player gets a game over.
     */
    public void incGameOvers()
    {
        this.gameOvers++;
    }
    /**
     * Increment the times the Siren has been defeated by one.
     */
    public void incTimesSirenDefeated()
    {
        this.timesSirenDefeated++;
    }
    /**
     * Get the list of {@code Idols} for the player to save.
     * 
     * @return the list of {@code Idols}.
     */
    public ArrayList<Idol> getIdolList()
    {
        return this.idolList;
    }
    /**
     * Returns the reference to the {@code Player}.
     * 
     * @return the {@code Player} reference.
     */
    public Player getPlayer()
    {
        return this.player;
    }
    /**
     * Returns the reference to the {@code Dungeon}.
     * 
     * @return the {@code Dungeon} reference.
     */
    public Dungeon getDungeon()
    {
        return this.dungeon;
    }
    /**
     * Sets if the Intro should be playing or not.
     * 
     * @param isIntroPlaying boolean on if it should be playing.
     */
    public void setIsIntroPlaying(boolean isIntroPlaying)
    {
        this.isIntroPlaying = isIntroPlaying;
    }
    /**
     * Returns if the Intro should be playing or not.
     * 
     * @return if the Intro should be playing or not.
     */
    public boolean isIntroPlaying()
    {
        return this.isIntroPlaying;
    }
    /**
     * Sets which screen of the Intro should be currently playing.
     * 
     * @param introIndex which screen of the Intro should be currently playing.
     */
    public void setIntroIndex(int introIndex)
    {
        this.introIndex = introIndex;
    }
    /**
     * Sets if the dungeon is currently Won.
     * 
     * @param dungeonWon {@code true} if the dungeon is currently Won.
     */
    public void setDungeonWon(boolean dungeonWon)
    {
        this.dungeonWon = dungeonWon;
    }
    /**
     * Sets if the Final Fight is currently Won.
     * 
     * @param finalFightWon {@code true} if the Final Fight is currently Won.
     */
    public void setFinalFightWon(boolean finalFightWon)
    {
        this.finalFightWon = finalFightWon;
    }
    /**
     * Get which screen of the Intro should be currently playing.
     * 
     * @return which screen of the Intro should be currently playing.
     */
    public int getIntroIndex()
    {
        return this.introIndex;
    }
    /**
     * Returns how many times each Idol has been saved.
     * 
     * @return how many times each Idol has been saved.
     */
    public ArrayList<Idol> getSavedIdols()
    {
        return this.savedIdols;
    }
    /**
     * Returns how many times the Siren has been defeated.
     * 
     * @return how many times the Siren has been defeated.
     */
    public int getTimesSirenDefeated()
    {
        return this.timesSirenDefeated;
    }
    /**
     * Return if the Dungeon is currently won.
     * 
     * @return {@code true} if the Dungeon is currently won.
     */
    public boolean getDungeonWon()
    {
        return this.dungeonWon;
    }
    /**
     * Return if the Final Fight isi currently won.
     * 
     * @return {@code true} if the Final Fight isi currently won.
     */
    public boolean getFinalFightWon()
    {
        return this.finalFightWon;
    }
    /**
     * Sets the available items to buy in the shop.
     * <p>
     * Iterates through each Idol's item, adding them if the Item has not been bought, and the Idol has been saved.
     * Hides Tears of a Fallen Angel if it has been bought.
     */
    public void setAvailableShopItems()
    {
        this.availableShopItems = new ArrayList<>();
        this.availableShopItems.add(new Item(11));
        if(!this.player.boughtTears())
        {
            this.availableShopItems.add(new Item(10));
        }
        for (Idol idol : this.savedIdols)
        {
            Item newItem = new Item(idol.getIdolNumber());
            if(newItem.getItemName() != null)
            {
                boolean notAvailable = false;
                switch(newItem.getItemCode())
                {
                        case 11:
                            break;
                        case 1: //MikanMochi
                            notAvailable = this.player.hasMikanMochi();
                            break;
                        case 2: //AirShoes
                            notAvailable = this.player.hasAirShoes();
                            break;
                        case 3: //BatTamer
                            notAvailable = this.player.hasBatTamer();
                            break;
                        case 5: //ChocoMint
                            notAvailable = this.player.boughtChoco();
                            break;
                        case 6: //KurosawaMacha
                            notAvailable = this.player.hasKurosawaMacha();
                            break;
                        case 7: //ShovelUpgrade
                            notAvailable = this.player.hasShovelUpgrade();
                            break;
                        case 8: //Stewshine
                            notAvailable = this.player.hasStewShine();
                            break;
                }
                if(!notAvailable)
                {
                    availableShopItems.add(new Item(idol.getIdolNumber()));
                }
            }
        }
    }
    /**
     * Returns an ArrayList of the list of available Items in the Shop.
     * 
     * @return an ArrayList of the list of available Items in the Shop.
     */
    public ArrayList<Item> getAvailableShopItems()
    {
        return this.availableShopItems;
    }
    
}