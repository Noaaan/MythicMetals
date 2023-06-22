package nourl.mythicmetals.misc;

public class StringUtilsAtHome {

    /**
     * Source: <a href="https://www.baeldung.com/java-string-title-case">Baeldung Link</a>
     * @return Example:
     * cAt -> CAt
     * super title -> Super Title
     */
    public static String toProperCase(String input) {
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
}
