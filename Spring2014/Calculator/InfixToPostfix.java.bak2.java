import java.io.*;
import java.util.Stack;
import java.util.ArrayDeque;

public class InfixToPostfix{
    // Data Fields
    /** The operator stack */
    private Stack<Character> operatorStack;
    private ArrayDeque<String> postfixDeque;
    private Stack<Double> operandStack;
    /** The operators */
    private static final String OPERATORS = "()^!/*+-";
    /** The precedence of the operators, matches order in OPERATORS. */
    private static final int[] PRECEDENCE = {4,4,3,3,2,2,1,1};
    

    // public InfixToPostfix(){
    
    // }
    
    public static void main(String[] sa) throws IOException {
        new InfixToPostfix().doIt();
    }
    private void doIt() throws IOException {

        BufferedReader brinput = new BufferedReader(new InputStreamReader(System.in));
        String input = brinput.readLine();

        ArrayDeque ad = convert(input);
        System.out.println("Postfix: "+ad);
        double result = evaluate(ad);
        System.out.println("Result: "+result);
    }

    public ArrayDeque convert(String infix) throws IOException{
        operatorStack = new Stack<Character>();
        postfixDeque = new ArrayDeque<String>();
        StringBuilder tempDoubleAsString = new StringBuilder();
        boolean operandFlag = true;
        BufferedReader br = stringToBR(infix);
        int c;
        while ((c = br.read()) != -1){
            char firstChar = (char)c;
            if (isOperator(firstChar)) {
                if(operandFlag == true && tempDoubleAsString.length() > 0){
                    postfixDeque.add(tempDoubleAsString.toString());
                    tempDoubleAsString = new StringBuilder();
                }
                operandFlag = false;
                processOperator(firstChar);
            }else{
                if(operandFlag == false && tempDoubleAsString.length() > 0){
                    postfixDeque.add(tempDoubleAsString.toString());
                    tempDoubleAsString = new StringBuilder();
                }
                operandFlag = true;
                tempDoubleAsString.append(String.valueOf(firstChar));
            }
        }
        if(tempDoubleAsString.length() > 0){
            postfixDeque.add(tempDoubleAsString.toString());
            tempDoubleAsString = new StringBuilder();
        }
        // Pop any remaining operators and
        // append them to postfix.
        while (!operatorStack.empty()) {
            char op = operatorStack.pop();
            postfixDeque.add(String.valueOf(op));
        }
        return postfixDeque;
    }

    // private helper methods:
    private static BufferedReader stringToBR(String str){
        InputStream is = new ByteArrayInputStream(str.getBytes());
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        return br;
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
    private boolean isOperator(char ch) {
        return OPERATORS.indexOf(ch) != -1;
    }
    private int precedence(char op) {
        return PRECEDENCE[OPERATORS.indexOf(op)];
    }
}