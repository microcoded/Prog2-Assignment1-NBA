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
                addTeam();
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
//        System.out.println("+----------------------+---------------------+-----------------------+--------------+\n" +
//                "| Team Name            | Number of Players   | Average Player Credit | Average Age  |\n" +
//                "+----------------------+---------------------+-----------------------+--------------+");
        Utils.teamsHeader();

        for (Team team : teams) {
            // Team name
//            System.out.print("| ");
            String teamName = team.getName();
//            System.out.print(teamName);
//            System.out.print(" ".repeat(21 - teamName.length()));
//            System.out.print("| ");

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
//            System.out.print(playerCount + " " .repeat(20 - playerCount.toString().length()));
//            System.out.print("| ");

            // Average player credit
            avgCredit = avgCredit / playerCount;
            if(Double.isNaN(avgCredit)) avgCredit = 0;
//            System.out.print(formatted(avgCredit));
//            System.out.print(" ".repeat(21 - Double.toString(avgCredit).length()));
//            System.out.print("| ");

            // Average age
            avgAge = avgAge / playerCount;
            if(Double.isNaN(avgAge)) avgAge = 0;
//            System.out.print(formatted(avgAge));
//            System.out.print(" ".repeat(12 - Double.toString(avgAge).length()));
//            System.out.println("|");
            System.out.format(Utils.teamsFormat,teamName,playerCount,avgCredit,avgAge);
        }

        // Line at the bottom
        Utils.teamTableEnd();
        mainMenu();
    }

    private static String formatted(double number) {
        return new DecimalFormat("#####0.00").format(number);
    }

    public static void displayPlayers() {
//        System.out.println("+----------------------+----------------+--------------+-------+-------+-----------+\n" +
//                "| Player Name          | Credit         | Level        | Age   | No    | Team      |\n" +
//                "+----------------------+----------------+--------------+-------+-------+-----------+");

        Utils.DisplayPlayerFromAllTeamsHeader();

        // Each team
        for (Team team : teams) {
            ArrayList<Player> teamPlayers = team.getPlayers().getPlayerList();
            // Each player
            for (Player player : teamPlayers) {
                // Name
//                System.out.print("| ");
                String name = player.getName();
//                System.out.print(name);
//                System.out.print(" ".repeat(21 - name.length()) + "| ");

                // Credit
                Double credit = player.getCredit();
//                System.out.print(formatted(credit));
//                System.out.print(" ".repeat(14 - credit.toString().length()) + "| ");

                // Level
                String level = player.getLevel();
//                System.out.print(level);
//                System.out.print(" ".repeat(13 - level.toString().length()) + "| ");

                // Age
                Integer age = player.getAge();
//                System.out.print(age);
//                System.out.print(" ".repeat(6 - age.toString().length()) + "| ");

                // No
                Integer no = player.getNo();
//                System.out.print(no);
//                System.out.print(" ".repeat(6 - no.toString().length()) + "| ");

                // Team
//                System.out.print(team.getName());
//                System.out.println(team + " ".repeat(10 - team.getName().length()) + "|");
                System.out.format(Utils.DisplayPlayerFromAllTeamsFormat,name,credit,level,age,no,team.getName());
            }
            // Print dividing line for end of team
//            System.out.println("+----------------------+----------------+--------------+-------+-------+-----------+");
            Utils.DisplayPlayerFromAllTeamsEnd();
        }
        mainMenu();
    }

    public static void addTeam() {
        System.out.print("Please enter the name of the team: ");
        String choice = In.nextLine();

        // While team already exists, get a new name
        while(teamExists(choice)) {
            System.out.print("Team " + choice + " already exist! Please enter a new name: ");
            choice = In.nextLine();
        }
        teams.add(new Team(choice));
        System.out.println("Team " + choice + " added!");
        mainMenu();
    }

    private static boolean teamExists(String teamName) {
        for (Team team : teams) {
            if (team.getName().equals(teamName)) return true;
        }
        return false;
    }
}
