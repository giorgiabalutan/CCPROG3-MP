import java.awt.*;
import javax.swing.*;
import model.Model;
import model.Player;
import model.creature.Creature;
import model.dungeon.Floor;
import model.dungeon.Tile;
import model.loot.Loot;
import model.structure.Structure;
public class FloorPanel extends JPanel
{
    private int tileScale;
    private int viewRows;
    private int viewCols;
    private Model model;
    private Floor floor;
    private Player player;
    private Tile[][] grid;
    private int sizeRow;
    private boolean centerRow;
    private int sizeCol;
    private boolean centerCol;


    public FloorPanel(Model model, int x, int panelHeight)
    {
        this.setBounds(x,0,700,panelHeight);
        this.setBackground(Color.LIGHT_GRAY);
        this.tileScale = 2; //700x540 px, 32x32px tiles,15x21 tiles
        this.viewRows = 15;
        this.viewCols = 21;
        this.model = model;
        this.player = model.getPlayer();
    }

    public void loadFloor()
    {
        this.floor = model.getDungeon().getFloor();
        this.grid = this.floor.getGrid();
        this.sizeRow = floor.getSizeY();
        this.sizeCol = floor.getSizeX();
        this.centerRow = false;
        this.centerCol = false;
        if(sizeRow <= 33)
        {
            this.centerRow = true;
        }
        if(sizeCol <= 43)
        {
            this.centerCol = true;
        }
    }

    private int getStartPaint(int playerPos, int actualSize, int halfViewSize, int viewSize)
    {
        int start = playerPos-halfViewSize;
        int maxStart = actualSize - viewSize;

        start = Math.max(start, 0);
        start = Math.min(start, maxStart);

        return start;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        int playerRow = player.getPosition().getPosY();
        int startRow = getStartPaint(playerRow,this.sizeRow,viewRows/2,viewRows);
        int playerCol = player.getPosition().getPosX();
        int startCol = getStartPaint(playerCol, sizeCol,viewCols/2,viewCols);
        if(!centerRow) //More rows than can fit, 15 rows
        {
            if(!centerCol){
                for (int i = startRow; i < startRow+viewRows; i++) {
                    for(int j = startCol; j < startCol+viewCols; j++){
                        if(grid[i][j].getStructures().isEmpty())
                        {
                            g2D.drawImage(SpriteCache.getImage("assets/structureSprites/Passable.png"), 14+((j-startCol)*16*tileScale), 30+((i-startRow)*16*tileScale), (16*tileScale), (16*tileScale), this);
                        }
                        for(Structure struct: grid[i][j].getStructures())
                        {
                            g2D.drawImage(SpriteCache.getImage(struct.getImageFilePath()), 14+((j-startCol)*16*tileScale), 30+((i-startRow)*16*tileScale), (16*tileScale), (16*tileScale), this);
                        }
                    }
                }                
            }else{
                //
                // System.out.println("TEST2");
            }
        }else{
            //
            if(!centerCol)
            {
                int padRow = (this.getHeight()-(16*tileScale*sizeRow))/2;
                // System.out.println((startRow));
                for (int i = 0; i < sizeRow; i++) {
                    for(int j = startCol; j < startCol+viewCols; j++){
                        if(grid[i][j].getStructures().isEmpty())
                        {
                            g2D.drawImage(SpriteCache.getImage("assets/dungeonSprites/structureSprites/Passable.png"), 14+((j-startCol)*16*tileScale), padRow+(i*16*tileScale), (16*tileScale), (16*tileScale), this);
                            // System.out.println(padRow+(i*16*tileScale));
                        }
                        for(Structure struct: grid[i][j].getStructures())
                        {
                            g2D.drawImage(SpriteCache.getImage(struct.getImageFilePath()), 14+((j-startCol)*16*tileScale), padRow+(i*16*tileScale), (16*tileScale), (16*tileScale), this);
                            // System.out.println(padRow+(i*16*tileScale));
                        }
                        for(Loot loot: grid[i][j].getLoots())
                        {
                            g2D.drawImage(SpriteCache.getImage(loot.getImageFilePath()), 14+((j-startCol)*16*tileScale), padRow+(i*16*tileScale), (16*tileScale), (16*tileScale), this);
                        }
                        for(Creature creature: grid[i][j].getCreatures())
                        {
                            g2D.drawImage(SpriteCache.getImage(creature.getImageFilePath()), 14+((j-startCol)*16*tileScale), padRow+(i*16*tileScale), (16*tileScale), (16*tileScale), this);
                        }

                        if(i==playerRow && j==playerCol)
                        {
                            g2D.drawImage(SpriteCache.getImage("assets/dungeonSprites/yohaneSprites/idle_front.png"), 14+((j-startCol)*16*tileScale), padRow+(i*16*tileScale), (16*tileScale), (16*tileScale), this);
                        }
                    }
                }   
            }else{
                // System.out.println("TEST4");
            }
        }
    }
}