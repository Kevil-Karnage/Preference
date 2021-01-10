package main.java.Preference.models.enums;

public enum RoundTypes {
    DEFAULT("Обычный");

    private String name;

    RoundTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
