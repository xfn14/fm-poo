package objects.game;

import exceptions.InvalidPlayerSubException;
import objects.game.plays.GamePlay;
import objects.game.plays.PlayFinish;
import objects.game.plays.PlayPass;
import objects.player.*;
import objects.team.Team;
import objects.team.TeamFormation;
import utils.ColorUtils;
import utils.DateUtils;
import utils.RandomUtils;
import utils.Tuple;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class GameSim extends GameInfo implements Serializable {
    private final Random random = new Random();
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
     * Simulate a new play/plays of the game with knowledge about the last one
     * @param time Time to simulate the new play
     * @param lastPlay Last play executed
     * @return the resulted list of plays
     */
    public List<GamePlay> simulateGame(int time, GamePlay lastPlay){
        List<GamePlay> newPlay = new ArrayList<>();
        boolean stop = skipTime(time);

        if(lastPlay instanceof PlayFinish){
            PlayFinish playFinish = (PlayFinish) lastPlay;
            List<Player> inField = playFinish.getTeam() == 1 ? this.inFieldHome : this.inFieldAway;
            List<Player> rival = playFinish.getTeam() == 1 ? this.inFieldAway : this.inFieldHome;
            if(playFinish.getResult()){
                newPlay.add(PlayPass.initialGamePass(
                        playFinish.getTeam() == 1 ? 2 : 1,
                        this.gameState == GameState.FST_HALF ? 1 : 2,
                        rival.get(rival.size()-1),
                        rival.get(rival.size()-2)
                ));
                if(playFinish.getTeam() == 1) this.goals.setX(this.goals.getX()+1);
                else this.goals.setY(this.goals.getY()+1);
            }else{
                int interceptorRandom = RandomUtils.randomBetween(1, 3);
                int receiverRandom = RandomUtils.randomBetween(1,4);
                newPlay.add(new PlayPass(
                        rival.get(0),
                        this.time,
                        playFinish.getTeam() == 1 ? 2 : 1,
                        rival.get(receiverRandom),
                        inField.get(inField.size()-interceptorRandom)
                ));
            }
        }else if(lastPlay instanceof PlayPass){
            PlayPass playPass = (PlayPass) lastPlay;
            List<Player> inField = playPass.getTeam() == 1 ? this.inFieldHome : this.inFieldAway;
            TeamFormation teamFormation = playPass.getTeam() == 1 ? this.homeFormation : this.awayFormation;
            List<Player> rival = playPass.getTeam() == 1 ? this.inFieldAway : this.inFieldHome;
            if(playPass.getResult()){
                int playerIdx = inField.indexOf(playPass.getReceiver());
                if(inField.size() - teamFormation.getStrikers() - teamFormation.getMidFielders() + 1 < playerIdx){
                    if(random.nextInt(100) > 60){
                        PlayFinish playFinish = new PlayFinish(playPass.getReceiver(), this.time, playPass.getTeam(), rival.get(0));
                        newPlay.add(playFinish);
                        if(playFinish.getResult()){
                            newPlay.add(PlayPass.initialGamePass(
                                    playPass.getTeam() == 1 ? 2 : 1,
                                    this.gameState == GameState.FST_HALF ? 1 : 2,
                                    rival.get(rival.size()-1),
                                    rival.get(rival.size()-2)
                            ));
                            if(playFinish.getTeam() == 1) this.goals.setX(this.goals.getX()+1);
                            else this.goals.setY(this.goals.getY()+1);
                        }else{
                            int interceptorRandom = RandomUtils.randomBetween(1, 3);
                            int receiverRandom = RandomUtils.randomBetween(1,4);
                            newPlay.add(new PlayPass(
                                    rival.get(0),
                                    this.time,
                                    playPass.getTeam() == 1 ? 2 : 1,
                                    rival.get(receiverRandom),
                                    inField.get(inField.size()-interceptorRandom)
                            ));
                        }
                    }else{
                        int passRandom = RandomUtils.randomBetween(-1, 4);
                        if(passRandom == 0) passRandom++;
                        newPlay.add(new PlayPass(
                                playPass.getReceiver(),
                                this.time,
                                playPass.getTeam(),
                                inField.get(Math.min(playerIdx + passRandom, inField.size()-1)),
                                rival.get(rival.size() - playerIdx - 1)
                        ));
                    }
                }else{
                    int passRandom = RandomUtils.randomBetween(1, 6);
                    newPlay.add(new PlayPass(
                            playPass.getReceiver(),
                            this.time,
                            playPass.getTeam(),
                            inField.get(inField.size() - passRandom),
                            rival.get(rival.size() - playerIdx - 2)
                    ));
                }
            }else{
                int passRandom = RandomUtils.randomBetween(-1, 6);
                if(passRandom == 0) passRandom++;
                if(playPass.getInterceptor() == null){
                    int randomIdx = rival.indexOf(playPass.getInterceptor());
                    newPlay.add(new PlayPass(
                            playPass.getInterceptor(),
                            this.time,
                            playPass.getTeam(),
                            rival.get(rival.size() + passRandom),
                            inField.get(inField.size() - randomIdx - 1)
                    ));
                }else{
                    int playerIdx = inField.indexOf(playPass.getPlayer());
                    newPlay.add(new PlayPass(
                            rival.get(Math.min(rival.size() - playerIdx - 1, rival.size() - 1)),
                            this.time,
                            playPass.getTeam(),
                            rival.get(Math.min(rival.size() - passRandom, rival.size() - 1)),
                            inField.get(Math.min(inField.size() - playerIdx - 1, inField.size()-1))
                    ));
                }
            }
        }

        if(stop && this.gameState == GameState.FST_HALF){
            Player player = this.inFieldAway.get(this.inFieldAway.size()-1);
            Player receiver = this.inFieldAway.get(this.inFieldAway.size()-2);
            PlayPass halfStartPass = PlayPass.initialGamePass(2, 2, player, receiver);
            newPlay.add(halfStartPass);
        }
        return newPlay;
    }

    /**
     * Skips time resulting in game's state changes
     * @param time Time in minutes
     * @return If game is changing state
     */
    private boolean skipTime(int time){
        int timeInSeconds = time*60;
        if(this.gameState == GameState.FST_HALF){
            if(this.time >= GameConstants.GAME_HALF_TIME && this.extraTime > 0){
                int delta = this.extraTime - timeInSeconds > 0 ? timeInSeconds : this.extraTime;

                this.extraTime -= delta;
                this.time += delta;
            }else if(this.time < GameConstants.GAME_HALF_TIME){
                this.time = Math.min(this.time + timeInSeconds, GameConstants.GAME_HALF_TIME);
            }else{
                this.time = GameConstants.GAME_HALF_TIME;
                this.gameState = GameState.HALF_TIME;
                return true;
            }
        }else if(this.gameState == GameState.SND_HALF){
            if(this.time >= (GameConstants.GAME_HALF_TIME*2) && this.extraTime > 0){
                this.extraTime = Math.min(0, this.extraTime - timeInSeconds);
                this.time += timeInSeconds;
            }else if(this.time < (GameConstants.GAME_HALF_TIME*2)){
                this.time = Math.min(this.time + timeInSeconds, GameConstants.GAME_HALF_TIME*2);
            }else{
                this.gameState = GameState.END_GAME;
                return true;
            }
        }
        return false;
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
     * Parses an array of strings and creates a GameSim's instance from it
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

        inFieldPlayer.sort((p1, p2) -> {
            int res;
            int val1, val2;

            if(p1 instanceof Striker) val1 = 5;
            else if (p1 instanceof FullBack) val1 = 4;
            else if (p1 instanceof MidFielder) val1 = 3;
            else if (p1 instanceof Defender) val1 = 2;
            else if (p1 instanceof Keeper) val1 = 1;
            else val1 = 0;

            if(p2 instanceof Striker) val2 = 5;
            else if (p2 instanceof FullBack) val2 = 4;
            else if (p2 instanceof MidFielder) val2 = 3;
            else if (p2 instanceof Defender) val2 = 2;
            else if (p2 instanceof Keeper) val2 = 1;
            else val2 = 0;

            res = Integer.compare(val1, val2);
            if(res == 0) res = Integer.compare(p1.calcAbility(), p2.calcAbility());

            return res;
        });

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
