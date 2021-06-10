package objects.game;

import exceptions.InvalidPlayerSubException;
import objects.player.*;
import objects.team.Team;
import objects.team.TeamFormation;
import utils.ColorUtils;
import utils.DateUtils;
import utils.Tuple;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class GameSim extends GameInfo {
    /**
     * Current state of the game
     */
    private GameState gameState;

    /**
     * Current time in minutes of the game
     */
    private int time;

    /**
     * Accumulated extra time in minutes during the game
     */
    private int extraTime;

    /**
     * Number of goals of each team
     */
    private Tuple<Integer,Integer> goals;

    /**
     * Players in field from the team which is playing home
     */
    private List<Player> inFieldHome;

    /**
     * Substitutions' list of the Team which is playing home
     * Each substitution is composed (Player leaving, Player joining)
     */
    private List<Tuple<Integer,Integer>> homeSubs;

    /**
     * Formation of the team which is playing home
     */
    private TeamFormation homeFormation;

    /**
     * Players in field from the team which is playing away
     */
    private List<Player> inFieldAway;

    /**
     * Substitutions' list of the Team which is playing away
     * Each substitution is composed (Player leaving, Player joining)
     */
    private List<Tuple<Integer,Integer>> awaySubs;

    /**
     * Formation of the team which is playing away
     */
    private TeamFormation awayFormation;

    /**
     * Instantiates a GameSim
     */
    public GameSim(){
        super();

        this.gameState = GameState.INIT_GAME;
        this.time = 0;
        this.extraTime = 0;
        this.goals = new Tuple<>(0, 0);

        this.inFieldHome = new ArrayList<>();
        this.homeSubs = new ArrayList<>();
        this.homeFormation = TeamFormation.getRandomFormation();

        this.inFieldAway = new ArrayList<>();
        this.awaySubs = new ArrayList<>();
        this.awayFormation = TeamFormation.getRandomFormation();
    }

    /**
     * Instantiates a GameSim with a specific id
     * @param id Unique id
     */
    public GameSim(int id){
        super(id);

        this.gameState = GameState.INIT_GAME;
        this.time = 0;
        this.extraTime = 0;
        this.goals = new Tuple<>(0, 0);

        this.inFieldHome = new ArrayList<>();
        this.homeSubs = new ArrayList<>();
        this.homeFormation = TeamFormation.getRandomFormation();

        this.inFieldAway = new ArrayList<>();
        this.awaySubs = new ArrayList<>();
        this.awayFormation = TeamFormation.getRandomFormation();
    }

    /**
     * Instantiates a GameSim with a specific id and respective teams
     * @param id Unique id
     * @param homeTeam Team which is playing home
     * @param awayTeam Team which is playing away
     */
    public GameSim(int id, Team homeTeam, Team awayTeam){
        super(id, homeTeam.clone(), awayTeam.clone());

        this.gameState = GameState.INIT_GAME;
        this.time = 0;
        this.extraTime = 0;
        this.goals = new Tuple<>(0, 0);

        this.inFieldHome = new ArrayList<>();
        this.homeSubs = new ArrayList<>();
        this.homeFormation = TeamFormation.getRandomFormation();

        this.inFieldAway = new ArrayList<>();
        this.awaySubs = new ArrayList<>();
        this.awayFormation = TeamFormation.getRandomFormation();
    }

    /**
     * Instantiates a GameSim with respective attributes
     * @param id Unique id
     * @param date Date of occurence
     * @param homeTeam Team which is playing home
     * @param awayTeam Team which is playing away
     * @param gameState Current game's state
     * @param time Current game's time in minutes
     * @param extraTime Accumulated extra time in minutes during the game
     * @param goals Number of goals
     * @param inFieldHome Players in field from the team which is playing home
     * @param homeSubs Subsitutions of the team which is playing home
     * @param homeFormation Formation of the team which is playing home
     * @param inFieldAway Players in field from the team which is playing away
     * @param awaySubs Subsitutions of the team which is playing away
     * @param awayFormation Formation of the team which is playing away
     */
    public GameSim(int id, LocalDate date, Team homeTeam, Team awayTeam, GameState gameState,
                   int time, int extraTime, Tuple<Integer, Integer> goals, List<Player> inFieldHome,
                   List<Tuple<Integer, Integer>> homeSubs, TeamFormation homeFormation,
                   List<Player> inFieldAway, List<Tuple<Integer, Integer>> awaySubs,
                   TeamFormation awayFormation) {
        super(id, date, homeTeam, awayTeam);

        this.gameState = gameState;
        this.time = time;
        this.extraTime = extraTime;
        this.goals = goals;

        setInFieldHome(inFieldHome);
        setHomeSubs(homeSubs);
        this.homeFormation = homeFormation;

        setInFieldAway(inFieldAway);
        setAwaySubs(awaySubs);
        this.awayFormation = awayFormation;
    }

    /**
     * Instantiates a GameSim from a GameSim's object
     * @param gameSim GameSim's object
     */
    public GameSim(GameSim gameSim){
        super(gameSim.getId(), gameSim.getDate(), gameSim.getHomeTeam(), gameSim.getAwayTeam());

        this.gameState = gameSim.getGameState();
        this.time = gameSim.getTime();
        this.extraTime = gameSim.getExtraTime();
        this.goals = gameSim.getGoals();

        this.inFieldHome = gameSim.getInFieldHome();
        this.homeSubs = gameSim.getHomeSubs();
        this.homeFormation = gameSim.getHomeFormation();

        this.inFieldAway = gameSim.getInFieldAway();
        this.awaySubs = gameSim.getAwaySubs();
        this.awayFormation = gameSim.getAwayFormation();
    }

    /**
     * Simulate a new play of the game
     * @param time Time until the new play
     * @return
     */
    public String simulateGame(int time){
        int timeInSeconds = time*60;
        if(this.gameState == GameState.FST_HALF){
            if(this.time >= GameConstants.GAME_HALF_TIME && this.extraTime > 0){
                this.extraTime = Math.min(0, this.extraTime - timeInSeconds);
            }else if(this.time < GameConstants.GAME_HALF_TIME){
                this.time = Math.min(this.time + timeInSeconds, GameConstants.GAME_HALF_TIME);
            }else{
                this.time = GameConstants.GAME_HALF_TIME;
                this.gameState = GameState.HALF_TIME;
            }
        }else if(this.gameState == GameState.HALF_TIME){

        }else if(this.gameState == GameState.SND_HALF){

        }
        this.time += time;
        this.goals.setX(this.goals.getX()+1);
        return "Golo Equipa da casa";
    }

    /**
     * Execute a substitution
     * @param team Team (Home is 0 and Away is 1)
     * @param leave Player's shirt who is about to leave
     * @param stay Player's shirt who is about to join
     * @return Substitution succeeded
     */
    public boolean subPlayers(int team, int leave, int stay){
        if(leave == stay) return false;
        if((team == 0 ? this.homeSubs : this.awaySubs).size() >= 3) return false;
        if((team == 0 ? this.inFieldHome : this.inFieldAway).stream()
                .anyMatch(player -> player.getNumber() == stay)) return false;

        Player leavePlayer = (team == 0 ? this.inFieldHome : this.inFieldAway).stream()
                .filter(player -> leave == player.getNumber())
                .findAny()
                .orElse(null);
        if(leavePlayer == null) return false;

        Player stayPlayer = (team == 0 ? getHomeTeam() : getAwayTeam()).getTeamPlayers().stream()
                .filter(player -> stay == player.getNumber())
                .findAny()
                .orElse(null);

        if(stayPlayer != null){
            ((team == 0) ? this.inFieldHome : this.inFieldAway).remove(leavePlayer);
            ((team == 0) ? this.inFieldHome : this.inFieldAway).add(stayPlayer.clone());
            ((team == 0) ? this.homeSubs : this.awaySubs).add(new Tuple<>(leave, stay));
            return true;
        } return false;
    }

    /**
     * Parses an array of strings and loads the current instance with those values
     * @param args Array of strings containing respective values to be parsed
     * @throws IllegalArgumentException
     */
    public void parseCustom(String[] args) throws IllegalArgumentException {
        setHomeFormation(args[1] != null ? TeamFormation.valueOf(args[1]) : null);
        setAwayFormation(args[2] != null ? TeamFormation.valueOf(args[2]) : null);

        setGameState(args[3] != null ? GameState.valueOf(args[3]) : null);

        try{
            setTime(Integer.parseInt(args[4]));
        }catch (NumberFormatException e){
            System.out.println("GameSim_Time must be a number");
        }
    }

    /**
     * Parses an array of strings and creates a FullBack's instance from it
     * @param args Array of strings containing respective values to be parsed
     * @param gameId Game's id
     * @param homeTeam Team which plays at home
     * @param awayTeam Team which plays away
     * @return GameSim's object
     * @throws NumberFormatException
     * @throws InvalidPlayerSubException
     */
    public static GameSim parser(String[] args, int gameId, Team homeTeam, Team awayTeam)
            throws NumberFormatException, InvalidPlayerSubException {

        if(homeTeam == null || awayTeam == null) return null;

        GameSim gameSim = new GameSim(gameId, homeTeam, awayTeam);
        gameSim.setGoals(new Tuple<>(
                Integer.parseInt(args[2]),
                Integer.parseInt(args[3])
        ));
        gameSim.setDate(LocalDate.parse(args[4], DateUtils.dateFormatter));

        int switchTeam = 0;
        for(int i = 5; i < args.length; i++){
            if(args[i].contains("->")){
                switchTeam = 1;
                String[] players = args[i].split("->");
                int player1 = Integer.parseInt(players[0]), player2 = Integer.parseInt(players[1]);

                if (!gameSim.subPlayers( i > 20 ? 1 : 0, player1, player2))
                    throw new InvalidPlayerSubException(ColorUtils.RED +
                            "In game " + gameId + " theres an invalid sub with player " + player1 + " and " + player2
                    + ColorUtils.RESET);
            }else {
                gameSim.addPlayerToField(
                        switchTeam,
                        Integer.parseInt(args[i])
                );
            }
        }
        return gameSim;
    }

    /**
     * Selects player out of a team following a specific formation
     * @param team Respective Team value
     * @return List of picked Players
     */
    public List<Player> initInFieldTeam(int team){
        Random random = new Random();
        List<Player> teamPlayers = team == 1 ? this.getHomeTeam().getTeamPlayers() : this.getAwayTeam().getTeamPlayers();
        TeamFormation teamFormation = team == 1 ? this.homeFormation : this.awayFormation;
        /*
        int randomValue = random.nextInt(2);
        int totalKeepers = teamFormation.getKeepers();
        int totalDefenders = teamFormation.getDefenders();
        int totalMidFielders = teamFormation.getMidFielders() - randomValue;
        int totalFullBacks = randomValue;
        randomValue = random.nextInt(2);
        int totalStrikers = teamFormation.getStrikers() - randomValue;
        totalFullBacks += randomValue;

        List <Player> finalTeamPlayers = new ArrayList<>();
        List<Player> inFieldPlayer = new ArrayList<>();

        for (Player player: teamPlayers){
            if (player instanceof Keeper){
                if (totalKeepers > 0) {
                    --totalKeepers;
                    inFieldPlayer.add(player);
                }
                else
                    finalTeamPlayers.add(player);
            }
            else if (player instanceof Defender){
                if (totalDefenders > 0) {
                    --totalDefenders;
                    inFieldPlayer.add(player);
                }
                else
                    finalTeamPlayers.add(player);
            }
            else if (player instanceof MidFielder){
                if (totalMidFielders > 0) {
                    --totalMidFielders;
                    inFieldPlayer.add(player);
                }
                else
                    finalTeamPlayers.add(player);
            }
            else if (player instanceof Striker){
                if (totalStrikers > 0) {
                    --totalStrikers;
                    inFieldPlayer.add(player);
                }
                else
                    finalTeamPlayers.add(player);
            }
            else {
                if (totalFullBacks > 0){
                    --totalStrikers;
                    inFieldPlayer.add(player);
                }
                else
                    finalTeamPlayers.add(player);
            }
        }

        if (inFieldPlayer.size() != 11) {
            inFieldPlayer.addAll(
                    teamPlayers.stream()
                            .map(Player::clone)
                            .filter(player -> player instanceof Striker || player instanceof FullBack)
                            .limit(11 - inFieldPlayer.size())
                            .collect(Collectors.toList())
            );
        }

        return inFieldPlayer;
        */
        List<Player> inFieldPlayer = teamPlayers.stream()
                .map(Player::clone)
                .filter(player -> player instanceof Keeper)
                .limit(teamFormation.getKeepers()).collect(Collectors.toList());
        teamPlayers.removeAll(inFieldPlayer);

        inFieldPlayer.addAll(
                teamPlayers.stream()
                        .map(Player::clone)
                        .filter(player -> player instanceof Defender)
                        .limit(teamFormation.getDefenders())
                        .collect(Collectors.toList())
        );
        teamPlayers.removeAll(inFieldPlayer);

        int randomDef = random.nextInt(2);
        inFieldPlayer.addAll(
                teamPlayers.stream()
                        .map(Player::clone)
                        .filter(player -> player instanceof MidFielder)
                        .limit(teamFormation.getMidFielders() - randomDef)
                        .collect(Collectors.toList())
        );
        teamPlayers.removeAll(inFieldPlayer);
        if(randomDef > 0)
            inFieldPlayer.addAll(
                    teamPlayers.stream()
                            .map(Player::clone)
                            .filter(player -> player instanceof FullBack)
                            .limit(randomDef)
                            .collect(Collectors.toList())
            );
        teamPlayers.removeAll(inFieldPlayer);

        int randomMid = random.nextInt(2);
        inFieldPlayer.addAll(
                teamPlayers.stream()
                        .map(Player::clone)
                        .filter(player -> player instanceof Striker)
                        .limit(teamFormation.getStrikers() - randomMid)
                        .collect(Collectors.toList())
        );
        teamPlayers.removeAll(inFieldPlayer);
        inFieldPlayer.addAll(
                teamPlayers.stream()
                        .map(Player::clone)
                        .filter(player -> player instanceof FullBack)
                        .limit(randomMid)
                        .collect(Collectors.toList())
        );
        teamPlayers.removeAll(inFieldPlayer);

        inFieldPlayer.addAll(
                teamPlayers.stream()
                .map(Player::clone)
                .filter(player -> player instanceof Striker || player instanceof FullBack)
                .limit(11 - inFieldPlayer.size())
                .collect(Collectors.toList())
        );
        return inFieldPlayer;
    }

    /**
     * Get Game's winner
     * @return Game's winner
     */
    public int getWinner(){
        if(this.gameState == GameState.END_GAME)
            if(this.goals.getX() > this.goals.getY())
                return 1;
            else if(this.goals.getX() < this.goals.getY())
                return 2;
            else
                return 0;
        return -1;
    }

    /**
     * Add Player to the respective team's list of players in field
     * @param team Team value
     * @param playerNumber Player's shirt number
     * @return Boolean representing if the player was added
     */
    public boolean addPlayerToField(int team, int playerNumber){
        if((team == 0 ? this.inFieldHome : this.inFieldAway).size() >= 11)
            return false;

        Player wantedPlayer = (team == 0 ? getHomeTeam() : getAwayTeam())
                .getTeamPlayers().stream()
                .filter(player -> playerNumber == player.getNumber())
                .findAny()
                .orElse(null);

        if(wantedPlayer != null){
            (team == 0 ? this.inFieldHome : this.inFieldAway).add(wantedPlayer.clone());
            return true;
        }
        return false;
    }

    /**
     * Get current game's state
     * @return Game's state
     */
    public GameState getGameState() {
        return this.gameState;
    }

    /**
     * Set current game's state
     * @param gameState Game's state
     */
    public void setGameState(GameState gameState) {
        if(gameState != null)
            this.gameState = gameState;
    }

    /**
     * Get current time in minutes
     * @return Time in minutes
     */
    public int getTime() {
        return this.time;
    }

    /**
     * Set current time in minutes
     * @param time Time in minutes
     */
    public void setTime(int time) {
        if(0 <= time && time <= 90 + GameConstants.MAX_EXTRA_TIME)
            this.time = time;
    }

    /**
     * Get accumulated extra time in minutes
     * @return Extra time in minutes
     */
    public int getExtraTime() {
        return this.extraTime;
    }

    /**
     * Set accumulated extra time in minutes
     * @param extraTime Extra time in minutes
     */
    public void setExtraTime(int extraTime) {
        if(extraTime <= GameConstants.MAX_EXTRA_TIME)
            this.extraTime = extraTime;
    }

    /**
     * Get pair of goals per team
     * @return Pair of goals per team
     */
    public Tuple<Integer, Integer> getGoals() {
        return this.goals.clone();
    }

    /**
     * Set pair of goals per team
     * @param goals Pair of goals per team
     */
    public void setGoals(Tuple<Integer, Integer> goals) {
        this.goals = goals.clone();
    }

    /**
     * Get Players in field from the team which is playing home
     * @return List of Players
     */
    public List<Player> getInFieldHome() {
        return this.inFieldHome.stream()
                .map(Player::clone)
                .collect(Collectors.toList());
    }

    /**
     * Set Players in field from the team which is playing home
     * @param inFieldHome List of Players
     */
    public void setInFieldHome(List<Player> inFieldHome) {
        this.inFieldHome = inFieldHome.stream()
                .map(Player::clone)
                .collect(Collectors.toList());
    }

    /**
     * Get substitutions made by the team which is playing home during the game
     * @return List of pairs representing each substitution
     */
    public List<Tuple<Integer, Integer>> getHomeSubs() {
        return this.homeSubs.stream()
                .map(Tuple::clone)
                .collect(Collectors.toList());
    }

    /**
     * Set substitutions made by the team which is playing home during the game
     * @param homeSubs List of pairs representing each substitution
     */
    public void setHomeSubs(List<Tuple<Integer, Integer>> homeSubs) {
        this.homeSubs = homeSubs.stream()
                .map(Tuple::clone)
                .collect(Collectors.toList());
    }

    /**
     * Get formation of the team which is playing home
     * @return Team's formation
     */
    public TeamFormation getHomeFormation() {
        return this.homeFormation;
    }

    /**
     * Set formation of the team which is playing home
     * @param homeFormation Team's formation
     */
    public void setHomeFormation(TeamFormation homeFormation) {
        if(homeFormation != null)
            this.homeFormation = homeFormation;
    }

    /**
     * Get Players in field from the team which is playing away
     * @return List of Players
     */
    public List<Player> getInFieldAway() {
        return this.inFieldAway.stream()
                .map(Player::clone)
                .collect(Collectors.toList());
    }

    /**
     * Set Players in field from the team which is playing away
     * @param inFieldAway List of Players
     */
    public void setInFieldAway(List<Player> inFieldAway) {
        this.inFieldAway = inFieldAway.stream()
                .map(Player::clone)
                .collect(Collectors.toList());
    }

    /**
     * Get substitutions made by the team which is playing away during the game
     * @return List of pairs representing each substitution
     */
    public List<Tuple<Integer, Integer>> getAwaySubs() {
        return this.awaySubs.stream()
                .map(Tuple::clone)
                .collect(Collectors.toList());
    }

    /**
     * Set substitutions made by the team which is playing away during the game
     * @param awaySubs List of pairs representing each substitution
     */
    public void setAwaySubs(List<Tuple<Integer, Integer>> awaySubs) {
        this.awaySubs = awaySubs.stream()
                .map(Tuple::clone)
                .collect(Collectors.toList());
    }

    /**
     * Get formation of the team which is playing away
     * @return Team's formation
     */
    public TeamFormation getAwayFormation() {
        return this.awayFormation;
    }

    /**
     * Set formation of the team which is playing away
     * @param awayFormation Team's formation
     */
    public void setAwayFormation(TeamFormation awayFormation) {
        if(awayFormation != null)
            this.awayFormation = awayFormation;
    }

    /**
     * String representation of GameSim's instance
     * @return String representation of the instance
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GameSim{");
        sb.append("gameState=").append(gameState);
        sb.append(", time=").append(time);
        sb.append(", extraTime=").append(extraTime);
        sb.append(", goals=").append(goals);
        sb.append(", inFieldHome=").append(inFieldHome.stream().map(Player::getNumber).collect(Collectors.toList()));
        sb.append(", homeSubs=").append(homeSubs);
        sb.append(", homeFormation=").append(homeFormation);
        sb.append(", inFieldAway=").append(inFieldAway.stream().map(Player::getNumber).collect(Collectors.toList()));
        sb.append(", awaySubs=").append(awaySubs);
        sb.append(", awayFormation=").append(awayFormation);
        sb.append('}').append(super.toString()).append('\n');
        return sb.toString();
    }

    /**
     * Equality between GameSim's instance and another object
     * @param o Object
     * @return Boolean representing the equality of this instance comparing to the given object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GameSim gameSim = (GameSim) o;
        return Objects.equals(goals, gameSim.goals) && Objects.equals(inFieldHome, gameSim.inFieldHome) && Objects.equals(homeSubs, gameSim.homeSubs) && homeFormation == gameSim.homeFormation && Objects.equals(inFieldAway, gameSim.inFieldAway) && Objects.equals(awaySubs, gameSim.awaySubs) && awayFormation == gameSim.awayFormation;
    }

    /**
     * Clone GameSim's instance
     * @return GameSim's cloned instance
     */
    @Override
    public GameSim clone(){
        return new GameSim(this);
    }
}
