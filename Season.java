import java.util.ArrayList;

public class Season {
    private static ArrayList<Game> schedule = new ArrayList<>();
    private static ArrayList<Team> currentTeamList;
    private static final ArrayList<Record> records = new ArrayList<>();

    // Takes no parameters
    public Season() {
        currentTeamList = new ArrayList<>(Teams.getTeamsList());
        for (int i = 0; i < (currentTeamList.size() / 2) + 1; i++) {
            addGame(i + 1);
        }
    }

    private static void addGame(int term) {
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
                playGames();
                break;
            case "4":
                // Display the game result records
                displayResults();
                break;
            case "R":
                Association.user();
                break;
            default:
                System.out.println("Please enter a number between 1 and 4 or press R to return to the previous menu.");
                mainMenu();
                break;
        }
    }

    private static void addTeam() {
        String scheduleTeamName;
        double gameNo = 1.0;
        int pos = 1;
        StringBuilder teamsOutput;
        while (currentTeamList.size() > 0) {
            teamsOutput = new StringBuilder();
            System.out.println("The existing teams are as follows: ");
            for (Team team : currentTeamList) {
                teamsOutput.append(team.getName()).append(" ");
            }
            teamsOutput = new StringBuilder(teamsOutput.substring(0, (teamsOutput.length() - 1)));
            System.out.println(teamsOutput);
            System.out.println("Please enter the team's name that you want to schedule: ");
            scheduleTeamName = In.nextLine();
            Team scheduleTeam = Teams.getTeam(scheduleTeamName);
            boolean found = false;
            for (Team team : currentTeamList) {
                if (scheduleTeam == team) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                // Team doesn't exist
                System.out.println("No such team! Please try again");
            } else {
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
                if (teams.size() > 0) {
                    team1 = teams.get(0).getName();
                    team2 = teams.get(1).getName();
                    System.out.format(Utils.GamesFormat, team1, " vs ", team2);
                }
            }

        // Bottom of display
        Utils.GameEnd();
        mainMenu();
    }

    public static void playGames() {
        Game game;
        ArrayList<Team> teams;
        Team winTeam;
        Team loseTeam;
        String champion = "";

        if (schedule.isEmpty()) mainMenu();

        if (schedule.get(1).hasTeams()) {
            int gameNo = 1;
            int roundNo;

            if (schedule.size() == 3) {
                roundNo = 1;
            } else /* if (schedule.size() == 2) */ {
                roundNo = 2;
            }

            // Decide winner
            for (int i = 1; i < schedule.size(); i++) {
                game = schedule.get(i);
                teams = game.getTeams();
                if (teams.get(0).averageCredit() < teams.get(1).averageCredit()) {
                    winTeam = teams.get(1);
                    loseTeam = teams.get(0);
                } else {
                    winTeam = teams.get(0);
                    loseTeam = teams.get(1);
                }
                records.add(new Record(winTeam.getName(), loseTeam.getName(), gameNo, roundNo));
                gameNo++;

                // Add winner to currentTeamList if round = 1
                if (roundNo == 1) {
                    currentTeamList.add(winTeam);
                }
                champion = winTeam.getName();

                // Update credit
                updateCredit(winTeam, loseTeam);

            }
            System.out.println("All games finished! You can use 4 to check the results.");
        } else {
            System.out.println("No game in the current round, please add teams to the round first!");
        }

        // Clear schedule
        schedule.clear();

        // Resize
        for (int i = 0; i < (currentTeamList.size() / 2) + 1; i++) {
            addGame(i + 1);
        }

        if (currentTeamList.size() == 0) {
            System.out.println("This season ends!");
            System.out.println(champion + " is the Champion!!");
        }

        mainMenu();
    }

    public static void updateCredit(Team winTeam, Team loseTeam) {
        double difference = winTeam.averageCredit() - loseTeam.averageCredit();

        ArrayList<Player> winTeamPlayers = winTeam.getPlayers().getPlayerList();
        ArrayList<Player> loseTeamPlayers = loseTeam.getPlayers().getPlayerList();

        for (Player winTeamPlayer : winTeamPlayers) {
            winTeamPlayer.setCredit(winTeamPlayer.getCredit() + (difference / 5));
        }

        for (Player loseTeamPlayer : loseTeamPlayers) {
            loseTeamPlayer.setCredit(loseTeamPlayer.getCredit() + (difference / 5));
        }

        winTeam.setPlayers(winTeamPlayers);
        loseTeam.setPlayers(loseTeamPlayers);
    }

    public static void displayResults() {
        // Header
        Utils.RecordHeader();

        // Each result
        for (Record record : records) {
            System.out.format(Utils.RecordFormat, record.getRound(), record.getGameNo(), record.getWinTeam(), record.getLoseTeam());
        }

        // Footer
        Utils.RecordEnd();

        mainMenu();
    }

}
