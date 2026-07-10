public abstract class Loot{
    private LootType type;
    private char displayChar;
    private String color;
    private Position pos;

    public Loot()
    {
        this.type = LootType.GOLD;
        this.displayChar = 'g';
        this.color = Color.yellow;
        pos = new Position();
    }

    public Loot(LootType type)
    {
        this.type = type;
        switch(type)
        {
            case LootType.GOLD:
                this.displayChar = 'g';
                this.color = Color.yellow;
                break;
            case LootType.TREASURE:
                this.displayChar = 'T';
                this.color = Color.green;
                break;
        }
        pos = new Position();
    }

    abstract boolean pickUpLoot(Floor floor);

    //Getters and Setters
    public void setLoot(LootType type)
    {
        this.type = type;
        switch(type)
        {
            case LootType.GOLD:
                this.displayChar = 'g';
                this.color = Color.yellow;
                break;
            case LootType.TREASURE:
                this.displayChar = 'T';
                this.color = Color.green;
                break;
        }
        pos = new Position();
    }

    public char getDisplayChar(){
        return this.displayChar;
    }

    public String getColor(){
        return this.color;
    }

    public Position getPosition()
    {
        return this.pos;
    }

    public void setPosition(int y, int x)
    {
        this.pos.setPosition(y, x);
    }
}