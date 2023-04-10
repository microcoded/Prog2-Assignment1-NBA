import java.util.ArrayList;

public class Teams {
    public static ArrayList<Team> teams = new ArrayList<>();

    // Takes no parameters
    public Teams() {
        addTeam("Suns");
        addTeam("Bulls");
        addTeam("Hawks");
        addTeam("Nets");
    }

    public void addTeam(String teamName) {
        // Add team if it does not already exist to avoid duplicates when entering from Association
        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                // Do nothing and exit if the team exists
                return;
            }
        }
        teams.add(new Team(teamName));
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

        switch (input) {
            case "1":
                displayTeams();
                break;
            case "2":
                // Players display
                displayPlayers();
                break;
            case "3":
                // Add new team
                addTeam();
                break;
            case "4":
                // Manage existing team
                manageTeam();
                break;
            case "5":
                // Delete an existing team
                deleteTeam();
                break;
            case "6":
                // Display all players by a level
                displayLevel();
                break;
            case "R":
                Association.user();
                break;

        }
    }

    private static void displayTeams() {
        // Display header
        Utils.teamsHeader();

        for (Team team : teams) {
            // Team name
            String teamName = team.getName();

            // Number of players
            ArrayList<Player> teamPlayers = team.getPlayers().getPlayerList();
            int playerCount = 0;
            double avgCredit = 0;
            double avgAge = 0;
            for (Player player : teamPlayers) {
                playerCount++;
                avgCredit += player.getCredit();
                avgAge += player.getAge();
            }

            // Average player credit
            avgCredit = avgCredit / playerCount;
            if (Double.isNaN(avgCredit)) avgCredit = 0;

            // Average age
            avgAge = avgAge / playerCount;
            if (Double.isNaN(avgAge)) avgAge = 0;
            System.out.format(Utils.teamsFormat, teamName, playerCount, avgCredit, avgAge);
        }

        // Line at the bottom
        Utils.teamTableEnd();
        mainMenu();
    }

    private static void displayPlayers() {
        // Display top header
        Utils.DisplayPlayerFromAllTeamsHeader();

        // Each team
        for (Team team : teams) {
            ArrayList<Player> teamPlayers = team.getPlayers().getPlayerList();
            // Each player
            for (Player player : teamPlayers) {
                // Name
                String name = player.getName();

                // Credit
                Double credit = player.getCredit();

                // Level
                String level = player.getLevel();

                // Age
                Integer age = player.getAge();

                // No
                Integer no = player.getNo();

                // Team
                String teamName = team.getName();
                System.out.format(Utils.DisplayPlayerFromAllTeamsFormat, name, credit, level, age, no, teamName);
            }
            // Print dividing line for end of team
            Utils.DisplayPlayerFromAllTeamsEnd();
        }
        mainMenu();
    }

    private static void addTeam() {
        System.out.print("Please enter the name of the team: ");
        String choice = In.nextLine();

        // While team already exists, get a new name
        while (teamExists(choice)) {
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

    private static void deleteTeam() {
        System.out.print("Please enter the team's name that you want to delete: ");
        String choice = In.nextLine();

        // If team doesn't exist, go to main menu
        if (!teamExists(choice)) {
            System.out.println("Team " + choice + " does not exist!");
            mainMenu();
        }

        // Find the team that matches the name and delete it
        teams.removeIf(team -> (team.getName().equals(choice)));
        System.out.println("The team " + choice + " has been deleted.");
        mainMenu();
    }

    private static void manageTeam() {
        System.out.print("Please enter the team's name that you want to manage: ");
        String choice = In.nextLine();

        // If team doesn't exist, go to main menu
        if (!teamExists(choice)) {
            System.out.println("Team does not exist!");
            mainMenu();
        }

        // Find the team that matches the name and open teams page
        for (Team team : teams) {
            if (team.getName().equals(choice)) {
                teamsPage(team);
                break;
            }
        }
    }

    private static void displayLevel() {
        ArrayList<String> levels = new ArrayList<>();
        levels.add("Edge");
        levels.add("Common");
        levels.add("Core");
        levels.add("All Star");

        System.out.print("Please enter the player's level that you want to view: ");
        String choice = In.nextLine();

        while (!levels.contains(choice)) {
            System.out.print("No such level! Please re-enter the level: ");
            choice = In.nextLine();
        }

        // Display top header
        Utils.DisplayPlayerFromAllTeamsHeader();

        // Each team
        for (Team team : teams) {
            ArrayList<Player> teamPlayers = team.getPlayers().getPlayerList();
            // Each player
            for (Player player : teamPlayers) {
                // Level
                String level = player.getLevel();

                if (level.equals(choice)) {

                    // Name
                    String name = player.getName();

                    // Credit
                    Double credit = player.getCredit();

                    // Age
                    Integer age = player.getAge();

                    // No
                    Integer no = player.getNo();

                    // Team
                    String teamName = team.getName();
                    System.out.format(Utils.DisplayPlayerFromAllTeamsFormat, name, credit, level, age, no, teamName);
                }
            }
        }
        // Print bottom line
        Utils.DisplayPlayerFromAllTeamsEnd();

        mainMenu();
    }

    private static void teamsPage(Team team) {
        System.out.println("Welcome to the " + team.getName() + " 's Page! Please make a selection from the menu:\n" +
                "1. Display team's players.\n" +
                "2. Add a new player.\n" +
                "3. Update an existing player.\n" +
                "4. Delete an existing player.\n" +
                "R. Return to previous menu.");
        System.out.print("Enter a choice: ");
        String input = In.nextLine();

        switch (input) {
            case "1":
                teamDisplayPlayers(team);
                break;
            case "2":
                teamAddPlayer(team);
                break;
            case "3":
                teamUpdatePlayer(team);
                break;
            case "4":
                teamDeletePlayer(team);
                break;
            case "R":
                mainMenu();
                break;
        }
    }

    private static void teamDisplayPlayers(Team team) {
        // Display top header
        Utils.playerHeader();

        ArrayList<Player> teamPlayers = team.getPlayers().getPlayerList();
        // Each player
        for (Player player : teamPlayers) {
            // Name
            String name = player.getName();

            // Credit
            Double credit = player.getCredit();

            // Level
            String level = player.getLevel();

            // Age
            Integer age = player.getAge();

            // No
            Integer no = player.getNo();

            // Output
            System.out.format(Utils.PlayerFormat, name, credit, level, no, age);
        }
        // Print dividing line for end of team
        Utils.playerTableEnd();
        teamsPage(team);
    }

    private static void teamAddPlayer(Team team) {
        System.out.print("Please enter the player's name: ");
        String name = In.nextLine();
        System.out.print("Please enter the player's credit: ");
        Double credit = In.nextDouble();
        System.out.print("Please enter the player's age: ");
        Integer age = In.nextInt();
        System.out.print("Please enter the player's No: ");
        Integer No = In.nextInt();

        // Check if No already exists in team
        ArrayList<Player> teamPlayers = team.getPlayers().getPlayerList();
        No = getUniqueNo(No, teamPlayers);
        team.getPlayers().addPlayer(name, credit, age, team.getName(), No);
        System.out.println("Player " + name + " added!");
        teamsPage(team);
    }

    private static Integer getUniqueNo(Integer no, ArrayList<Player> teamPlayers) {
        boolean NoExists = true;
        while (NoExists) {
            NoExists = false;
            // Each player
            for (Player player : teamPlayers) {
                // If number is found
                if (no.equals(player.getNo())) {
                    System.out.print("This No has been occupied by: " + player.getName() + ". Please re-enter the No: ");
                    no = In.nextInt();
                    NoExists = true;
                    break;
                }
            }
        }
        return no;
    }

    private static void teamUpdatePlayer(Team team) {
        ArrayList<Player> teamPlayers = team.getPlayers().getPlayerList();
        Object[][] playerLocation = playerExists(team);
        boolean playerExists = (boolean) playerLocation[0][0];
        int playerIndex = (int) playerLocation[0][1];

        // Go back if player does not exist, otherwise continue
        if (!playerExists) {
            System.out.println("Player does not exist.");
        } else {
            System.out.print("Please enter the name: ");
            String newName = In.nextLine();
            System.out.print("Please enter the credit: ");
            Double credit = In.nextDouble();
            System.out.print("Please enter the age: ");
            Integer age = In.nextInt();
            System.out.print("Please enter the No: ");
            Integer No = In.nextInt();

            // Check if No already exists in team
            No = getUniqueNo(No, teamPlayers);

            // Update player information
            teamPlayers.set(playerIndex, new Player(newName, credit, age, team.getName(), No));
            System.out.println("Player information updated.");
        }
        // Go back
        teamsPage(team);
    }

    private static void teamDeletePlayer(Team team) {
        ArrayList<Player> teamPlayers = team.getPlayers().getPlayerList();
        Object[][] playerLocation = playerExists(team);
        boolean playerExists = (boolean) playerLocation[0][0];
        int playerIndex = (int) playerLocation[0][1];

        // Go back if player does not exist, otherwise continue
        if (!playerExists) {
            System.out.println("Player does not exist.");
        } else {
            // Delete player
            team.getPlayers().deletePlayer(teamPlayers.get(playerIndex));
            System.out.println("Player deleted.");
        }
        // Go back
        teamsPage(team);
    }

    private static Object[][] playerExists(Team team) {
        System.out.print("Please enter the player's name: ");
        String name = In.nextLine();

        // Finding if the player we want exists
        boolean playerExists = false;
        int playerIndex = 0;
        ArrayList<Player> teamPlayers = team.getPlayers().getPlayerList();
        for (int i = 0; i < teamPlayers.size(); i++) {
            Player player = teamPlayers.get(i);
            if (player.getName().equalsIgnoreCase(name)) {
                playerExists = true;
                playerIndex = i;
            }
        }
        Object[][] playerLocation = new Object[1][2];
        playerLocation[0][0] = playerExists;
        playerLocation[0][1] = playerIndex;
        return playerLocation;
    }

    public static ArrayList<Team> getTeamsList() {
        return teams;
    }

    public static Team getTeam(String teamName) {
        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                return team;
            }
        }
        return null;
    }

}
