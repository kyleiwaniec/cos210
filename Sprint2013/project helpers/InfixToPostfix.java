import java.util.Stack;
import java.util.EmptyStackException;
import java.util.regex.Pattern;
import java.util.Scanner;

/**
 * Translates an infix expression to a postfix expression.
 */
public class InfixToPostfix {

    // Nested Class
    /** Class to report a syntax error. */
    public static class SyntaxErrorException extends Exception {

        /**
         * Construct a SyntaxErrorException with the specified
         * message.
         * @param message The message
         */
        SyntaxErrorException(String message) {
            super(message);
        }
    }
    // Data Fields
    /** The operator stack */
    private Stack<Character> operatorStack;
    /** The operators */
    private static final String OPERATORS = "-+*/";
    /** The precedence of the operators, matches order in OPERATORS. */
    private static final int[] PRECEDENCE = {1, 1, 2, 2};
    /**
     * The Pattern to extract tokens
     * A token is either a string of digits (\d+)
     * or a JavaIdentifier
     * or an operator
     */
    private static final Pattern tokens =
            Pattern.compile("\\d+\\.\\d*|\\d+|"
            + "\\p{javaJavaIdentifierStart}\\p{javaJavaIdentifierPart}*"
            + "|[" + OPERATORS + "]");
    /** The postfix string */
    private StringBuilder postfix;

    /**
     * Convert a string from infix to postfix.
     * @param infix The infix expression
     * @throws SyntaxErrorException
     */
    public String convert(String infix) throws SyntaxErrorException {
        operatorStack = new Stack<Character>();
        postfix = new StringBuilder();
        Scanner s = new Scanner(infix);
        try {
            // Process each token in the infix string.
            String nextToken = null;
            while ((nextToken = s.findInLine(tokens)) != null) {
                char firstChar = nextToken.charAt(0);
                // Is it an operand?
                if (Character.isJavaIdentifierStart(firstChar)
                        || Character.isDigit(firstChar)) {
                    postfix.append(nextToken);
                    postfix.append(' ');
                } // Is it an operator?
                else if (isOperator(firstChar)) {
                    processOperator(firstChar);
                } else {
                    throw new SyntaxErrorException
                            ("Unexpected Character Encountered: " + firstChar);
                }
            } // End while.

            // Pop any remaining operators and
            // append them to postfix.
            while (!operatorStack.empty()) {
                char op = operatorStack.pop();
                postfix.append(op);
                postfix.append(' ');
            }
            // assert: Stack is empty, return result.
            return postfix.toString();
        } catch (EmptyStackException ex) {
            throw new SyntaxErrorException("Syntax Error: The stack is empty");
        }
    }

    /**
     * Method to process operators.
     * @param op The operator
     * @throws EmptyStackException
     */
    private void processOperator(char op) {
        if (operatorStack.empty()) {
            operatorStack.push(op);
        } else {
            // Peek the operator stack and
            // let topOp be top operator.
            char topOp = operatorStack.peek();
            if (precedence(op) > precedence(topOp)) {
                operatorStack.push(op);
            } else {
                // Pop all stacked operators with equal
                // or higher precedence than op.
                while (!operatorStack.empty()
                        && precedence(op) <= precedence(topOp)) {
                    operatorStack.pop();
                    postfix.append(topOp);
                    postfix.append(' ');
                    if (!operatorStack.empty()) {
                        // Reset topOp.
                        topOp = operatorStack.peek();
                    }
                }
                // assert: Operator stack is empty or
                //         current operator precedence >
                //         top of stack operator precedence.
                operatorStack.push(op);
            }
        }
    }

    /**
     * Determine whether a character is an operator.
     * @param ch The character to be tested
     * @return true if ch is an operator
     */
    private boolean isOperator(char ch) {
        return OPERATORS.indexOf(ch) != -1;
    }

    /**
     * Determine the precedence of an operator.
     * @param op The operator
     * @return the precedence
     */
    private int precedence(char op) {
        return PRECEDENCE[OPERATORS.indexOf(op)];
    }
}