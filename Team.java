import java.util.ArrayList;

public class Team {
    private String name;
    private Players players = new Players(new ArrayList<Player>());


    public Team(String name) {
        this.name = name;

        if (name.equals("Suns")) {
            players.initialAddPlayer("Devin Booker", 2500.0, 26, "Suns", 1);
            players.initialAddPlayer("Chris Paul", 1500.0, 37, "Suns", 3);
            players.initialAddPlayer("Deandre Ayton", 2000.0, 24, "Suns", 22);
            players.initialAddPlayer("Kevin Durant", 3000.0, 34, "Suns", 35);
            players.initialAddPlayer("Terrence Ross", 1000.0, 32, "Suns", 8);
        }

        if (name.equals("Bulls")) {
            players.initialAddPlayer("Andre Drummond", 1500.0, 29, "Bulls", 3);
            players.initialAddPlayer("Zach LaVine", 3000.0, 28, "Bulls", 8);
            players.initialAddPlayer("Dalen Terry", 900.0, 20, "Bulls", 25);
            players.initialAddPlayer("Terry Taylor", 1000.0, 23, "Bulls", 32);
            players.initialAddPlayer("Carlik Jones", 800.0, 25, "Bulls", 22);
        }

        if (name.equals("Hawks")) {
            players.initialAddPlayer("Trae Young", 2200.0, 24, "Hawks", 11);
            players.initialAddPlayer("John Collins", 2000.0, 25, "Hawks", 20);
            players.initialAddPlayer("Aaron Holiday", 800.0, 26, "Hawks", 3);
            players.initialAddPlayer("Jalen Johnson", 1000.0, 21, "Hawks", 1);
            players.initialAddPlayer("Trent Forrest", 1200.0, 24, "Hawks", 2);
        }

        if (name.equals("Nets")) {
            players.initialAddPlayer("Mikal Bridges", 2400.0, 26, "Nets", 1);
            players.initialAddPlayer("Ben Simmons", 2000.0, 26, "Nets", 10);
            players.initialAddPlayer("Patty Mills", 900.0, 34, "Nets", 8);
            players.initialAddPlayer("Joe Harris", 1200.0, 31, "Nets", 12);
            players.initialAddPlayer("Seth Curry", 1900.0, 32, "Nets", 30);
        }

    }

    public String getName() {
        return name;
    }

    public Players getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = new Players(players);
    }

    @Override
    public String toString() {
        return "";
    }

    public Double averageCredit() {
        // Number of players
        ArrayList<Player> teamPlayers = this.getPlayers().getPlayerList();
        Integer playerCount = 0;

        double avgCredit = 0;
        for (Player player : teamPlayers) {
            playerCount++;
            avgCredit += player.getCredit();
        }

        // Average player credit
        avgCredit = avgCredit / playerCount;
        if(Double.isNaN(avgCredit)) avgCredit = 0;

        return avgCredit;
    }
}
