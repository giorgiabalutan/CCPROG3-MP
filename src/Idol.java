
import java.io.Serializable;

/**
 * Represents Idols that the player must rescue.
 */
public class Idol implements Serializable
{
    /**
     * The name of the Idol.
     */
    private String name;
    /**
     * The name of the {@link Dungeon} that the Idol is trapped in.
     */
    private String dungeonName;
    /**
     * The {@link DungeonCode} of the {@code Dungeon} that the Idol is trapped in.
     */
    private DungeonCode dungeonCode;
    /**
     * How many times the Idol has been saved.
     */
    private int timesSaved;
    
    private String idolImageFilePath;

    /**
     * Constructs an Idol based on the provided idolNumber. The following numbers correspond to:
     * <ul>
     * <li>1 - Chika Takami trapped in Yasudaya Ryokan.
     * <li>2 - You Watanabe trapped in Izu-Mito Sea Paradise.
     * <li>3 - Riko Sakurauchi trapped in Numazu Deep Sea Aquarium.
     * <li>4 - Hanamaru Kunikida trapped in Shougetsu Confectionary.
     * <li>5 - Ruby Kurosawa trapped in Nagahama Castle Ruins.
     * <li>6 - Dia Kurosawa trapped in Numazugoyotei.
     * <li>7 - Kanan Matsuura trapped in Uchiura Bay Pier.
     * <li>8 - Mari Ohara trapped in Awashima Marine Park.
     * </ul>
     * 
     * @param idolNumber determines which Idol to construct.
     */
    public Idol(int idolNumber)
    {
        switch(idolNumber)
        {
            case 1: 
                this.name = "Chika Takami";
                this.dungeonName = "Yasudaya Ryokan";
                this.dungeonCode = DungeonCode.YASUDAYA_RYOKAN;
                this.idolImageFilePath = "/assets/chikaTakami.png";
                break;
            case 2:
                this.name = "You Watanabe";
                this.dungeonName = "Izu-Mito Sea Paradise";
                this.dungeonCode = DungeonCode.IZU_MITO_SEA_PARADISE;
                this.idolImageFilePath = "/assets/youWatanabe.png";
                break;
            case 3:
                this.name = "Riko Sakurauchi";
                this.dungeonName = "Numazu Deep Sea Aquarium";
                this.dungeonCode = DungeonCode.NUMAZU_DEEP_SEA_AQUARIUM;
                this.idolImageFilePath = "/assets/rikoSakurauchi.png";
                break;
            case 4:
                this.name = "Hanamaru Kunikida";
                this.dungeonName = "Shougetsu Confectionary";
                this.dungeonCode = DungeonCode.SHOUGETSU_CONFECTIONARY;
                this.idolImageFilePath = "/assets/hanamaruKunikida.png";
                break;
            case 5:
                this.name = "Ruby Kurosawa";
                this.dungeonName = "Nagahama Castle Ruins";
                this.dungeonCode = DungeonCode.NAGAHAMA_CASTLE_RUINS;
                this.idolImageFilePath = "/assets/rubyKurosawa.png";
                break;
            case 6:
                this.name = "Dia Kurosawa";
                this.dungeonName = "Numazugoyotei";
                this.dungeonCode = DungeonCode.NUMAZUGOYOTEI;
                this.idolImageFilePath = "/assets/diaKurosawa.png";
                break;
            case 7:
                this.name = "Kanan Matsuura";
                this.dungeonName = "Uchiura Bay Pier";
                this.dungeonCode = DungeonCode.UCHIURA_BAY_PIER;
                this.idolImageFilePath = "/assets/kananMatsuura.png";
                break;
            case 8:
                this.name = "Mari Ohara";
                this.dungeonName = "Awashima Marine Park";
                this.dungeonCode = DungeonCode.AWASHIMA_MARINE_PARK;
                this.idolImageFilePath = "/assets/mariOhara.png";
                break;
        }
    }

    /**
     * Returns the Idol's name.
     * 
     * @return the idol name.
     */
    public String getIdolName()
    {
        return this.name;
    }
    /**
     * Returns the {@code Dungeon}'s name.
     * 
     * @return the {@code Dungeon} name.
     */
    public String getDungeonName()
    {
        return this.dungeonName;
    }
    /**
     * Returns the {@code DungeonCode}.
     * 
     * @return the {@code DungeonCode}.
     */
    public DungeonCode getDungeonCode()
    {
        return this.dungeonCode;
    }
    
    public String getIdolImageFilePath()
    {
        return this.idolImageFilePath;
    }
}