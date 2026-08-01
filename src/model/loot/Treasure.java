package model.loot;

import java.util.Random;
import model.CombatLogType;
import model.Item;
import model.Player;
import model.dungeon.DungeonModifier;
import model.dungeon.Floor;
/**
 * Represents the random floor loot Treasure.
 * <p>
 * Extends the {@link Loot} class.
 * Treasure gives either an {@link Item} or a random amount of gold.
 */

public class Treasure extends Loot
{
    /**
     * Constructs a Treasure loot via the {@link Loot#Loot(LootType) Loot constructor}.
     */
    public Treasure()
    {
        super(LootType.TREASURE);
    }
    /**
     * Gives either a random {@code Item} or a random amount of gold (10-100) to the {@link Player} at equal odds.
     * 
     * @param floor the {@code Floor} the Treasure is on, allows it to use the floor's {@code Random} seed to generate loot.
     */
    @Override
    public void pickUpLoot(Floor floor)
    {
        Random rand = floor.getRand();
        int table = rand.nextInt(2);
        switch(table)
        {
            case 0:
                int amt = rand.nextInt(91) + 10;
                if(floor.getDungeonModifiers().contains(DungeonModifier.GOLD_TAX))
                {
                    amt /= 2;
                }
                floor.getPlayer().gainGold(amt);
                floor.addCombatLog("Yohane found the Treasure and got " + amt + " Gold!",CombatLogType.LOOT_GAIN);
                break;
            case 1:
                Item itm = new Item(11);
                floor.getPlayer().pickUpItem(itm);
                floor.addCombatLog("Yohane found the Treasure and got " + itm.getItemName() + "!",CombatLogType.LOOT_GAIN);
                break;
        }
    }
}