package objects.game;

import objects.team.Team;

import java.time.LocalDate;
import java.util.Objects;

public class GameInfo {
    private final int id;
    private LocalDate date;
    private Team homeTeam;
    private Team awayTeam;

    public GameInfo(){
        this.id = -1;
        this.date = LocalDate.now();
        this.homeTeam = null;
        this.awayTeam = null;
    }

    public GameInfo(int id, Team homeTeam, Team awayTeam) {
        this.id = id;
        this.date = LocalDate.now();
        setHomeTeam(homeTeam);
        setAwayTeam(awayTeam);
    }

    public GameInfo(int id, LocalDate date, Team homeTeam, Team awayTeam) {
        this.id = id;
        setDate(date);
        setHomeTeam(homeTeam);
        setAwayTeam(awayTeam);
    }

    public GameInfo(GameInfo gameInfo){
        this.id = gameInfo.getId();
        this.date = gameInfo.getDate();
        this.homeTeam = gameInfo.getHomeTeam();
        this.awayTeam = gameInfo.getAwayTeam();
    }

    public int getId() {
        return this.id;
    }

    public LocalDate getDate() {
        return LocalDate.of(
                this.date.getYear(),
                this.date.getMonth(),
                this.date.getDayOfMonth()
        );
    }

    public void setDate(LocalDate date) {
        this.date = LocalDate.of(
                this.date.getYear(),
                this.date.getMonth(),
                this.date.getDayOfMonth()
        );
    }

    public Team getHomeTeam() {
        return this.homeTeam.clone();
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam.clone();
    }

    public Team getAwayTeam() {
        return this.awayTeam.clone();
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam.clone();
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameInfo gameInfo = (GameInfo) o;
        return id == gameInfo.id && Objects.equals(date, gameInfo.date) && Objects.equals(homeTeam, gameInfo.homeTeam) && Objects.equals(awayTeam, gameInfo.awayTeam);
    }

    public GameInfo clone(){
        return new GameInfo(this);
    }
}
