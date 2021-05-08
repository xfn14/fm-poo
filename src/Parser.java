import objects.Game;
import objects.Team;
import exceptions.FileIOException;
import objects.player.*;
import utils.FileUtils;

import java.util.*;

public class Parser {
    public static List<Game> loadLogFile(String path) throws FileIOException{
        List<String> lines = FileUtils.fileToList(path);
        String[] splitLine; int crtLine = 0;
        Team lastTeam = null; int lastTeamLine = 0;
        Player player; int crtPlayerId = 0;

//        Map<Integer, Player> playerMap = new HashMap<>();
        Map<String, Team> teamMap = new HashMap<>();
        List<Game> gameList = new ArrayList<>();
        for(String line : lines){
            splitLine = line.split(":", 2);
            switch (splitLine[0]){
                case "Equipa":
                    Team team = Team.parse(splitLine[1]);
                    teamMap.put(team.getName(), team);
                    lastTeam = team; lastTeamLine = crtLine;
                    break;
                case "Guarda-Redes":
                    try{
                        player = Keeper.parser(splitLine[1].split(","), crtPlayerId++);
                        if(lastTeam != null) lastTeam.addPlayer(player);
                        else throw new FileIOException(
                                "Invalid format at " + path + " in Team format. (line " + lastTeamLine + ")");
                    }catch (NumberFormatException e){
                        System.out.println("Invalid fields in parse of Keeper. (line " + crtLine + ")");
                    }
                    break;
                case "Defesa":
                    try{
                        player = Defender.parser(splitLine[1].split(","), crtPlayerId++);
                        if(lastTeam != null) lastTeam.addPlayer(player);
                        else throw new FileIOException(
                                "Invalid format at " + path + " in Team format. (line " + lastTeamLine + ")");
                    }catch (NumberFormatException e){
                        System.out.println("Invalid fields in parse of Defender. (line " + crtLine + ")");
                    }
                    break;
                case "Medio":
                    try{
                        player = MidFielder.parser(splitLine[1].split(","), crtPlayerId++);
                        if(lastTeam != null) lastTeam.addPlayer(player);
                        else throw new FileIOException(
                                "Invalid format at " + path + " in Team format. (line " + lastTeamLine + ")");
                    }catch (NumberFormatException e){
                        System.out.println("Invalid fields in parse of MidFielder. (line " + crtLine + ")");
                    }
                    break;
                case "Lateral":
                    try{
                        player = FullBack.parser(splitLine[1].split(","), crtPlayerId++);
                        if(lastTeam != null) lastTeam.addPlayer(player);
                        else throw new FileIOException(
                                "Invalid format at " + path + " in Team format. (line " + lastTeamLine + ")");
                    }catch (NumberFormatException e){
                        System.out.println("Invalid fields in parse of FullBack. (line " + crtLine + ")");
                    }
                    break;
                case "Avancado":
                    try{
                        player = Striker.parser(splitLine[1].split(","), crtPlayerId++);
                        if(lastTeam != null) lastTeam.addPlayer(player);
                        else throw new FileIOException(
                                "Invalid format at " + path + " in Team format. (line " + lastTeamLine + ")");
                    }catch (NumberFormatException e){
                        System.out.println("Invalid fields in parse of Striker. (line " + crtLine + ")");
                    }
                    break;
                case "Jogo":
                    try{
                        Game game = Game.parser(splitLine[1].split(","), gameList.size(), teamMap);
                        if(game != null) gameList.add(game);
                    }catch (NumberFormatException e){
                        System.out.println("Invalid fields in parse of Game. (line " + crtLine + ")");
                    }
                    break;
                default:
                    throw new FileIOException("Invalid type of player. (line " + crtLine + ")");
            }
            crtLine++;
        }
        return gameList;
    }
}
