import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;/*
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
        
        private ActionListener listener;
        
        private JPanel buttonPanel;

        public MainMenuPanel(Model model, ActionListener listener)
        {
            this.model = model;
            this.listener = listener;
            this.setPreferredSize(new Dimension(1100, 540));
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
            quitButton.setActionCommand("H");
            
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
        
        public void styleButton(JButton button)
        {
            button.addActionListener(this.listener);
            
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
        }

}
