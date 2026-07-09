public class Position{
    private int y;
    private int x;

    public Position()
    {
        this.x = 0;
        this.y = 0;
    }

    public void setPosition(int y, int x)
    {
        this.y = y;
        this.x = x;
    }

    public void move(int y, int x)
    {
        this.y += y;
        this.x += x;
    }
    
    public int getPosX()
    {
        return this.x;
    }

    public int getPosY()
    {
        return this.y;
    }
}