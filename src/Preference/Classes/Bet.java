package Preference.Classes;

import Preference.Enums.CountTricks;
import Preference.Enums.TrickTrump;

public class Bet {
    private CountTricks countTricks;
    private TrickTrump trickTrump;
    private Player player;

    public Bet(CountTricks countTricks, TrickTrump trickTrump, Player player) {
        this.countTricks = countTricks;
        this.trickTrump = trickTrump;
        this.player = player;
    }

    public CountTricks getCountTricks() {
        return countTricks;
    }

    public void setCountTricks(CountTricks countTricks) {
        this.countTricks = countTricks;
    }

    public TrickTrump getTrickTrump() {
        return trickTrump;
    }

    public void setTrickTrump(TrickTrump trickTrump) {
        this.trickTrump = trickTrump;
    }

    public int getTrumpRank() {
        return trickTrump.getRank() + countTricks.getRank();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
