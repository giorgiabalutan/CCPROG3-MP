package model;

import java.io.Serializable;
import java.util.ArrayList;
import model.dungeon.Dungeon;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */
public class DataStorage implements Serializable{
    private boolean ngPlusAvailable;
    private boolean playthroughExists;
    private int gameOvers;
    private ArrayList<Idol> idolList;
    private Player player;
    private Dungeon dungeon;
    private ArrayList<Idol> savedIdols;
    private int timesSirenDefeated;
    
    public void setNGPlusAvailable(boolean ngPlusAvailable)
    {
        this.ngPlusAvailable = ngPlusAvailable;
    }
    
    public void setPlaythroughExists(boolean playthroughExists)
    {
        this.playthroughExists = playthroughExists;
    }
    
    public void setGameOvers(int gameOvers)
    {
        this.gameOvers = gameOvers;
    }
    
    public void setIdolList(ArrayList<Idol> idolList)
    {
        this.idolList = idolList;
    }
    
    public void setPlayer(Player player)
    {
        this.player = player;
    }
    
    public void setDungeon(Dungeon dungeon)
    {
        this.dungeon = dungeon;
    }
    
    public void setSavedIdols(ArrayList<Idol> savedIdols)
    {
        this.savedIdols = savedIdols;
    }
    
    public void setTimesSirenDefeated(int timesSirenDefeated)
    {
        this.timesSirenDefeated = timesSirenDefeated;
    }
    
    public boolean getNGPlusAvailable()
    {
        return this.ngPlusAvailable;
    }
    
    public boolean getPlaythroughExists()
    {
        return this.playthroughExists;
    }
    
    public int getGameOvers()
    {
        return this.gameOvers;
    }
    
    public ArrayList<Idol> getIdolList()
    {
        return this.idolList;
    }
    
    public Player getPlayer()
    {
        return this.player;
    }
    
    public Dungeon getDungeon()
    {
        return this.dungeon;
    }
    
    public ArrayList<Idol> getSavedIdols()
    {
        return this.savedIdols;
    }
    
    public int getTimesSirenDefeated()
    {
        return this.timesSirenDefeated;
    }
    
}
