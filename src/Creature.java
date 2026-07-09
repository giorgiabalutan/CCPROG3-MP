public abstract class Creature{
    private double maxHp;
    private double hp;
    private CreatureType type;
    private char displayChar;
    private Position pos;
    private boolean flying;

    public Creature()
    {
        this.type = CreatureType.BAT;
        this.displayChar = 'b';
        this.maxHp = 1;
        this.hp = maxHp;
        this.pos = new Position();
        this.flying = true;
    }

    public Creature(CreatureType type)
    {
        this.type = type;
        switch(type)
        {
            case CreatureType.BAT:
                this.displayChar = 'b';
                this.maxHp = 1;
                this.flying = true;
                break;
        }
        this.hp = maxHp;
        this.pos = new Position();
    }

    //Methods
    abstract public boolean tick(Floor floor);
    abstract public boolean dropLoot(Floor floor);

    //Getters and Setters
    public void setCreature(CreatureType type)
    {
        this.type = type;
        switch(type)
        {
            case CreatureType.BAT:
                this.displayChar = 'b';
                break;
        }
        pos = new Position();
    }

    public char getDisplayChar(){
        return this.displayChar;
    }

    public Position getPosition()
    {
        return this.pos;
    }

    public void setPosition(int y, int x)
    {
        this.pos.setPosition(y, x);
    }

    public void move(int y, int x)
    {
        this.pos.move(y, x);
    }

    public void damageCreature(double damage)
    {
        this.hp -= damage;
        if(this.isDead())
        {
            //Death Sequence
        }
    }

    public boolean isDead(){
        return this.hp <= 0;
    }

    public CreatureType getCreatureType()
    {
        return this.type;
    }

    public boolean canFly()
    {
        return this.flying;
    }
}