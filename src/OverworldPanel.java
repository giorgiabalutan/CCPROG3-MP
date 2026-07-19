
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */
public class OverworldPanel extends JPanel{
    
    private Model model;
    private Image overworldBackground;
    private Image lailapsAndYohaneImage;
    
    Font defaultFont;
    
    
    public OverworldPanel(Model model, int panelWidth, int panelHeight)
    {
        this.model = model;
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.overworldBackground = new ImageIcon(getClass().getResource("/assets/overworldBackground.png")).getImage();
        this.lailapsAndYohaneImage = new ImageIcon(getClass().getResource("/assets/lailapsAndYohaneImage.png")).getImage();
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(this.overworldBackground, 0, 0, null);
        g2D.drawImage(this.lailapsAndYohaneImage, 0, 10, null);
        
        drawChoicesBox(g2D);
        
        
    }
    
    public void drawChoicesBox(Graphics2D g2D)
    {
        defaultFont = new Font("Courier New", Font.BOLD, 20);
        int x = 19; 
        int y = 340;
        int width = 1065;
        int height = 189;
        
        int i;
        Player player = this.model.getPlayer();
        ArrayList<Idol> idolList = this.model.getIdolList();
        int size = idolList.size();
        
        FontMetrics fontMetrics = g2D.getFontMetrics();
        
        String hpText = String.format("HP: %.1f / %.1f", player.getCurrHP(), player.getTotalHP());
        String itemOnHandText = "Item on Hand: " + player.getItemOnHand().getItemName();
        String totalGoldText = String.format("Total gold: " + player.getTotalGold());
        String inventoryText = "[I]nventory";
        String saveAndQuitText = "[S]ave and Quit";
        String dungeon1 = "[1] " + idolList.get(0).getDungeonName();
        String dungeon2 = "[2] " + idolList.get(1).getDungeonName();
        String dungeon3 = "[3] " + idolList.get(2).getDungeonName();
        
        //Choices box
        drawBox(g2D, x, y, width, height);
        g2D.setFont(defaultFont);
        g2D.drawString(hpText, 38, 370);
        g2D.drawString(totalGoldText, 800, 370);
        g2D.drawString(itemOnHandText, 38, 400);
        g2D.drawString(inventoryText, 645, 400);
        g2D.drawString(saveAndQuitText, 799, 400);
        g2D.drawString(dungeon1, 38, 450);
        g2D.drawString(dungeon2, 38, 470);
        g2D.drawString(dungeon3, 38, 490);
        
        //Dialogue box between Yohane and Lailaps
        drawBox(g2D, 360, 46, 405, 100);
        String lailapsText = "Lailaps: Yohane! What should we\ndo next?";
        String yohaneText = "Yohane: Hmmmmm...";
        
        y = 75;
        for(String line : lailapsText.split("\n"))
        {
             g2D.drawString(line, 376, y);
             y += 20;
        }
        
        g2D.drawString(yohaneText, 376, 125);
    }
    
    public void drawBox(Graphics2D g2D, int x, int y, int width, int height)
    {
        g2D.setColor(new Color(0, 0, 0));
        g2D.fillRoundRect(x, y, width, height, 35, 35);
       
        
        g2D.setColor(Color.white);
        g2D.setStroke(new BasicStroke(5));
        g2D.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }
}
