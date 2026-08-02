package model;

import java.io.Serializable;
import java.util.ArrayList;
import model.creature.Siren;
import model.dungeon.Dungeon;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Saves the Player's Data
 * 
 * @author LENOVO
 */
public class DataStorage implements Serializable{
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
    /**
     * Tracks the data related to the Player's Character.
     */
    private Player player;
    /**
     * Tracks the data related to the {@code Dungeons} that the player needs to challenge.
     */
    private Dungeon dungeon;
    /**
     * How many times each {@code Idol} has been saved.
     */
    private ArrayList<Idol> savedIdols;
    /**
     * How many times the {@link Siren} has been defeated.
     */
    private int timesSirenDefeated;
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
     * Sets the flag on if a playthrough exists or not.
     * 
     * @param playthroughExists a boolean indicating whether a playthrough should exist or not.
     */
    public void setPlaythroughExists(boolean playthroughExists)
    {
        this.playthroughExists = playthroughExists;
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
     * Set the ArrayList of {@code Idols} the player needs to save.
     * 
     * @param idolList the ArrayList of {@code Idols} the player needs to save.
     */
    public void setIdolList(ArrayList<Idol> idolList)
    {
        this.idolList = idolList;
    }
    /**
     * Sets the Player Data.
     * 
     * @param player the data related to the Player.
     */
    public void setPlayer(Player player)
    {
        this.player = player;
    }
    /**
     * Sets the Dungeon Data.
     * 
     * @param dungeon the data related to the Dungeon.
     */
    public void setDungeon(Dungeon dungeon)
    {
        this.dungeon = dungeon;
    }
    /**
     * Set the data on how many times each Idol has been Saved.
     * 
     * @param savedIdols the data on how many times each Idol has been Saved.
     */
    public void setSavedIdols(ArrayList<Idol> savedIdols)
    {
        this.savedIdols = savedIdols;
    }
    /**
     * Sets how many times the Sired has been Defeated.
     * 
     * @param timesSirenDefeated how many times the Sired has been Defeated.
     */
    public void setTimesSirenDefeated(int timesSirenDefeated)
    {
        this.timesSirenDefeated = timesSirenDefeated;
    }
    /**
     * Checks if New Game Plus is an available option.
     * 
     * @return {@code true} if New Game Plus is available, {@code false} if not.
     */
    public boolean getNGPlusAvailable()
    {
        return this.ngPlusAvailable;
    }
    /**
     * Checks if a playthrough currently exists.
     * 
     * @return {@code true} if a playthrough exists, {@code false} if not.
     */
    public boolean getPlaythroughExists()
    {
        return this.playthroughExists;
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
     * Get the list of {@code Idols} for the player to save.
     * 
     * @return the list of {@code Idols}.
     */
    public ArrayList<Idol> getIdolList()
    {
        return this.idolList;
    }
    /**
     * Returns the Player Data.
     * 
     * @return the Player Data.
     */
    public Player getPlayer()
    {
        return this.player;
    }
    /**
     * Returns the Dungeon Data.
     * 
     * @return the Dungeon Data.
     */
    public Dungeon getDungeon()
    {
        return this.dungeon;
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
    
}
