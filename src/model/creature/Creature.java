package model.creature;

import java.io.Serializable;
import model.Direction;
import model.dungeon.Floor;
import model.dungeon.Position;
import model.loot.Loot;
/**
 * Represents a creature that spawns during gameplay.
 * <p>
 * Contains the shared characteristics and behaviors between different creatures.
 * Creatures have these characteristics determined by their {@link CreatureType},
 * hp and maxhp, display character and {@link Color}, {@link Position}, and various flags.
 */
public abstract class Creature implements Serializable{
    /**
     * The maximum amount of hp this creature can have.
     */
    private double maxHp;
    /**
     * The current amount of hp this creature has. It is considered dead at 0 or less.
     */
    private double hp;
    /**
     * The type of creature this is.
     */
    private CreatureType type;
    /**
     * The display character of the creature. For use in console view.
     */
    private char displayChar;
    /**
     * The color of creature display. For use in console view.
     */
    private String color;
    private String creatureImageFilePath;
    private String name;
    private Direction direction;
    private Boolean idle;
    /**
     * The current position of the creature on the map.
     */
    private Position pos;
    /**
     * A flag that represents if a creature can fly or not.
     */
    private boolean flying;

    //Constructors
    /**
     * Constructs a creature of the given type.
     * <p>
     * Sets the display character, {@code Color}.
     * Intended for use by a subclass via {@code super(...)}.
     * 
     * @param type decides the initial characteristics of the creature
     */
    public Creature(CreatureType type)
    {
        this.type = type;
        switch(type)
        {
            case BAT:
                this.displayChar = 'b';
                this.maxHp = 1;
                this.flying = true;
                this.creatureImageFilePath = "assets/dungeonSprites/creatureSprites/Bat/Bat_idle_down.png";
                this.name = "Bat";
                break;
            case LAILAPS:
                this.displayChar = 'L';
                this.maxHp = 4;
                this.flying = false;
                this.name = "Lailaps";
                break;
            case SIREN:
                this.displayChar = 'B';
                this.maxHp = 1;
                this.flying = true;
                this.name = "Siren";
                break;
            case SKELETON:
                this.displayChar = 's';
                this.maxHp = 1;
                this.flying = false;
                this.name = "Skeleton";
                break;
            case ARROW:
                this.displayChar = '>';
                this.maxHp = 1;
                this.flying = true;
                this.name = "Arrow";
                break;
        }
        this.hp = maxHp;
        this.pos = new Position();
        this.direction = Direction.DOWN;
        this.idle = true;
    }

    //Methods
    /**
     * Triggers the creature to do something on its turn.
     * <p>
     * Each subclass of creature must define its own behavior on a turn.
     * 
     * @param floor the {@link Floor} the creature is on, allows the creature to access it.
     * 
     * @return {@code true} if the creature died as a result of its turn.
     */
    abstract public boolean tick(Floor floor);
    /**
     * Triggers the creature to drop its {@link Loot} on death.
     * <p>
     * Each subclass of creature must define what {@code Loot} it drops on death.
     * 
     * @param floor the {@code Floor} the creature is on, allows the creature to create {@code Loot} there.
     */
    abstract public void dropLoot(Floor floor);

    //Getters and Setters
    /**
     * Returns the character representing the creature.
     * 
     * @return character representation of the creature.
     */
    public char getDisplayChar(){
        return this.displayChar;
    }
    /**
     * Returns the {@code Color} of the creature.
     * 
     * @return {@code Color} of the creature.
     */
    public String getColor(){
        return this.color;
    }
    public String getImageFilePath(){
        return this.creatureImageFilePath;
    }
    /**
     * Returns the current {@code Position} of the creature on the floor.
     * 
     * @return current {@code Position} of the creature.
     */
    public Position getPosition()
    {
        return this.pos;
    }
    /**
     * Sets the creature's {@code Position} to the given coordinates.
     * 
     * @param y distance from the top edge of the map.
     * @param x distance from the left edge of the map.
     */
    public void setPosition(int y, int x)
    {
        this.pos.setPosition(y, x);
    }
    /**
     * Offsets the creature's current {@code Position} by the given values.
     * 
     * @param y how many spaces to shift down.
     * @param x how many spaces to shift right.
     */
    public void move(int y, int x)
    {
        this.pos.move(y, x);
    }
    /**
     * Reduces the creature's hp by the damage dealt to it.
     * 
     * @param damage the amount of damage being dealt to the creature.
     */
    public void damageCreature(double damage)
    {
        this.hp -= damage;
    }
    /**
     * Returns {@code true} if the creature should be dead (0 or less hp), and {@code false} if not.
     * 
     * @return {@code true} if the creature is dead, {@code false} if not.
     */
    public boolean isDead(){
        return this.hp <= 0;
    }
    /**
     * Returns the {@code CreatureType} of the creature.
     * 
     * @return {@code CreatureType} of the creature.
     */
    public CreatureType getCreatureType()
    {
        return this.type;
    }
    /**
     * Returns the flag representing if the creature can fly.
     * 
     * @return {@code true} if it can fly, {@code false} if not.
     */
    public boolean canFly()
    {
        return this.flying;
    }
    public String getName()
    {
        return this.name;
    }
    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }
    public Direction getDirection()
    {
        return this.direction;
    }
    public void setIdle(Boolean bool)
    {
        this.idle = bool;
    }
    public Boolean isIdle()
    {
        return this.idle;
    }

    public double getMaxHp()
    {
        return this.maxHp;
    }
    public double getHp()
    {
        return this.hp;
    }

    public void incMaxHp(double hp)
    {
        this.maxHp += hp;
        this.hp += hp;
    }
}