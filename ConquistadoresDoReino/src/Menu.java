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
        SwingUtilities.invokeLater(Menu::criarTelaInicial);
    }

    public static void criarTelaInicial() {
        frame = new JFrame("Conquistadores de Reino - Tela Inicial");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        JLabel labelBemVindo = new JLabel("Bem-vindo ao jogo Conquistadores de Reino");
        labelBemVindo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(labelBemVindo);

        JButton btnComecarJogo = new JButton("Começar o jogo");
        btnComecarJogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnComecarJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                iniciarJogo();
            }
        });
        panel.add(btnComecarJogo);

        JButton btnVerPontuacao = new JButton("Ver melhor pontuação");
        btnVerPontuacao.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVerPontuacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para verificar a melhor pontuação
                JOptionPane.showMessageDialog(frame, "Implementação para verificar melhor pontuação aqui.");
            }
        });
        panel.add(btnVerPontuacao);

        JButton btnOQueEJogo = new JButton("O que é o jogo");
        btnOQueEJogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOQueEJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para explicar o jogo
                String mensagem = "O Conquistadores de Reino é um jogo de estratégia onde você assume o papel de um líder ";
                mensagem += "em busca de dominar os reinos vizinhos. Você pode gastar influência para fortalecer suas tropas, ";
                mensagem += "unificar reinos sob sua bandeira e até mesmo atacar reinos inimigos. ";
                mensagem += "O objetivo final é conquistar todos os reinos e se tornar o líder supremo!";
                JOptionPane.showMessageDialog(frame, mensagem);
            }
        });
        panel.add(btnOQueEJogo);

        frame.pack();
        centralizarJanela(frame);
        frame.setVisible(true);
    }

    public static void iniciarJogo() {
        inicializarReinos();
        SwingUtilities.invokeLater(Menu::criarMenuInicio);
    }

    public static void criarMenuInicio() {
        frame = new JFrame("Menu de Início de Jogo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);

        // Layout do painel
        panel.setLayout(new BorderLayout());

        // Painel para nome do jogador
        JPanel panelNome = new JPanel();
        panelNome.setLayout(new FlowLayout());
        JLabel labelNome = new JLabel("Digite seu nome:");
        JTextField textFieldNome = new JTextField(20);
        panelNome.add(labelNome);
        panelNome.add(textFieldNome);

        // Painel para escolha do reino
        JPanel panelEscolhaReino = new JPanel();
        panelEscolhaReino.setLayout(new FlowLayout());
        JLabel labelEscolhaReino = new JLabel("Escolha um reino:");
        panelEscolhaReino.add(labelEscolhaReino);

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
                        frame.dispose();
                        abrirMenuAcoes(reino);
                        // Mensagem para informar ao jogador que o reino pertence a ele
                        JOptionPane.showMessageDialog(null, "Parabéns, você agora é o líder de " + reino.getNome() + "!");
                        // Mensagem para explicar as ações disponíveis no jogo
                        String mensagem = "Você pode gastar influência para fortalecer suas tropas, ";
                        mensagem += "unificar reinos sob sua bandeira e até mesmo atacar reinos inimigos. ";
                        mensagem += "O objetivo final é conquistar todos os reinos e se tornar o líder supremo!";
                        JOptionPane.showMessageDialog(null, mensagem);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Por favor, digite seu nome.");
                    }
                }
            });
            panelEscolhaReino.add(button);
        }

        // Adicionando os painéis ao painel principal
        panel.add(panelNome, BorderLayout.NORTH);
        panel.add(panelEscolhaReino, BorderLayout.CENTER);

        frame.pack();
        centralizarJanela(frame);
        frame.setVisible(true);
    }



    public static void abrirMenuAcoes(Reino reino) {
        JFrame frame = new JFrame("Menu de Ações");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 1));
        frame.add(panel);

        JButton btnInfoReinos = new JButton("Ver informações dos reinos");
        btnInfoReinos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pergunta ao usuário qual reino ele deseja ver as informações
                String[] nomesReinos = reinosGeral.keySet().toArray(new String[0]);
                String selectedReino = (String) JOptionPane.showInputDialog(frame, "Selecione um reino:",
                        "Selecionar Reino", JOptionPane.QUESTION_MESSAGE, null, nomesReinos, nomesReinos[0]);

                if (selectedReino != null) {
                    Reino reinoSelecionado = reinosGeral.get(selectedReino);
                    abrirJanelaInformacoesReino(reinoSelecionado);
                }
            }
        });
        panel.add(btnInfoReinos);

        JButton btnGastarInfluencia = new JButton("Gastar Influência");
        btnGastarInfluencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame gastarFrame = new JFrame("Gastar Influência");
                gastarFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel gastarPanel = new JPanel(new GridLayout(3, 1));
                gastarFrame.add(gastarPanel);

                JButton btnComprarSoldados = new JButton("Comprar Soldados");
                btnComprarSoldados.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String influenciaDisponivel = "Influência disponível: " + reino.getInfluencia();
                        String custoSoldado = "Custo por soldado: 3 de influência";
                        String inputSoldados = JOptionPane.showInputDialog(frame,
                                influenciaDisponivel + "\n" + custoSoldado + "\nDigite a quantidade de soldados a comprar:");

                        if (inputSoldados != null && !inputSoldados.isEmpty()) {
                            try {
                                int quantidadeSoldados = Integer.parseInt(inputSoldados);
                                int influenciaGasta = quantidadeSoldados * 3;
                                if (influenciaGasta <= reino.getInfluencia() && influenciaGasta > 0) {
                                    reino.comprarSoldado(influenciaGasta, reino);
                                    JOptionPane.showMessageDialog(frame, "Soldados comprados com sucesso!");
                                    gastarFrame.dispose();
                                } else {
                                    JOptionPane.showMessageDialog(frame, "Quantidade de influência insuficiente para comprar essa quantidade de soldados.");
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(frame, "Por favor, insira um número válido para a quantidade de soldados.");
                            }
                        }
                    }
                });
                gastarPanel.add(btnComprarSoldados);

                JButton btnComprarServentes = new JButton("Comprar Serventes");
                btnComprarServentes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String influenciaDisponivel = "Influência disponível: " + reino.getInfluencia();
                        String custoServente = "Custo por servente: 3 de influência";
                        String inputServentes = JOptionPane.showInputDialog(frame,
                                influenciaDisponivel + "\n" + custoServente + "\nDigite a quantidade de serventes a comprar:");

                        if (inputServentes != null && !inputServentes.isEmpty()) {
                            try {
                                int quantidadeServentes = Integer.parseInt(inputServentes);
                                int influenciaGasta = quantidadeServentes * 3;
                                if (influenciaGasta <= reino.getInfluencia()  && influenciaGasta > 0)  {
                                    reino.comprarServente(influenciaGasta, reino);
                                    JOptionPane.showMessageDialog(frame, "Serventes comprados com sucesso!");
                                    gastarFrame.dispose();
                                } else {
                                    JOptionPane.showMessageDialog(frame, "Quantidade de influência insuficiente para comprar essa quantidade de serventes.");
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(frame, "Por favor, insira um número válido para a quantidade de serventes.");
                            }
                        }
                    }
                });
                gastarPanel.add(btnComprarServentes);

                JButton btnDoarInfluencia = new JButton("Doar Influência");
                btnDoarInfluencia.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Lógica para doar influência para outro reino
                        JOptionPane.showMessageDialog(frame, "Implemente a lógica para doar influência para outro reino.");
                        gastarFrame.dispose();
                    }
                });
                gastarPanel.add(btnDoarInfluencia);

                gastarFrame.pack();
                centralizarJanela(gastarFrame);
                gastarFrame.setVisible(true);
            }
        });
        panel.add(btnGastarInfluencia);

        JButton btnUnificarReino = new JButton("Unificar Reino");
        btnUnificarReino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementação de ações
                JOptionPane.showMessageDialog(frame, "Implementação das ações aqui.");
            }
        });
        panel.add(btnUnificarReino);

        JButton btnAtacarReino = new JButton("Atacar Reino");
        btnAtacarReino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementação de ações
                JOptionPane.showMessageDialog(frame, "Implementação das ações aqui.");
            }
        });
        panel.add(btnAtacarReino);

        frame.pack();
        centralizarJanela(frame);
        frame.setVisible(true);
    }

    private static void inicializarReinos() {
        // Criar os líderes
        Lider lider1 = new Lider("Wigner");
        Lider lider2 = new Lider("Freeoni");
        Lider lider3 = new Lider("Irineu");
        Lider lider4 = new Lider("James");
        Lider lider5 = new Lider("Tercio");

        // Criar os reinos e associar os líderes
        Reino reino1 = new Reino("Prontera", 100, 0, 0, 0, 0);
        reino1.setLider(lider1);

        Reino reino2 = new Reino("Morroc", 100, 0, 0, 0, 0);
        reino2.setLider(lider2);

        Reino reino3 = new Reino("Gefen", 100, 0, 0, 0, 0);
        reino3.setLider(lider3);

        Reino reino4 = new Reino("Pyon", 100, 0, 0, 0, 0);
        reino4.setLider(lider4);

        Reino reino5 = new Reino("Izold", 100, 0, 0, 0, 0);
        reino5.setLider(lider5);

        // Adicionar os reinos ao mapa
        reinosGeral = new LinkedHashMap<>();
        reinosGeral.put(reino1.getNome(), reino1);
        reinosGeral.put(reino2.getNome(), reino2);
        reinosGeral.put(reino3.getNome(), reino3);
        reinosGeral.put(reino4.getNome(), reino4);
        reinosGeral.put(reino5.getNome(), reino5);
    }


    public static void abrirJanelaInformacoesReino(Reino reino) {
        JFrame infoFrame = new JFrame("Informações do Reino: " + reino.getNome());
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        infoFrame.add(panel);

        JLabel titleLabel = new JLabel("Informações do Reino:  " + reino.getNome());
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridLayout(0, 2));

        // Adicionando os detalhes do reino em pares de rótulos e valores
        infoPanel.add(new JLabel("Nome:"));
        infoPanel.add(new JLabel(reino.getNome()));
        infoPanel.add(new JLabel("Tropas:"));
        infoPanel.add(new JLabel(String.valueOf(reino.getSoldadosDef() + reino.getSoldadosAtk() + reino.getSoldados())));
        infoPanel.add(new JLabel("Influência:"));
        infoPanel.add(new JLabel(String.valueOf(reino.getInfluencia())));
        infoPanel.add(new JLabel("Lider:"));
        infoPanel.add(new JLabel(String.valueOf(reino.getLider().getNome())));
        infoPanel.add(new JLabel("Serventes:"));
        infoPanel.add(new JLabel(String.valueOf(reino.getServentes())));

        panel.add(infoPanel, BorderLayout.CENTER);

        infoFrame.pack();
        centralizarJanela(infoFrame);
        infoFrame.setVisible(true);
    }


    public static void centralizarJanela(JFrame frame) {
        Dimension dimensaoTela = Toolkit.getDefaultToolkit().getScreenSize();
        int larguraJanela = frame.getSize().width;
        int alturaJanela = frame.getSize().height;
        int x = (dimensaoTela.width - larguraJanela) / 2;
        int y = (dimensaoTela.height - alturaJanela) / 2;
        frame.setLocation(x, y);
    }
}
