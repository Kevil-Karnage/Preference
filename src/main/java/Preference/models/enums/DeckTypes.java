package main.java.Preference.models.enums;

public enum DeckTypes {
    DEFAULT(32);

    private int countCards;

    DeckTypes(int countCards) {
        this.countCards = countCards;
    }
}
