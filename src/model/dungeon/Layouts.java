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
     * Rare Treasure Room for the Izu Mito Sea Paradise Dungeon.
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
     * Diving into the aquarium!
     */
    public static final String[] DIVE_IN_2 = {
        "***************",
        "*......S......*",
        "*vvvvv...vvvvv*",
        "*....v...v....*",
        "*.............*",
        "*..........s..*",
        "*...b.........*",
        "*..........b..*",
        "*....b........*",
        "*..........b..*",
        "*..b.....s....*",
        "*.............*",
        "*..T..b.......*",
        "*.............*",
        "*..s.....b....*",
        "*...b.........*",
        "*.........b...*",
        "*......E......*",
        "***************",
    };
    /**
     * Diving into the aquarium!
     * This one loads in all the water.
     */
    public static final String[] DIVE_IN_1 = {
        "***************",
        "*.............*",
        "*.............*",
        "*.............*",
        "*wwwwwwwwwwwww*",
        "*wwwwwwwwwwwww*",
        "*wwwwwwwwwwwww*",
        "*wwwwwwwwwwwww*",
        "*wwwwwwwwwwwww*",
        "*wwwwwwwwwwwww*",
        "*wwwwwwwwwwwww*",
        "*wwwwwwwwwwwww*",
        "*wwwwwwwwwwwww*",
        "*wwwwwwwwwwwww*",
        "*wwwwwwwwwwwww*",
        "*wwwwwwwwwwwww*",
        "*wwwwwwwwwwwww*",
        "*wwwwwwwwwwwww*",
        "***************",
    };
    /**
     * A Piskel art of Fish.
     */
    public static final String[] FISHKEL_ART_2 = {
        "*******************",
        "*S...vvvv........x*",
        "*..vv.b..vv.....vv*",
        "*.v.T......vvv.vbv*",
        "*v.....b......xbTv*",
        "*.v..b..b..vvv.vbv*",
        "*..vv....vv.....vv*",
        "*....vvvv.....E..x*",
        "*******************",
    };
    /**
     * A Piskel art of Fish.
     * This one loads in all the water.
     */
    public static final String[] FISHKEL_ART_1 = {
        "*******************",
        "*S...wwwwwwwwwwwww*",
        "*..wwwwwwwwwwwwwww*",
        "*.wwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwww*",
        "*******************",
    };
    /**
     * A Piskel art of FISHES
     */
    public static final String[] FISHESKEL_ART_2 = {
        "******************",
        "*.......S........*",
        "*................*",
        "*.vv.v...........*",
        "*vbbx............*",
        "*.vv.v.....vv.v..*",
        "*.........vbbx...*",
        "*..v.vv....vv.v..*",
        "*...xbbv.........*",
        "*..v.vv..v...x...*",
        "*.......vbv.v....*",
        "*......vbTbx.v...*",
        "*.......vbv.v....*",
        "*.v.vv...v...x...*",
        "*..xbEv..........*",
        "*.v.vv...........*",
        "******************",
    };
    /**
     * A Piskel art of FISHES
     * This one loads in all the water.
     */
    public static final String[] FISHESKEL_ART_1 = {
        "******************",
        "*wwwwww...wwwwwww*",
        "*wwwwww...wwwwwww*",
        "*wwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwww*",
        "******************",
    };
    /**
     * That means you are attacking the shark btw.
     */
    public static final String[] SHARK_ATTACK_2 = {
        "******************************************",
        "*..................S.....................*",
        "*........................................*",
        "*s.................x....................s*",
        "*s................xxx..........b........s*",
        "*s.....b........xxxxxxx.................s*",
        "*s.............xxxxxxxxx..........b.....s*",
        "*s............xxxxxxxxxxx...............s*",
        "*s...........xxxxxvEvxxxxx..............s*",
        "*s..b........xTxvvvvvvvxTx..............s*",
        "*s..........xxvvvvhvhvvvvxx..b..........s*",
        "*s..........xxvvvhhhhhvvvxx.............s*",
        "*s....b....xxvvvhhhhhhhvvvxx............s*",
        "*s.........xxvvhhhhhhhhhvvxx............s*",
        "*s.........xvvvhvhvhvhvhvvvx...b........s*",
        "*s...b.....xvvhhvvvvvvvhhvvx............s*",
        "*s.........xvvvvvvvvvvvvvvvx............s*",
        "*s.........vvvvvvvvvvvvvvvvv.......b....s*",
        "******************************************",
    };
    /**
     * That means you are attacking the shark btw.
     * This one loads in all the water.
     */
    public static final String[] SHARK_ATTACK_1 = {
        "******************************************",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "******************************************",
    };
    /**
     * A fish tank full of fish.
     * Why do they look like bats? we ran out of budget...
     */
    public static final String[] FISH_TANK_2 = {
        "***************************",
        "*****..v....v.E.....v.....*",
        "*****.v.....v......v......*",
        "*****..v...b.v......v..b..*",
        "*****..b.....v..b....v....*",
        "*****.......vb.....vb...v.*",
        "*****....b.............v..*",
        "*****..*.......v..b...v...*",
        "*****..*v.b..b.v.....v....*",
        "*...v..*.v....v.....b.v.b.*",
        "*.S.v..*.v.Tb.v.b....Tv..T*",
        "***************************",
    };
    /**
     * A fish tank full of fish.
     * Why do they look like bats? we ran out of budget...
     * This one loads in all the water
     */
    public static final String[] FISH_TANK_1 = {
        "***************************",
        "*****wwwwwwwwwwwwwwwwwwwww*",
        "*****wwwwwwwwwwwwwwwwwwwww*",
        "*****wwwwwwwwwwwwwwwwwwwww*",
        "*****wwwwwwwwwwwwwwwwwwwww*",
        "*****wwwwwwwwwwwwwwwwwwwww*",
        "*****wwwwwwwwwwwwwwwwwwwww*",
        "*****ww*wwwwwwwwwwwwwwwwww*",
        "*****ww*wwwwwwwwwwwwwwwwww*",
        "*....ww*wwwwwwwwwwwwwwwwww*",
        "*....ww*wwwwwwwwwwwwwwwwww*",
        "***************************",
    };
    /**
     * A fish tank full of bonefish.
     * Why are their skeletons humanoid shaped? please just stop asking questions bro...
     */
    public static final String[] BONEFISH_TANK_2 = {
        "***************************",
        "*****..v..T.v.E.....v...T.*",
        "*****.v.....v......v......*",
        "*****..v...s.v......v..s..*",
        "*****..s.....v..s....v....*",
        "*****.......vs.....vs...v.*",
        "*****....s.............v..*",
        "*****..*.......v..s...v...*",
        "*****..*v.s..s.v.....v....*",
        "*...v..*.v....v.....s.v.s.*",
        "*.S.v..*Tv.Ts.v.s.T..Tv..T*",
        "***************************",
    };
    /**
     * A fish tank full of bonefish.
     * Why are their skeletons humanoid shaped? please just stop asking questions bro...
     * This one loads in all the water
     */
    public static final String[] BONEFISH_TANK_1 = {
        "***************************",
        "*****wwwwwwwwwwwwwwwwwwwww*",
        "*****wwwwwwwwwwwwwwwwwwwww*",
        "*****wwwwwwwwwwwwwwwwwwwww*",
        "*****wwwwwwwwwwwwwwwwwwwww*",
        "*****wwwwwwwwwwwwwwwwwwwww*",
        "*****wwwwwwwwwwwwwwwwwwwww*",
        "*****ww*wwwwwwwwwwwwwwwwww*",
        "*****ww*wwwwwwwwwwwwwwwwww*",
        "*....ww*wwwwwwwwwwwwwwwwww*",
        "*....ww*wwwwwwwwwwwwwwwwww*",
        "***************************",
    };
    /**
     * Poor bats stuck in the caves...
     * Now kill them all for loot!
     */
    public static final String[] BAT_CAVES_2 = {
        "***********************************************",
        "*......*******................................*",
        "*.....*.b...T.*...............................*",
        "*....*.b..b..b.*.............****.............*",
        "*....*.vvvvvvvb*............*.T.b*............*",
        "*....*v.......v*...........*..b...*...........*",
        "*..........................*bvvvv.*...........*",
        "*.................*****....*v....v*...***.....*",
        "*................*.bTb.*.............*.E.*....*",
        "*...**..........*.vvvvv.*...........*.b.b.*...*",
        "*..*bT*.........*v.....v*...........*.vvv.*...*",
        "*..*vv*.............................*vv.vv*...*",
        "*.............................................*",
        "***********************************************",
    };
    /**
     * Poor bats stuck in the caves...
     * Now kill them all for loot!
     * This one loads all the water.
     */
    public static final String[] BAT_CAVES_1 = {
        "***********************************************",
        "*wwwwww.......wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwww.........wwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwww...........wwwwwwwwwwwww....wwwwwwwwwwwww*",
        "*wwww...........wwwwwwwwwwww......wwwwwwwwwwww*",
        "*wwww...........wwwwwwwwwww........wwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwww........wwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwww.....wwww........www...wwwww*",
        "*wwwwwwwwwwwwwwww.......wwwwwwwwwwwww.....wwww*",
        "*www..wwwwwwwwww.........wwwwwwwwwww.......www*",
        "*ww....wwwwwwwww.........wwwwwwwwwww*......www*",
        "*ww....wwwwwwwwwwwwwwwwwwwwwwwwwwwww*......www*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "***********************************************",
    };
    /**
     * The second chill aquarium area...
     */
    public static final String[] CHILL_AQUARIUM_TWO_2 = {
        "********************************",
        "*T............................T*",
        "*........b..............b......*",
        "*...b...................v......*",
        "*........v....b...v..b...v.....*",
        "*.......vb.......v......v......*",
        "*..vv...v.......v.b....v..b....*",
        "*.v...b..v.....v.......v.......*",
        "*..v.....v.b....v......bv...b..*",
        "*.vvv...vv.....vv.....vvv......*",
        "*vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv*",
        "*.S..........................E.*",
        "********************************",
    };
    /**
     * The second chill aquarium area...
     * This one loads in all the water.
     */
    public static final String[] CHILL_AQUARIUM_TWO_1 = {
        "********************************",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "********************************",
    };
    /**
     * A firing squad.
     * Good thing you're under water!
     */
    public static final String[] FIRING_SQUAD_2 = {
        "***********************************************",
        "****s*s*s*s*s*s*s*s*s*s*s*s*s*s*s*s*s*s*s*s.Ts*",
        "*..................................vvv.....s..*",
        "*....vvv.....b..............b......vb........s*",
        "*.....bv...........................v.......s..*",
        "*......v..........vvv........................s*",
        "*S.................bv.....b..vvv............sE*",
        "*...........v.......v........vb..............s*",
        "*....b.....bv................v.............s..*",
        "*.........vvv..b.............................s*",
        "*..........................................s..*",
        "***s*s*s*s*s*s*s*s*s*s*s*s*s*s*s*s*s*s*s*s*.Ts*",
        "***********************************************",
    };
    /**
     * A firing squad.
     * Good thing you're under water!
     * This loads in all the water.
     */
    public static final String[] FIRING_SQUAD_1 = {
        "***********************************************",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*..wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*..wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*..wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "***********************************************",
    };
    /**
     * Rare Treasure Room for the Numazu Deep Sea Aquarium Dungeon.
     */
    public static final String[] NUMAZU_DEEP_SEA_AQUARIUM_TREASURE_ROOM_2 = {
        "********************",
        "*S...............E.*",
        "*..vvvvvvvvvvvvvvvv*",
        "*vvvb....b..b....ws*",
        "*ssw..b......b..wws*",
        "*wsw.b...b....b.wsw*",
        "*xxxxxxxxxxxxxxxxxx*",
        "*TTTw..b.TT.b..wTTT*",
        "*TTTw.T..b...T.wTTT*",
        "********************",
    };
    /**
     * Rare Treasure Room for the Numazu Deep Sea Aquarium Dungeon.
     * Loads in all the water.
     */
    public static final String[] NUMAZU_DEEP_SEA_AQUARIUM_TREASURE_ROOM_1 = {
        "********************",
        "*..wwwwwwwwwwwwwwww*",
        "*..wwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwww*",
        "********************",
    };



    //SHOUGETSU CONFECTIONARY Maps
    /**
     * Candy!!
     */
    public static final String[] CANDY_PISKEL_ART = {
        "*********************",
        "*S..................*",
        "*...vvv...........b.*",
        "*..v.bv.....b.......*",
        "*.v.T.v.vvvvv.......*",
        "*.vb.vxv.....vv.....*",
        "*.vvvx....b....v.b..*",
        "*....v.b.....b.v....*",
        "*...v...........v...*",
        "*...v....TbT....v...*",
        "*...v.b..bEb..b.v.b.*",
        "*.b.v....TbT....v...*",
        "*...v...........v...*",
        "*....v.b.....b.v..T.*",
        "*.b..v....b...xxvvv.*",
        "*.....vv.....vx..bv.*",
        "*....b..vvvvv.v.T.v.*",
        "*s......b.....vb.v..*",
        "*..s.......s..vvv...*",
        "*TTT.s..s...........*",
        "*********************",
    };
    /**
     * Lollipop!!
     */
    public static final String[] LOLLIPOP_PISKEL_ART = {
        "*****************",
        "*.....xxxxx.....*",
        "*...xx..E..xx...*",
        "*..x..b.v.b..x..*",
        "*.xw.T..v..T.wx.*",
        "*.xw....v....wx.*",
        "*xsw.b..x..b.wsx*",
        "*xsw....x....wsx*",
        "*xsw.T..x..T.wsx*",
        "*xsw.b..x..b.wsx*",
        "*.xw....v....wx.*",
        "*.xw....v....wx.*",
        "*..x..b.v.b..x..*",
        "*...xx..v..xx...*",
        "*.....xvvvx.....*",
        "*......x.x......*",
        "*......xbx......*",
        "*......x.x......*",
        "*......xbx......*",
        "*......x.x......*",
        "*......xbx......*",
        "*......x.x......*",
        "*......xbx......*",
        "*......x.x......*",
        "*xxxxxxxvxxxxxxx*",
        "*...............*",
        "*.......S.......*",
        "*****************",
    };
    /**
     * No this is not just a rectangle what do you mean?
     */
    public static final String[] CHOCOLATE_PISKEL_ART = {
        "*************",
        "*.....S.....*",
        "*.xxxxxxxxx.*",
        "*.xsvsvbvTx.*",
        "*.xvvvvvvvx.*",
        "*.xbvTvhvsx.*",
        "*.xvvvvvvvx.*",
        "*.xbvhvwvEx.*",
        "*.xxxxxxxxx.*",
        "*...........*",
        "*************",
    };
    /**
     * Just the chill backroom of the shop dungeon i mean candy dungeon.
     * What do you mean this wasn't how the backroom looked like from another floor???
     */
    public static final String[] CHILL_BACKROOM = {
        "*****************",
        "*.........v...v.*",
        "*.v...v..vsvTvbv*",
        "*vsvTvbv..v...v.*",
        "*.v...v.........*",
        "*......v...v....*",
        "*.....vbvTvsv...*",
        "*......v...v....*",
        "*...............*",
        "*S.............E*",
        "*****************",
    };
    /**
     * A floor where you sacrifice HP for Treasure.
     * Well unless you got the shovel then you're just a thief.
     * Or you go through the back room....
     * Uhhhh......
     */
    public static final String[] HP_SHOP = {
        "*********************************************",
        "*sw.........................................*",
        "***v*****v*****v*****v*****v*****v*****v***b*",
        "*.....*.....*.....*.....*T...T*.TTT.*.....*b*",
        "*.....*.....*.....*.TTT.*.bwb.*w.T.w*.bTb.*b*",
        "*..T..*.TbT.*.TsT.*.....*.wsw.*sw.ws*.TbT.*b*",
        "*.....*.....*.....*.bbb.*.bwb.*w...w*.bTb.*b*",
        "*.....*.....*.....*.....*T...T*.....*.....*b*",
        "***x*****x*****x*****x*****x*****x*****x***b*",
        "*.........................................v.*",
        "*S.......................................E*w*",
        "*.........................................*s*",
        "*********************************************",
    };
    /**
     * Rare treasure room for the Shougetsu Confectionary Dungeon
     */
    public static final String[] SHOUGETSU_CONFECTIONARY_TREASURE_ROOM = {
        "********************",
        "*S...............E.*",
        "*xxxxxxxxxxxxxxxxxx*",
        "*sw.b.T..b..b....ws*",
        "*sww..b.T....b..wws*",
        "*wsw.b...b.T..b.wsw*",
        "*xxxxxxxxxxxxxxxxxx*",
        "*TTTwTTbTTTTbTTwTTT*",
        "*TTTwTTTTbTTTTTwTTT*",
        "********************",
    };


    //NAGAHAMA CASTLE RUINS Maps
    /**
     * An assault at the ruined bridge.
     */
    public static final String[] CASTLE_BRIDGE_ASSAULT = {
        "***************************************************************",
        "*wwwswswswswswswswswswswswswswswswswswswswswswswswswswswswswww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*..b..wwwww...............b........s......wv...........v......*",
        "*......www..b........b...v.......v.......vw..b........wsv...TE*",
        "*S......w....v.......w..vx.s....xwv..b....x......v.....v....TE*",
        "*....b......vxv...b..w...x.......xb.............wsv..b......TE*",
        "*..........vxv......ww.....b...........s.........v............*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwswswswswswswswswswswswswswswswswswswswswswswswswswswswswsww*",
        "***************************************************************",
    };
    /**
     * An assault at the ruined castle tower.
     */
    public static final String[] CASTLE_TOWER_ASSAULT = {
        "*****************",
        "*T..v..v......sE*",
        "*...*v***********",
        "*..**....b.s..b.*",
        "*T.**.b..s...b..*",
        "**..vT.s.s.b....*",
        "***************v*",
        "*.T.*..s...b....*",
        "*T..x...b.s..b..*",
        "*.sx..b.s..b...T*",
        "**x**v***********",
        "*T.v*...b..s.....*",
        "*..v*...s...b...*",
        "*b..vTs...b......*",
        "***************v*",
        "*...*.s....b....*",
        "*...*.....b.s.b.*",
        "*S..v.......b..T*",
        "*****************",
    };
    /**
     * An assault at the castle vault.
     */
    public static final String[] CASTLE_VAULT_ASSAULT = {
        "**************************************",
        "*T*..T..v....b*E.Thbv...ws*sw.TT*sw..*",
        "*s*.b...*.xb..*.bhhh*bbbbw*w...T*wbTT*",
        "*.*.x..s*b...x*b.hhh*hhhhh*.s.b.*.TTT*",
        "*.*..b..*.v.x.*..bhh*hhhhh*..s..v.TTE*",
        "*.*.s.x.*..b..*b...h*hhhhh*.b.s.*.TTT*",
        "*v*b....*w..wb*.b.hw*wbbbb*.b..w*wbTT*",
        "*Sv...b.*swTs.v..hws*swTbbv..bws*sw..*",
        "**************************************",
    };
    /**
     * The only safe respite in these ruins...
     */
    public static final String[] CHILL_RUINED_CREVICE = {
        "************",
        "*bssTTTbbTb*",
        "*xbbbsxxsbs*",
        "*bvsxxvvxvx*",
        "*vvxvvvxvvx*",
        "*S....v...E*",
        "************",
    };
    /**
     * Another firing squad... but without any waters.
     */
    public static final String[] FIRING_SQUAD_EX = {
        "***********************************************",
        "****s*s*s*s*s*s*s*s*s*s*s*s*s*s*s*s*s*s*s*s.Ts*",
        "*sw................................vvv.....s..*",
        "**...vvv.....b..............b......vb........s*",
        "*sw...bv...........................v.......s.T*",
        "**.....v..........vvv.......................Ts*",
        "*S.................bv.....b..vvv............sE*",
        "**..........v.......v........vb.............Ts*",
        "*sw..b.....bv................v.............s.T*",
        "**........vvv..b.............................s*",
        "*sw...................x....................s..*",
        "***s*s*s*s*s*s*s*s*s*s*s*s*s*s*s*s*s*s*s*s*.Ts*",
        "***********************************************",
    };
    /**
     * Rare treasure room for the Nagahama Castle Ruins Dungeon.
     */
    public static final String[] NAGAHAMA_CASTLE_RUINS_TREASURE_ROOM = {
        "********************",
        "*S...............E.*",
        "*vvvvvvvvvvvvvvvvvv*",
        "*sw.b.T..b..b....ws*",
        "*sww..b.T....b..wws*",
        "*wsw.b...b.T..b.wsw*",
        "*xxxxxxxxxxxxxxxxxx*",
        "*TTTwTTbTTTTbTTwTTT*",
        "*TTTwTTTTbTTTTTwTTT*",
        "********************",
    };


    //NUMAZUGOYOTEI Maps
    /**
     * Just explorin da forest.
     */
    public static final String[] FOREST_EXPLORATION = {
        "****************************************",
        "*......vv...v...b..v...s.........vb....*",
        "*...vv.vv....vvv..vvv..vvvv...vv......E*",
        "*...vv.......vTv..vvv..vTbv...vv..vvv..*",
        "*....s..vvv..vvv...v...vbTv....s..vvv..*",
        "*..v....vTv......v.....vvvv..vv...vvv..*",
        "*...vv..vvv..vvs.vvv.........vv..v....v*",
        "*S..vv....b..vv..vvv.....vv..vv.vvv..vv*",
        "*......v.........vvv.....vv..b...v.....*",
        "****************************************",
    };
    /**
     * A swarm of Bats in the Forest.
     */
    public static final String[] BAT_SWARM = {
        "****************************************",
        "*..b...vv...v...b..v...b...T.....vb....*",
        "*.v.vv.vvb...vbv..v..b.vvvv...vv..b...E*",
        "*b..vv.b...v..T....bv..vTbv.b.vv..v.vT.*",
        "*.v..b..v.v..vb.v..v...vbTv....b...b...*",
        "*..v..b....b.....v...b.vvvv..vv...v.v..*",
        "*...v.v.v.v..vvs.v.v....b....vv..v....v*",
        "*S.....v..b..vv...b.b....vv..vv.vvv..vv*",
        "*..v.b.v...v..b..v.v...b.vv..b...v.....*",
        "****************************************",
    };
    /**
     * Bats or Mine Slop. Call it.
     */
    public static final String[] BATS_OR_SLOP = {
        "***************",
        "*sssssssssss***",
        "*wwwwwwwwwww***",
        "*....b......vS*",
        "*.b.........*v*",
        "*...b.b...b.*v*",
        "*.b.........*v*",
        "*..b...b.b..*v*",
        "*...b.....b.*v*",
        "*b.....b....*v*",
        "*.b...b...b.*v*",
        "*..b.b......*v*",
        "*b.......b..*v*",
        "*...b..b....*v*",
        "*.b.......b.*v*",
        "*...b.b..b..*v*",
        "*....b......*v*",
        "*..b.....b..*v*",
        "*b..b..b....*v*",
        "*.b..b.b..b.*v*",
        "*T.b.b...b..*v*",
        "*TT...b.....vE*",
        "***************",
    };
    /**
     * A chill hut in the forest.
     */
    public static final String[] CHILL_HUT = {
        "********************",
        "*bv.v.........Ev.vs*",
        "*v.v.....xx.....v.v*",
        "*bv.v...xxxx...v.vs*",
        "*v.v...xxxxxx...v.v*",
        "*sv.v...vvvv...v.vb*",
        "*v.v....vTTv....v.v*",
        "*sv.v...vTTv...v.vb*",
        "*v.v............v.v*",
        "*bv.vS.........v.vs*",
        "********************",
    };
    /**
     * They are in the forests.
     */
    public static final String[] GUERILLA_CHALLENGE = {
        "***********************************************",
        "*.v...v..b..T.b...vs...v..b.s..v...sv...v.b...*",
        "*...vb.v.v..b...v....v.s.v..v.b.b.v.T.bv...v..*",
        "*.v..xxxb..vhbv...v.T.v...vs..v...s.v.v.v...s.*",
        "*.b.vTx.v..hh.v.b...v..v.b.T..s.v.....T...b...*",
        "*vv....v..vhv...v.b..v...v..b.v...b.bv....v.T.*",
        "*Sv.vb....b...v.....s.vs...v...s.vT....v.....E*",
        "*vv...v.....vb..sw.v.....v.b.v..b...v.s....b..*",
        "*..bvwww.v.s...vwww.b.s.v....b..v..b.b.T.v....*",
        "*.v...bwv...v..b.wv...v..s.v....s...v..v..bT..*",
        "*.s.v.T....v.s..v.s......v.s..v..s....s...v.s.*",
        "*.v..b.vs..b..v..T..vb.b...v.b...v.b..v.b...v.*",
        "***********************************************",
    };
    /**
     * A rare treasure room for the Numazugoyotei Dungeon.
     */
    public static final String[] NUMAZUGOYOTEI_TREASURE_ROOM = {
        "********************",
        "*S...............E.*",
        "*vvvvvvvvvvvvvvvvvv*",
        "*sw.b.T.vb..bv.b.ws*",
        "*sww.vb.Tv.v.bv.wws*",
        "*wsw.b.v.b.Tb.b.wsw*",
        "*vvvvvvvvvvvvvvvvvv*",
        "*TTTwTTbTTTTbTTwTTT*",
        "*TTTwTTTTbTTTTTwTTT*",
        "********************",
    };


    //UCHIURA BAY PIER Maps
    /**
     * A River Deltas.
     */
    public static final String[] RIVER_DELTAS_2 = {
        "***************************",
        "*...........T............E*",
        "*.........................*",
        "*..................s......*",
        "*.........b....b..........*",
        "*..................b......*",
        "*....b....................*",
        "*..........b..........b...*",
        "*.......s......s..........*",
        "*...b.....................*",
        "*.................T.......*",
        "*.....b......s............*",
        "*.........................*",
        "*......b............s.....*",
        "*...........s.............*",
        "*...................b.....*",
        "*....s..........b.........*",
        "*.........................*",
        "*.........................*",
        "*.......b.........s.......*",
        "*.........................*",
        "*S...................b....*",
        "***************************",
    };
    /**
     * A River Deltas.
     * This loads water.
     */
    public static final String[] RIVER_DELTAS_1 = {
        "***************************",
        "*.........wwwww...........*",
        "*.........wwwww...........*",
        "*.........wwwww...........*",
        "*........ww.www...........*",
        "*.......ww...www..........*",
        "*.......ww....www.........*",
        "*......ww......www........*",
        "*.....ww......w..ww.......*",
        "*....ww......w....ww......*",
        "*....ww.....w....ww.......*",
        "*...w.w....w......ww......*",
        "*...w..w....w......ww.....*",
        "*..w....w....w.....w.w....*",
        "*.w....w......w...w...w...*",
        "*w......w....w...w....w...*",
        "*w.....w...ww...w......w..*",
        "*w....w...w......w....w...*",
        "*....w.....w......w....w..*",
        "*.....w.....w......w....w.*",
        "*......w...w......w......w*",
        "*.....w.....w....w........*",
        "***************************",
    };
    /**
     * A sea of skeletons.
     */
    public static final String[] DEAD_SEA_2 = {
        "**********************",
        "*....sTs............E*",
        "*.....s....s.........*",
        "*..vvv.........s.....*",
        "*......s....s..vvv...*",
        "*..........sTs...s...*",
        "*..s..vvv...s........*",
        "*.......s........s...*",
        "*......sTs......sTs..*",
        "*.......s........s...*",
        "*..............vvv...*",
        "*...s.......s........*",
        "*..vvv...............*",
        "*..............s.....*",
        "*.........vvv........*",
        "*....................*",
        "*S...................*",
        "**********************",
    };
    /**
     * A sea of skeletons.
     * This loads water.
     */
    public static final String[] DEAD_SEA_1 = {
        "**********************",
        "*wwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwww*",
        "*..wwwwwwwwwwwwwwwwww*",
        "*..wwwwwwwwwwwwwwwwww*",
        "**********************",
    };
    /**
     * A beach area.
     */
    public static final String[] BEACH_2 = {
        "********************",
        "*E...........v..v..*",
        "*..b........v.vv.v.*",
        "*..........T...v..v*",
        "*........s.....v...*",
        "*..............v...*",
        "*....b.......v...b.*",
        "*T.......v..v.v....*",
        "*.......v.vv...v...*",
        "*..b...v...v...T...*",
        "*.......v..v.b.....*",
        "*....b.....v.......*",
        "*.................S*",
        "********************",
    };
    /**
     * A beach area.
     * This loads the water.
     */
    public static final String[] BEACH_1 = {
        "********************",
        "*wwwwwww...........*",
        "*wwwwww............*",
        "*wwwww.............*",
        "*wwwwww............*",
        "*wwwwwww...........*",
        "*wwwwwwww..........*",
        "*wwwwwwwww.........*",
        "*wwwwwwwww.........*",
        "*wwwwwww...........*",
        "*wwwwww............*",
        "*wwwww.............*",
        "*wwww..............*",
        "********************",
    };
    /**
     * A chill lake area.
     */
    public static final String[] CHILL_LAKE = {
        "***********************",
        "*bbv...............vss*",
        "*bv.......www....E..vs*",
        "*v......wwwwwww......v*",
        "*....wwwwwwwwwwww.....*",
        "*...wwwwwwwTwwwwwww...*",
        "*.....wwwwwwwwwwww....*",
        "*.......wwwwwww.......*",
        "*v...S....www........v*",
        "*sv.................vb*",
        "*ssv...............vbb*",
        "***********************",
    };
    /**
     * Water and Land war.
     */
    public static final String[] DDAY_CHALLENGE_2 = {
        "***********************************************",
        "*..............b.....TTETT....................*",
        "*..s...b..............TTT......b..........s...*",
        "*.......vvv..b.........T..b...........b.....b.*",
        "*......vs............b..........s..x..........*",
        "*...b...vvv...b.........x..........x....b.....*",
        "*...................b..xxx.s.......x......s...*",
        "*..vvv..b.......b.......x.....b.....s.........*",
        "*.vs...............s.h.b...........x......b...*",
        "*..vvv........vvv...hhh........s....x..s......*",
        "*..s.....b...vs....hhhhh.b...........x........*",
        "*.............vvv..shhh..........b....s...b...*",
        "*.....b..............h.b.....s.........s......*",
        "*........vvv....b...........b.......x.........*",
        "*....b..vs..........s..............x...s......*",
        "*........vvv..xx.b.......s........s.....b.....*",
        "*..vvv.......xx............h..s....x..........*",
        "*.vs.............b...b....hhh.....x...b....s..*",
        "*..vvv......b...........b..h.......s..........*",
        "*......s.........b..........b......x.....b....*",
        "*...b.....vvv........................s.b......*",
        "*........vs....b...s..........s.....x.........*",
        "*.........vvv..........s...........s..........*",
        "*.....b.....................s....x.....b...s..*",
        "*.............b.....b.............x...........*",
        "*.....vvv.b.....h.........b......s......b.....*",
        "*....vs........hhh....s.......s...x...s.......*",
        "*.....vvv...b...h..b...............x..........*",
        "*..s.......................b........x.....b...*",
        "*........b...s..x.x..s.......s.....s..........*",
        "*................x......b.......b...s.........*",
        "*..vvv.....b....x.x...............x......s....*",
        "*.vs.......................b.....x....b.......*",
        "*..vvv........b.................s.............*",
        "*......................S.................b....*",
        "***********************************************",
    };
    /**
     * Water and Land war.
     * This one loads in all the water tiles.
     */
    public static final String[] DDAY_CHALLENGE_1 = {
        "***********************************************",
        "*wwwwwwwwwwwww........T.T.....................*",
        "*wwwwwwwwwwwwwwww......T......................*",
        "*wwwwwwwwwwwwwwwwww...........................*",
        "*wwwwwwwwwwwwwwwwwwwww........................*",
        "*wwwwwwwwwwwwwwwwwwwwwwww.....................*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwww...................*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww.............*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww.........*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww..........*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwww................*",
        "*wwwwwwwwwwwwwwwwwwwwww.......................*",
        "*wwwwwwwwwwwwwwwwwwwwwww......................*",
        "*wwwwwwwwwwwwwwwwwww..........................*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww............*",
        "*wwwwwwwwwwwwwww..............................*",
        "*wwwwwwwwwwwwwwwwww...........................*",
        "*wwwwwwwwwwwwwwwwwwww.........................*",
        "*wwwwwwwwwwwwwwww.............................*",
        "*wwwwwwwwwwwwwwwwww...........................*",
        "*wwwwwwwwwwwwwwwwwwwwww.......................*",
        "*wwwwwwwwwwwwwwwwwwwwwwww.....................*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwww.................*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwww................*",
        "*wwwwwwwwwwwwwwwwwwwwwww......................*",
        "*wwwwwwwwwwwwwwwwww...........................*",
        "*wwwwwwwwwwwwww...............................*",
        "*wwwwwwwwwwwwwwwwww...........................*",
        "*wwwwwwwwwwww.................................*",
        "*wwwwwwwwwwwwww...............................*",
        "*wwwwwwwww....................................*",
        "*wwwwwwwwww...................................*",
        "*wwwwww.......................................*",
        "*wwww.........................................*",
        "*www..........................................*",
        "***********************************************",
    };
    /**
     * Rare treasure room map for the Uchiura Bay Pier Dungeon.
     */
    public static final String[] UCHIURA_BAY_PIER_TREASURE_ROOM_2 = {
        "********************",
        "*S...............E.*",
        "*vvvvvvvvvvvvvvvvvv*",
        "*sw.b.T.vb..bv.b.ws*",
        "*sww.vb.Tv.v.bv.wws*",
        "*wsw.b.v.b.Tb.b.wsw*",
        "*vvvvvvvvvvvvvvvvvv*",
        "*TTTwTTbTTTTbTTwTTT*",
        "*TTTwTTTTbTTTTTwTTT*",
        "********************",
    };
    /**
     * Rare treasure room map for the Uchiura Bay Pier Dungeon.
     * This one loads in the water.
     */
    public static final String[] UCHIURA_BAY_PIER_TREASURE_ROOM_1 = {
        "********************",
        "*..wwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwww*",
        "********************",
    };


    //AWASHIMA MARINE PARK Maps;
    /**
     * Defeat the swarm on a narrow path.
     */
    public static final String[] BAT_SWARM_NARROW_2 = {
        "************************************",
        "*...bbbbbbbbbbbbbbbbbbbbbbbbbbbb...*",
        "*vv.bbbbbbbbbbbbbbbbbbbbbbbbbbbb.vv*",
        "*S..bbbbbbbbbbbbbbbbbbbbbbbbbbbb..E*",
        "*vv.bbbbbbbbbbbbbbbbbbbbbbbbbbbb.vv*",
        "*...bbbbbbbbbbbbbbbbbbbbbbbbbbbb...*",
        "************************************",
    };
    /**
     * Defeat the swarm on a narrow path.
     * This loads water.
     */
    public static final String[] BAT_SWARM_NARROW_1 = {
        "************************************",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*..................................*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "************************************",
    };
    /**
     * You MUST Parry the arrows.
     */
    public static final String[] PARRY_CHALLENGE = {
        "**********************",
        "*S.....wsww........ww*",
        "*wwwww.wwww.wwwwww.ww*",
        "*wwwww.wwww.wwwwww.ww*",
        "*wwwww.wwww.wwwwww.ww*",
        "*wwwww......wsww...ww*",
        "*wwwwwwwwwwwwwww.wwww*",
        "*wwwwwswwwwwwwww.wsww*",
        "*wwwwwwwwwwwwwww.wwww*",
        "*wwwwwwwwwwwwwww.wwww*",
        "*wwwwwwwsw.......wwww*",
        "*wwwwwwwww.wwwwwwwwww*",
        "*wwTTT.....wwwwwswwww*",
        "*wwwwwwwwwEwwwwwwwwww*",
        "**********************",
    };
    /**
     * A chill hallway.
     */
    public static final String[] CHILL_HALL = {
        "**************************",
        "*swbbbTbbbTbbbbTbbbTbbbws*",
        "*wwwwwwwwwwwwwwwwwwwwwwww*",
        "*vvvvvvvvvvvvvvvvvvvvvvvv*",
        "*S......................E*",
        "*vvvvvvvvvvvvvvvvvvvvvvvv*",
        "*wwwwwwwwwwwwwwwwwwwwwwww*",
        "*swbbbTbbbTbbbbTbbbTbbbws*",
        "**************************",
    };
    /**
     * Inspired by Be Crushed By A Speeding Wall!
     */
    public static final String[] BE_CRUSHED_BY_A_SPEEDING_ARROWS = {
        "*********************************************************",
        "*wwwswwwwwwwwwwwwwwwwwwwwwwwwwwwswwwwwwwwwwwswwwwwwswwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*S...wwwwwwwwwwwwwswwwwwwwwwwwwwwwwwwwwwwwww........wwww*",
        "*www.wwwwwwwwwwwwww...wwwwwwwwwwwwwwwwwwwwww.wwwwww.wwww*",
        "*www..wwwwwwwwwwww...............wwwwswwwwww.wwwwww.wwww*",
        "*www..wwwwwwwwwwww.wwwwww...wwww.wwwwwwwwwww.wwwwww.wwww*",
        "*ww...wwwwwwwwwwww..wwwwwwwwwwww......x......wwwwsw.wwsw*",
        "*ww..wwwwww....www..wwwwwwwwwwww.wwwwwwwwwww.wwwwww.wwww*",
        "*ww................wwswwwwwwwww...wwwwwwwwww.wwwwww.wwww*",
        "*wwwww....wwwww..w.wwwwwwwwwwww...wwwwwwwwww.wwwsww.wwww*",
        "*wwwswwwwwwwwwwwwwx...wwwwwwwww...wwwwwwwwww.wswwww.wwww*",
        "*wwwwwwwwwwwwwwwwwwww.wwwwwwwww...wwwwwwwwww.wwswww.wwww*",
        "*wwwwwwwwww...........wwwwwwwwww.wwwwwwwwwww.wwwwww.wwww*",
        "*wwwwwwwwww.w...w...w.wwwwwwwwww.wwwwwwwwwww....x...wwsw*",
        "*wwwwwwwwww.wwwwwwwww.wwwwwwwwww.wwwwwwwwwww.wwwwww.wwww*",
        "*wwwwwwwwww.wwwwwwwww............wwwwwswwwww.wwwwww.wwww*",
        "*wwwwwwwwww.wwwwwwwwwwwwwww.wwwwwwwwwwwwwwww.wwwwww.wwww*",
        "*wwwwwwwwww.wwwwwwwwwwwwwww.wwwwwwwwwwwwwwww.wwwwww.wwww*",
        "*wwwwwwwwww.wwwwwwwwwwwwwww.wwwwwwwwwwwwwwww.wwwwww.wwww*",
        "*wwwwwwwwww.wwwwwwwwwwwwwww.wwwswwwwwwwwwwww.wwwwwwEwwww*",
        "*wwwTTT.....wwwwwwwwwwwwwww.wwwwwwwwwwwwwwww.wwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwwwwwwwwwsw..................wwwwwwwwwww*",
        "*wwwwwwwwwwswwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*",
        "*********************************************************",
    };
    /**
     * A rare treasure room for the Awashima Marine Park Dungeon.
     */
    public static final String[] AWASHIMA_MARINE_PARK_TREASURE_ROOM_2 = {
        "********************",
        "*S...............E.*",
        "*vvvvvvvvvvvvvvvvvv*",
        "*sw.b.T.vb..bv.b.ws*",
        "*sww.vb.Tv.v.bv.wws*",
        "*wsw.b.v.b.Tb.b.wsw*",
        "*vvvvvvvvvvvvvvvvvv*",
        "*TTTwTTbTTTTbTTwTTT*",
        "*TTTwTTTTbTTTTTwTTT*",
        "********************",
    };
    /**
     * A rare treasure room for the Awashima Marine Park Dungeon.
     * This loads water.
     */
    public static final String[] AWASHIMA_MARINE_PARK_TRASURE_ROOM_1 = {
        "********************",
        "*..wwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwww*",
        "*wwwwwwwwwwwwwwwwww*",
        "********************",
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