import java.util.ArrayList;

public class Team {
    private String name;
    private Players players;


    public Team(String name) {
        this.name = name;
        this.players = new Players(name);
    }

    public String getName() {
        return name;
    }

    public Players getPlayers() {
        return players;
    }

}
