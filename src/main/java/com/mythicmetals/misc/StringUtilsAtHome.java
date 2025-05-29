package com.mythicmetals.misc;

import com.mythicmetals.MythicMetals;
import java.util.Calendar;

public class StringUtilsAtHome {

    /**
     * Source: <a href="https://www.baeldung.com/java-string-title-case">Baeldung Link</a>
     *
     * @return Example:
     * cAt -> Cat
     * super title -> Super Title
     */
    public static String toTitleCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder converted = new StringBuilder();

        boolean convertNext = true;
        for (char ch : input.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }

        return converted.toString();
    }

    public static boolean isFunnyDay() {
        if (MythicMetals.CONFIG.disableFunny()) return false;
        var calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) == Calendar.APRIL && calendar.get(Calendar.DAY_OF_MONTH) == 1;
    }
}
