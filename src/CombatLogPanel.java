import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import model.CombatLogEntry;
import model.Model;

public class CombatLogPanel extends JPanel {
    private ArrayList<CombatLogEntry> combatLog;
    private Font defaultFont;
    private Model model;

    public CombatLogPanel(Model model, int x, int panelHeight){
        this.setBounds(x,0,200,panelHeight);
        this.setBackground(Color.YELLOW);//Placeholders
        this.model = model;

        this.defaultFont = new Font("Courier New", Font.BOLD, 20);
    }

    public void loadFloor()
    {
        this.combatLog = this.model.getDungeon().getFloor().getCombatLogs();
    }
}
