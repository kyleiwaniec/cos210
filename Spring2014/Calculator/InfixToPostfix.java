import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Stack;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class InfixToPostfix{
    private Stack<Character> operatorStack;
    private Stack<Double> operandStack;
    private ArrayDeque<String> postfixDeque;
    private static final String OPERATORS = "()s^!/*+-";
    /** The precedence of the operators, matches order in OPERATORS. */
    private static final int[] PRECEDENCE = {4,4,4,3,3,2,2,1,1};

    // public InfixToPostfix(){
    
    // }
    
    public static void main(String[] sa) throws IOException {
        new InfixToPostfix().doIt();
    }
    private void doIt() throws IOException {
        BufferedReader brinput = new BufferedReader(new InputStreamReader(System.in));
        String input = brinput.readLine();

        ArrayDeque ad = shunting(lex(input));
        System.out.println("Postfix: "+ad);
        double result = evaluate(ad);
        System.out.println("Result: "+result);
    }



    public static class Token {
        public String type;
        public String data;
        public Token(String type, String data) {
            this.type = type;
            this.data = data;
        }
        @Override
        public String toString() {
            return String.format("[%s : %s]", this.type, this.data);
            //return this.data;
        }
    }
    // TODO: redo the whole lexer logic using "Scanner" phase to extract lexemes, then "Evaluator" phase to construct tokens.
    // http://en.m.wikipedia.org/wiki/Lexical_analyzer

    public ArrayDeque<Token> lex(String input) {
        // The tokens to return
        ArrayDeque<Token> tokens = new ArrayDeque<Token>();

        // Lexer logic begins here
        String  patterns = "(?<STARTNEGATE>^[-])|(?<OPERATOR>(?<![-*/+^])[-*/+^])|(?<LEFTPARENS>[(])|(?<RIGHTTPARENS>[)])|(?<SIN>sin\\(\\s*[-]?[0-9.\\s]+\\))|(?<NUMBER>[-]?[0-9.]+)|(?<WHITESPACE>[\t\f\r\n]+)";

        Pattern tokenPatterns = Pattern.compile(patterns);
        // Begin matching tokens
        Matcher matcher = tokenPatterns.matcher(input);
        while (matcher.find()) {
            if(matcher.group("NUMBER") != null){
                tokens.add(new Token("NUMBER", matcher.group("NUMBER")));
                continue;
            } else if (matcher.group("OPERATOR") != null) {
                tokens.add(new Token("OPERATOR", matcher.group("OPERATOR")));
                continue;
            } else if (matcher.group("LEFTPARENS") != null) {
                tokens.add(new Token("OPERATOR", matcher.group("LEFTPARENS")));
                continue;
            } else if (matcher.group("RIGHTTPARENS") != null) {
                tokens.add(new Token("OPERATOR", matcher.group("RIGHTTPARENS")));
                continue;
            } else if (matcher.group("SIN") != null) {
                tokens.add(new Token("SIN", matcher.group("SIN")));
                continue;
            } else if (matcher.group("STARTNEGATE") != null) {
                tokens.add(new Token("NUMBER", "-1"));
                tokens.add(new Token("OPERATOR", "*"));
                continue;
            } else if (matcher.group("WHITESPACE") != null)
                continue;
        }
        System.out.println(tokens.toString());
        return tokens;
    }

    public ArrayDeque shunting(ArrayDeque<Token> infix) throws IOException{
        operatorStack = new Stack<Character>();
        postfixDeque = new ArrayDeque<String>();

        while (!infix.isEmpty()){
            Token token = infix.poll();
            if (token.type == "OPERATOR") { 
                processOperator((token.data).charAt(0));
            }else if(token.type == "SIN"){
                // TODO: This is kind of a weird place to put this... should be delegated to a more generic method.
                String d = "";
                Pattern pattern = Pattern.compile("([-]?[0-9.]+)");
                Matcher matcher = pattern.matcher(token.data);
                while (matcher.find()) {
                    d = matcher.group(1);
                }
                processOperator('s'); // 's' will be sin function.
                postfixDeque.add(d);
            }else{
                postfixDeque.add(token.data);
            }
        }
        // Pop any remaining operators and
        // append them to postfix.
        while (!operatorStack.empty()) {
            char op = operatorStack.pop();
            postfixDeque.add(String.valueOf(op));
        }
        return postfixDeque;
    }

    private void processOperator(char op){
        if (operatorStack.empty()) {
            operatorStack.push(op);
        } else {
            char topOp = operatorStack.peek();
            if(op == '('){
                operatorStack.push(op);
            }else if ((topOp == '(' || precedence(op) > precedence(topOp)) && op != ')') {
                operatorStack.push(op);
            }else if(op == ')'){
                while(topOp != '('){
                    operatorStack.pop();
                    postfixDeque.add(String.valueOf(topOp));
                    if (!operatorStack.empty()) {
                        topOp = operatorStack.peek();
                    }
                }
                operatorStack.pop(); // removes the right parenthesis
            } else {
                // Pop all stacked operators with equal
                // or higher precedence than op.
                while (!operatorStack.empty() && precedence(op) <= precedence(topOp) && topOp != '(') {
                    operatorStack.pop();
                    postfixDeque.add(String.valueOf(topOp));
                    if (!operatorStack.empty()) {
                        // Reset topOp.
                        topOp = operatorStack.peek();
                    }
                }
                operatorStack.push(op);
            }
        }
    }

    public double evaluate(ArrayDeque<String> postfix){
         double result = 0.0;
         double first = 0.0;
         double second = 0.0;

         operandStack = new Stack<Double>();
         while(!postfix.isEmpty()) {
            String c = postfix.poll(); //Retrieves and removes the head of the queue 
            if(!isOperator(c.charAt(0)) || c.length() > 1){ // crappy hackyness - todo: fix.
                operandStack.push(Double.parseDouble(c));
            }else if(c.charAt(0) == '+'){
                first = operandStack.pop();
                second = operandStack.pop();
                result = first + second;
                operandStack.push(result);
            }else if(c.charAt(0) == '-' && c.length() == 1){ // crappy hackyness - todo: fix.
                first = operandStack.pop();
                second = operandStack.pop();
                result = second - first;
                operandStack.push(result);
            }else if(c.charAt(0) == '/'){
                first = operandStack.pop();
                second = operandStack.pop();
                result = second / first;
                operandStack.push(result);
            }else if(c.charAt(0) == '*'){
                first = operandStack.pop();
                second = operandStack.pop();
                result = first * second;
                operandStack.push(result);
            }else if(c.charAt(0) == '^'){
                first = operandStack.pop();
                second = operandStack.pop();
                result = Math.pow(second, first);
                operandStack.push(result);
            }else if(c.charAt(0) == '!'){
                first = operandStack.pop();
                result = factorial(first);
                operandStack.push(result);
            }else if(c.charAt(0) == 's'){
                first = operandStack.pop();
                result = Math.sin(first);
                operandStack.push(result);
            }
        }
        return operandStack.pop();
    }
    private double factorial(double f){
        // TODO: implement. hmm... what to do about floating point numbers?
        return f;
    }
    
    // private helper methods:
    private static BufferedReader stringToBR(String str){
        InputStream is = new ByteArrayInputStream(str.getBytes());
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        return br;
    }
    private boolean isOperator(char ch) {
        return OPERATORS.indexOf(ch) != -1;
    }
    private int precedence(char op) {
        return PRECEDENCE[OPERATORS.indexOf(op)];
    }
}