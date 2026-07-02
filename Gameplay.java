import java.util.ArrayList;

public class Gameplay
{
    private boolean quit;

    public Gameplay(Player player, Display UI, ArrayList<Idol> idolList)
    {
        this.quit = false;
        UI.displayIntro(idolList);
        UI.displayGamePlay(player, idolList);
        String choice = player.pickChoice(UI.getChoices());

        do
        {
            if (choice.compareTo("1") == 0)
                System.out.println(idolList.get(0).getDungeonName());
            else if (choice.compareTo("2") == 0)
                System.out.println(idolList.get(1).getDungeonName());
            else if (choice.compareTo("3") == 0)
                System.out.println(idolList.get(0).getDungeonName());
            else if (choice.compareTo("I") == 0)
            {
                UI.displayInventory(player, player.getInventory());
                //choice = player.pickChoice(UI.getChoices());
            }
            else if (choice.compareTo("S") == 0)
                this.quit = true;
        }while(!this.quit);
            
        
    }
}