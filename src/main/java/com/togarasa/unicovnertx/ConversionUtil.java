package main.java.com.togarasa.unicovnertx;

public class ConversionUtil {

    public static String decimalToUnicode(String decimalInput) {
        return convertToUnicode(decimalInput, 10);
    }

    public static String hexToUnicode(String hexInput) {
        return convertToUnicode(hexInput, 16);
    }

    public static String octalToUnicode(String octalInput) {
        return convertToUnicode(octalInput, 8);
    }

    public static String htmlToUnicode(String htmlText) {
    	StringBuilder unicodeText = new StringBuilder();
        
        int index = 0;
        while (index < htmlText.length()) {
            int entityStart = htmlText.indexOf("&#", index);
            
            if (entityStart != -1) {
                unicodeText.append(htmlText, index, entityStart);
                int entityEnd = htmlText.indexOf(';', entityStart);
                if (entityEnd != -1) {
                    String entity = htmlText.substring(entityStart + 2, entityEnd);
                    try {
                        int decimalValue = Integer.parseInt(entity);
                        unicodeText.append((char) decimalValue);
                    } catch (NumberFormatException e) {
                        // Handle invalid entity (not a number)
                        unicodeText.append(htmlText, entityStart, entityEnd + 1);
                    }
                    index = entityEnd + 1;
                } else {
                    // Handle incomplete entity (no closing semicolon)
                    unicodeText.append(htmlText, entityStart, entityStart + 2);
                    index = entityStart + 2;
                }
            } else {
                unicodeText.append(htmlText, index, htmlText.length());
                break;
            }
        }

        return unicodeText.toString();
    }

    private static String convertToUnicode(String input, int radix) {
        StringBuilder unicodeOutput = new StringBuilder();
        String[] array = input.split("\\s+");
        for (String str : array) {
            try {
                int intValue = Integer.parseInt(str, radix);
                unicodeOutput.append((char) intValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return unicodeOutput.toString();
    }
}

