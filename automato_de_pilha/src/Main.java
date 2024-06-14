import javax.swing.*;

public class Main extends AutomatoDePilha{

    public static void main(String[] args) {
        while (true) {
            String expression = JOptionPane.showInputDialog(null, "Digite uma expressão matemática:", "Validador de Expressão", JOptionPane.QUESTION_MESSAGE);
            if (expression == null) {
                JOptionPane.showMessageDialog(null, "Nenhuma expressão fornecida.", "Erro", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }

            if (isValidExpression(expression)) {
                JOptionPane.showMessageDialog(null, "A expressão é válida.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "A expressão é inválida.", "Resultado", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

