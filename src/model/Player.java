package model;

import java.io.Serializable;
import model.dungeon.Floor;
import model.dungeon.Position;
/**
 * Tracks the data related to the Player's character.
 * <p>
 * Tracks and manages their hp, gold, {@link Inventory}, {@link Position}, and cause of death.
 */
public class Player implements Serializable
{
    /**
     * The current hp of the player.
     */
    private double currHP;
    /**
     * The highest amount of hp the player can have.
     */
    private double totalHP;
    /**
     * The lifetime amount of gold the player has obtained.
     */
    private int totalGold;
    /**
     * The amount of the gold that is currently spent.
     */
    private int goldSpent;
    /**
     * The {@link Item} the player is currently holding.
     */
    private Item itemOnHand;
    /**
     * The {@code Inventory} of the player, tracking the whole list of {@code Items} they have.
     */
    private Inventory inventory;
    /**
     * The amount of damage the player can deal with an attack.
     */
    private double attack;
    /**
     * The current {@code Position} of the player on the map of the {@link Floor}.
     */
    private Position pos;
    /**
     * Stores the name of the entity that caused the player to die.
     */
    private String causeOfDeath;
    /**
     * Indicates if the Player is in the inventory screen.
     */
    private boolean isInventoryOpen;
    /**
     * Indicates the {@link Direction} the player is currently facing.
     */
    private Direction direction;
    /**
     * Indicates if the player is currently idle.
     */
    private Boolean idle;
    /**
     * Indicates if the player has bought the Shovel Upgrade.
     */
    private boolean hasShovelUpgrade;
    /**
     * Indicates if the player has bought the Bat Tamer.
     */
    private boolean hasBatTamer;
    /**
     * Indicates if the player has bought the Air Shoes.
     */
    private boolean hasAirShoes;
    /**
     * Indicates if the player has bought the StewShine.
     */
    private boolean hasStewShine;
    /**
     * Indicates if the player has bought the Mikan Mochi.
     */
    private boolean hasMikanMochi;
    /**
     * Indicates if the player has bought the Kurosawa Macha.
     */
    private boolean hasKurosawaMacha;
    /**
     * Indiciates if the player has bought the Tears of a Fallen Angel.
     */
    private boolean boughtTears;
    /**
     * Indicates if the player has bought the Choco Mint Ice Cream.
     */
    private boolean boughtChoco;

    //Constructor
    /**
     * Constructs the player class.
     * <p>
     * Sets their initial statistics and initialzies their {@code Inventory} and {@code Position}.
     */
    public Player()
    {
        currHP = 3;
        totalHP = 3;
        totalGold = 0;
        goldSpent = 0;
        attack = 1;
        causeOfDeath = "";
        inventory = new Inventory();
        itemOnHand = inventory.getItems().get(0);
        pos = new Position();
        isInventoryOpen = false;
        this.direction = Direction.DOWN;
        this.idle = true;
    }
    /**
     * Reseets the Player's info on a New Game (plus).
     */
    public void resetPlayer()
    {
        currHP = 3;
        totalHP = 3;
        goldSpent = 0;
        attack = 1;
        causeOfDeath = "";
        inventory = new Inventory();
        itemOnHand = inventory.getItems().get(0);
        isInventoryOpen = false;
        this.direction = Direction.DOWN;
        this.idle = true;
        this.hasAirShoes = false;
        this.hasBatTamer = false;
        this.hasAirShoes = false;
        this.hasStewShine = false;
        this.hasMikanMochi = false;
        this.hasKurosawaMacha = false;
        this.boughtTears = false;
        this.boughtChoco = false;
    }

    //Methods
    /**
     * Deals damage to the Player.
     * 
     * @param dmg the amount of damage to deal to the Player's current hp.
     */
    public void damage(double dmg){
        currHP -= dmg;
        if(currHP <= 0 && itemOnHand != null && itemOnHand.getItemCode() == 5)
        {
            this.currHP = this.totalHP;
            itemOnHand.reduceQuantity();
            if (this.itemOnHand.getQuantity() == 0)
                updateItemOnHand();
        }
    }

    /**
     * Uses the {@code Item} the {@code Player} is currently holding.
     * Returns an array of error messages depending on why the {@code Item} could not be used.
     * If there was no error, an empty String array is returned causing no errors to print.
     * 
     * @return array of error messages stating why the {@code Item} could not be used.
     */
    public String[] useItem()
    {
        String[] messages = {};
        if(this.itemOnHand != null)
        {
            int itemCode = this.itemOnHand.getItemCode();
            switch(itemCode)
            {
                case 1: 
                    this.totalHP++;
                    break;
                // case 2:
                    
                //     break;
                // case 3:
                    
                //     break;
                case 5:
                    // this.currHP = this.totalHP;
                    // itemOnHand.reduceQuantity();
                    // if (this.itemOnHand.getQuantity() == 0)
                    //     updateItemOnHand();
                    break;
                case 6:
                    // this.totalHP++;
                    break;
                // case 7:
                    
                //     break;
                case 8:
                    // this.totalHP++;
                    break;
                case 10:
                    messages = heal();
                    break;
                case 11:
                    messages = heal();
                    break;
            }
        }
        return messages;
    }
    /**
     * Attempts to use the {@code Player}'s held {@code Item} to heal.
     * On a success, it reduces the quantity of the {@code Item}.
     * If the quantity drops to 0, the held item gets updated.
     * On a fail, it returns an array of error messages stating why it failed.
     * If there was no error, an empty String array is returned causing no errors to print.
     * 
     * @return array of error messages stating why the {@code Item} could not be used.
     */
    public String[] heal()
    {
        String[] messages;
        if (this.currHP < this.totalHP && this.itemOnHand.getQuantity() > 0)
        {
            messages = new String[]{"You healed for 0.5 using " + this.itemOnHand.getItemName() + "."};
            this.currHP += 0.5;
            itemOnHand.reduceQuantity();

            if (this.itemOnHand.getQuantity() == 0)
                updateItemOnHand();
            
        }
        else if (this.currHP == this.totalHP && this.itemOnHand.getQuantity() > 0)
        {
            messages = new String[]{
                "Lailaps: You're still fully\nhealed Yohane-chan\nStop being nervous eh."
            };
        }
        else
        {
            messages = new String[]{"Lailaps: You've used up all your\nitems Yohane-chan."};
        }
        return messages;
    }

    /**
     * Updates which {@code Item} the {@code Player} is holding.
     * Removes the current {@code Item} that reached quantity 0.
     * If there is another {@code Item} available in the inventory,
     * it switches the held {@code Item} to the first one.
     */
    //if one item is used up, remove that item and put the other one
    public void updateItemOnHand()
    {
        System.out.println("TEST");
        this.inventory.removeUsedItem(this.itemOnHand);
        if (this.inventory.getItemCount() > 0)
            this.itemOnHand = inventory.getItems().get(0);
        else
            this.itemOnHand = null;
    }
    /**
     * Switches the held {@code Item} to the previous one in the {@code Inventory}.
     * Wraps back around to the last {@code Item} on the list if the held {@code Item} is the first.
     * Returns an error message if there are no {@code Items} left.
     * 
     * @return an error message stating there are no {@code Items} left.
     */
    public String previousItem()
    {
        int currentIndex = this.inventory.getItems().indexOf(this.itemOnHand);
        String message = "";
        if (this.inventory.getItemCount() > 0)
        {
            if(currentIndex > 0)
            {
                this.itemOnHand = inventory.getItems().get(currentIndex - 1);
            }else{
                this.itemOnHand = inventory.getItems().get(this.inventory.getItemCount()-1);
            }
        }
        else if (this.inventory.getItemCount() == 0)
            message = "Lailaps: You've used up all your\nitems Yohane-chan.";
        return message;
    }
    /**
     * Adds an item obtained through looting or shopping to the inventory.
     * 
     * @param item the item obtained through looting or shopping.
     */
    public void pickUpItem(Item item)
    {
        this.inventory.addItem(item);
    }
    /**
     * Switches the held {@code Item} to the next one in the {@code Inventory}.
     * Wraps back around to the first {@code Item} on the list if the held {@code Item} is the last.
     * Returns an error message if there are no {@code Items} left.
     * 
     * @return an error message stating there are no {@code Items} left.
     */
    public String nextItem()
    {
        int currentIndex = this.inventory.getItems().indexOf(this.itemOnHand);
        String message = "";
        if (this.inventory.getItemCount() > 0)
        {
            if (currentIndex + 1 < this.inventory.getItemCount()) {
                this.itemOnHand = inventory.getItems().get(currentIndex + 1);
            }else{
                this.itemOnHand = inventory.getItems().get(0);
            }
        }
        else if (this.inventory.getItemCount() == 0)
            message = "Lailaps: You've used up all your\nitems Yohane-chan.";
        return message;
    }

    //Setter Methods
    /**
     * Adds a certain amount of gold to the player character.
     * 
     * @param gold the amount of gold to add.
     */
    public void gainGold(int gold)
    {
        this.totalGold += gold;
    }
    /**
     * Increments the player's spent gold.
     * 
     * @param gold how much gold is spent.
     */
    public void spendGold(int gold)
    {
        this.goldSpent += gold;
    }
    /**
     * Sets the player character's current position to the given coordinates.
     * 
     * @param y distance from the top edge of the map.
     * @param x distance from the left edge of the map.
     */
    public void setPosition(int y, int x)
    {
        this.pos.setPosition(y, x);
    }
    /**
     * Moves the player character's current position by the given offsets.
     * 
     * @param y how many spaces down to move.
     * @param x how many spaces right to move.
     */
    public void move(int y, int x)
    {
        this.pos.move(y, x);
    }
    /**
     * Set the cause of the player character's death.
     * 
     * @param cause the name of the entity that caused the player character to die.
     */
    public void setCauseOfDeath(String cause)
    {
        this.causeOfDeath = cause;
    }

    //Getter methods
    /**
     * Returns how much of the player character's hp remains.
     * 
     * @return the character's current hp.
     */
    public double getCurrHP()
    {
        return this.currHP;
    }
    /**
     * Returns the max possible hp the player character can have.
     * 
     * @return the character's total hp.
     */
    public double getTotalHP()
    {
        return this.totalHP;
    }
    /**
     * Checks if the player character is dead.
     * 
     * @return {@code true} if the current hp is at 0 or less, {@code false} otherwise.
     */
    public boolean isDead()
    {
        return this.currHP <= 0;
    }
    /**
     * Returns the total amount of gold the player has ever obtained.
     * 
     * @return the total gold obtained.
     */
    public int getTotalGold()
    {
        return this.totalGold;
    }
    /**
     * Returns the amount of gold the player has currently spent.
     * 
     * @return the gold spent.
     */
    public int getGoldSpent()
    {
        return this.goldSpent;
    }
    /**
     * Returns the player character's {@code Inventory}.
     * 
     * @return the player character's {@code Inventory}.
     */
    public Inventory getInventory()
    {
        return this.inventory;
    }
    /**
     * Returns the {@code Item} that the player is currently holding.
     * 
     * @return the item on Hand.
     */
    public Item getItemOnHand()
    {
        return this.itemOnHand;
    }
    /**
     * Get how much damage the player character would deal.
     * 
     * @return the player character's attack stat.
     */
    public double getAttack(){
        return this.attack;
    }
    /**
     * Get the current {@code Position} of the player on the map.
     * 
     * @return the player character's {@code Position}.
     */
    public Position getPosition()
    {
        return this.pos;
    }
    /**
     * Get the cause of the player character's death.
     * 
     * @return the name of the entity that caused the player character to die.
     */
    public String getCauseOfDeath()
    {
        return this.causeOfDeath;
    }
    /**
     * Indicates if the player has their inventory open.
     * 
     * @return {@code true} if the player has their inventory open.
     */
    public boolean isInventoryOpen()
    {
        return this.isInventoryOpen;
    }
    /**
     * Sets if the inventory should be open.
     * 
     * @param isInventoryOpen if the inventory should be open.
     */
    public void setIsInventoryOpen(boolean isInventoryOpen)
    {
        this.isInventoryOpen = isInventoryOpen;
    }
    /**
     * Set the direction the player should be facing.
     * 
     * @param direction the direction the player should be facing.
     */
    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }
    /**
     * Get the direction the player is facing.
     * 
     * @return the direction the player is facing.
     */
    public Direction getDirection()
    {
        return this.direction;
    }
    /**
     * Sets if the player should be idle.
     * 
     * @param bool if the player should be idle.
     */
    public void setIdle(boolean bool)
    {
        this.idle = bool;
    }
    /**
     * Return if the player is currently idle.
     * 
     * @return {@code true} if the player is currently idle.
     */
    public Boolean isIdle()
    {
        return this.idle;
    }
    /**
     * Sets if the player should have the Shovel Upgrade.
     * 
     * @param bool if the player should have the Shovel Upgrade.
     */
    public void setShovelUpgrade(Boolean bool)
    {
        this.hasShovelUpgrade = bool;
    }
    /**
     * Sets if the player should have the Bat Tamer.
     * 
     * @param bool if the player should have the Bat Tamer.
     */
    public void setBatTamer(Boolean bool)
    {
        this.hasBatTamer = bool;
    }
    /**
     * Sets if the player should have the Air Shoes.
     * 
     * @param bool if the player should have the Air Shoes.
     */
    public void setAirShoes(Boolean bool)
    {
        this.hasAirShoes = bool;
    }
    /**
     * Sets if the player should have the StewShine.
     * 
     * @param bool if the player should have the StewShine.
     */
    public void setStewShine(Boolean bool)
    {
        this.hasStewShine = bool;
    }
    /**
     * Sets if the player should have the Mikan Mochi.
     * 
     * @param bool if the player should have the Mikan Mochi.
     */
    public void setMikanMochi(Boolean bool)
    {
        this.hasMikanMochi = bool;
    }
    /**
     * Sets if the player should have the Kurosawa Macha.
     * 
     * @param bool if the player should have the Kurosawa Macha.
     */
    public void setKurosawaMacha(Boolean bool)
    {
        this.hasKurosawaMacha = bool;
    }
    /**
     * Sets if the player should have bought the Tears of a Fallen Angel.
     * 
     * @param bool if the player should have bought the Tears of a Fallen Angel.
     */
    public void setTears(Boolean bool)
    {
        this.boughtTears = bool;
    }
    /**
     * Sets if the player should have bought the Choco Mint Ice Cream.
     * 
     * @param bool if the player should have bought the Choco Mint Ice Cream.
     */
    public void setChoco(Boolean bool)
    {
        this.boughtChoco = bool;
    }
    /**
     * Returns if the player has bought the Shovel Upgrade.
     * 
     * @return {@code true} if the player has bought the Shovel Upgrade.
     */
    public boolean hasShovelUpgrade()
    {
        return this.hasShovelUpgrade;
    }
    /**
     * Returns if the player has bought the Bat Tamer.
     * 
     * @return {@code true} if the player has bought the Bat Tamer.
     */
    public boolean hasBatTamer()
    {
        return this.hasBatTamer;
    }
    /**
     * Returns if the player has bought the Air Shoes.
     * 
     * @return {@code true} if the player has bought the Air Shoes.
     */
    public boolean hasAirShoes()
    {
        return this.hasAirShoes;
    }
    /**
     * Returns if the player has bought the StewShine.
     * 
     * @return {@code true} if the player has bought the StewShine.
     */
    public boolean hasStewShine()
    {
        return this.hasStewShine;
    }
    /**
     * Returns if the player has bought the Mikan Mochi.
     * 
     * @return {@code true} if the player has bought the Mikan Mochi.
     */
    public boolean hasMikanMochi()
    {
        return this.hasMikanMochi;
    }
    /**
     * Returns if the player has bought the Kurosawa Macha.
     * 
     * @return {@code true} if the player has bought the Kurosawa Macha.
     */
    public boolean hasKurosawaMacha()
    {
        return this.hasKurosawaMacha;
    }
    /**
     * Returns if the player has bought the Tears of a Fallen Angel.
     * 
     * @return {@code true} if the player has bought the Tears of a Fallen Angel.
     */
    public boolean boughtTears()
    {
        return this.boughtTears;
    }
    /**
     * Returns if the player has bought the Choco Mint Ice Cream.
     * 
     * @return {@code true} if the player has bought the Choco Mint Ice Cream.
     */
    public boolean boughtChoco()
    {
        return this.boughtChoco;
    }
    /**
     * Increments the player's max hp by the amount given. Also increases the current hp by the same amount.
     * 
     * @param hp how much to increment by.
     */
    public void incMaxHp(int hp)
    {
        this.totalHP += hp;
        this.currHP += hp;
    }
}