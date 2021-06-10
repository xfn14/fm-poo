package utils;

import exceptions.InvalidPlayerSubException;
import objects.game.GameManager;
import objects.game.GameSim;
import objects.team.Team;
import exceptions.FileInvalidLineException;
import objects.player.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {
    public static List<GameSim> loadGameSimFile(String path, List<GameSim> logGameSims) throws FileInvalidLineException {
        List<String> lines = fileToList(path);
        List<GameSim> gameSims = logGameSims.stream().map(GameSim::clone).collect(Collectors.toList());

        String[] splitLine, splitLineDetailed;
        int crtLine = 0;

        for(String line : lines){
            splitLine = line.split(":", 2);
            switch (splitLine[0]){
                case "GameSim":
                    try{
                        splitLineDetailed = splitLine[1].split(",");
                        int gameSimId = -1;
                        if(splitLineDetailed[0] != null)
                            gameSimId = Integer.parseInt(splitLineDetailed[0]);

                        if(gameSimId < 0 || gameSims.size() <= gameSimId)
                            throw new FileInvalidLineException("Invalid GameSim_ID needs to be [0-" + gameSims.size() + "] " + crtLine);

                        GameSim gameSim = gameSims.get(gameSimId);
                        if(gameSim == null)
                            throw new NullPointerException("Error while getting GameSim from log list");

                        gameSim.parseCustom(splitLineDetailed);
                    }catch (NumberFormatException e){
                        System.out.println("GameSim_ID must be a number");
                    }catch (IllegalArgumentException e){
                        System.out.println("Invalid GameSim_TeamFormation");
                    }
                    break;
                default:
                    throw new FileInvalidLineException("Error in line " + crtLine);
            }
            crtLine++;
        }
        return gameSims;
    }

    public static GameManager loadLogFile(String path) throws FileInvalidLineException, FileNotFoundException {
        GameManager gameManager = new GameManager();
        List<String> lines = fileToList(path);
        String[] splitLine; int crtLine = 0;
        Team lastTeam = null; int lastTeamLine = 0;
        Player player;

        Map<Integer, Player> playerMap = new HashMap<>();
        Map<String, Team> teamMap = new HashMap<>();
        List<GameSim> gameSimList = new ArrayList<>();
        for(String line : lines){
            splitLine = line.split(":", 2);

            if(splitLine[0] == null || splitLine[1] == null) continue;
            switch (splitLine[0]){
                case "Equipa":
                    Team team = Team.parse(splitLine[1]);
                    teamMap.put(team.getName(), team);
                    lastTeam = team; lastTeamLine = crtLine;
                    break;
                case "Guarda-Redes":
                    try{
                        player = Keeper.parser(splitLine[1].split(","), playerMap.size());
                        if(lastTeam != null){
                            player = lastTeam.addPlayer(player);
                            playerMap.put(player.getId(), player);
                        }
                        else throw new FileInvalidLineException(
                                "Invalid format at " + path + " in Team format. (line " + lastTeamLine + ")");
                    }catch (NumberFormatException e){
                        System.out.println("Invalid fields in parse of Keeper. (line " + crtLine + ")");
                    }
                    break;
                case "Defesa":
                    try{
                        player = Defender.parser(splitLine[1].split(","), playerMap.size());
                        if(lastTeam != null){
                            player = lastTeam.addPlayer(player);
                            playerMap.put(player.getId(), player);
                        }
                        else throw new FileInvalidLineException(
                                "Invalid format at " + path + " in Team format. (line " + lastTeamLine + ")");
                    }catch (NumberFormatException e){
                        System.out.println("Invalid fields in parse of Defender. (line " + crtLine + ")");
                    }
                    break;
                case "Medio":
                    try{
                        player = MidFielder.parser(splitLine[1].split(","), playerMap.size());
                        if(lastTeam != null){
                            player = lastTeam.addPlayer(player);
                            playerMap.put(player.getId(), player);
                        }
                        else throw new FileInvalidLineException(
                                "Invalid format at " + path + " in Team format. (line " + lastTeamLine + ")");
                    }catch (NumberFormatException e){
                        System.out.println("Invalid fields in parse of MidFielder. (line " + crtLine + ")");
                    }
                    break;
                case "Lateral":
                    try{
                        player = FullBack.parser(splitLine[1].split(","), playerMap.size());
                        if(lastTeam != null){
                            player = lastTeam.addPlayer(player);
                            playerMap.put(player.getId(), player);
                        }
                        else throw new FileInvalidLineException(
                                "Invalid format at " + path + " in Team format. (line " + lastTeamLine + ")");
                    }catch (NumberFormatException e){
                        System.out.println("Invalid fields in parse of FullBack. (line " + crtLine + ")");
                    }
                    break;
                case "Avancado":
                    try{
                        player = Striker.parser(splitLine[1].split(","), playerMap.size());
                        if(lastTeam != null){
                            player = lastTeam.addPlayer(player);
                            playerMap.put(player.getId(), player);
                        }
                        else throw new FileInvalidLineException(
                                "Invalid format at " + path + " in Team format. (line " + lastTeamLine + ")");
                    }catch (NumberFormatException e){
                        System.out.println("Invalid fields in parse of Striker. (line " + crtLine + ")");
                    }
                    break;
                case "Jogo":
                    String[] args = splitLine[1].split(",");
                    if (args.length != 33)
                        throw new FileInvalidLineException("Invalid fields in parse of Game. (line " + crtLine + ")");
                    Team team1 = teamMap.get(args[0]);
                    Team team2 = teamMap.get(args[1]);
                    try{
                        GameSim gameSim = GameSim.parser(args, gameSimList.size(), team1, team2);
                        if(gameSim != null) gameSimList.add(gameSim);
                    }catch (NumberFormatException e){
                        System.out.println("Invalid fields in parse of Game. (line " + crtLine + ")");
                    }catch (InvalidPlayerSubException e){
                        System.out.println(e.getLocalizedMessage());
                    }
                    break;
                default:
                    throw new FileInvalidLineException("Error in line " + crtLine );
            }
            crtLine++;
        }
        gameManager.setPlayerMap(playerMap);
        gameManager.setTeamMap(teamMap);
        gameManager.setGameList(gameSimList);
        return gameManager;
    }

    public static List<String> fileToList(String path) {
        List<String> lines = new ArrayList<>();
        try{
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                if(!line.startsWith("#") || line.isBlank())
                    lines.add(line);
            }
            scanner.close();
        }catch (NullPointerException | FileNotFoundException e){
            System.out.println(ColorUtils.YELLOW + path + ColorUtils.RED_BOLD + " path is invalid." + ColorUtils.RESET);
            lines = new ArrayList<>();
        }
        return lines;
    }
}
