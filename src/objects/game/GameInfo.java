package objects.game;

import objects.team.Team;

import java.time.LocalDate;
import java.util.Objects;

public class GameInfo {

    /**
     * Game's unique Id
     */
    private final int id;

    /**
     * Game's date
     */
    private LocalDate date;

    /**
     * Game's team playing home
     */
    private Team homeTeam;

    /**
     * Game's team playing away
     */
    private Team awayTeam;

    /**
     * Instantiates a GameInfo
     */
    public GameInfo(){
        this.id = -1;
        this.date = LocalDate.now();
        this.homeTeam = null;
        this.awayTeam = null;
    }

    /**
     * Instantiates a GameInfo with respective Id
     * @param id Unique id
     */
    public GameInfo(int id){
        this.id = id;
        this.date = LocalDate.now();
        this.homeTeam = new Team();
        this.awayTeam = new Team();
    }

    /**
     * Instantiates a Team with respective id and teams
     * @param id Unique id
     * @param homeTeam Team's playing home
     * @param awayTeam Team's playing away
     */
    public GameInfo(int id, Team homeTeam, Team awayTeam) {
        this.id = id;
        this.date = LocalDate.now();
        setHomeTeam(homeTeam);
        setAwayTeam(awayTeam);
    }

    /**
     * Instantiates a Team with respective attributes
     * @param id Unique id
     * @param date Date
     * @param homeTeam Team's playing home
     * @param awayTeam Team's playing away
     */
    public GameInfo(int id, LocalDate date, Team homeTeam, Team awayTeam) {
        this.id = id;
        setDate(date);
        setHomeTeam(homeTeam);
        setAwayTeam(awayTeam);
    }

    /**
     * Instantiates a Team from a GameInfo's object
     * @param gameInfo GameInfo's object
     */
    public GameInfo(GameInfo gameInfo){
        this.id = gameInfo.getId();
        this.date = gameInfo.getDate();
        this.homeTeam = gameInfo.getHomeTeam();
        this.awayTeam = gameInfo.getAwayTeam();
    }

    /**
     * Get GameInfo's id
     * @return Unique id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get GameInfo's date of occurrence
     * @return Date
     */
    public LocalDate getDate() {
        return LocalDate.of(
                this.date.getYear(),
                this.date.getMonth(),
                this.date.getDayOfMonth()
        );
    }

    /**
     * Set GameInfo's date of occurence
     * @param date Date
     */
    public void setDate(LocalDate date) {
        this.date = LocalDate.of(
                date.getYear(),
                date.getMonth(),
                date.getDayOfMonth()
        );
    }

    /**
     * Get GameInfo's team which is playing home
     * @return Team's object
     */
    public Team getHomeTeam() {
        return this.homeTeam.clone();
    }

    /**
     * Set GameInfo's team which is playing home
     * @param homeTeam Team's object
     */
    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam.clone();
    }

    /**
     * Get GameInfo's team which is playing away
     * @return Team's object
     */
    public Team getAwayTeam() {
        return this.awayTeam.clone();
    }

    /**
     * Set GameInfo's team which is playing away
     * @param awayTeam Team's object
     */
    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam.clone();
    }

    /**
     * String representation of TeamInfo's instance
     * @return String representation of the instance
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GameInfo{");
        sb.append("id=").append(id);
        sb.append(", date=").append(date);
        sb.append(", homeTeam=").append(homeTeam);
        sb.append(", awayTeam=").append(awayTeam);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Equality between TeamInfo's instance and another object
     * @param o Object
     * @return Boolean representing the equality of this instance comparing to the given object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameInfo gameInfo = (GameInfo) o;
        return id == gameInfo.id && Objects.equals(date, gameInfo.date) && Objects.equals(homeTeam, gameInfo.homeTeam) && Objects.equals(awayTeam, gameInfo.awayTeam);
    }

    /**
     * Clone TeamInfo's instance
     * @return TeamInfo's cloned instance
     */
    public GameInfo clone(){
        return new GameInfo(this);
    }
}
