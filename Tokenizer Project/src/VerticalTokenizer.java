import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VerticalTokenizer {
    String term = "", type;
    ArrayList<String> token = new ArrayList<String>();
    List<Character> punctuation = Arrays.asList('.', ',', '!', '?', ';', ':', '-', '(', ')', '[', ']', '{', '}', '\'', '\"');
    char delim = '|';
    String userInput;
    String output = "";

    public VerticalTokenizer(String userInput) {
        this.userInput = userInput;
    }

    public String GetTokens() {
        for (int i = 0; i < userInput.length(); i++) {
            char character = userInput.charAt(i);
            boolean withinBounds = i + 1 < userInput.length();
            char nextCharacter = (withinBounds) ? userInput.charAt(i + 1) : '\0';

            if (Character.isLetter(character)) {
                if (withinBounds && punctuation.contains(nextCharacter)) {
                    term += character;
                    token.add(term);
                    term = "";
                } else {
                    term += character;
                }
            } else if (Character.isDigit(character)) {
                    term += character;

            } else if (character == '.' && Character.isDigit(nextCharacter)) {
                term += character + "" + nextCharacter;
                i++;

            } else if (character == delim) {
                if (!term.isEmpty()) {
                    token.add(term);
                    term = "";
                }
            } else {
                if (!term.isEmpty()){
                    token.add(term);
                    term = "";
                }
                token.add(Character.toString(character));
            }
        }

        if (!term.isEmpty()) {
            token.add(term);
        }

        output += "Phase 1 Output: \n";
        for (String i : token) {
            if (isOnlyNumbers(i)) {
                type = "Number";
            } else if (isAWord(i)) {
                type = "Word";
            } else if (isAlphanumeric(i)) {
                type = "Alphanumeric";
            } else if (i.equals(" ")) {
                type = "White Space";
            } else if (punctuation.contains(i.charAt(0)) || i.equals("\'") || i.equals("\"")) {
                type = "Punctuation";
            } else if (i.equals("\\n")) {
                output += "Token: " + "\\n" + "\t - " + "Type : \t" + "New Line";
                continue;
            } else {
                type = "Special Character";
            }
            output += "Token: " + i + "\t - " + "Type : \t" + type + "\n";
        }

        String lastWord = token.get(token.size() - 1);
        char lastCharacter = lastWord.charAt(lastWord.length() - 1);
        boolean endOfLine = lastCharacter == '.';

        if (endOfLine) {
            output += "Token: " + "\\n" + "\t - " + "Type : \t" + "End of Line\n";
        }

        output += "\nPhase 2 Output (Granular Breakdown):\n";
        for (String word : token) {
            // Print the token
            output += "Token: \"" + word + "\" -> ";

            // Convert the word to a character array
            char[] characters = word.toCharArray();

            // Loop through each character and print it
            for (int i = 0; i < characters.length; i++) {
                output += "'" + characters[i] + "'";
                if (i < characters.length - 1) {
                    output += ", ";
                }
            }

            // Print a newline for the next token
            output += "\n";

        }
        if (endOfLine) {
            output += "Token: \"\\n\" -> '\\', 'n'";
        }
        return output;
    }
    public static boolean isAWord(String str) {
        return str != null && str.matches("[a-zA-Z]+[.,!?]?");
    }

    public static boolean isOnlyNumbers(String str) {
        return str != null && str.matches("\\d*\\.?\\d+");
    }

    public static boolean isAlphanumeric(String str) {
        return str != null && str.matches("[a-zA-Z0-9]+");
    }
}
