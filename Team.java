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

    public static void updateCredit(Team winTeam, Team loseTeam) {
        double difference = winTeam.averageCredit() - loseTeam.averageCredit();

        ArrayList<Player> winTeamPlayers = winTeam.getPlayers().getPlayerList();
        ArrayList<Player> loseTeamPlayers = loseTeam.getPlayers().getPlayerList();

        for (Player player : winTeamPlayers) {
            player.setCredit(player.getCredit() + (difference / 5));
        }

        for (Player player : loseTeamPlayers) {
            player.setCredit(player.getCredit() + (difference / 5));
        }

        winTeam.getPlayers().setPlayerList(winTeamPlayers);
        loseTeam.getPlayers().setPlayerList(loseTeamPlayers);
    }
}
