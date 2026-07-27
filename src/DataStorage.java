
import java.io.Serializable;
import java.util.ArrayList;

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
    
}
