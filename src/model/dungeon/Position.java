package model.dungeon;

import java.io.Serializable;
/**
 * Represents the current position of an entity on the map.
 * <p>
 * Each entity has its own <code>Position</code> that it modifies and checks against for interactions.
 */
public class Position implements Serializable{
    /**
     * The distance of the entity from the top edge of the map.
     */
    private int y;
    /**
     * The distance of the entity from the left edge of the map.
     */
    private int x;

    //Constructors
    /**
     * Constructs the Position at default coordinates (1, 1).
     */
    public Position()
    {
        this.x = 1;
        this.y = 1;
    }

    //Methods
    /**
     * Sets the Position at the given coordinates.
     * 
     * @param y the distance from the top edge of the map
     * @param x the distance from the left edge of the map
     */
    public void setPosition(int y, int x)
    {
        this.y = y;
        this.x = x;
    }

    /**
     * Shifts the position by the given offsets.
     * 
     * @param y how many spaces down to move the position
     * @param x how many spaces right to move the position
     */
    public void move(int y, int x)
    {
        this.y += y;
        this.x += x;
    }
    
    /**
     * Returns the current distance of the entity from the left edge of the map.
     * 
     * @return the distance from the left edge
     */
    public int getPosX()
    {
        return this.x;
    }

    /**
     * Returns the current distance of the entity from the top edge of the map.
     * 
     * @return the distance from the top edge
     */
    public int getPosY()
    {
        return this.y;
    }
}