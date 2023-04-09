public class Record {
    private String WinTeam;
    private String LoseTeam;
    private Integer GameNo;
    private Integer Round;

    public Record(String WinTeam, String LoseTeam, Integer GameNo, Integer Round) {
        this.WinTeam = WinTeam;
        this.LoseTeam = LoseTeam;
        this.GameNo = GameNo;
        this.Round = Round;
    }

    public String getWinTeam() {
        return this.WinTeam;
    }

    public String getLoseTeam() {
        return this.LoseTeam;
    }

    public Integer getGameNo() {
        return this.GameNo;
    }

    public Integer getRound() {
        return this.Round;
    }
}
