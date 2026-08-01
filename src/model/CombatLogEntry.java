package model;

import java.io.Serializable;

public class CombatLogEntry implements Serializable{
    private String message;
    private CombatLogType type;

    public CombatLogEntry(String message, CombatLogType type)
    {
        this.message = message;
        this.type = type;
    }

    public String getMessage()
    {
        return this.message;
    }

    public CombatLogType getType()
    {
        return this.type;
    }
}
