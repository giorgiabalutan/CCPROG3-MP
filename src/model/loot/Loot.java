package model.loot;

import model.dungeon.Floor;
import model.dungeon.Position;
/**
 * Represents Loot on the floor during gameplay.
 * <p>
 * Contains the shared characteristics and behaviors between different types of loot.
 * Different types of loot share display and {@link Position} information, differing on effects on pick-up.
 */
public abstract class Loot{
    /**
     * The type of loot this is.
     */
    private LootType type;
    /**
     * The display character of the loot. For use in console view.
     */
    private char displayChar;
    /**
     * The color of loot display. For use in console view.
     */
    private String color;
    /**
     * The current position of the loot on the map.
     */
    private Position pos;

    //Constructors
    /**
     * Constructs Loot of the given type.
     * <p>
     * Sets the display character, {@link Color}.
     * Intended for use by a subclass via {@code super(...)}.
     * 
     * @param type decides the type of loot given.
     */
    public Loot(LootType type)
    {
        this.type = type;
        switch(type)
        {
            case GOLD:
                this.displayChar = 'g';
                break;
            case TREASURE:
                this.displayChar = 'T';
                break;
        }
        pos = new Position();
    }

    //Methods
    /**
     * Allows the {@link Player} to claim the loot.
     * <p>
     * Each subclass of loot must define what it gives on pick-up.
     * 
     * @param floor the {@link Floor} the loot is on, allows it to use the floor's {@code Random} seed and give loot to the {@code Player}.
     */
    abstract public void pickUpLoot(Floor floor);

    //Getters and Setters
    /**
     * Returns the character representing the loot.
     * 
     * @return character representation of the loot.
     */
    public char getDisplayChar(){
        return this.displayChar;
    }
    /**
     * Returns the {@code Color} of the loot.
     * 
     * @return {@code Color} of the loot.
     */
    public String getColor(){
        return this.color;
    }
    /**
     * Returns the current {@code Position} of the loot on the floor.
     * 
     * @return current {@code Position} of the loot.
     */
    public Position getPosition()
    {
        return this.pos;
    }
    /**
     * Sets the loot's {@code Position} to the given coordinates.
     * 
     * @param y distance from the top edge of the map.
     * @param x distance from the left edge of the map.
     */
    public void setPosition(int y, int x)
    {
        this.pos.setPosition(y, x);
    }
}