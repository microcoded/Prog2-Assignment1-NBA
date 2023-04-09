import java.util.ArrayList;

public class Players {
    private ArrayList<Player> Players;

    public Players(String team) {
        Players = new ArrayList<>();
        if (team.equals("Suns")) {
            Players.add(new Player("Devin Booker", 2500.0, 26, "Suns", 1));
            Players.add(new Player("Chris Paul", 1500.0, 37, "Suns", 3));
            Players.add(new Player("Deandre Ayton", 2000.0, 24, "Suns", 22));
            Players.add(new Player("Kevin Durant", 3000.0, 34, "Suns", 35));
            Players.add(new Player("Terrence Ross", 1000.0, 32, "Suns", 8));
        }
        if (team.equals("Bulls")) {
            Players.add(new Player("Andre Drummond", 1500.0, 29, "Bulls", 3));
            Players.add(new Player("Zach LaVine", 3000.0, 28, "Bulls", 8));
            Players.add(new Player("Dalen Terry", 900.0, 20, "Bulls", 25));
            Players.add(new Player("Terry Taylor", 1000.0, 23, "Bulls", 32));
            Players.add(new Player("Carlik Jones", 800.0, 25, "Bulls", 22));
        }

        if (team.equals("Hawks")) {
            Players.add(new Player("Trae Young", 2200.0, 24, "Hawks", 11));
            Players.add(new Player("John Collins", 2000.0, 25, "Hawks", 20));
            Players.add(new Player("Aaron Holiday", 800.0, 26, "Hawks", 3));
            Players.add(new Player("Jalen Johnson", 1000.0, 21, "Hawks", 1));
            Players.add(new Player("Trent Forrest", 1200.0, 24, "Hawks", 2));
        }

        if (team.equals("Nets")) {
            Players.add(new Player("Mikal Bridges", 2400.0, 26, "Nets", 1));
            Players.add(new Player("Ben Simmons", 2000.0, 26, "Nets", 10));
            Players.add(new Player("Patty Mills", 900.0, 34, "Nets", 8));
            Players.add(new Player("Joe Harris", 1200.0, 31, "Nets", 12));
            Players.add(new Player("Seth Curry", 1900.0, 32, "Nets", 30));
        }
    }

    public ArrayList<Player> getPlayerList() {
        return Players;
    }

    public void addPlayer(String name, Double credit, Integer age, String team, Integer No) {
        Players.add(new Player(name, credit, age, team, No));
    }

    public void deletePlayer(Player delPlayer) {
        Players.removeIf(player -> (player == delPlayer));
    }

}
