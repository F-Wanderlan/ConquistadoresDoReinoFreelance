import javax.swing.JOptionPane;
import java.util.Stack;

public class AutomatoDePilha {

    public static boolean isValidExpression(String expression) {
        Stack<Character> stack = new Stack<>();
        boolean lastWasOperator = true;

        for (char ch : expression.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
                lastWasOperator = true;
            } else if (ch == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
                lastWasOperator = false;
            } else if (isOperator(ch)) {
                if (lastWasOperator) {
                    return false;
                }
                lastWasOperator = true;
            } else if (Character.isDigit(ch)) {
                lastWasOperator = false;
            } else if (!Character.isWhitespace(ch)) {
                return false;
            }
        }

        return stack.isEmpty() && !lastWasOperator;
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }
}
