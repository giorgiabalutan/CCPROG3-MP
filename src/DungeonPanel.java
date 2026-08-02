import java.awt.*;
import javax.swing.*;
import model.*;

public class DungeonPanel extends JPanel{
    private Model model;
    private PlayerStatusPanel statusPanel;
    private FloorPanel floorPanel;
    private CombatLogPanel logPanel;
    private Image yohaneDeadImage;

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
        
        this.yohaneDeadImage = new ImageIcon(getClass().getResource("assets/dungeonSprites/yohaneSprites/yohane_dead.png")).getImage();

    }

    // public void setKeyListener(KeyListener keyListener)
    // {
    //     this.addKeyListener(keyListener);
    // }

    public void loadFloor()
    {
        this.floorNum = this.model.getDungeon().getFloorNum();
        // System.out.println(this.model.getDungeon().getDungeonCode());
        this.statusPanel.loadFloor();
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
        
        if(this.model.getPlayer().isDead())
        {
            statusPanel.setVisible(false);
            floorPanel.setVisible(false);
            logPanel.setVisible(false);
            showDeathScreen(g2D, this.model.getPlayer().getCauseOfDeath());
        }
        else
        {
            statusPanel.setVisible(true);
            floorPanel.setVisible(true);
            logPanel.setVisible(true);
            if(this.floorNum != this.model.getDungeon().getFloorNum())
            {
                loadFloor();
                this.floorNum = this.model.getDungeon().getFloorNum();
            }
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
    
    public void showDeathScreen(Graphics2D g2D, String causeOfDeath)
    {
        String[] deathText = {
            "---------------------------------------------------------------------------",
            "                           GAME OVER                                ",
            "---------------------------------------------------------------------------",
            "                Yohane has fallen from " + causeOfDeath
        };
        
        this.setForeground(Color.white);
        this.setBackground(Color.black);
        g2D.drawImage(this.yohaneDeadImage, 374, 151, 350, 350, this);
        
        int x = 0;
        int y = 54;
        int lineSpacing = 20;
        int i;
        this.setFont(new Font("Courier New", Font.BOLD, 30));
        for (i = 0; i < deathText.length; i++ )
        {
            g2D.drawString(deathText[i], x, y);
            y+=lineSpacing;
        }
        
        g2D.drawString("[E]xit to Main Menu", 374, 500);
        
    }
}
