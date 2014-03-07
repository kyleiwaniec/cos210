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
    private ArrayDeque<String> postfixDeque;
    private Stack<Double> operandStack;
    private static final String OPERATORS = "()^!/*+";
    /** The precedence of the operators, matches order in OPERATORS. */
    private static final int[] PRECEDENCE = {4,4,3,3,2,2,1};

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

    public ArrayDeque<String> lex(String input) {
        // The tokens to return
        ArrayDeque<String> tokens = new ArrayDeque<String>();

        // Lexer logic begins here
        String  patterns = "(?<NUMBER>[-]?[0-9.]+)|(?<OPERATOR>[()*/+^])|(?<WHITESPACE>[\t\f\r\n]+)";

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
        System.out.println(tokens.toString());
        return tokens;
    }

    public ArrayDeque shunting(ArrayDeque<String> infix) throws IOException{
        operatorStack = new Stack<Character>();
        postfixDeque = new ArrayDeque<String>();

        while (!infix.isEmpty()){
            String token = infix.poll();
            if (isOperator(token.charAt(0))) { // should also check that the length is 1.
                processOperator(token.charAt(0));
            }else{
                postfixDeque.add(token);
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

    public double evaluate(ArrayDeque<String> postfix){
         double result = 0.0;
         double first = 0.0;
         double second = 0.0;

         operandStack = new Stack<Double>();
         while(!postfix.isEmpty()) {
            String c = postfix.poll(); //Retrieves and removes the head of the queue 
            if(!isOperator(c.charAt(0))){
                operandStack.push(Double.parseDouble(c));
                System.out.println("the double "+Double.parseDouble(c));
            }else if(c.charAt(0) == '+'){
                first = operandStack.pop();
                second = operandStack.pop();
                result = first + second;
                operandStack.push(result);
            }else if(c.charAt(0) == '-'){
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
            }
        }
        return operandStack.pop();
    }
    private double factorial(double f){
        // TODO: implement.
        return f;
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