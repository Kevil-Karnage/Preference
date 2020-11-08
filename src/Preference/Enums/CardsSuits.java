package Preference.Enums;

public enum CardsSuits {
    SPADE("♠", 1), CLUB("♣", 2),
    DIAMOND("♦", 3), HEART("♥", 4);

    private String name;
    private int rank;

    CardsSuits(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }
}
