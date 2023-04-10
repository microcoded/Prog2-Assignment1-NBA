public class Association {
    private final Teams teams;
    private final Season season;

    // Takes no parameters
    public Association() {
        teams = new Teams();
        season = new Season();
    }

    public static void main(String[] args) {
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
                Season.mainMenu();
                break;
            case "X":
                System.out.println("Done");
                break;
            default:
                System.out.println("Please enter a number 1 or 2, or press X to exit.");
                use();
                break;
        }
    }
}
