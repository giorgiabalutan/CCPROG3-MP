import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.Item;
import model.Model;
import model.Player;
import model.creature.Lailaps;
import model.dungeon.*;

public class PlayerStatusPanel extends JPanel{
    private Model model;
    private Dungeon dungeon;
    private Player player;
    private Item itemOnHand;
    private transient BufferedImage emptyHeart;
    private transient BufferedImage fullHeart;
    private transient BufferedImage gold;
    private transient BufferedImage nextItem;
    private transient BufferedImage prevItem;
    private transient BufferedImage heldItem;

    private Font defaultFont;
    private Font goldFont;
    private Color golden;
    private Color reset;


    public PlayerStatusPanel(Model model, int x, int panelHeight){
        this.model = model;
        this.dungeon = model.getDungeon();
        this.player = model.getPlayer();

        this.setBounds(x,0,200,panelHeight);
        this.setBackground(Color.CYAN);//Placeholders

        try
        {
            emptyHeart = ImageIO.read(new File("assets/dungeonSprites/EmptyHeart.png"));
            fullHeart = ImageIO.read(new File("assets/dungeonSprites/FullHeart.png"));
            gold = ImageIO.read(new File("assets/dungeonSprites/Gold.png"));
            nextItem = ImageIO.read(new File("assets/itemSprites/nextItem.png"));
            prevItem = ImageIO.read(new File("assets/itemSprites/prevItem.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        defaultFont = new Font("Courier New", Font.BOLD, 20);
        goldFont = new Font("Courier New", Font.BOLD, 24);
        golden = new Color(255,215,0);
        reset = new Color(0,0,0);
    }

    public void loadFloor()
    {
        this.dungeon = this.model.getDungeon();
        this.player = this.model.getPlayer();
        this.itemOnHand = this.player.getItemOnHand();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        int nextLineHeight = 20;
        g2D.setFont(defaultFont);
        // g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        
        // g2D.drawString("" + dungeon.getName() + "", 10, nextLineHeight);
        // nextLineHeight += 20;

        if(dungeon.getDungeonCode() == DungeonCode.SIRENS_LAIR)
        {
            g2D.drawString("Final Dungeon", 10, nextLineHeight);
        }else{
            g2D.drawString("Dungeon " + dungeon.getOrder() + " of 3", 10, nextLineHeight);
        }
        nextLineHeight += 20;

        g2D.drawString("Floor   " + (dungeon.getFloorNum()+1) + " of " + dungeon.getMaxFloor(), 10, nextLineHeight);
        nextLineHeight += 10;


        g2D.drawImage(SpriteCache.getImage("assets/dungeonSprites/YohaneIcon.png"),10,nextLineHeight,128,128,this);
        nextLineHeight += 140;

        // g2D.drawString("HP: " + player.getCurrHP() +  "/" + player.getTotalHP(), 10, 60);
        // System.out.println(player.getCurrHP());
        int MaxHp = (int)Math.ceil(player.getTotalHP());
        int fullHearts = (int)Math.floor(player.getCurrHP());
        boolean partial = (player.getCurrHP()%1)>0;
        for (int i = 0; i<MaxHp; i++)
        {
            g2D.drawImage(emptyHeart, 13 + (30*(i%6)), nextLineHeight, 24, 24,  this);
            if(i < fullHearts)
            {
                g2D.drawImage(fullHeart, 13 + (30*(i%6)), nextLineHeight, 24, 24,  this);
            }else if (i==fullHearts && partial) {
                int percent = (int) (player.getCurrHP()%1 * 16);
                g2D.drawImage(fullHeart, 13 + (30*(i%6)), nextLineHeight, (10 + (30*(i%6))) + 2*percent, nextLineHeight + 24, 0, 0, percent, 16, this);
            }
            if(i%6 == 5 && MaxHp%6 > 0)
            {
                nextLineHeight += 30;
            }
        }
        nextLineHeight += 30;
        g2D.drawImage(gold, 13, nextLineHeight, 24, 24, this);
        g2D.setColor(golden);
        g2D.setFont(goldFont);
        g2D.drawString("" + (player.getTotalGold()-player.getGoldSpent()), 43, nextLineHeight + 17);
        g2D.setColor(reset);
        g2D.setFont(defaultFont);
        nextLineHeight += 30;

        g2D.drawImage(prevItem, 26, nextLineHeight, 48, 48,  this);

        itemOnHand = player.getItemOnHand();

        try {
            heldItem = ImageIO.read(new File(itemOnHand.getItemImageFilePath()));
        } catch (IOException e) {
            e.printStackTrace();
            try{
                heldItem = ImageIO.read(new File("assets/itemSprites/None.png"));
            }catch(IOException f){
                f.printStackTrace();
            }
        }

        if(itemOnHand.getQuantity()>0)
        {
            if(!g2D.drawImage(heldItem, 76, nextLineHeight, 48, 48,  this)){
                try{
                    heldItem = ImageIO.read(new File("assets/itemSprites/None.png"));
                    g2D.drawImage(heldItem, 76, nextLineHeight, 48, 48,  this);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            g2D.drawString(""+itemOnHand.getQuantity(),115,nextLineHeight+50);
        }else{
            try{
                heldItem = ImageIO.read(new File("assets/itemSprites/None.png"));
                g2D.drawImage(heldItem, 76, nextLineHeight, 48, 48,  this);
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        g2D.drawImage(nextItem, 126, nextLineHeight, 48, 48,  this);

        nextLineHeight += 60;

        ArrayList<Lailaps> lailapses = this.dungeon.getFloor().getLailapses();
        for(Lailaps lailaps : lailapses)
        {
            g2D.drawImage(SpriteCache.getImage("assets/dungeonSprites/LailapsIcon.png"),10,nextLineHeight,128,128,this);
            nextLineHeight += 140;
            int LailapsMaxHp = (int)Math.ceil(lailaps.getMaxHp());
            int LailapsFullHearts = (int)Math.floor(lailaps.getHp());
            boolean LailapsPartial = (lailaps.getHp()%1)>0;
            for (int i = 0; i<LailapsMaxHp; i++)
            {
                g2D.drawImage(emptyHeart, 13 + (30*(i%6)), nextLineHeight, 24, 24,  this);
                if(i < LailapsFullHearts)
                {
                    g2D.drawImage(fullHeart, 13 + (30*(i%6)), nextLineHeight, 24, 24,  this);
                }else if (i==LailapsFullHearts && LailapsPartial) {
                    int percent = (int) (lailaps.getHp()%1 * 16);
                    g2D.drawImage(fullHeart, 13 + (30*(i%6)), nextLineHeight, (10 + (30*(i%6))) + 2*percent, nextLineHeight + 24, 0, 0, percent, 16, this);
                }
                if(i%6 == 5 && LailapsMaxHp%6 > 0)
                {
                    nextLineHeight += 30;
                }
            }
        }
    }
}
