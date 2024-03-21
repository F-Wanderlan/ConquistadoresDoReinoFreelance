    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.MouseAdapter;
    import java.awt.event.MouseEvent;
    import java.util.*;
    import java.util.List;

    public class Menu {

        private static JFrame frame;
        private static Map<String, Reino> reinosGeral = new LinkedHashMap<>();

        public static Turno turno = new Turno(new ArrayList<>(reinosGeral.values()));


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
                            lider.setJogador(true);
                            Reino reino = reinosGeral.get(reinoInicial);
                            reinosGeral.get(reinoInicial).setLider(lider);
                            frame.dispose();
                            // Mensagem para informar ao jogador que o reino pertence a ele
                            JOptionPane.showMessageDialog(null, "Parabéns, você agora é o líder de " + reino.getNome() + "!");
                            // Mensagem para explicar as ações disponíveis no jogo
                            String mensagem = "Você pode gastar influência para fortalecer suas tropas, ";
                            mensagem += "unificar reinos sob sua bandeira e até mesmo atacar reinos inimigos. ";
                            mensagem += "O objetivo final é conquistar todos os reinos e se tornar o líder supremo!";
                            JOptionPane.showMessageDialog(null, mensagem);
                            Turno turno = new Turno(new ArrayList<>(reinosGeral.values()));
                            turno.iniciarTurnos();
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


                    gastarFrame.pack();
                    centralizarJanela(gastarFrame);
                    gastarFrame.setVisible(true);
                }
            });
            panel.add(btnGastarInfluencia);

            JButton btnUnificarReinos = new JButton("Unificar Reinos");
            btnUnificarReinos.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    unificarReinos(reino);
                }
            });
            panel.add(btnUnificarReinos);

            // Adiciona botão para atacar reino
            JButton btnAtacarReino = new JButton("Atacar Reino");
            btnAtacarReino.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnAtacarReino.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame atacarFrame = new JFrame("Atacar Reino");
                    JPanel atacarPanel = new JPanel(new GridLayout(0, 1));
                    atacarFrame.add(atacarPanel);

                    JLabel labelInstrucao = new JLabel("Selecione o reino para atacar:");
                    atacarPanel.add(labelInstrucao);

                    // Remove o reino do jogador da lista de reinos disponíveis para ataque
                    Map<String, Reino> reinosAtaque = new HashMap<>(reinosGeral);
                    reinosAtaque.remove(reino.getNome());
                    JComboBox<String> reinosComboBox = new JComboBox<>(reinosAtaque.keySet().toArray(new String[0]));
                    atacarPanel.add(reinosComboBox);

                    JButton btnAtacar = new JButton("Atacar");
                    btnAtacar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String nomeReinoAtacado = (String) reinosComboBox.getSelectedItem();
                            Reino reinoAtacado = reinosAtaque.get(nomeReinoAtacado);
                            Reino reinoAtacante = reino;
                            // Realiza o combate entre os reinos
                            Sys sys = new Sys();
                            Reino vencedor = sys.combateEntreReinos(reinoAtacante, reinoAtacado);
                            if (vencedor == reinoAtacante) {
                                JOptionPane.showMessageDialog(null, "Ataque ao reino " + nomeReinoAtacado + " realizado com sucesso! O reino atacante venceu o combate.");
                            } else if (vencedor == reinoAtacado) {
                                JOptionPane.showMessageDialog(null, "Ataque ao reino " + nomeReinoAtacado + " realizado com sucesso! O reino defensor venceu o combate.");
                            } else {
                                JOptionPane.showMessageDialog(null, "O combate ao reino " + nomeReinoAtacado + " terminou em empate.");
                            }
                            atacarFrame.dispose();
                            frame.dispose();
                            turno.iniciarTurnos();
                        }
                    });
                    atacarPanel.add(btnAtacar);

                    atacarFrame.pack();
                    centralizarJanela(atacarFrame);
                    atacarFrame.setVisible(true);
                }
            });
            panel.add(btnAtacarReino);


            JButton btnDistribuirTropas = new JButton("Distribuir Tropas");
            btnDistribuirTropas.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    abrirJanelaDistribuicaoTropas(reino);
                }
            });
            panel.add(btnDistribuirTropas);

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
            Reino reino1 = new Reino("Prontera", 1000, 0, 0, 0, 0);
            reino1.setLider(lider1);

            Reino reino2 = new Reino("Morroc", 100, 0, 0, 50, 0);
            reino2.setLider(lider2);

            Reino reino3 = new Reino("Gefen", 100, 0, 0, 50, 0);
            reino3.setLider(lider3);

            Reino reino4 = new Reino("Pyon", 100, 0, 0, 50, 0);
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

        public static void unificarReinos(Reino reino) {
            JFrame unificarFrame = new JFrame("Unificar Reinos");
            JPanel unificarPanel = new JPanel(new GridLayout(0, 1));
            unificarFrame.add(unificarPanel);

            JLabel labelInstrucao = new JLabel("Selecione o reino para unificar com " + reino.getNome() + ":");
            unificarPanel.add(labelInstrucao);

            JComboBox<String> reinosComboBox = new JComboBox<>(reinosGeral.keySet().toArray(new String[0]));
            unificarPanel.add(reinosComboBox);

            JButton btnUnificar = new JButton("Unificar");
            btnUnificar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nomeReinoSelecionado = (String) reinosComboBox.getSelectedItem();
                    Reino reinoSelecionado = reinosGeral.get(nomeReinoSelecionado);
                    if (reino != reinoSelecionado) {
                        Sys sys = new Sys();
                        sys.unificarReino(reino, reinoSelecionado);
                        JOptionPane.showMessageDialog(null, "Veja o console para saber se a unificação funcionou");
                        unificarFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Não é possível unificar o mesmo reino.");
                    }
                }
            });
            unificarPanel.add(btnUnificar);

            unificarFrame.pack();
            centralizarJanela(unificarFrame);
            unificarFrame.setVisible(true);
        }

        // Adicione este método à classe Menu
        public static void abrirJanelaDistribuicaoTropas(Reino reino) {
            JFrame distribuicaoFrame = new JFrame("Distribuição de Tropas");
            JPanel distribuicaoPanel = new JPanel(new GridLayout(0, 1));
            distribuicaoFrame.add(distribuicaoPanel);

            JLabel labelInstrucao = new JLabel("Distribua suas tropas para ataque e defesa:");
            distribuicaoPanel.add(labelInstrucao);

            JTextField textFieldAtaque = new JTextField(10);
            JTextField textFieldDefesa = new JTextField(10);

            distribuicaoPanel.add(new JLabel("Tropas para Ataque:"));
            distribuicaoPanel.add(textFieldAtaque);
            distribuicaoPanel.add(new JLabel("Tropas para Defesa:"));
            distribuicaoPanel.add(textFieldDefesa);

            JButton btnDistribuir = new JButton("Distribuir Tropas");
            btnDistribuir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String strTropasAtaque = textFieldAtaque.getText();
                    String strTropasDefesa = textFieldDefesa.getText();

                    try {
                        int tropasAtaque = Integer.parseInt(strTropasAtaque);
                        int tropasDefesa = Integer.parseInt(strTropasDefesa);

                        if (tropasAtaque < 0 || tropasDefesa < 0) {
                            JOptionPane.showMessageDialog(null, "Por favor, insira um número positivo de tropas.");
                        } else if (tropasAtaque + tropasDefesa > reino.getSoldados()) {
                            JOptionPane.showMessageDialog(null, "Você não possui tantas tropas disponíveis.");
                        } else {
                            reino.soldadosAtacantes(tropasAtaque);
                            reino.soldadosDefensores(tropasDefesa);
                            JOptionPane.showMessageDialog(null, "Tropas distribuídas com sucesso.");
                            distribuicaoFrame.dispose();
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Por favor, insira números válidos para as tropas.");
                    }
                }
            });
            distribuicaoPanel.add(btnDistribuir);

            distribuicaoFrame.pack();
            centralizarJanela(distribuicaoFrame);
            distribuicaoFrame.setVisible(true);
        }

        public static Turno getTurno() {
            return turno;
        }

        public static void setTurno(Turno turno) {
            Menu.turno = turno;
        }

        public static Map<String, Reino> getReinosGeral() {
            return reinosGeral;
        }

        public static void setReinosGeral(Map<String, Reino> reinosGeral) {
            Menu.reinosGeral = reinosGeral;
        }


    }
