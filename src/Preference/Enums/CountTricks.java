package Preference.Enums;

public enum CountTricks {
    DEFAULT(0, 0, "default"),
    PASS(0, 0, "pass"),
    SIX(6, 1, "6"),
    SEVEN(7, 6, "7"),
    EIGHT(8, 11, "8"),
    MISER(0, 16, "miser"),
    NINE(9, 21, "9"),
    TEN(10, 26, "10");

    private int count;
    private int rank;
    private String name;

    CountTricks(int count, int rank, String name) {
        this.count = count;
        this.rank = rank;
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public int getCount() {
        return count;
    }

    public static CountTricks get(int n) {
        for (CountTricks countTricks: CountTricks.values()) {
            if (countTricks.getRank() == n) {
                return countTricks;
            }
        }
        return CountTricks.DEFAULT;
    }

    public String getName() {
        return name;
    }
}
