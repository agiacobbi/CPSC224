public class GameStats {
    private int wins;
    private int losses;
    private int scratch;
    private char playerSymbol;

    public GameStats(char playerSymbol) {
        this.playerSymbol = playerSymbol;
        this.wins = 0;
        this.losses = 0;
        this.scratch = 0;
    }

    public void addWin() {
        wins++;
    }

    public void addLoss() {
        losses++;
    }

    public void addScratch() {
        scratch++;
    }

    @Override
    public String toString() {
        double w = wins, t = (losses + wins + scratch);
        double percentage = w / t;

        return "Player " + playerSymbol + " game stats\n" +
                "-------------------\n" +
                "Win to loss ratio: " + wins + ":" + losses + '\n' +
                "Win percentage: " + percentage + '\n' +
                "Number of scratch games: " + scratch + '\n';
    }
}
