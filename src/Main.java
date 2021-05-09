import menus.StartMenu;
import utils.TextUtils;

public class Main {
    public static void main(String[] args) {
        System.out.println(TextUtils.getASCIIArt());
        StartMenu.startMenuLoop();
//        try{
//            List<Game> gameList = Parser.loadLogFile("resources/logs.txt");
////            System.out.println(teams.get("Bartok F. C."));
//
////            String[] gameTester = "Bartok F. C.,Mendelssohn F. C.,3,3,2021-02-22,8,7,1,4,40,25,31,26,13,22,43,40->15,22->14,4->16,2,1,40,16,25,49,41,17,14,33,36,2->22,1->43,14->45".split(",");
////            Game newGame = Game.parser(gameTester, 0, teams);
////            assert newGame != null;
////            System.out.println(newGame);
//
////            for(Game game : gameList)
////                System.out.println(game);
//
////            for(Map.Entry<String, Team> entry : teams.entrySet()){
////                System.out.println(entry.getKey() + " : " + entry.getValue());
////            }
//        }catch (FileIOException e){
//            System.out.println(e.getMessage());
//        }
    }

}
