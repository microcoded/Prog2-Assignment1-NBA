import java.util.ArrayList;

public class Season {
    private static ArrayList<Game> schedule;
    private static ArrayList<Team> currentTeamList;

    private ArrayList<Record> records;

    // Takes no parameters
    public Season() {
        schedule = new ArrayList<Game>();

        currentTeamList = Teams.getTeamsList();
        for (int i = 0; i < (currentTeamList.size() / 2) + 1; i++) {
            addGame(i + 1);
        }
    }

    private void addGame(int term) {
        // Add game if it does not already exist to avoid duplicates when entering from Association
        for (Game game : schedule) {
            if (game.getTerm().equals(term)) {
                // Do nothing and exit if the game exists
                return;
            }
        }
        schedule.add(new Game(term));
    }


    public static void mainMenu() {
        System.out.println("Welcome to the season page! Please make a selection from the menu:\n" +
                "1. Add a team to the round.\n" +
                "2. Display the current round.\n" +
                "3. Play games.\n" +
                "4. Display the game result records.\n" +
                "R. Return to previous menu.");
        System.out.print("Enter a choice: ");
        String choice = In.nextLine();

        switch (choice) {
            case "1":
                // Add a team to the round
                addTeam();
                break;
            case "2":
                // Display the current round
                displayRound();
                break;
            case "3":
                // Play games
                break;
            case "4":
                // Display the game result records
                break;
            case "R":
                new Association().use();
                break;
            default:
                System.out.println("Please enter a number between 1 and 4 or press R to return to the previous menu.");
                mainMenu();
                break;
        }
    }

    private static void addTeam() {
        String scheduleTeamName;
        Double gameNo = 1.0;
        int pos = 1;
        String teamsOutput;
        while (currentTeamList.size() > 0) {
            teamsOutput = "";
            System.out.println("The existing teams are as follows: ");
            for (Team team : currentTeamList) {
                teamsOutput += (team.getName() + " ");
            }
            teamsOutput = teamsOutput.substring(0, (teamsOutput.length() - 1));
            System.out.println(teamsOutput);
            System.out.println("Please enter the team's name that you want to schedule: ");
            scheduleTeamName = In.nextLine();
            Team scheduleTeam = Teams.getTeam(scheduleTeamName);
            if (scheduleTeam == null) {
                // Team doesn't exist
                System.out.println("No such team! Please try again");
            } else {
                //schedule.get((int) Math.round(gameNo/2)).addTeam(scheduleTeam);
                schedule.get(Math.toIntExact(Math.round(gameNo / 2))).addTeam(scheduleTeam);

                // Remove team from list
                currentTeamList.removeIf(team -> (team.equals(scheduleTeam)));

                // Output
                System.out.println("Team " + scheduleTeamName + " has been added at the Game " + Math.round(Math.ceil(gameNo/2)) + " position " + pos);
                if (pos == 1) {
                    pos++;
                } else if (pos == 2) {
                    pos = 1;
                }
                gameNo++;
            }
        }
        mainMenu();
    }

    private static void displayRound() {
        String team1;
        String team2;
        ArrayList<Team> teams;
        Game game;
        // Display header
        Utils.GameHeader();
        for (int i = 1; i < schedule.size(); i++) {
            game = schedule.get(i);
            teams = game.getTeams();
            team1 = teams.get(0).getName();
            team2 = teams.get(1).getName();
            System.out.format(Utils.GamesFormat, team1, " vs ", team2);
        }

        // Bottom of display
        Utils.GameEnd();
        mainMenu();
    }

}
