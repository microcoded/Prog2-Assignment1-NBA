import java.util.ArrayList;

public class Players {
    private ArrayList<Player> Players;

    public Players(ArrayList<Player> Players) {
        this.Players = Players;

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

    public void addPlayer(String name, Double credit, Integer age, String team, Integer No) {
        Players.add(new Player(name, credit, age, team, No));
    }

    public void deletePlayer(Player delPlayer) {
        Players.removeIf(player -> (player == delPlayer));
    }

}
