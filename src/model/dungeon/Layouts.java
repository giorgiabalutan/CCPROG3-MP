package model.dungeon;

import model.creature.Bat;
import model.loot.Treasure;
import model.structure.Exit;
import model.structure.Heat;
import model.structure.Spawn;
import model.structure.Spike;
import model.structure.Wall;
import model.structure.Water;

/**
 * A library to hold preset layouts for {@link Floor} maps.
 * <p>
 * Each layout is an array of Strings where each character represents a {@link Tile}.
 * Each character represents:
 * <ul>
 * <li>S - {@link Spawn}
 * <li>E - {@link Exit}
 * <li>* - {@link Border}
 * <li>v - {@link Wall}
 * <li>x - {@link Spike}
 * <li>w - {@link Water}
 * <li>h - {@link Heat}
 * <li>b - {@link Bat}
 * <li>T - {@link Treasure}
 * <li>. - Empty {@code Tile}
 * </ul>
 */
public class Layouts
{
    /**
     * Private Constructor to prevent Instantiation. This class is meant to provide static data.
     */
    private Layouts(){}

    //YASUDAYA_RYOKAN Maps
    /**
     * A map with four pools of water.
     */
    public static final String[] FOUR_POOLS = {
        "********************",
        "*S................b*",
        "*..www........www..*",
        "*x.www...b...wwbww.*",
        "*xvvvvvvvv....www.x*",
        "*x.www...vvvvvvvvvx*",
        "*.wwbww..b....www.x*",
        "*b.www........www..*",
        "*Tb...............E*",
        "********************",
    };
    /**
     * A map with a 2d representation of a hot body of water.
     */
    public static final String[] PISKEL_ART_POOL = {
        "*****************************************",
        "*S......................................*",
        "*..........h.......b......h............s*",
        "*....h......hh..........h..........h....*",
        "*...h.........h.........h...........h..s*",
        "*...h....b.............h......b......h..*",
        "*..h.....wwwwwwwwwwwvwwwwwwwwwww....h...*",
        "*....wwwwwwwwwwwwwwvvvwwwwwwwwwwwwww...b*",
        "*wwwwwwwwwwwwwwwwwvvEvvwwwwwwwwwwwwwwwwT*",
        "*****************************************",
    };
    /**
     * Bat's Hotspring do not enter.
     */
    public static final String[] BAT_SPRING = {
        "********************",
        "*S......vvvv......b*",
        "*....vvvbww.vvv.b..*",
        "*.vvv....ww.b..vvv.*",
        "*v....b.wwww...b..v*",
        "*vb.wwwwwTTwwwww.wv*",
        "*v..wwwww..wwwww.wv*",
        "*.vvvb....b..b.vvv.*",
        "*.b..vvvwwwwvvv....*",
        "*...b...vvvv...b..E*",
        "********************",
    };
    /**
     * Wait did bro just copy the bat spring map.
     */
    public static final String[] SKELETON_SPRING = {
        "********************",
        "*S......vvvv......s*",
        "*....vvvsww.vvv.s..*",
        "*.vvv....ww.s..vvv.*",
        "*v....s.wwww...s..v*",
        "*vs.wwwwwTTwwwww.wv*",
        "*v..wwwww..wwwww.wv*",
        "*.vvvs....s..s.vvv.*",
        "*.s..vvvwwwwvvv....*",
        "*...s...vvvv...s..E*",
        "********************",
    };
    /**
     * This is getting out of hand...
     */
    public static final String[] LOVE_SPRING = {
        "********************",
        "*S..vvvx.s..xvvvs..*",
        "*..v....x.sx....vs.*",
        "*.v......vv......v.*",
        "*vs..wwwwbbwwww..sv*",
        "*.v..wwwbTTbwww..v.*",
        "*.v....wwbbww....v.*",
        "*.svv..wwwww...vvs.*",
        "*...svvvwwwwvvvs...*",
        "*......sxxxxs.....E*",
        "********************",
    };
    /**
     * The heater broke... the hot springs are now a fire hazard.
     */
    public static final String[] OVERHEATED_SPRING = {
        "****************************************",
        "*S............hhh......T.....hhhwwwwwww*",
        "*............hhhhh...b.......hhhwwwwwww*",
        "*hhh...b....hhwwwhh.......b..hhhwwwwwww*",
        "*hhhhh.....hhwwwwwhh....h.....hhhwwwwww*",
        "*hhhhhh....hhwwwwwhh...hwh....hhhwwwwww*",
        "*hhhhhhh...hhwwwwwhh..hwwwh....hhhwwwww*",
        "*wwwhhhhh...hhwwwhh....hwh......hhhhwww*",
        "*wwwwwhhhh...hhhhh......h...b....hhhhhh*",
        "*wwwwwwhhhh...hhh...b.............hhhhh*",
        "*wwwwwwhhhh.........................hhh*",
        "*wwwwwwhhhh.b............hhh..........*",
        "*wwwwwwhhhh.....h........hhhhh.........*",
        "*wwwwwwhhhh....hwh......hhwwwhh...b....*",
        "*wwwwwhhhh....hwwwh....hhwwwwwhh.......*",
        "*wwwhhhhh......hwh.....hhwwwwwhh....b..*",
        "*hhhhhhh........h......hhwwwwwhh.......*",
        "*hhhhhh.................hhwwwhh..b.....*",
        "*hhhhh.........T.........hhhhh........E*",
        "****************************************",
    };
    /**
     * A challenge path with an alternate Air Shoes path for additional treasures.
     */
    public static final String[] AIR_SHOES_CHALLENGE_2 = {
        "***************************",
        "*www.......v.S.w....vsTsTs*",
        "*ww........v...w....vTsTsT*",
        "*w........w*****....v.....*",
        "*........ww*****....v.....*",
        "*w..b...www*****..........*",
        "*ww......ww*****..........*",
        "*ww.......w*****..........*",
        "*w...b.....*****..........*",
        "*.........w*****..........*",
        "*........ww*****bbbbbbbbbb*",
        "*......bwww*****bbbbbbbbbb*",
        "*...b...www*****bbbbbbbbbb*",
        "*.......www*****..........*",
        "*.b......ww*****..........*",
        "*...b.....w**..w..........*",
        "*..........**.**..........*",
        "*ww........**.**..........*",
        "*www.......**.**..........*",
        "*www.......**w**.....v....*",
        "*wwww......v.T.*.....v....*",
        "*wwwwwwwwww*.E.*TsTsTv....*",
        "*swswswswsw*****sTsTsvTTTT*",
        "***************************",
    };
    /**
     * A challenge path with an alternate Air Shoes path for additional treasures.
     * This one loads in the water tiles for the alternate path.
     */
    public static final String[] AIR_SHOES_CHALLENGE_1 = {
        "***************************",
        "*...............wwwww.w.w.*",
        "*...............wwwwww.w.w*",
        "*...............wwwwwwwwww*",
        "*...............wwwwwwwwww*",
        "*...............wwwwwwwwww*",
        "*...............wwwwwwwwww*",
        "*...............wwwwwwwwww*",
        "*...............wwwwwwwwww*",
        "*...............wwwwwwwwww*",
        "*...............wwwwwwwwww*",
        "*...............wwwwwwwwww*",
        "*...............wwwwwwwwww*",
        "*...............wwwwwwwwww*",
        "*...............wwwwwwwwww*",
        "*...............wwwwwwwwww*",
        "*...............wwwwwwwwww*",
        "*...............wwwwwwwwww*",
        "*...............wwwwwwwwww*",
        "*...............wwwwwwwwww*",
        "*...............wwwwwwwwww*",
        "*...............w.w.wwwwww*",
        "*................w.w.wwwww*",
        "***************************",
    };
    /**
     * A not so cold chill water area without any foes.
     */
    public static final String[] CHILL_WATER_AREA_2 = {
        "*****************************************",
        "*....b..v....S.wwwTTwww.E....v..........*",
        "*b....b.v......wwwwwwww......v..b...b...*",
        "*.......vwww....wwwwww....wwwv........b.*",
        "*..b....vwwww............wwwwv.b...b....*",
        "*.b...b.vwwwww..........wwwwwv...b......*",
        "*....b..vwwwwwwwwwwwwwwwwwwwwv........b.*",
        "*****************************************",
    };
    /**
     * A not so cold chill water area without any foes.
     */
    public static final String[] CHILL_WATER_AREA_1 = {
        "*****************************************",
        "*wwwwwwww....................wwwwwwwwwww*",
        "*wwwwwwww....................wwwwwwwwwww*",
        "*wwwwwwww....................wwwwwwwwwww*",
        "*wwwwwwww....................wwwwwwwwwww*",
        "*wwwwwwww....................wwwwwwwwwww*",
        "*wwwwwwww....................wwwwwwwwwww*",
        "*****************************************",
    };
    /**
     * A map where every tile has a Heat tiles.
     * Has a firing squad of Skeletons.
     * Contains the non Heat Tiles.
     */
    public static final String[] THE_FLOOR_IS_LAVA_2 = {
        "*****************************************",
        "*S..x..........w..TT..w...............ws*",
        "*...x..........w......w...............ws*",
        "*...x...w......w......w......w........ws*",
        "*...x...w....................w........ws*",
        "*...x...w..b......b.......b..w........ws*",
        "*.......v....................v.....E..ws*",
        "*****************************************",
    };
    /**
     * A map where every tile has a Heat Tile.
     * Is loaded first to be drawn under other structures.
     */
    public static final String[] THE_FLOOR_IS_LAVA_1 = {
        "*****************************************",
        "*.hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh.*",
        "*hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh.*",
        "*hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh.*",
        "*hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh.*",
        "*hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh.*",
        "*hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh.*",
        "*****************************************",
    };
    /**
     * Rare Treasure Room for the Yasudaya Ryokan Dungeon
     */
    public static final String[] YASUDAYA_RYOKAN_TREASURE_ROOM = {
        "********************",
        "*wS..............Ew*",
        "*wwvvvvvvvvvvvvvvww*",
        "*sw.h...h..h...h.ws*",
        "*www.hbh.hh.hbh.www*",
        "*wswhb..hb.h..bhwsw*",
        "*wwwwxxxxxxxxxxwwww*",
        "*TTTwhhhh.ThhhhwTTT*",
        "*TTTwhThhhhhhThwTTT*",
        "********************",
    };


    //IZU MITO SEA PARADISE Maps
    /**
     * Breaking in to the aquarium.
     * Ok so theres 4 water dungeons and im running out of ideas ok.
     */
    public static final String[] BREAK_IN = {
        "***********************",
        "*....vvvsvsvsvsvsvvvww*",
        "*....vvvwwwwwwwwwvvvww*",
        "*....vvv.........vvvww*",
        "*....vvv.b.b.b.b.vvvww*",
        "*S...vvv....T....vvvTE*",
        "*....vvv.b.b.b.b.vvvww*",
        "*....vvv.........vvvww*",
        "*....vvvwwwwwwwwwvvvww*",
        "*....vvvsvsvsvsvsvvvww*",
        "***********************",
    };
    /**
     * A map with a 2d representation of a dolphin.
     */
    public static final String[] DOLPHIN_PISKEL_ART_1 = {
        "******************",
        "*S..........b...w*",
        "*..vvvvvbvvvv...s*",
        "*..vwwwvvwwwwvv..*",
        "*...vwvwwwwwwwwv.*",
        "*....vwwwwwwwTwv.*",
        "*....vwwwwwwwwwwv*",
        "*.b.vwwwwvvvvvvv.*",
        "*...vwwwv.vTv....*",
        "*...vwwv..vv.....*",
        "*...vwv...v......*",
        "*...vwv......b...*",
        "*...bvv..........*",
        "*..vvwvv......b..*",
        "*.vwwxwwv..b.....*",
        "*.vvvbvvv........*",
        "*ws.............E*",
        "******************",
    };
    /**
     * A map with a 2d representation of a dolphin.
     * This one loads in all the bats in the dolphin.
     */
    public static final String[] DOLPHIN_PISKEL_ART_2 = {
        "******************",
        "*................*",
        "*................*",
        "*....b...........*",
        "*........b.......*",
        "*................*",
        "*.......b..b.....*",
        "*................*",
        "*.....b..........*",
        "*................*",
        "*................*",
        "*................*",
        "*................*",
        "*................*",
        "*................*",
        "*................*",
        "*................*",
        "******************",
    };
    /**
     * 
     */
    public static final String[] STARFISH_PISKEL_ART = {
        "********************",
        "*..b...............*",
        "*.........xx..b...E*",
        "*....b...vx........*",
        "*.b.....vwv....b...*",
        "*.......vwv........*",
        "*...x...vwv...x....*",
        "*...xxvvwwwvvxx....*",
        "*....vwwwTwwwv...b.*",
        "*.b...vwwwwwv......*",
        "*......vwwwv...b...*",
        "*.....vwwwwwv......*",
        "*....vwv...vwv.....*",
        "*....vv.b...vwv..b.*",
        "*....x....b..vxx...*",
        "*...Sx.........x..T*",
        "********************",
    };
    /**
     * A maze of aquarium.
     * Very Original Idea.
     */
    public static final String[] AQUARIUM_MAZE_1 = {
        "****************************",
        "*.S...vvvvv................*",
        "*vvvv.vwwwx.vvvvvv.vvvvvvv.*",
        "*xwwv.vwvvv.vwwwwx.vwwwwwv.*",
        "*vvwv.vwv...vwvvvvvvvvvvwv.*",
        "*Tvwv.vxv.vvvwv.b......vwv.*",
        "*vvwv.....xwwwv...T.b..vwvv*",
        "*bvwv.vxv.vvvvxvvvvvvvvvwvb*",
        "*vvwv.vwv.....vwwwwwwwwwwvv*",
        "*.vwvvvwv.....vvvvvvvvvvwv.*",
        "*.vwwwwwvvvv...........vwvT*",
        "*.vvvvvwwwwv...........vwwx*",
        "*.....vvvvwv....E......vvvv*",
        "*.vvv....vwv...............*",
        "*svwv....vwvvvvvvvvvvvvvv..*",
        "*vvwv....vwwwwwwwwwwwwwwv..*",
        "*vwwv....vvvvvvvvvvvvvvvv..*",
        "*vvvv......................*",
        "****************************",
    };
    /**
     * A maze of aquarium.
     * Very Original Idea.
     * This one spawns in the enemies in the water.
     */
    public static final String[] AQUARIUM_MAZE_2 = {
        "****************************",
        "*..........................*",
        "*......b...................*",
        "*.............b........b...*",
        "*..........................*",
        "*T.b.......................*",
        "*...........b..............*",
        "*..........................*",
        "*..................b.......*",
        "*......b...................*",
        "*..b.......................*",
        "*.......................b..*",
        "*..........................*",
        "*..........................*",
        "*.........b................*",
        "*...................b......*",
        "*..b.......................*",
        "*..........................*",
        "****************************",
    };
    /**
     * Seems like security was thrashed before you came...
     */
    public static final String[] BREAK_IN_TWO = {
        "***************************",
        "*.S.v.......www.b.....v.T.*",
        "*...v..b.....w....b...vxxx*",
        "*...v....Tw...s..w....v...*",
        "*...v....www....wwwx..v..w*",
        "*....vshx.w.....xw..hv..ww*",
        "*.....v.......h..........w*",
        "*.w..........hhh..........*",
        "*www....w....sh...x.......*",
        "*.w..v.www......b.whhx...h*",
        "*...vhs.w........www..v.hh*",
        "*...v.x...b.....b.w...v..h*",
        "*.b.v....w...bh..T....v...*",
        "*...v...www..hhh......v.E.*",
        "***************************",
    };
    /**
     * Is this just copy pasted with more enemies?
     * no......
     */
    public static final String[] BREAK_IN_THREE = {
        "***************************",
        "*.S.x......swww.b.....x.T.*",
        "*...x..b.....w....b...xxxx*",
        "*...x....Tw...s..w....x...*",
        "*...v....wwwb...wwwx.bv..w*",
        "*....vshx.w.....xw..hv..ww*",
        "*.....v....b..h..........w*",
        "*.w.b........hhh....T.....*",
        "*www....w....sh...x.......*",
        "*.w..v.www..b...b.whhx...h*",
        "*...vhs.w.....s..www..v.hh*",
        "*...x.x...b.....b.w...x..h*",
        "*.b.xs...w...bh..T..s.xT..*",
        "*...x.T.www..hhh......x.E.*",
        "***************************",
    };
    /**
     * A chaotic swarm of bats has taken a hallway.
     */
    public static final String[] SWARMING_CHAOS_1 = {
        "***********************************",
        "*......bbb...bbb...bbb...bbb....ws*",
        "*......bbb...bbb...bbb...bbb....ws*",
        "*vv....bbb...bbb...bbb...bbb....ws*",
        "*Sv............................Ews*",
        "*vvbbb....bbb...bbb...bbb...bbb.ws*",
        "*..bbb....bbb...bbb...bbb...bbb.ws*",
        "*..bbb....bbb...bbb...bbb...bbb.ws*",
        "***********************************",
    };
    /**
     * A chaotic swarm of bats has taken a hallway.
     * This one loads in all of the water.
     */
    public static final String[] SWARMING_CHAOS_2 = {
        "***********************************",
        "*...wwwwwwwwwwwwwwwwwwwwwwwwwwww..*",
        "*...wwwwwwwwwwwwwwwwwwwwwwwwwwww..*",
        "*.................................*",
        "*.................................*",
        "*.................................*",
        "*...wwwwwwwwwwwwwwwwwwwwwwwwwwww..*",
        "*...wwwwwwwwwwwwwwwwwwwwwwwwwwww..*",
        "***********************************",
    };
    /**
     * Just a chill aquarium map.
     */
    public static final String[] CHILL_AQUARIUM_1 = {
        "*********************************",
        "*T.....................b........*",
        "*....b..........................*",
        "*.................b.............*",
        "*........b..............s.......*",
        "*vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv*",
        "*S.............................E*",
        "*vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv*",
        "*............s........b.........*",
        "*...........................b...*",
        "*.....b........b................*",
        "*........................b.....T*",
        "*********************************",
    };
    /**
     * Just a chill aquarium map.
     * This one loads in all of the water.
     */
    public static final String[] CHILL_AQUARIUM_2 = {
        "*********************************",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwww.wwwwwww*",
        "*...............................*",
        "*...............................*",
        "*...............................*",
        "*wwwwwwwwwwww.wwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*********************************",
    };
    /**
     * A narrow path swarmed by faster bats, and camping skeletons.
     */
    public static final String[] SWARMED_PATH_1 = {
        "*********************************",
        "*.S.*wwwswTwswww*wwwswTwswww*...*",
        "*...*wwwwwwwwwww*wwwwwwwwwww*...*",
        "*...*ww.......ww*ww.......ww*...*",
        "*...*ww.wwwww.ww*ww.wwwww.ww*.E.*",
        "*...*ww.wwsww.ww*ww.wwsww.ww*...*",
        "*...*ww.ww*ww.wwsww.ww*ww.ww*...*",
        "*...*ww.ww*ww.wwwww.ww*ww.ww*T..*",
        "*...*ww.ww*ww.......ww*ww.ww*..T*",
        "*...*ww.ww*wwwwwwwwwww*ww.ww*.T.*",
        "*...v...ww*wwwswTwswww*ww.......*",
        "*********************************",
    };
    /**
     * A narrow path swarmed by faster bats, and camping skeletons.
     * This one spawns all of the bats.
     */
    public static final String[] SWARMED_PATH_2 = {
        "*********************************",
        "*.....b..b.b..b...b..b.b..b.....*",
        "*....b.........b.b.........b....*",
        "*...............................*",
        "*....b.........b.b.........b....*",
        "*...............................*",
        "*....b.........b.b.........b....*",
        "*..........b.........b..........*",
        "*....b.....................b....*",
        "*..........b.........b..........*",
        "*...........b..b.b..b...........*",
        "*********************************",
    };
    /**
     * Rare Treasure Room for the Izu Mito Sea Paradise Dungeon
     */
    public static final String[] IZU_MITO_SEA_PARADISE_TREASURE_ROOM = {
        "********************",
        "*wS..............Ew*",
        "*wwvvvvvvvvvvvvvvww*",
        "*sw.b....b..b....ws*",
        "*www..b......b..www*",
        "*wsw.b...b....b.wsw*",
        "*wwwwxxxxxxxxxxwwww*",
        "*TTTw..b..T.b..wTTT*",
        "*TTTw.T..b...T.wTTT*",
        "********************",
    };


    //NUMAZU DEEP SEA AQUARIUM Maps
    /**
     * 
     */
    public static final String[] A = {
        "",
    };
    /**
     * 
     */
    public static final String[] B = {
        "",
    };
    /**
     * 
     */
    public static final String[] C = {
        "",
    };
    /**
     * 
     */
    public static final String[] D = {
        "",
    };
    /**
     * 
     */
    public static final String[] E = {
        "",
    };
    /**
     * 
     */
    public static final String[] F = {
        "",
    };
    /**
     * 
     */
    public static final String[] G = {
        "",
    };
    /**
     * 
     */
    public static final String[] H = {
        "",
    };
    /**
     * 
     */
    public static final String[] I = {
        "",
    };    /**
     * 
     */
    public static final String[] J = {
        "",
    };

















    //SHOUGETSU CONFECTIONARY Maps
    //NAGAHAMA CASTLE RUINS Maps
    //NUMAZUGOYOTEI Maps
    //UCHIURA BAY PIER Maps
    //AWASHIMA MARINE PARK Maps;

    public static final String[] FISH_TANK = {
        "*****.........E...........*",
        "*****.....................*",
        "*****.....................*",
        "*****.....................*",
        "*****.....................*",
        "*****.....................*",
        "*****..*..................*",
        "*****..*..................*",
        "*...v..*..................*",
        "*.S.v..*..................*",
        "*.........................*",
    };
    /**
     * Final Boss Arena
     */
    public static final String[] SIREN_LAIR = {
        "****************************************",
        "*..........#.......BE.......#..........*",
        "*..........#................#..........*",
        "*..........##################..........*",
        "*......................................*",
        "*......................................*",
        "*......................................*",
        "*......................................*",
        "*..........*......*.........*..........*",
        "*......................................*",
        "*................SL....................*",
        "****************************************"
    };

    /**
     * The reference level found in the specs.
     */
    public static final String[] REFERENCE = {
        "*******************************************************",
        "*.....x.............x.................................*",
        "*..T..x....h........x...............b.................*",
        "*.....x....hh.......x.......ww........xx..............*",
        "*.....xx.......b...xx.......ww........xx....x...b.....*",
        "*.....x....v.......xx..x....ww........xx.b..x.........*",
        "*.x...x....x.......vx..x....ww........xx....x.........*",
        "*.x..Sx....x.......vx.......ww........xx..vvv...xxxbxx*",
        "*..x..vvvvvvvvvvvvv....vvvvvww........xx.h.....vv.....*",
        "*..x....x.................vvv............h.....vv.....*",
        "*.......x..................v.......b.....h.....vv.b..E*",
        "*******************************************************"
    };
    /**
     * A level to test Bat movement on Water Tiles.
     */
    public static final String[] BAT_WATER_TEST = {
        "*******************************************************",
        "*.....*.s.*.........x.................................*",
        "*..T..*****h........x...............b.................*",
        "****..x....hh.......x.......ww........xx..............*",
        "*..*..xx.......b...xx.......ww........xx....x...b.....*",
        "*..*..x....v.......xx..x....ww........xx.b..x.........*",
        "*..*.......x.......vx..x....wwww......xx....x.........*",
        "*..*.S.....x.......vx.......wwbw......xx..vvv...xxxbxx*",
        "*..**v**vvvvvvvvvvv....vvvvvwwww......xx.h.....vv.....*",
        "*..*www*x.................vvv............h.....vv.....*",
        "*s.*wbw*x..................v.......b.....h.....vv.b..E*",
        "*******************************************************"
    };
    /**
     * Map used to test Skeletons.
     */
    public static final String[] SKELETON_TEST = {
        "***************",
        "*......s......*",
        "*.............*",
        "*.............*",
        "*.............*",
        "*......v......*",
        "*.............*",
        "*s...v.SEvwwws*",
        "*.............*",
        "*......v......*",
        "*......w......*",
        "*......w......*",
        "*......w......*",
        "*......s......*",
        "***************",
    };
    /**
     * Map used to test long vertical dungeons.
     */
    public static final String[] VERTICAL_TEST = {
        "*******",
        "*..S..*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*..E..*",
        "*******",
    };
    /**
     * Map used to test small dungeons.
     */
    public static final String[] SMALL_TEST = {
        "*******",
        "*..S..*",
        "*.....*",
        "*.....*",
        "*.....*",
        "*..E..*",
        "*******",
    };
    /**
     * Map used to test large dungeons.
     */
    public static final String[] LARGE_TEST = {
        "********************************************************",
        "*..S..................................................E*",
        "*......................................................*",
        "*......................................................*",
        "*......................................................*",
        "*......................................................*",
        "*......................................................*",
        "*......................................................*",
        "*......................................................*",
        "*......................................................*",
        "*......................................................*",
        "*......................................................*",
        "*......................................................*",
        "*......................................................*",
        "*......................................................*",
        "*......................................................*",
        "*......................................................*",
        "*......................................................*",
        "*......................................................*",
        "*......................................................*",
        "*......................................................*",
        "*......................................................*",
        "********************************************************"
    };
}