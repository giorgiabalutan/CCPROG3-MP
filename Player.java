import java.util.Scanner;

public class Player
{
    int gameStatus;

    public Player()
    {
        gameStatus = 1;
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