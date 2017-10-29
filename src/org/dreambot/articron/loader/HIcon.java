package org.dreambot.articron.loader;

/**
 * Created by: Niklas
 * Date: 18.09.2017
 * Alias: Dinh
 * Time: 23:08
 */

public enum HIcon {
    INFORMATION("OptionPane.informationIcon"),
    QUESTION("OptionPane.questionIcon"),
    WARNING("OptionPane.warningIcon"),
    ERROR("OptionPane.errorIcon");

    private String name;

    HIcon(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String getIconName(int severity) {
        return HIcon.values()[Math.abs(severity) % HIcon.values().length].getName();
    }
}
