
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import model.Item;
import model.Model;
import model.Player;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */
public class ShopPanel extends JPanel{
    /**
     * Model to grab data from.
     */
    private Model model;
    /**
     * Background image for the shop area.
     */
    private Image shopBackground;
    /**
     * Image showing Hanamaru, the shopkeeper.
     */
    private Image hanamaruImage;
    /**
     * Font to draw text with.
     */
    Font defaultFont;
    /**
     * Dialogue Hanamaru should be saying.
     */
    private String hanamaruText;

    /**
     * Constructs the Shop Panel, along with initial Hanamaru Dialogue.
     * 
     * @param model Model to grab data from.
     * @param panelWidth how wide this is.
     * @param panelHeight how tall this is.
     */
    public ShopPanel(Model model, int panelWidth, int panelHeight)
    {
        this.model = model;
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.shopBackground = new ImageIcon(getClass().getResource("/assets/shopBackground.png")).getImage();
        this.hanamaruImage = new ImageIcon(getClass().getResource("/assets/hanamaruKunikida.png")).getImage();
        this.hanamaruText = "Hanamaru: Thanks again for\nsaving me, Yohane-chan. I'll\nsell these special relics just\nto you.";
        defaultFont = new Font("Courier New", Font.BOLD, 20);
    }
    
    // public void setKeyListener(KeyListener keyListener)
    // {
    //     this.addKeyListener(keyListener);
    // }
    /**
     * Draws the Shop.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        
        drawShop(g2D);
        
        
    }
    /**
     * Draws the Shop.
     * Shows the items available to be bought and their corresponding sprite.
     * Also draws Hanamaru and her dialogue.
     * 
     * @param g2D graphics2D object to draw with.
     */
    public void drawShop(Graphics2D g2D)
    {
        Player player = this.model.getPlayer();
        ArrayList<Item> availableItems = this.model.getAvailableShopItems();
        // System.out.println(availableItems);
        
        g2D.setFont(this.defaultFont);
        g2D.drawImage(this.shopBackground, 0, 0, null);
        g2D.drawImage(this.hanamaruImage, -42, 0, null);
        drawBox(g2D, 200, 58, 405, 140);
        
        int y = 85;
        int x = 220;
        for(String line : this.hanamaruText.split("\n"))
        {
            g2D.drawString(line, x, y);
            y += 25;
        }
        
        x = 642;
        y = 19;
        int width = 429;
        int height = (availableItems.size() + 3) * 40;
        drawBox(g2D, x, y, width, height);
        
        x = 656;
        y = 45;
        int lineSpacing = 30;
        
        String totalGoldText = String.format("Gold: " + (player.getTotalGold() - player.getGoldSpent()));
        g2D.drawString(totalGoldText, x, y);
        y+=lineSpacing;
        
        int i;
        for (i = 0; i < availableItems.size(); i++)
        {
            g2D.drawString("[" + (i+1) + "] " + availableItems.get(i).getItemName() + "   " + availableItems.get(i).getPrice(), x, y);
                y+=lineSpacing;
        }
        g2D.drawString("[R]eturn", x, y + 30);
        
        
    }
    /**
     * Sets the Dialogue Hanamaru should be saying.
     * 
     * @param text the Dialogue Hanamaru should be saying.
     */
    public void setHanamaruText(String text)
    {
        this.hanamaruText = text;
    }
    /**
     * Draw a rounded box.
     * 
     * @param g2D graphics2D object to draw with.
     * @param x starting position X.
     * @param y starting position Y.
     * @param width how wide this is.
     * @param height how tall this is.
     */
    public void drawBox(Graphics2D g2D, int x, int y, int width, int height)
    {
        g2D.setColor(new Color(0, 0, 0, 220));
        g2D.fillRoundRect(x, y, width, height, 35, 35);
        
        
        g2D.setColor(Color.white);
        g2D.setStroke(new BasicStroke(5));
        g2D.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }
}
