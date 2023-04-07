public class Association {
    private Teams teams;
    private Season season;

    // Takes no parameters
    public Association() {
        teams = new Teams();
        season = new Season();
    }

    public static void main(String args[]) {
        new Association().use();
    }

    public void use() {
        System.out.println("Welcome to the Association! Please make a selection from the menu:\n" +
                "1. Explore the teams.\n" +
                "2. Arrange a new season.\n" +
                "X. Exit the system.");
        System.out.print("Enter a choice: ");
        String input = In.nextLine();

        switch(input) {
            case "1":
                Teams.mainMenu();
                break;
            case "2":
                // Season
                break;
            case "X":
                System.out.println("Done");
                break;
        }
    }
}
