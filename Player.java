public class Player {
    private String name;
    private Double credit;
    private String level;
    private Integer age;
    private String team;
    private Integer No;
    public Player(String name, Double credit, Integer age, String team, Integer No) {
        this.name = name;
        this.credit = credit;
        this.age = age;
        this.No = No;
        this.team = team;

        if (credit < 1000 ) {
            this.level = "Edge";
        } else if (credit >= 1000 && credit < 1500) {
            this.level = "Common";
        } else if (credit >= 1500 && credit < 2000) {
            this.level = "Core";
        } else if (credit >= 2000) {
            this.level = "All Star";

        }
    }

    public String getTeam() {
        return this.team;
    }

    public Double getCredit() {
        return this.credit;
    }

    public Integer getAge() {
        return this.age;
    }

}
