package org.dreambot.articron.price;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by: Niklas
 * Date: 16.09.2017
 * Alias: Dinh
 * Time: 13:08
 */


public class PriceCheck {

    public static String price(int itemID) {
        int price = getPrice(itemID);
        if (price == 0) return "N/A";
        else return getTransformed(round(price));
    }

    private static String getTransformed(String string) {
        if (string.matches("^[1-9][0]{6,8}\\b")) {
            return string.replaceAll("[0]{6}\\b", "M");
        } else if (string.matches("^[1-9]{2}[0]{5}\\b")) {
            return insertInto(string.replaceAll("[0]{5}\\b", "M"));
        } else if (string.matches("^[1-9][0-9]{3,11}\\b")) {
            return string.replaceAll("[0-9]{3}\\b", "K");
        } else {
            return string;
        }
    }

    private static String insertInto(String string) {
        return string.charAt(0) + "." + string.substring(1);
    }

    private static String round(int value) {
        return String.valueOf(((value + 99999) / 100000) * 100000);
    }

    private static int getPrice(int itemID) {
        int price;
        if ((price = getBuddyPrice(itemID)) == -1) {
            if ((price = getGraphPrice(itemID)) == -1) {
                price = getRunescapePrice(itemID);
                return price < 0 ? 0 : price;
            } else return price;
        } else {
            return price;
        }
    }

    private static int getBuddyPrice(int itemID) {
        try {
            String response = readURL(PriceType.RS_BUDDY.getLocation(), itemID);
            return Integer.parseInt(response.substring(response.indexOf(":") + 1, response.indexOf(",")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static int getRunescapePrice(int itemID) {
        try {
            String response = readURL(PriceType.RUNESCAPE.getLocation(), itemID);
            int indexOf = response.indexOf("price");
            return formatPrice(response.substring(response.indexOf(":", indexOf) + 1, response.indexOf("}", indexOf)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static int getGraphPrice(int itemID) {
        try {
            String response = readURL(PriceType.GRAPHS.getLocation(), String.valueOf(itemID) + ".json");
            int lastIndexOf = response.lastIndexOf(":") + 1;
            return Integer.parseInt(response.substring(lastIndexOf, response.indexOf("}", lastIndexOf)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static Integer formatPrice(String price) {
        return Integer.parseInt(price.replaceAll("k", "000").replaceAll("m", "000000").replaceAll("b", "00000000").replaceAll("[^\\d+]", ""));
    }

    private static String readURL(String base, Object o) throws IOException {
        InputStream streamIn = new URL(base + o).openStream();
        try (BufferedReader readIn = new BufferedReader(new InputStreamReader(streamIn, Charset.forName("UTF-8")))) {
            return getString(readIn);
        }
    }

    private static String getString(Reader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int currentChar;
        while ((currentChar = reader.read()) != -1) {
            stringBuilder.append((char) currentChar);
        }
        return stringBuilder.toString();
    }

}