package org.dreambot.articron.loader;

/**
 * Created by: Niklas
 * Date: 18.09.2017
 * Alias: Dinh
 * Time: 23:07
 */

public enum HImage {
    ICON_BIG("big", "https://i.imgur.com/kDwSzfS.png"),
    ICON32_32("32", "https://i.imgur.com/CqB6GtU.png"),
    ICON16_16("16", "https://i.imgur.com/hmMnl9x.png"),
    DISPOSE("dispose", "https://i.imgur.com/FSWbKE4.png"),
    ICONIFY("iconify", "https://i.imgur.com/A1LiarH.png"),
    UP_ICON("up", "https://i.imgur.com/CNCxvu3.png"),
    DOWN_ICON("down", "https://i.imgur.com/0wFw49p.png"),
    LOGO("logo","https://media.discordapp.net/attachments/300574817827028992/371059059345850378/crondroid-light.png");

    private String URL, description;

    HImage(String description, String URL) {
        this.description = description;
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }

    public String getDescription() {
        return description;
    }
}
