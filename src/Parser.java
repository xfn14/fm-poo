import objects.Game;
import objects.Team;
import objects.exceptions.FileIOException;
import objects.player.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    public static List<Game> loadLogFile(String path) throws FileIOException{
        List<String> lines = fileToList(path);
        Map<String, Team> teamMap = new HashMap<>();
//        Map<Integer, Player> playerMap = new HashMap<>();
        List<Game> gameList = new ArrayList<>();
        String[] splitLine;
        Team lastTeam = null; int lastTeamLine = 0;
        Player player;
        int crtId = 0;
        int crtLine = 0;

        for(String line : lines){
            splitLine = line.split(":", 2);
            switch (splitLine[0]){
                case "Equipa":
                    Team team = Team.parse(splitLine[1]);
                    teamMap.put(team.getName(), team);
                    lastTeam = team; lastTeamLine = crtLine;
                    break;
                case "Guarda-Redes":
                    player = Keeper.parser(splitLine[1], crtId);
                    if(player == null){
                        System.out.println("Invalid fields in parse of Keeper. (line " + crtLine + ")");
                        break;
                    }
                    if(lastTeam != null) lastTeam.addPlayer(player);
                    else throw new FileIOException(
                            "Invalid format at " + path + " in Team format. (line " + lastTeamLine + ")");
                    break;
                case "Defesa":
                    player = Defender.parser(splitLine[1], crtId);
                    if(player == null){
                        System.out.println("Invalid fields in parse of Defender. (line " + crtLine + ")");
                        break;
                    }
                    if(lastTeam != null) lastTeam.addPlayer(player);
                    else throw new FileIOException(
                            "Invalid format at " + path + " in Team format. (line " + lastTeamLine + ")");
                case "Medio":
                    player = MidFielder.parser(splitLine[1], crtId);
                    if(player == null){
                        System.out.println("Invalid fields in parse of MidFielder. (line " + crtLine + ")");
                        break;
                    }
                    if(lastTeam != null) lastTeam.addPlayer(player);
                    else throw new FileIOException(
                            "Invalid format at " + path + " in Team format. (line " + lastTeamLine + ")");
                case "Lateral":
                    player = FullBack.parser(splitLine[1], crtId);
                    if(player == null){
                        System.out.println("Invalid fields in parse of FullBack. (line " + crtLine + ")");
                        break;
                    }
                    if(lastTeam != null) lastTeam.addPlayer(player);
                    else throw new FileIOException(
                            "Invalid format at " + path + " in Team format. (line " + lastTeamLine + ")");
                case "Avancado":
                    player = Striker.parser(splitLine[1], crtId);
                    if(player == null){
                        System.out.println("Invalid fields in parse of Striker. (line " + crtLine + ")");
                        break;
                    }
                    if(lastTeam != null) lastTeam.addPlayer(player);
                    else throw new FileIOException(
                            "Invalid format at " + path + " in Team format. (line " + lastTeamLine + ")");
                case "Jogo":
                    // TODO: 5/7/2021
                    break;
                default:
                    throw new FileIOException("Invalid type of player. (line " + crtLine + ")");
            }
            crtLine++;
        }
        return gameList;
    }

    public static List<String> fileToList(String path){
        List<String> lines;
        try{
            lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        }catch (IOException e){
            System.out.println("Invalid path.");
            lines = new ArrayList<>();
        }
        return lines;
    }
}
