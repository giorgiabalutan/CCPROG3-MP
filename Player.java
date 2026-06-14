import java.util.Scanner;

public class Player
{
    private int gameStatus;
    private int currHP;
    private int totalHP;
    private int itemCount;
    private int totalGold;
 
    public Player()
    {
        gameStatus = 1;
        currHP = 3;
        totalHP = 3;
        itemCount = 0;
        totalGold = 0;

    }

    public Player(int gameStatus)
    {
        this.gameStatus = gameStatus;
    }

    public String pickChoice(String choices)
    {
        Scanner sc = new Scanner(System.in);
        String choice;
        do
        {
            System.out.println();
            System.out.println("Your choice: ");
            choice = sc.nextLine();
        } while(!choices.contains(choice.toUpperCase()));
        
        return choice.toUpperCase();
    }

    public int getGameStatus()
    {
        return this.gameStatus;
    }

    
}