import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.*;

public class DungeonPanel extends JPanel{
    private Model model;
    private PlayerStatusPanel statusPanel;
    private FloorPanel floorPanel;
    private CombatLogPanel logPanel;

    private Font defaultFont;

    public DungeonPanel(Model model, int panelWidth, int panelHeight){
        this.model = model;
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setLayout(null);

        this.statusPanel = new PlayerStatusPanel(model,0,panelHeight);

        this.floorPanel = new FloorPanel(model, 200, panelHeight);

        this.logPanel = new CombatLogPanel(model, 900, panelHeight);

        this.add(statusPanel);
        this.add(this.floorPanel);
        this.add(logPanel);

        defaultFont = new Font("Courier New", Font.BOLD, 20);

    }

    public void setKeyListener(KeyListener keyListener)
    {
        this.addKeyListener(keyListener);
    }

    public void loadFloor()
    {
        this.floorPanel.loadFloor();
        this.logPanel.loadFloor();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        
        // g2D.drawString();


        // if(this.model.isIntroPlaying())
        //     drawIntroSequence(g2D);
        
        // else
        // {
        //     if(this.model.getPlayer().isInventoryOpen())
        //     drawInventory(g2D);
        //     else
        //         drawOverworld(g2D);
        // }
        
        
    }
}
