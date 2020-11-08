package Preference.Enums;

public enum TrickTrump {
    DEFAULT("Default", 0),
    SPADE("♠", 1), CLUB("♣", 2),
    DIAMOND("♦", 3), HEART("♥", 4),
    NO_TRUMP("No trump", 5);

    private String name;
    private int rank;

    TrickTrump(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public static TrickTrump getTrickTrump(int rank) {
        for (TrickTrump n: TrickTrump.values()) {
            if (rank == n.rank) {
                return n;
            }
        }
        return DEFAULT;
    }

    public static TrickTrump get(int n) {
        for (TrickTrump trickTrump: TrickTrump.values()) {
            if (trickTrump.getRank() == n) {
                return trickTrump;
            }
        }
        return DEFAULT;
    }
}
