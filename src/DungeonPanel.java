import java.awt.*;
import javax.swing.*;
import model.CombatLogEntry;
import model.Idol;
import model.Item;
import model.Model;

public class DungeonPanel extends JPanel{
    /**
     * {@link Model} to grab Data From.
     */
    private Model model;
    /**
     * Component Panel to show {@link Player} Stats.
     */
    private PlayerStatusPanel statusPanel;
    /**
     * Component Panel to show {@link CombatLogEntry Combat Log Entries}.
     */
    private FloorPanel floorPanel;
    /**
     * Component Panel to show the current {@link Floor}.
     */
    private CombatLogPanel logPanel;
    /**
     * Image for if Yohane Dies.
     */
    private Image yohaneDeadImage;
    /**
     * Background Image.
     */
    private Image bgImage;
    /**
     * An image of the {@link Idol} to be saved in this Dungeon.
     */
    private Image savedIdolImage;
    /**
     * Big sprite of Yohane.
     */
    private Image yohaneImage;
    /**
     * Image of the Siren.
     */
    private Image sirenImage;
    /**
     * Background for the Final Fight.
     */
    private Image finalFightBg;
    /**
     * Number of the current floor.
     */
    private int floorNum;
    /**
     * Font to print text with.
     */
    private Font defaultFont;
    /**
     * Constructor to initialize the Panel.
     * Also initializes the component panels.
     * 
     * @param model Model to grab data from.
     * @param panelWidth How wide this panel is.
     * @param panelHeight How tall this panel is.
     */
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
        this.yohaneImage = new ImageIcon(getClass().getResource("assets/yohaneInventoryImage.png")).getImage();
        this.bgImage =  new ImageIcon(getClass().getResource("assets/intro7.png")).getImage();
        this.sirenImage = new ImageIcon(getClass().getResource("assets/sirenImage.png")).getImage();
        this.finalFightBg = new ImageIcon(getClass().getResource("assets/finalFightBg.png")).getImage();
    }

    // public void setKeyListener(KeyListener keyListener)
    // {
    //     this.addKeyListener(keyListener);
    // }
    /**
     * Loads in the current {@link Floor} for the component Panels.
     * Used when Switching Floors.
     */
    public void loadFloor()
    {
        this.floorNum = this.model.getDungeon().getFloorNum();
        // System.out.println(this.model.getDungeon().getDungeonCode());
        this.statusPanel.loadFloor();
        this.floorPanel.loadFloor();
        this.logPanel.loadFloor();
    }
    /**
     * Ticks the Animation in {@code FloorPanel}.
     */
    public void tickAnimation()
    {
        this.floorPanel.tickAnimation();
    }
    /**
     * Draws Outcome screens, for Death, Win, and Final Boss Win.
     * Otherwise, just calls the paints of component panels from the usual behavior.
     */
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
        else if(this.model.getDungeonWon())
        {
            statusPanel.setVisible(false);
            floorPanel.setVisible(false);
            logPanel.setVisible(false);
            showDungeonWinScreen(g2D);
        }
        else if(this.model.getFinalFightWon())
        {
            statusPanel.setVisible(false);
            floorPanel.setVisible(false);
            logPanel.setVisible(false);
            showSirenWinScreen(g2D);
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
    /**
     * Shows a Death Screen on Player Death.
     * 
     * @param g2D graphics2D object from the paint method.
     * @param causeOfDeath what caused Yohane to die.
     */
    public void showDeathScreen(Graphics2D g2D, String causeOfDeath)
    {
        String[] deathText = {
            "---------------------------------------------------------------------------",
            "                           GAME OVER                                ",
            "---------------------------------------------------------------------------",
            "                Yohane has fallen from " + causeOfDeath,
        };
        
        this.setForeground(Color.red);
        this.setBackground(Color.black);
        g2D.drawImage(this.yohaneDeadImage, 374, 151, 350, 350, this);
        
        int x = 0;
        int y = 54;
        int lineSpacing = 20;
        int i;
        g2D.setFont(new Font("Courier New", Font.BOLD, 30));
        for (i = 0; i < deathText.length; i++ )
        {
            g2D.drawString(deathText[i], x, y);
            y+=lineSpacing;
        }
        g2D.setFont(new Font("Courier New", Font.BOLD, 18));
        g2D.drawString("[E]xit to Main Menu", 450, 520);
        
    }
    /**
     * Shows a Win Screen on Dungeon Clear.
     * 
     * @param g2D graphics2D object from the paint method.
     */
    public void showDungeonWinScreen(Graphics2D g2D)
    {
        Idol idol = this.model.getDungeon().getIdol();
        Item item = new Item(idol.getIdolNumber());
        this.savedIdolImage =  new ImageIcon(getClass().getResource(idol.getIdolImageFilePath())).getImage();
        g2D.drawImage(bgImage, 0, 0, null);
        String[] clearedText = {
            "-------------------------------------------------------------------------------------",
            "                                    Dungeon cleared!                                          ",
            "-------------------------------------------------------------------------------------",
            "Dungeon Complete: " +idol.getDungeonName() + "\n",
            "You have saved " +idol.getIdolName()
        };
        
        String unlockedItem;
        if (idol.getIdolNumber() != 4)
            unlockedItem = "Unlocked: " + item.getItemName();
        else
            unlockedItem = "Unlocked: Hanamaru's Shop";
        
        String idolText[];
        switch(idol.getIdolNumber())
        {
            case 1:
                idolText = new String[]
                {
                    "Chika: Waaah! Yoshiko-chan, you actually came!",
                    "Yohane: Ugh, its Yohane!! but fufu, of course. A fallen angel never\nabandons those in need.",
                    "Chika: I couldn't sing at all... it was so scary not having my voice.",
                    "Lailaps: Onto the next, Yohane-chan."
                };
                break;
            case 2:
                idolText = new String[]
                {
                    "You: Land ho! I mean- Yohane-chan!, over here!",
                    "Yohane: The seas were rough, but a fallen angel fears no tide.",
                    "You: Ehehe, thanks for diving in after me. Literally."
                };
                break;
            case 3:
                idolText = new String[]
                {
                    "Riko: I... I was so scared down there in the dark.",
                    "Yohane: Fear not. My holy shield reaches even the deepest trenches.",
                    "Riko: Thank you, Yohane-chan. Really."
                };
                break;
            case 4:
                idolText = new String[]
                {
                    "Hanamaru: Yohane-chan, zura! You're a sight for sore eyes, zura.",
                    "Yohane: Hanamaru! I felt your voice calling out to me through the mirror.",
                    "Hanamaru: I knew you'd come. Fallen angels always keep their promises, zura.",
                    "Hanamaru: Because of this, I'll open up my relic shop for you!!"
                };
                break;
            case 5:
                idolText = new String[]
                {
                    "Ruby: Y-Yoshiko-chan?! I was so scared, I couldn't even scream...",
                    "Yohane: Do not fret, little one. The fallen angel Yohane has arrived. And uhm\n, it's Yohane...",
                    "Ruby: Thank you... I mean it."
                };
                break;
            case 6:
                idolText = new String[]
                {
                    "Dia: Took you long enough. Though... I suppose I should thank you.",
                    "Yohane: Hah! Even the great Dia Kurosawa cannot resist my aid.",
                    "Dia: Don't get used to it. But truly, thank you, Yoshiko.",
                    "Yohane: If you want to thank me, say my name correct at least!"
                };
                break;
            case 7:
                idolText = new String[]
                {
                    "Kanan: Yohane! I knew you'd come through for us.",
                    "Yohane: Naturally. A fallen angel's wings carry her wherever she's needed.",
                    "Kanan: Ruby and Dia will be relieved to hear you're clearing these out one\nby one."
                };
                break;
            case 8:
                idolText = new String[]
                {
                    "Mari: Yohane-chan~! Zuramaru said you'd come, and here you are!",
                    "Yohane: Of course. My holy shield does not discriminate between rich or poor,\nidol or otherwise.",
                    "Mari: Shishishi, as dramatic as ever. Thank you, truly."
                };
                break;
                default:
                    idolText = new String[]{ "Yohane: ...another voice returns." };
                    break;
        }
        
        int x = 62;
        int y = 250;
        int width = 975;
        int height = 243;
        
        g2D.drawImage(this.yohaneImage, 19, 20, null);
        g2D.drawImage(this.savedIdolImage, 717, 0, null);
        drawBox(g2D, x, y, width, height);
        
        this.setForeground(Color.white);
        x = 75;
        y = 275;
        int lineSpacing = 20;
        int i;
        
        g2D.setFont(new Font("Courier New", Font.BOLD, 18));
        for (i = 0; i < clearedText.length; i++ )
        {
            g2D.drawString(clearedText[i], x, y);
            y+=lineSpacing;
        }
        g2D.drawString(unlockedItem, x, y);
        y+=20;
        for (i = 0; i < idolText.length; i++ )
        {
            for (String line : idolText[i].split("\n"))
            {
                g2D.drawString(line, x, y);
                y+=lineSpacing;
            }
            
        }
        g2D.drawString("[E]xit to Main Menu", 450, 520);
    }
    /**
     * Shows a Win Screen for a Final Boss Clear.
     * 
     * @param g2D graphics2D object from the paint method.
     */
    public void showSirenWinScreen(Graphics2D g2D)
    {
        g2D.drawImage(finalFightBg, 0, 0, null);
        String[] clearedText = {
            "-------------------------------------------------------------------------------------",
            "                                    Siren defeated!                                          ",
            "-------------------------------------------------------------------------------------\n\n",
            "Siren: N-no... my voice, my power...",
            "Yohane: You've caused enough trouble stealing everyone's voices, Siren.",
            "Siren: I only wanted... to be heard. Is that so wrong?",
            "Yohane: ...Perhaps not. But this was never the way to do it.",
            "Yohane: As a fallen angel, I hereby free these voices to their owners!",
            "Siren: . . .",
            "Yohane: You... already have a beautiful voice. So you don't have to steal. Hmp, take\nthis as my mercy"
        };
        
        int x = 62;
        int y = 260;
        int width = 975;
        int height = 240;
        
        g2D.drawImage(this.yohaneImage, 650, 0, null);
        g2D.drawImage(this.sirenImage, 19, 0, null);
        drawBox(g2D, x, y, width, height);
        
        this.setForeground(Color.white);
        x = 75;
        y = 285;
        int lineSpacing = 20;
        int i;
        
        g2D.setFont(new Font("Courier New", Font.BOLD, 18));
        for (i = 0; i < clearedText.length; i++ )
        {
            for (String line : clearedText[i].split("\n"))
            {
                g2D.drawString(line, x, y);
                y+=lineSpacing;
            }
        }
        y+=20;
        g2D.drawString("[E]xit to Main Menu", 450, 520);
    }
    /**
     * Draws a Box.
     * 
     * @param g2D graphics2D object from the paint method.
     * @param x Start Position X.
     * @param y Start Position Y.
     * @param width How wide the box is.
     * @param height How tall the box is.
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
