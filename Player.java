import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

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
    
    public void newGame()
    {
        int i, nIdols = 8, chosenIdols = 3;
        int index;
        ArrayList<Integer> numList = new ArrayList<Integer>();
        ArrayList<Integer> idolList = new ArrayList<Integer>();
        for (i = 1; i <= nIdols; i++)
            numList.add(i);

        Random r = new Random();
        for (i = 0; i < chosenIdols; i++)
        {
            index = r.nextInt(numList.size());
            idolList.add(numList.remove(index));
        }
        Idol idol1 = new Idol(idolList.get(0));
        Idol idol2 = new Idol(idolList.get(1));
        Idol idol3 = new Idol(idolList.get(2));

        
    }

    
}