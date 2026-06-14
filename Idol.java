public class Idol
{
    String name;
    String dungeonName;
    int timesSaved;

    public Idol(int idolNumber)
    {
        switch(idolNumber)
        {
            case 1: 
                this.name = "Chika Takami";
                this.dungeonName = "Yasudaya Ryokan";
                break;
            case 2:
                this.name = "You Watanabe";
                this.dungeonName = "Izu-Mito Sea Paradise";
                break;
            case 3:
                this.name = "Riko Sakurauchi";
                this.dungeonName = "Numazu Deep Sea Aquarium";
                break;
            case 4:
                this.name = "Hanamaru Kunikida";
                this.dungeonName = "Shougetsu Confectionary";
                break;
            case 5:
                this.name = "Ruby Kurosawa";
                this.dungeonName = "Nagahama Castle Ruins";
                break;
            case 6:
                this.name = "Dia Kurosawa";
                this.dungeonName = "Numazugoyotei";
                break;
            case 7:
                this.name = "Kanan Matsuura";
                this.dungeonName = "Uchiura Bay Pier";
                break;
            case 8:
                this.name = "Mari Ohara";
                this.dungeonName = "Awashima Marine Park";
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
}