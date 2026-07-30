package model.structure;

import model.Player;
import model.creature.Creature;
import model.dungeon.Floor;
import model.dungeon.Position;
/**
 * Represents a structures on tiles during gameplay.
 * <p>
 * Contains the shared characteristics and behaviors between different structures.
 * Different types of structures share display and {@link Position} information, differing on effects on interaction.
 */
public abstract class Structure{
    /**
     * The type of structure this is.
     */
    private StructureType type;
    /**
     * The display character of the structure. For use in console view.
     */
    private char displayChar;
    /**
     * The color of structure display. For use in console view.
     */
    private String color;
    private String structureImageFilePath;
    /**
     * The current position of the structure on the map.
     */
    private Position pos;

    //Constructors
    /**
     * Constructs a structure of the given type.
     * <p>
     * Sets the display character, {@link Color}.
     * Intended for use by a subclass via {@code super(...)}.
     * 
     * @param type decides the type of structure made.
     */
    public Structure(StructureType type)
    {
        this.type = type;
        switch(type)
        {
            case SPAWN:
                this.displayChar = '.';
                this.structureImageFilePath = "assets/dungeonSprites/structureSprites/Passable.png";
                break;
            case EXIT:
                this.displayChar = 'E';
                this.structureImageFilePath = "assets/dungeonSprites/structureSprites/Exit.png";
                break;
            case BORDER:
                this.displayChar = '*';
                this.structureImageFilePath = "assets/dungeonSprites/structureSprites/Border.png";
                break;
            case WALL:
                this.displayChar = 'v';
                this.structureImageFilePath = "assets/dungeonSprites/structureSprites/Wall.png";
                break;
            case SPIKE:
                this.displayChar = 'x';
                this.structureImageFilePath = "assets/dungeonSprites/structureSprites/Spike.png";
                break;
            case WATER:
                this.displayChar = 'w';
                this.structureImageFilePath = "assets/dungeonSprites/structureSprites/Water.png";
                break;
            case HEAT:
                this.displayChar = 'h';
                this.structureImageFilePath = "assets/dungeonSprites/structureSprites/Heat.png";
                break;
        }
        this.pos = new Position();
    }

    //Methods
    /**
     * Processes what happens if the {@link Player} interacts with the structure.
     * <p>
     * Each subclass of structure must define their own interaction.
     * 
     * @param floor the {@link Floor} the structure is on.
     * 
     * @return {@code true} if the structure is destroyed as a result of the interaction,
     * this is read by the {@link Floor#interact(int, int, int, int) Floor interact} method which processes the destruction.
     */
    abstract public boolean interact(Floor floor);
    /**
     * Checks if the structure would block the {@code Player} from moving.
     * <p>
     * Each subclass of structure must define this and their conditions to allow or disallow it.
     * 
     * @param floor the {@code Floor} the structure is on.
     * 
     * @return {@code true} if the structure blocks the player from moving.
     */
    abstract public boolean isBlocking(Floor floor);
    /**
     * Processes what happens if the {@code Player} idles on top of the structure.
     * <p>
     * Each subclass of structure must define what occurs.
     * 
     * @param floor the {@code Floor} the structure is on.
     * 
     * @return {@code true} if the structure is destroyed as a result of the idling,
     * this is read by the {@link Floor#idle(int, int) Floor idle} method which processes the destruction.
     */
    abstract public boolean idle(Floor floor);
    //Creature Methods
    /**
     * Processes what happens if a {@link Creature} interacts with the structure.
     * <p>
     * Each subclass of structure must define their own interaction.
     * 
     * @param floor the {@code Floor} the structure is on.
     * @param creature the {@code Creature} interacting with the structure.
     * 
     * @return {@code true} if the structure is destroyed as a result of the interaction.
     */
    abstract public boolean creatureInteract(Floor floor, Creature creature);
    /**
     * Checks if the structure would block a {@code Creature} from moving.
     * <p>
     * Each subclass of structure must define this and their conditions to allow or disallow it.
     * 
     * @param floor the {@code Floor} the structure is on.
     * @param creature the {@code Creature} attempting to move on the structure.
     * 
     * @return {@code true} if the structure blocks the creature from moving.
     */
    abstract public boolean creatureIsBlocking(Floor floor, Creature creature);
    /**
     * Processes what happens if a {@code Creature} idles on top of the structure.
     * <p>
     * Each subclass of structure must define what occurs.
     * 
     * @param floor the {@code Floor} the structure is on.
     * @param creature the {@code Creature} idling on the structure.
     * 
     * @return {@code true} if the structure is destroyed as a result of the idling.
     */
    abstract public boolean creatureIdle(Floor floor, Creature creature);

    //Getters and Setters
    /**
     * Returns the character representing the structure.
     * 
     * @return character representation of the structure.
     */
    public char getDisplayChar(){
        return this.displayChar;
    }
    /**
     * Returns the {@code Color} of the structure.
     * 
     * @return {@code Color} of the structure.
     */
    public String getColor(){
        return this.color;
    }
    public String getImageFilePath(){
        return this.structureImageFilePath;
    }
    /**
     * Returns the current {@code Position} of the structure on the floor.
     * 
     * @return current {@code Position} of the structure.
     */
    public Position getPosition()
    {
        return this.pos;
    }
    /**
     * Sets the structure's {@code Position} to the given coordinates.
     * 
     * @param y distance from the top edge of the map.
     * @param x distance from the left edge of the map.
     */
    public void setPosition(int y, int x)
    {
        this.pos.setPosition(y, x);
    }
}