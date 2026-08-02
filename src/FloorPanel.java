import java.awt.*;
import javax.swing.*;
import model.Direction;
import model.Model;
import model.Player;
import model.creature.Creature;
import model.dungeon.Dungeon;
import model.dungeon.DungeonModifier;
import model.dungeon.Floor;
import model.dungeon.Tile;
import model.loot.Loot;
import model.structure.Exit;
import model.structure.Structure;
import model.structure.StructureType;
public class FloorPanel extends JPanel
{
    /**
     * How much to scale the sprite by. Just 2 here usually.
     */
    private int tileScale;
    /**
     * How many Rows can be fit on the screen.
     */
    private int viewRows;
    /**
     * How many Columns can be fit on the screen.
     */
    private int viewCols;
    /**
     * {@link Model} to grab data from.
     */
    private Model model;
    /**
     * {@link Dungeon} to grab data from.
     */
    private Dungeon dungeon;
    /**
     * {@link Floor} to grab data from.
     */
    private Floor floor;
    /**
     * {@link Player} to grab data from.
     */
    private Player player;
    /**
     * Grid of the dungeon to print.
     */
    private Tile[][] grid;
    /**
     * How many Rows the grid has.
     */
    private int sizeRow;
    /**
     * If the number of rows is small enough to center.
     */
    private boolean centerRow;
    /**
     * How many Columns the grid has.
     */
    private int sizeCol;
    /**
     * If the number of columns is small enough to center.
     */
    private boolean centerCol;
    /**
     * The number of the current floor.
     */
    private int floorNum;
    /**
     * Which frame of animation to draw in.
     */
    private int animationFrame;
    /**
     * Font to draw text with.
     */
    private Font defaultFont;

    /**
     * Constructor to initialize the panel.
     * The tile scale and how many rows and columns are set here.
     * 
     * @param model Model to grab data from.
     * @param x How far to the right this is.
     * @param panelHeight How tall this is.
     */
    public FloorPanel(Model model, int x, int panelHeight)
    {
        this.setBounds(x,0,700,panelHeight);
        this.setBackground(Color.LIGHT_GRAY);
        this.tileScale = 2; //700x540 px, 32x32px tiles,15x21 tiles
        this.viewRows = 15;
        this.viewCols = 21;
        this.model = model;
        this.player = model.getPlayer();
        this.dungeon = model.getDungeon();
        this.animationFrame = 0;
        defaultFont = new Font("Courier New", Font.BOLD, 20);
    }

    /**
     * Loads in the new floor to show.
     */
    public void loadFloor()
    {
        this.dungeon = model.getDungeon();
        this.floor = model.getDungeon().getFloor();
        this.floorNum = model.getDungeon().getFloorNum();
        this.player = model.getPlayer();
        this.grid = this.floor.getGrid();
        this.sizeRow = floor.getSizeY();
        this.sizeCol = floor.getSizeX();
        this.centerRow = false;
        this.centerCol = false;
        if(sizeRow <= 15)
        {
            this.centerRow = true;
        }
        if(sizeCol <= 21)
        {
            this.centerCol = true;
        }
    }
    /**
     * Calculates the Row/Column to start painting on.
     * 
     * @param playerPos Position of the Player on the Row/Column.
     * @param actualSize Size of the Grid on the Row/Column.
     * @param halfViewSize Half of how many Rows/Columns fits on screen.
     * @param viewSize How many Rows/Columns fits on screen.
     * @return Which Row/Column of the grid to start printing.
     */
    private int getStartPaint(int playerPos, int actualSize, int halfViewSize, int viewSize)
    {
        int start = playerPos-halfViewSize;
        int maxStart = actualSize - viewSize;

        start = Math.max(start, 0);
        start = Math.min(start, maxStart);

        return start;
    }

    /**
     * Increments AnimationFrame by one.
     * Resets it to zero at a high number to prevent overflow.
     * Calls a Repaint to shift {@code Creature} sprites.
     */
    public void tickAnimation()
    {
        this.animationFrame = (animationFrame + 1) % 720720; //Divisible from one to sixteen waow
        repaint();
    }

    /**
     * Draw Yohane on the Grid.
     * 
     * @param g2D graphics2D object to draw with.
     * @param colLoc column location to draw on.
     * @param rowLoc row location to draw on.
     * @param spriteWidth width to scale the sprite.
     * @param spriteHeight height to scale the sprite.
     * @param direction which sprite direction to draw.
     * @param idle decide to draw either an idle or a walking sprite.
     */
    private void drawYohane(Graphics2D g2D, int colLoc, int rowLoc, int spriteWidth, int spriteHeight, Direction direction, Boolean idle)
    {
        String filepath = "assets/dungeonSprites/yohaneSprites/";
        if(idle)
        {
            filepath += "idle_";
        }else{
            filepath += "walk_";
        }
        switch(direction)
        {
            case UP:
                filepath += "up";
                break;
            case DOWN:
                filepath += "down";
                break;
            case LEFT:
                filepath += "left";
                break;
            case RIGHT:
                filepath += "right";
                break;
        }
        if(!idle)
        {
            switch(animationFrame%2)
            {
                case 0:
                    filepath += "_1.png";
                    break;
                case 1:
                    filepath += "_2.png";
                    break;
            }
        }else{
            filepath += ".png";
        }
        g2D.drawImage(SpriteCache.getImage(filepath), colLoc, rowLoc, spriteWidth, spriteHeight, this);
    }

    /**
     * Draw a creature.
     * 
     * @param g2D graphics2D object to draw with.
     * @param colLoc column location to draw on.
     * @param rowLoc row location to draw on.
     * @param spriteWidth width to scale the sprite.
     * @param spriteHeight height to scale the sprite.
     * @param idle decide to draw either an idle or a walking sprite.
     */
    private void drawCreature(Graphics g2D, int colLoc, int rowLoc, int spriteWidth, int spriteHeight, Creature creature)
    {
        String filepath = "assets/dungeonSprites/creatureSprites/";
        filepath += creature.getName() + "/" + creature.getName() + "_";
        if(creature.isIdle())
        {
            filepath += "idle_";
        }else{
            filepath += "walk_";
        }
        switch(creature.getDirection())
        {
            case UP:
                filepath += "up";
                break;
            case DOWN:
                filepath += "down";
                break;
            case LEFT:
                filepath += "left";
                break;
            case RIGHT:
                filepath += "right";
                break;
        }
        if(!creature.isIdle())
        {
            switch(animationFrame%2)
            {
                case 0:
                    filepath += "_1.png";
                    break;
                case 1:
                    filepath += "_2.png";
                    break;
            }
        }else{
            filepath += ".png";
        }
        g2D.drawImage(SpriteCache.getImage(filepath), colLoc, rowLoc, spriteWidth, spriteHeight, this);
    }

    /**
     * Paints the current floor of the Dungeon.
     * Also shows the Dungeon Name and Dungeon Modifiers.
     * Images to be drawn are loaded using the {@link SpriteCache} to avoid frequent calls.
     * 
     * Is repainted every animation tick and every time the Player does something.
     */
    @Override
    public void paintComponent(Graphics g) {
        // System.out.println("FloorPanel created: " + System.identityHashCode(this));
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setFont(defaultFont);

        int nextOverlayLineHeight = 20;
        g2D.drawString("" + dungeon.getName() + "", 10, nextOverlayLineHeight);
        nextOverlayLineHeight += 20;

        int playerRow = player.getPosition().getPosY();
        int startRow = getStartPaint(playerRow,this.sizeRow,viewRows/2,viewRows);
        int playerCol = player.getPosition().getPosX();
        int startCol = getStartPaint(playerCol, sizeCol,viewCols/2,viewCols);
        if(!centerRow) //More rows than can fit, 15 rows
        {
            if(!centerCol){
                for (int i = startRow; i < startRow+viewRows; i++) {
                    for(int j = startCol; j < startCol+viewCols; j++){
                        g2D.drawImage(SpriteCache.getImage("assets/dungeonSprites/structureSprites/Passable.png"), 14+((j-startCol)*16*tileScale), 30+((i-startRow)*16*tileScale), (16*tileScale), (16*tileScale), this);
                        for(Structure struct: grid[i][j].getStructures())
                        {
                            if(struct instanceof Exit exit && exit.isHidden())
                            {
                                //Dont Draw
                            }else{
                                g2D.drawImage(SpriteCache.getImage(struct.getImageFilePath()), 14+((j-startCol)*16*tileScale), 30+((i-startRow)*16*tileScale), (16*tileScale), (16*tileScale), this);
                            }
                        }
                        for(Loot loot: grid[i][j].getLoots())
                        {
                            g2D.drawImage(SpriteCache.getImage(loot.getImageFilePath()), 14+((j-startCol)*16*tileScale), 30+((i-startRow)*16*tileScale), (16*tileScale), (16*tileScale), this);
                        }
                        for(Creature creature: grid[i][j].getCreatures())
                        {
                            // g2D.drawImage(SpriteCache.getImage(creature.getImageFilePath()), 14+((j-startCol)*16*tileScale), padRow+(i*16*tileScale), (16*tileScale), (16*tileScale), this);
                            drawCreature(g2D, 14+((j-startCol)*16*tileScale), 30+((i-startRow)*16*tileScale), (16*tileScale), (16*tileScale), creature);
                        }

                        if(i==playerRow && j==playerCol)
                        {
                            drawYohane(g2D, 14+((j-startCol)*16*tileScale), 30+((i-startRow)*16*tileScale), (16*tileScale), (16*tileScale), this.player.getDirection(), this.player.isIdle());
                        }
                    }
                }                
            }else{
                int padCol = (this.getWidth()-(16*tileScale*sizeCol))/2;
                for (int i = startRow; i < startRow+viewRows; i++) {
                    for(int j = 0; j < sizeCol; j++){
                        g2D.drawImage(SpriteCache.getImage("assets/dungeonSprites/structureSprites/Passable.png"), padCol+(j*16*tileScale), 30+((i-startRow)*16*tileScale), (16*tileScale), (16*tileScale), this);
                        for(Structure struct: grid[i][j].getStructures())
                        {
                            if(struct instanceof Exit exit && exit.isHidden())
                            {
                                //Dont Draw
                            }else{
                                g2D.drawImage(SpriteCache.getImage(struct.getImageFilePath()), padCol+(j*16*tileScale), 30+((i-startRow)*16*tileScale), (16*tileScale), (16*tileScale), this);
                            }
                        }
                        for(Loot loot: grid[i][j].getLoots())
                        {
                            g2D.drawImage(SpriteCache.getImage(loot.getImageFilePath()), padCol+(j*16*tileScale), 30+((i-startRow)*16*tileScale), (16*tileScale), (16*tileScale), this);
                        }
                        for(Creature creature: grid[i][j].getCreatures())
                        {
                            // g2D.drawImage(SpriteCache.getImage(creature.getImageFilePath()), 14+((j-startCol)*16*tileScale), padRow+(i*16*tileScale), (16*tileScale), (16*tileScale), this);
                            drawCreature(g2D, padCol+(j*16*tileScale), 30+((i-startRow)*16*tileScale), (16*tileScale), (16*tileScale), creature);
                        }

                        if(i==playerRow && j==playerCol)
                        {
                            drawYohane(g2D, padCol+(j*16*tileScale), 30+((i-startRow)*16*tileScale), (16*tileScale), (16*tileScale), this.player.getDirection(), this.player.isIdle());
                        }
                    }
                }       
            }
        }else{
            //
            if(!centerCol)
            {
                // System.out.println("Test");
                int padRow = (this.getHeight()-(16*tileScale*sizeRow))/2;
                // System.out.println((startRow));
                for (int i = 0; i < sizeRow; i++) {
                    for(int j = startCol; j < startCol+viewCols; j++){
                        g2D.drawImage(SpriteCache.getImage("assets/dungeonSprites/structureSprites/Passable.png"), 14+((j-startCol)*16*tileScale), padRow+(i*16*tileScale), (16*tileScale), (16*tileScale), this);
                        for(Structure struct: grid[i][j].getStructures())
                        {
                            if(struct.getType() == StructureType.EXIT && ((Exit) struct).isHidden())
                            {
                                //Dont Draw
                                // System.out.println("Test2");
                            }else{
                                g2D.drawImage(SpriteCache.getImage(struct.getImageFilePath()), 14+((j-startCol)*16*tileScale), padRow+(i*16*tileScale), (16*tileScale), (16*tileScale), this);
                            }
                            // System.out.println(padRow+(i*16*tileScale));
                        }
                        for(Loot loot: grid[i][j].getLoots())
                        {
                            g2D.drawImage(SpriteCache.getImage(loot.getImageFilePath()), 14+((j-startCol)*16*tileScale), padRow+(i*16*tileScale), (16*tileScale), (16*tileScale), this);
                        }
                        for(Creature creature: grid[i][j].getCreatures())
                        {
                            // g2D.drawImage(SpriteCache.getImage(creature.getImageFilePath()), 14+((j-startCol)*16*tileScale), padRow+(i*16*tileScale), (16*tileScale), (16*tileScale), this);
                            drawCreature(g2D, 14+((j-startCol)*16*tileScale), padRow+(i*16*tileScale), (16*tileScale), (16*tileScale), creature);
                        }

                        if(i==playerRow && j==playerCol)
                        {
                            drawYohane(g2D, 14+((j-startCol)*16*tileScale), padRow+(i*16*tileScale), (16*tileScale), (16*tileScale), this.player.getDirection(), this.player.isIdle());
                        }
                    }
                }   
            }else{
                int padCol = (this.getWidth()-(16*tileScale*sizeCol))/2;
                int padRow = (this.getHeight()-(16*tileScale*sizeRow))/2;
                for (int i = 0; i < sizeRow; i++) {
                    for(int j = 0; j < sizeCol; j++){
                        g2D.drawImage(SpriteCache.getImage("assets/dungeonSprites/structureSprites/Passable.png"), padCol+(j*16*tileScale), padRow+(i*16*tileScale), (16*tileScale), (16*tileScale), this);
                        for(Structure struct: grid[i][j].getStructures())
                        {
                            if(struct instanceof Exit exit && exit.isHidden())
                            {
                                //Dont Draw
                            }else{
                                g2D.drawImage(SpriteCache.getImage(struct.getImageFilePath()), padCol+(j*16*tileScale), padRow+(i*16*tileScale), (16*tileScale), (16*tileScale), this);
                            }
                            // System.out.println(padRow+(i*16*tileScale));
                        }
                        for(Loot loot: grid[i][j].getLoots())
                        {
                            g2D.drawImage(SpriteCache.getImage(loot.getImageFilePath()), padCol+(j*16*tileScale), padRow+(i*16*tileScale), (16*tileScale), (16*tileScale), this);
                        }
                        for(Creature creature: grid[i][j].getCreatures())
                        {
                            // g2D.drawImage(SpriteCache.getImage(creature.getImageFilePath()), 14+((j-startCol)*16*tileScale), padRow+(i*16*tileScale), (16*tileScale), (16*tileScale), this);
                            drawCreature(g2D, padCol+(j*16*tileScale), padRow+(i*16*tileScale), (16*tileScale), (16*tileScale), creature);
                        }

                        if(i==playerRow && j==playerCol)
                        {
                            drawYohane(g2D, padCol+(j*16*tileScale), padRow+(i*16*tileScale), (16*tileScale), (16*tileScale), this.player.getDirection(), this.player.isIdle());
                        }
                    }
                }    
            }
        }

        int nextModifierDisplay = 10;

        for(DungeonModifier mod : this.dungeon.getDungeonModifiers())
        {
            // System.out.println("Test");
            switch(mod)
            {
                case STRONGER_HEAT:
                    g2D.drawImage(SpriteCache.getImage("assets/dungeonSprites/dungeonModifierSprites/Stronger_Heat.png"),nextModifierDisplay,500,32,32,this);
                    break;
                case STRONGER_HEALS:
                    g2D.drawImage(SpriteCache.getImage("assets/dungeonSprites/dungeonModifierSprites/Stronger_Heals.png"),nextModifierDisplay,500,32,32,this);
                    break;
                case STRONGER_BATS:
                    g2D.drawImage(SpriteCache.getImage("assets/dungeonSprites/dungeonModifierSprites/Stronger_Bats.png"),nextModifierDisplay,500,32,32,this);
                    break;
                case STRONGER_SKELETONS:
                    g2D.drawImage(SpriteCache.getImage("assets/dungeonSprites/dungeonModifierSprites/Stronger_Skeletons.png"),nextModifierDisplay,500,32,32,this);
                    break;
                case STRONGER_WALLS:
                    g2D.drawImage(SpriteCache.getImage("assets/dungeonSprites/dungeonModifierSprites/Stronger_Walls.png"),nextModifierDisplay,500,32,32,this);
                    break;
                case FASTER_BATS:
                    g2D.drawImage(SpriteCache.getImage("assets/dungeonSprites/dungeonModifierSprites/Faster_Bats.png"),nextModifierDisplay,500,32,32,this);
                    break;
                case FASTER_SKELETONS:
                    g2D.drawImage(SpriteCache.getImage("assets/dungeonSprites/dungeonModifierSprites/Faster_Skeletons.png"),nextModifierDisplay,500,32,32,this);
                    break;
                case CRIPPLED_BATS:
                    g2D.drawImage(SpriteCache.getImage("assets/dungeonSprites/dungeonModifierSprites/Crippled_Bats.png"),nextModifierDisplay,500,32,32,this);
                    break;
                case REBREATHER:
                    g2D.drawImage(SpriteCache.getImage("assets/dungeonSprites/dungeonModifierSprites/Rebreather.png"),nextModifierDisplay,500,32,32,this);
                    break;
                case HOT_WATERS:
                    g2D.drawImage(SpriteCache.getImage("assets/dungeonSprites/dungeonModifierSprites/Hot_Waters.png"),nextModifierDisplay,500,32,32,this);
                    break;
                case GOLD_TAX:
                    g2D.drawImage(SpriteCache.getImage("assets/dungeonSprites/dungeonModifierSprites/Gold_Tax.png"),nextModifierDisplay,500,32,32,this);
                    break;
            }
            nextModifierDisplay += 35;
        }
    }
}