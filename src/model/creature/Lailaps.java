package model.creature;
import model.Player;
import model.dungeon.Floor;
/**
 * Represents the companion creature Lailaps.
 * <p>
 * Extends the {@link Creature} class.
 */
public class Lailaps extends Creature{
    /**
     * The amount of damage Lailaps deals on attack.
     */
    private double damage;

    /**
     * Constructs a Lailaps depending on the current {@code Dungeon}.
     * <p>
     * Constructs a Lailaps Creature via the {@link Creature#Creature(CreatureType) Creature constructor}.
     */
    public Lailaps()
    {
        super(CreatureType.LAILAPS);
        this.damage = 0;
        this.setIdle(false);
    }

    /**
     * Does nothing on tick, it moves with the {@link Player}.
     * 
     * @param floor the {@link Floor} the Lailaps is on, allows the Lailaps to search and move on it.
     * @return {@code true} if the Lailaps died as a result of its turn.
     */
    @Override
    public boolean tick(Floor floor)
    {
        return false;
    }

    /**
     * Drops nothing on death.
     * 
     * @param floor the {@code Floor} the Lailaps is on.
     */
    @Override
    public void dropLoot(Floor floor){}
}
