package model;

import java.io.Serializable;
import model.dungeon.Floor;
/**
 * A single entry for a {@link Floor Floor's} Combat Log.
 * <p>
 * Contains the Message and the {@link CombatLogType}. 
 */
public class CombatLogEntry implements Serializable{
    /**
     * The message in the Log.
     */
    private String message;
    /**
     * The type of {@code CombatLogType}.
     */
    private CombatLogType type;

    /**
     * Constructor that sets the Log's Message and {@code CombatLogType}.
     * 
     * @param message the message in the Log.
     * @param type the type of message being logged.
     */
    public CombatLogEntry(String message, CombatLogType type)
    {
        this.message = message;
        this.type = type;
    }

    /**
     * Returns the message.
     * 
     * @return the message.
     */
    public String getMessage()
    {
        return this.message;
    }
    /**
     * Returns the {@code CombatLogType}.
     * @return the {@code CombatLogType}.
     */
    public CombatLogType getType()
    {
        return this.type;
    }
}
