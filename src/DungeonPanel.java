import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.*;

public class DungeonPanel extends JPanel{
    Model model;

    private Font defaultFont;

    public DungeonPanel(Model model, int panelWidth, int panelHeight){
        this.model = model;
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setLayout(null);

        JPanel statusPanel = new JPanel();
        statusPanel.setBounds(0,0,200,panelHeight);
        statusPanel.setBackground(Color.CYAN);//Placeholders

        JPanel mapPanel = new JPanel();
        mapPanel.setBounds(200,0,700,panelHeight);
        mapPanel.setBackground(Color.LIGHT_GRAY);

        JPanel logPanel = new JPanel();
        logPanel.setBounds(900,0,200,panelHeight);
        logPanel.setBackground(Color.yellow);

        this.add(statusPanel);
        this.add(mapPanel);
        this.add(logPanel);

        defaultFont = new Font("Courier New", Font.BOLD, 20);

    }

    public void setKeyListener(KeyListener keyListener)
    {
        this.addKeyListener(keyListener);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        
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
