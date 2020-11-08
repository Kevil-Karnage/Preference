package Preference.Enums;

import javax.swing.*;

public enum RoundTypes {
    MISER("Мизер"), DEFAULT("Обычный"), ALL_PASS("Распасы");

    private String name;

    RoundTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
