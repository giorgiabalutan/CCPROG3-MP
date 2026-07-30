import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import model.CombatLogEntry;
import model.Model;

public class CombatLogPanel extends JPanel {
    private ArrayList<CombatLogEntry> combatLogs;
    private Font defaultFont;
    private Model model;

    public CombatLogPanel(Model model, int x, int panelHeight){
        this.setBounds(x,0,200,panelHeight);
        this.setBackground(Color.YELLOW);//Placeholders
        this.model = model;

        this.defaultFont = new Font("Courier New", Font.BOLD, 10);
    }

    public void loadFloor()
    {
        this.combatLogs = this.model.getDungeon().getFloor().getCombatLogs();
    }

    //I did this wrong but like what if i need it so its like this for now
    // private ArrayList<String> wrapTextDeprecated(ArrayList<CombatLogEntry> entries, FontMetrics fm, int maxWidth)
    // {
    //     ArrayList<String> finalLines = new ArrayList<>();
    //     String line = "";
    //     for(CombatLogEntry entry : entries)
    //     {
    //         String[] words = entry.getMessage().split(" ");
    //         for(String word : words)
    //         {
    //             String testLine = line.isEmpty() ? word : line + " " + word;
    //             int testWidth = fm.stringWidth(testLine);
    //             if (testWidth > 180)
    //             {
    //                 if (!line.isEmpty())
    //                 {
    //                     finalLines.add(line);
    //                 }
    //                 line = word;
    //             } else {
    //                 line = testLine;
    //             }
    //         }
    //         if(!line.isEmpty())
    //         {
    //             finalLines.add(line);
    //             line = "";
    //         }
    //     }
    //     return finalLines;
    // }

    private ArrayList<String> wrapText(CombatLogEntry entry, FontMetrics fm, int maxWidth)
    {
        ArrayList<String> finalLines = new ArrayList<>();
        String line = "";
        String[] words = entry.getMessage().split(" ");
        for(String word : words)
        {
            String testLine = line.isEmpty() ? word : line + " " + word;
            int testWidth = fm.stringWidth(testLine);
            if (testWidth > 180)
            {
                if (!line.isEmpty())
                {
                    finalLines.add(line);
                }
                line = word;
            } else {
                line = testLine;
            }
        }
        if(!line.isEmpty())
        {
            finalLines.add(line);
            line = "";
        }
        return finalLines;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setFont(defaultFont);
        int lineHeight = 15;
        FontMetrics fm = g2D.getFontMetrics();
        int nextLineY = this.getHeight()-lineHeight;

        boolean Stop = false;
        for(int i = combatLogs.size() - 1; i >= 0 && !Stop; i--)
        {
            CombatLogEntry entry = combatLogs.get(i);
            ArrayList<String> lines = wrapText(entry, fm, 180);

            int entryHeight = lines.size() * lineHeight;
            nextLineY -= entryHeight;

            int drawY = nextLineY + lineHeight;
            for(String line : lines)
            {
                g2D.drawString(line, 10, drawY);
                drawY += lineHeight;
            }

            if(nextLineY < 0)
            {
                Stop = true;
            }
        }
    }
}
