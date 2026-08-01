import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.Model;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */
public class MainMenuPanel extends JPanel{
    
        private Model model;
        private Image bgImage;
        private JButton newGameButton;
        private JButton newGamePlusButton;
        private JButton continueButton;
        private JButton statusButton;
        private JButton howToPlayButton;
        private JButton quitButton;
        private JButton returnButton;
        private boolean isStatsShowing;
        private boolean isManualShowing;
        
        private JPanel buttonPanel;

        public MainMenuPanel(Model model, int panelWidth, int panelHeight)
        {
            this.model = model;
            this.setPreferredSize(new Dimension(panelWidth, panelHeight));
            this.setLayout(null);
            this.bgImage = new ImageIcon(getClass().getResource("/assets/mainMenuImage.png")).getImage();
            
            this.buttonPanel = new JPanel();
            stylePanel();
            addButtons();
            this.add(buttonPanel);
        }
        
        public void stylePanel()
        {
            this.buttonPanel.setLayout(new GridLayout(0, 1, 0, 2));
            this.buttonPanel.setBounds(800, 310, 250, 180);
            this.buttonPanel.setOpaque(false);
        }
        
        public void addButtons()
        {
            newGameButton = new JButton("New Game");
            styleButton(newGameButton);
            newGameButton.setActionCommand("N");
            
            newGamePlusButton = new JButton("New Game+");
            styleButton(newGamePlusButton);
            newGamePlusButton.setActionCommand("N");
            
            continueButton = new JButton("Continue");
            styleButton(continueButton);
            continueButton.setActionCommand("C");
            
            statusButton = new JButton("Status");
            styleButton(statusButton);
            statusButton.setActionCommand("S");
            
            howToPlayButton = new JButton("How to play");
            styleButton(howToPlayButton);
            howToPlayButton.setActionCommand("H");
            
            quitButton = new JButton("Quit");
            styleButton(quitButton);
            quitButton.setActionCommand("Q");
            
            
            
            if (this.model.isNgPlusAvailable())
                this.buttonPanel.add(newGamePlusButton);
            else
                this.buttonPanel.add(newGameButton);
            
            if (this.model.isPlaythroughExists())
                this.buttonPanel.add(continueButton);
            
            this.buttonPanel.add(statusButton);
            this.buttonPanel.add(howToPlayButton);
            this.buttonPanel.add(quitButton);
            
            
        }
        
        public void setButtonActionListener(ActionListener actionListener)
        {
            this.newGameButton.addActionListener(actionListener);
            this.newGamePlusButton.addActionListener(actionListener);
            this.continueButton.addActionListener(actionListener);
            this.statusButton.addActionListener(actionListener);
            this.howToPlayButton.addActionListener(actionListener);
            this.quitButton.addActionListener(actionListener);
        }
        
        public void styleButton(JButton button)
        {
            button.setFocusable(false);
            button.setForeground(java.awt.Color.blue);
            button.setContentAreaFilled(false);
            button.setFont(new Font("Courier New", Font.PLAIN, 17));
            button.setBorderPainted(false);
            button.setHorizontalAlignment(JButton.RIGHT);
            
            button.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseEntered(MouseEvent e)
                {
                        button.setFont(new Font("Courier New", Font.BOLD, 20));
                }
                
                @Override
                public void mouseExited(MouseEvent e)
                {
                    button.setFont(new Font("Courier New", Font.PLAIN, 17));
                }
                
            });
        }
        
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2D = (Graphics2D) g;
            g2D.drawImage(this.bgImage, 0, 0, null);
            
            if (this.isManualShowing)
                drawManual(g2D);
            else
                showButtons();
            
        }
        
        public void showButtons()
        {
            this.quitButton.setText("Quit");
            this.quitButton.setActionCommand("Q");
            newGameButton.setVisible(true);
            newGamePlusButton.setVisible(true);
            continueButton.setVisible(true);
            statusButton.setVisible(true);
            howToPlayButton.setVisible(true);
            this.buttonPanel.setBounds(800, 310, 250, 180);
        }
       
        public void drawManual(Graphics2D g2D)
        {
            this.quitButton.setText("Return");
            this.quitButton.setActionCommand("R");
            newGameButton.setVisible(false);
            newGamePlusButton.setVisible(false);
            continueButton.setVisible(false);
            statusButton.setVisible(false);
            howToPlayButton.setVisible(false);
            this.buttonPanel.setBounds(800, 310, 250, 180);
            
            g2D.setFont(new Font("Courier New", Font.PLAIN, 17));
            String lines[] = {
                "------------------------------------------Goal---------------------------------------------",
                " Traverse through dungeons to save 3 random idols.",
                "--------------------------------------Dungeon Preparation----------------------------------",
                " You can choose to view your inventory or use your items. Items are used when pressing the\nspace bar or you can switch through them using [ or ]. ",
                "-----------------------------------------Dungeon-------------------------------------------",
                " You can move in any straight direction using W, S, A, D. You can also use or switch through\nitems here but you will consume one move while enemies will also move.",
                "-----------------------------------------Enemies-------------------------------------------",
                " Bats are flying creatures therefore they can also move diagonally. They will attack you if\nyou are in an adjacent tile while they move. Hint: They only move after you perform two moves.\n",
                " Skeletons are long-range enemies that shoot arrows. These arrows hurt you if you are directly\nin front of them. Hint: Arrows move after you perform one move.",
                "---------------------------------------Saving Idols----------------------------------------",
                " Saving different idols will unlock items and even the shop where you can buy these items.",
                "----------------------------------------Boss Battle----------------------------------------",
                " During the final fight, enemies will spawn more often. Good luck, fallen Angel!"
            };
            int i = 0;
            int x = 46;
            int y = 33;
            int width = 1009;
            int length = 453;
            int lineSpacing = 20;
            
            drawBox(g2D, x, y, width, length);
            
            x = 62;
            y = 56;
            for (i = 0; i < lines.length; i++)
            {   
                for(String line : lines[i].split("\n"))
                {
                    g2D.drawString(line, x, y);
                    y += lineSpacing;
                }
            }
            
            
        }
        
        public void drawBox(Graphics2D g2D, int x, int y, int width, int height)
        {
            g2D.setColor(new Color(0, 0, 0, 220));
            g2D.fillRoundRect(x, y, width, height, 35, 35);


            g2D.setColor(Color.white);
            g2D.setStroke(new BasicStroke(5));
            g2D.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
        }
        
        public void setIsManualShowing(boolean isManualShowing)
        {
            this.isManualShowing = isManualShowing;
        }
        
        public void setIsStatsShowing(boolean isStatsShowing)
        {
            this.isStatsShowing = isStatsShowing;
        }
        
}
