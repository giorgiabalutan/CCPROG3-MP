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
    private int goldSpent;
    private int gameOvers;
    private Inventory inventory;
 
    public Player()
    {
        gameStatus = 1;
        currHP = 3;
        totalHP = 3;
        itemCount = 0;
        totalGold = 0;
        goldSpent = 0;
        gameOvers = 0;
        inventory = new Inventory();
    }

    public Player(int gameStatus)
    {
        this.gameStatus = gameStatus;
    }

    public int getGameStatus()
    {
        return this.gameStatus;
    }

    public int getCurrHP()
    {
        return this.currHP;
    }

    public int getTotalHP()
    {
        return this.totalHP;
    }

    public int getItemCount()
    {
        return this.itemCount;
    }

    public int getTotalGold()
    {
        return this.totalGold;
    }

    public int getGoldSpent()
    {
        return this.goldSpent;
    }

    public int getGameOvers()
    {
        return this.gameOvers;
    }

    public String pickChoice(String choices)
    {
        Scanner sc = new Scanner(System.in);
        String choice;
        do
        {
            choice = sc.nextLine();
        } while(!choices.contains(choice.toUpperCase()));
        
        return choice.toUpperCase();
    }
    
    public ArrayList<Idol> whoToSave()
    {
        int i, nIdols = 8, chosenIdols = 3;
        int randIndex;
        ArrayList<Integer> numList = new ArrayList<Integer>();
        ArrayList<Idol> idolList = new ArrayList<Idol>();
        
        for (i = 1; i <= nIdols; i++)
            numList.add(i);

        Random r = new Random();
        for (i = 0; i < chosenIdols; i++)
        {
            randIndex = r.nextInt(numList.size());
            idolList.add(new Idol(numList.remove(randIndex)));
        }

        return idolList;
    }

    
}