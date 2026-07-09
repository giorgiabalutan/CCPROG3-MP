public class Idol
{
    String name;
    String dungeonName;
   // DungeonCode dungeonCode;
    int timesSaved;

    public Idol(int idolNumber)
    {
        switch(idolNumber)
        {
            case 1: 
                this.name = "Chika Takami";
                this.dungeonName = "Yasudaya Ryokan";
                //this.dungeonCode = DungeonCode.YASUDAYA_RYOKAN;
                break;
            case 2:
                this.name = "You Watanabe";
                this.dungeonName = "Izu-Mito Sea Paradise";
                //this.dungeonCode = DungeonCode.IZU_MITO_SEA_PARADISE;
                break;
            case 3:
                this.name = "Riko Sakurauchi";
                this.dungeonName = "Numazu Deep Sea Aquarium";
               // this.dungeonCode = DungeonCode.NUMAZU_DEEP_SEA_AQUARIUM;
                break;
            case 4:
                this.name = "Hanamaru Kunikida";
                this.dungeonName = "Shougetsu Confectionary";
               // this.dungeonCode = DungeonCode.SHOUGETSU_CONFECTIONARY;
                break;
            case 5:
                this.name = "Ruby Kurosawa";
                this.dungeonName = "Nagahama Castle Ruins";
                //this.dungeonCode = DungeonCode.NAGAHAMA_CASTLE_RUINS;
                break;
            case 6:
                this.name = "Dia Kurosawa";
                this.dungeonName = "Numazugoyotei";
                //this.dungeonCode = DungeonCode.NUMAZUGOYOTEI;
                break;
            case 7:
                this.name = "Kanan Matsuura";
                this.dungeonName = "Uchiura Bay Pier";
                //this.dungeonCode = DungeonCode.UCHIURA_BAY_PIER;
                break;
            case 8:
                this.name = "Mari Ohara";
                this.dungeonName = "Awashima Marine Park";
                //this.dungeonCode = DungeonCode.AWASHIMA_MARINE_PARK;
                break;
        }
    }

    public String getIdolName()
    {
        return this.name;
    }

    public String getDungeonName()
    {
        return this.dungeonName;
    }

    // public DungeonCode getDungeonCode()
    // {
    //     return this.dungeonCode;
    // }
}