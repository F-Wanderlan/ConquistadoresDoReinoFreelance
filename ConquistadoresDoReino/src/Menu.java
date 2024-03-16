import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class Menu {

    private static JFrame frame;
    private static Map<String, Reino> reinosGeral;

    public static void main(String[] args) {
        inicializarReinos();
        SwingUtilities.invokeLater(Menu::criarMenuInicio);
    }

    public static void criarMenuInicio() {
        frame = new JFrame("Menu de Início de Jogo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);

        JLabel labelNome = new JLabel("Digite seu nome:");
        panel.add(labelNome);

        JTextField textFieldNome = new JTextField(20);
        panel.add(textFieldNome);

        // Lista dos 5 reinos iniciais
        String[] reinosIniciais = {"Prontera", "Morroc", "Gefen", "Pyon", "Izold"};

        // Criação de botões para os reinos iniciais
        for (String reinoInicial : reinosIniciais) {
            JButton button = new JButton(reinoInicial);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nomeJogador = textFieldNome.getText();
                    if (!nomeJogador.isEmpty()) {
                        Lider lider = new Lider(nomeJogador);
                        Reino reino = reinosGeral.get(reinoInicial);
                        reino.setLider(lider);
                        abrirMenuAcoes(reino);
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Por favor, digite seu nome.");
                    }
                }
            });
            panel.add(button);
        }

        frame.pack();
        frame.setVisible(true);
    }

    public static void abrirMenuAcoes(Reino reino) {
        frame = new JFrame("Menu de Ações");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 1));
        frame.add(panel);

        JButton btnInfoReinos = new JButton("Ver informações dos reinos");
        btnInfoReinos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para ver informações dos reinos
                JOptionPane.showMessageDialog(frame, "Implemente a lógica para ver informações dos reinos aqui.");
            }
        });
        panel.add(btnInfoReinos);

        JButton btnGastarInfluencia = new JButton("Gastar Influência");
        btnGastarInfluencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para gastar influência
                JOptionPane.showMessageDialog(frame, "Implemente a lógica para gastar influência aqui.");
            }
        });
        panel.add(btnGastarInfluencia);

        JButton btnUnificarReino = new JButton("Unificar Reino");
        btnUnificarReino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para unificar reino
                JOptionPane.showMessageDialog(frame, "Implemente a lógica para unificar reino aqui.");
            }
        });
        panel.add(btnUnificarReino);

        JButton btnAtacarReino = new JButton("Atacar Reino");
        btnAtacarReino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para atacar reino
                JOptionPane.showMessageDialog(frame, "Implemente a lógica para atacar reino aqui.");
            }
        });
        panel.add(btnAtacarReino);

        frame.pack();
        frame.setVisible(true);
    }

    private static void inicializarReinos() {
        Reino reino1 = new Reino("Prontera", 100, 0, 0, 0, 0);
        Reino reino2 = new Reino("Morroc", 100, 0, 0, 0, 0);
        Reino reino3 = new Reino("Gefen", 100, 0, 0, 0, 0);
        Reino reino4 = new Reino("Pyon", 100, 0, 0, 0, 0);
        Reino reino5 = new Reino("Izold", 100, 0, 0, 0, 0);
        reinosGeral = new LinkedHashMap<>();
        reinosGeral.put(reino1.getNome(), reino1);
        reinosGeral.put(reino2.getNome(), reino2);
        reinosGeral.put(reino3.getNome(), reino3);
        reinosGeral.put(reino4.getNome(), reino4);
        reinosGeral.put(reino5.getNome(), reino5);
    }
}
