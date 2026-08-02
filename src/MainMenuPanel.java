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
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.Idol;
import model.Model;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Shows the Main Menu Screen and the available Options in it.
 * @author LENOVO
 */
public class MainMenuPanel extends JPanel{
        /**
         * {@link Model} to grab data from/
         */
        private Model model;
        /**
         * Background image.
         */
        private Image bgImage;
        /**
         * Button to start a new game.
         */
        private JButton newGameButton;
        /**
         * Button to continue a save.
         */
        private JButton continueButton;
        /**
         * Button to show player stats.
         */
        private JButton statusButton;
        /**
         * Button to open the manual.
         */
        private JButton howToPlayButton;
        /**
         * Button to quit the game.
         */
        private JButton quitButton;
        /**
         * Indicates if the Stats screen is showing.
         */
        private boolean isStatsShowing;
        /**
         * Indicates if the Manual screen is showing.
         */
        private boolean isManualShowing;
        /**
         * A panel to hold the buttons.
         */
        private JPanel buttonPanel;

        /**
         * Constructs the Menu panel.
         * 
         * @param model Model to grab data from.
         * @param panelWidth How wide this is.
         * @param panelHeight How tall this is.
         */
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
        
        /**
         * Sets the panel's style.
         * The layout type, bounds, and opacity.
         */
        public void stylePanel()
        {
            this.buttonPanel.setLayout(new GridLayout(0, 1, 0, 2));
            this.buttonPanel.setBounds(800, 310, 250, 180);
            this.buttonPanel.setOpaque(false);
        }
        /**
         * Adds in all of the buttons.
         */
        public void addButtons()
        {
            newGameButton = new JButton("New Game");
            styleButton(newGameButton);
            newGameButton.setActionCommand("N");
            
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
                this.newGameButton.setText("New Game+");
            else
                this.newGameButton.setText("New Game");
            buttonPanel.add(newGameButton);
            if (this.model.isPlaythroughExists())
                this.buttonPanel.add(continueButton);
            
            this.buttonPanel.add(statusButton);
            this.buttonPanel.add(howToPlayButton);
            this.buttonPanel.add(quitButton);
            
            
        }
        /**
         * Attaches a listener to all of the buttons to detect when the player clicks them.
         * 
         * @param actionListener Listener from the JFrame.
         */
        public void setButtonActionListener(ActionListener actionListener)
        {
            this.newGameButton.addActionListener(actionListener);
            this.continueButton.addActionListener(actionListener);
            this.statusButton.addActionListener(actionListener);
            this.howToPlayButton.addActionListener(actionListener);
            this.quitButton.addActionListener(actionListener);
        }
        
        /**
         * Styles the buttons.
         * 
         * @param button the button to apply the styles to.
         */
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
        
        /**
         * Draws the Menu Screen.
         */
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2D = (Graphics2D) g;
            g2D.drawImage(this.bgImage, 0, 0, null);
            
            if (this.isManualShowing)
                drawManual(g2D);
            else if(this.isStatsShowing)
                drawStats(g2D);
            else
                showButtons();
            
        }
        /**
         * Sets the buttons to be visible (if available).
         */
        public void showButtons()
        {
            this.quitButton.setText("Quit");
            this.quitButton.setActionCommand("Q");
            if (this.model.isNgPlusAvailable())
                this.newGameButton.setText("New Game+");
            newGameButton.setVisible(true);
            continueButton.setVisible(true);
            statusButton.setVisible(true);
            howToPlayButton.setVisible(true);
            this.buttonPanel.setBounds(800, 310, 250, 180);
        }
        /**
         * Draws the manual when opened.
         * 
         * @param g2D graphics2D object to draw with.
         */
        public void drawManual(Graphics2D g2D)
        {
            this.quitButton.setText("Return");
            this.quitButton.setActionCommand("R");
            newGameButton.setVisible(false);
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
        /**
         * Draws the status panel when opened.
         * 
         * @param g2D graphics2D object to draw with.
         */
        public void drawStats(Graphics2D g2D)
        {
            int i = 0;
            int x = 46;
            int y = 33;
            int width = 1009;
            int length = 453;
            int lineSpacing = 20;
            
            this.quitButton.setText("Return");
            this.quitButton.setActionCommand("R");
            newGameButton.setVisible(false);
            continueButton.setVisible(false);
            statusButton.setVisible(false);
            howToPlayButton.setVisible(false);
            this.buttonPanel.setBounds(800, 310, 250, 180);
            
            g2D.setFont(new Font("Courier New", Font.PLAIN, 17));
            ArrayList<Idol> savedIdols = this.model.getSavedIdols();
            String[] textBlock = {
                "-----------------------------------------------------------------------------------------------",
                "                                         Overall Stats                                         ",
                "-----------------------------------------------------------------------------------------------",
                "Saved Idols"
            };
            String[] idolBlock = new String[savedIdols.size()];
            String sirenDefeatedText = "Times Siren was defeated      " + this.model.getTimesSirenDefeated() + "\n";
            String gameOversText = "No. of game overs      " + this.model.getGameOvers() + "\n";
            String totalGoldSpentText = "Total gold spent      " + this.model.getPlayer().getTotalGold();
            
            for (i = 0; i < savedIdols.size(); i++)
            {
                idolBlock[i] = savedIdols.get(i).getIdolName() + "      " + savedIdols.get(i).getTimesSaved() + "\n";
            }

            drawBox(g2D, x, y, width, length);
            
            x = 62;
            y = 56;
            for (i = 0; i < textBlock.length; i++)
            {   
                for(String line : textBlock[i].split("\n"))
                {
                    g2D.drawString(line, x, y);
                    y += lineSpacing;
                }
            }
            for (i = 0; i < idolBlock.length; i++)
            {   
                for(String line : idolBlock[i].split("\n"))
                {
                    g2D.drawString(line, x, y);
                    y += lineSpacing;
                }
            }
            lineSpacing = 40;
            g2D.drawString(sirenDefeatedText, x, y);
            y+=lineSpacing;
            
            g2D.drawString(gameOversText, x, y);
            y+=lineSpacing;
            
            g2D.drawString(totalGoldSpentText, x, y);
            y+=lineSpacing;
        }
        /**
         * Draws a rounded box.
         * 
         * @param g2D graphics2D object to draw with.
         * @param x starting position X.
         * @param y starting position Y.
         * @param width how wide the box is.
         * @param height how tall the box is.
         */
        public void drawBox(Graphics2D g2D, int x, int y, int width, int height)
        {
            g2D.setColor(new Color(0, 0, 0, 240));
            g2D.fillRoundRect(x, y, width, height, 35, 35);


            g2D.setColor(Color.white);
            g2D.setStroke(new BasicStroke(5));
            g2D.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
        }
        /**
         * Sets if the manual should be showing.
         * 
         * @param isManualShowing if the manual should be showing.
         */
        public void setIsManualShowing(boolean isManualShowing)
        {
            this.isManualShowing = isManualShowing;
        }
        /**
         * Sets if the status screen should be showing.
         * 
         * @param isStatsShowing if the status screen should be showing.
         */
        public void setIsStatsShowing(boolean isStatsShowing)
        {
            this.isStatsShowing = isStatsShowing;
        }
        
}
