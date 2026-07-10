public abstract class Structure{
    private StructureType type;
    private char displayChar;
    private String color;
    private Position pos;

    public Structure()
    {
        this.type = StructureType.WALL;
        this.displayChar = 'v';
        this.color = Color.reset;
        this.pos = new Position();
    }

    public Structure(StructureType type)
    {
        this.type = type;
        switch(type)
        {
            case StructureType.SPAWN:
                this.displayChar = '.';
                this.color = Color.reset;
                break;
            case StructureType.EXIT:
                this.displayChar = 'E';
                this.color = Color.yellow;
                break;
            case StructureType.BORDER:
                this.displayChar = '*';
                this.color = Color.reset;
                break;
            case StructureType.WALL:
                this.displayChar = 'v';
                this.color = Color.reset;
                break;
            case StructureType.SPIKE:
                this.displayChar = 'x';
                this.color = Color.reset;
                break;
            case StructureType.WATER:
                this.displayChar = 'w';
                this.color = Color.blue;
                break;
            case StructureType.HEAT:
                this.displayChar = 'h';
                this.color = Color.orange;
                break;
        }
        this.pos = new Position();
    }

    //Methods
    abstract public boolean interact(Floor floor);
    abstract public boolean isBlocking(Floor floor);
    abstract public boolean idle(Floor floor);
    //Creature Methods
    abstract public boolean creatureInteract(Floor floor, Creature creature);
    abstract public boolean creatureIsBlocking(Floor floor, Creature creature);
    abstract public boolean creatureIdle(Floor floor, Creature creature);

    //Getters and Setters
    public void setStruct(StructureType type)
    {
        this.type = type;
        switch(type)
        {
            case StructureType.SPAWN:
                this.displayChar = '.';
                this.color = Color.reset;
                break;
            case StructureType.EXIT:
                this.displayChar = 'E';
                this.color = Color.yellow;
                break;
            case StructureType.BORDER:
                this.displayChar = '*';
                this.color = Color.reset;
                break;
            case StructureType.WALL:
                this.displayChar = 'v';
                this.color = Color.reset;
                break;
            case StructureType.SPIKE:
                this.displayChar = 'x';
                this.color = Color.reset;
                break;
            case StructureType.WATER:
                this.displayChar = 'w';
                this.color = Color.blue;
                break;
            case StructureType.HEAT:
                this.displayChar = 'h';
                this.color = Color.orange;
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