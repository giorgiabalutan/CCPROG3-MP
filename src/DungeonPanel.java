import java.awt.*;
import javax.swing.*;
import model.*;

public class DungeonPanel extends JPanel{
    private Model model;
    private PlayerStatusPanel statusPanel;
    private FloorPanel floorPanel;
    private CombatLogPanel logPanel;

    private int floorNum;

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

    // public void setKeyListener(KeyListener keyListener)
    // {
    //     this.addKeyListener(keyListener);
    // }

    public void loadFloor()
    {
        this.floorNum = this.model.getDungeon().getFloorNum();
        this.floorPanel.loadFloor();
        this.logPanel.loadFloor();
    }

    public void tickAnimation()
    {
        this.floorPanel.tickAnimation();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        
        if(this.floorNum != this.model.getDungeon().getFloorNum())
        {
            loadFloor();
            this.floorNum = this.model.getDungeon().getFloorNum();
        }

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
