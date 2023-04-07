import java.util.ArrayList;
import java.util.List;
import java.text.*;

public class Teams {
    public static ArrayList<Team> teams = new ArrayList<>();

    // Takes no parameters
    public Teams() {
        teams.add(new Team("Suns"));
        teams.add(new Team("Bulls"));
        teams.add(new Team("Hawks"));
        teams.add(new Team("Nets"));
    }

    public static void mainMenu() {
        System.out.println("Welcome to the Teams Page! Please make a selection from the menu:\n" +
                "1. Display all teams.\n" +
                "2. Display all players.\n" +
                "3. Add a new team.\n" +
                "4. Manage an existing team.\n" +
                "5. Delete an existing team.\n" +
                "6. Display all players by an level.\n" +
                "R. Return to previous menu.");

        System.out.print("Enter a choice: ");
        String input = In.nextLine();

        switch(input) {
            case "1":
                displayTeams();
                break;
            case "2":
                // Players display
                displayPlayers();
                break;
            case "3":
                // Add new team
                break;
            case "4":
                // Manage existing team
                break;
            case "5":
                // Delete an existing team
                break;
            case "6":
                // Delete all players by a level
                break;
            case "R":
                new Association().use();
                break;

        }
    }

    public static void displayTeams() {
        System.out.println("+----------------------+---------------------+-----------------------+--------------+\n" +
                "| Team Name            | Number of Players   | Average Player Credit | Average Age  |\n" +
                "+----------------------+---------------------+-----------------------+--------------+");

        for (Team team : teams) {
            // Team name
            System.out.print("| ");
            String teamName = team.getName();
            System.out.print(teamName);
            System.out.print(" ".repeat(21 - teamName.length()));
            System.out.print("| ");

            // Number of players
            ArrayList<Player> teamPlayers = team.getPlayers().getPlayerList();
            Integer playerCount = 0;
            double avgCredit = 0;
            double avgAge = 0;
            for (Player player : teamPlayers) {
                playerCount++;
                avgCredit += player.getCredit();
                avgAge += player.getAge();
            }
            System.out.print(playerCount + " " .repeat(20 - playerCount.toString().length()));
            System.out.print("| ");

            // Average player credit
            avgCredit = avgCredit / playerCount;
            System.out.print(formatted(avgCredit));
            System.out.print(" ".repeat(21 - Double.toString(avgCredit).length()));
            System.out.print("| ");

            // Average age
            avgAge = avgAge / playerCount;
            System.out.print(formatted(avgAge));
            System.out.print(" ".repeat(12 - Double.toString(avgAge).length()));
            System.out.println("|");
        }

        // Line at the bottom
        System.out.println("+----------------------+---------------------+-----------------------+--------------+");
        mainMenu();
    }

    private static String formatted(double number) {
        return new DecimalFormat("#####0.00").format(number);
    }

    public static void displayPlayers() {
        System.out.println("+----------------------+----------------+--------------+-------+-------+-----------+\n" +
                "| Player Name          | Credit         | Level        | Age   | No    | Team      |\n" +
                "+----------------------+----------------+--------------+-------+-------+-----------+");
        // Each team
        for (Team team : teams) {
            ArrayList<Player> teamPlayers = team.getPlayers().getPlayerList();
            // Each player
            for (Player player : teamPlayers) {
                // Name
                System.out.print("| ");
                String name = player.getName();
                System.out.print(name);
                System.out.print(" ".repeat(21 - name.length()) + "| ");

                // Credit
                Double credit = player.getCredit();
                System.out.print(formatted(credit));
                System.out.print(" ".repeat(14 - credit.toString().length()) + "| ");

                // Level
                String level = player.getLevel();
                System.out.print(level);
                System.out.print(" ".repeat(13 - level.toString().length()) + "| ");

                // Age
                Integer age = player.getAge();
                System.out.print(age);
                System.out.print(" ".repeat(6 - age.toString().length()) + "| ");

                // No
                Integer no = player.getNo();
                System.out.print(no);
                System.out.print(" ".repeat(6 - no.toString().length()) + "| ");

                // Team
                System.out.print(team.getName());
                System.out.println(team + " ".repeat(10 - team.getName().length()) + "|");
            }
            // Print dividing line for end of team
            System.out.println("+----------------------+----------------+--------------+-------+-------+-----------+");
        }
        mainMenu();
    }
}
