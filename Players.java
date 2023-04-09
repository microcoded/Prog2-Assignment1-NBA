import java.util.ArrayList;

public class Players {
    private ArrayList<Player> Players;

    public Players(String team) {
        Players = new ArrayList<>();
        if (team.equals("Suns")) {
            initialAddPlayer("Devin Booker", 2500.0, 26, "Suns", 1);
            initialAddPlayer("Chris Paul", 1500.0, 37, "Suns", 3);
            initialAddPlayer("Deandre Ayton", 2000.0, 24, "Suns", 22);
            initialAddPlayer("Kevin Durant", 3000.0, 34, "Suns", 35);
            initialAddPlayer("Terrence Ross", 1000.0, 32, "Suns", 8);
        }

        if (team.equals("Bulls")) {
            initialAddPlayer("Andre Drummond", 1500.0, 29, "Bulls", 3);
            initialAddPlayer("Zach LaVine", 3000.0, 28, "Bulls", 8);
            initialAddPlayer("Dalen Terry", 900.0, 20, "Bulls", 25);
            initialAddPlayer("Terry Taylor", 1000.0, 23, "Bulls", 32);
            initialAddPlayer("Carlik Jones", 800.0, 25, "Bulls", 22);
        }

        if (team.equals("Hawks")) {
            initialAddPlayer("Trae Young", 2200.0, 24, "Hawks", 11);
            initialAddPlayer("John Collins", 2000.0, 25, "Hawks", 20);
            initialAddPlayer("Aaron Holiday", 800.0, 26, "Hawks", 3);
            initialAddPlayer("Jalen Johnson", 1000.0, 21, "Hawks", 1);
            initialAddPlayer("Trent Forrest", 1200.0, 24, "Hawks", 2);
        }

        if (team.equals("Nets")) {
            initialAddPlayer("Mikal Bridges", 2400.0, 26, "Nets", 1);
            initialAddPlayer("Ben Simmons", 2000.0, 26, "Nets", 10);
            initialAddPlayer("Patty Mills", 900.0, 34, "Nets", 8);
            initialAddPlayer("Joe Harris", 1200.0, 31, "Nets", 12);
            initialAddPlayer("Seth Curry", 1900.0, 32, "Nets", 30);
        }

    }

    public void initialAddPlayer(String name, Double credit, Integer age, String team, Integer No) {
        // Add players only if new
        for (Player player : Players) {
            if (player.equals(new Player(name, credit, age, team, No))) {
                // Do nothing and exit if the player exists
                return;
            }
        }
        // Add new player
        Players.add(new Player(name, credit, age, team, No));
    }

    public ArrayList<Player> getPlayerList() {
        return Players;
    }

    public void setPlayerList(ArrayList<Player> list) {
        this.Players = list;
    }

    public void addPlayer(String name, Double credit, Integer age, String team, Integer No) {
        Players.add(new Player(name, credit, age, team, No));
    }

    public void deletePlayer(Player delPlayer) {
        Players.removeIf(player -> (player == delPlayer));
    }

}
