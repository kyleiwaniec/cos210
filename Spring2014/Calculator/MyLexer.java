import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MyLexer {
   
    public static ArrayList<String> lex(String input) {
        // The tokens to return
        ArrayList<String> tokens = new ArrayList<String>();

        // Lexer logic begins here
        String  patterns = "(?<NUMBER>-?[0-9.]+)|(?<OPERATOR>[()*/+-^])|(?<WHITESPACE>[ \t\f\r\n]+)";

        Pattern tokenPatterns = Pattern.compile(patterns);
        // Begin matching tokens
        Matcher matcher = tokenPatterns.matcher(input);
        while (matcher.find()) {
            if(matcher.group("NUMBER") != null){
                tokens.add(matcher.group("NUMBER"));
                continue;
            } else if (matcher.group("OPERATOR") != null) {
                tokens.add(matcher.group("OPERATOR"));
                continue;
            } else if (matcher.group("WHITESPACE") != null)
                continue;
            
        }

        return tokens;
    }

    public static void main(String[] args) {
        String input = "11.2^2 + (22 - 33)";

        // Create tokens and print them
        ArrayList<String> tokens = lex(input);
        for (String token : tokens)
            System.out.println(token);
    }
}