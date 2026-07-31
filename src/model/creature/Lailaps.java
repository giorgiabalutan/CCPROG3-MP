package model.creature;
import model.Player;
import model.dungeon.Floor;
public class Lailaps extends Creature{
    /**
     * The amount of damage the bat deals on attack.
     */
    private double damage;

    /**
     * Constructs a bat depending on the current {@code Dungeon}.
     * <p>
     * Constructs a Bat Creature via the {@link Creature#Creature(CreatureType) Creature constructor}.
     * Its characteristics depend on the {@code order} of the {@code Dungeon}.
     * 
     * @param order the order of the dungeon currently being challenged.
     */
    public Lailaps()
    {
        super(CreatureType.LAILAPS);
        this.damage = 0;
        this.setIdle(false);
    }

    /**
     * Reduces the Bat's move cooldown by one. Once it reaches 0, it resets the {@code curCooldown} to the {@code moveInterval} and takes its turn.
     * If the {@link Player} is adjacent to the Bat on its turn, the Bat attacks the {@code Player},
     * else the bat moves to a random valid adjacent space.
     * The bat is also able to move diagonally on the 3rd {@code Dungeon}.
     * The bat also triggers any Structure Idle effects if it does not move during its turn.
     * 
     * @param floor the {@link Floor} the Bat is on, allows the Bat to search and move on it.
     * @return {@code true} if the Bat died as a result of its turn.
     */
    @Override
    public boolean tick(Floor floor)
    {
        return false;
    }

    /**
     * Drops a small amount of gold on death.
     * 
     * @param floor the {@code Floor} the Bat is on, allows the Bat to create {@link Gold} there.
     */
    @Override
    public void dropLoot(Floor floor){}
}
