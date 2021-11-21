package controller;

import dao.DaoAnimalEstimacao;
import dao.DaoClientes;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class Cliente {

    public static JPanel pnlCliente;
    public static Border margin;

    public static int height;
    public static int widthInput;
    public static int widthLabel;

    public static JButton btnCliente;
    public static JButton btnFuncionario;

    public static JLabel lblCod;
    public static JLabel lblNome;
    public static JLabel lblTelefone;
    public static JLabel lblEndereco;

    public static JTextField txtCod;
    public static JTextField txtNome;
    public static JFormattedTextField txtTelefone;
    public static JTextField txtEndereco;
    public static JTextField txtSearch;

    public static MaskFormatter mfTel;

    public static JButton btnSearch;
    public static JButton btnPet;
    public static JButton btnSalvar;
    public static JButton btnDeletar;

    public static JScrollPane scroll;
    public static JTable table;

    public static ArrayList<DaoClientes> list;
    public static ArrayList<DaoAnimalEstimacao> infoPet;
    public static ArrayList<DaoAnimalEstimacao> pets;
    public static ArrayList<DaoClientes> clientePesquisado;

    public static boolean temRegistro;
    public static int idPet;
    public static String nomePet;

    public static JPanel createNewClient() throws ParseException, SQLException, ClassNotFoundException {
        temRegistro = false;

        pnlCliente = new JPanel();
        pnlCliente.setSize(750, 450);
        pnlCliente.setLocation(0, 0);
        pnlCliente.setOpaque(false);

        int any = 20;
        int marginValue = 10;

        margin = new EmptyBorder(marginValue, marginValue, marginValue, marginValue);
        pnlCliente.setBorder(BorderFactory.createEmptyBorder(any, any, any, any));

        widthInput = 250;
        widthLabel = 65;
        height = 25;

        btnCliente = new JButton("Cliente");
        btnCliente.setForeground(Color.GRAY);
        btnCliente.setBackground(Color.decode("#303030"));
        btnCliente.setOpaque(true);
        btnCliente.setHorizontalAlignment(SwingConstants.CENTER);
        btnCliente.setVerticalAlignment(SwingConstants.CENTER);
        btnCliente.setBounds(25, 150, 100, height + 5);
        btnCliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnCliente.setBackground(Color.decode("#1952da"));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCliente.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                btnCliente.setBackground(Color.decode("#303030"));
            }
        });

        btnFuncionario = new JButton("Funcionário");
        btnFuncionario.setForeground(Color.WHITE);
        btnFuncionario.setBackground(Color.decode("#303030"));
        btnFuncionario.setOpaque(true);
        btnFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
        btnFuncionario.setVerticalAlignment(SwingConstants.CENTER);
        btnFuncionario.setBounds(150, 150, 100, height + 5);
        btnFuncionario.setEnabled(true);
        btnFuncionario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFuncionario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnFuncionario.setBackground(Color.decode("#1952da"));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFuncionario.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                btnFuncionario.setBackground(Color.decode("#303030"));
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    reloadPnl();
                    pnlCliente.add(Funcionario.createNewFuncionario());
                } catch (ParseException | SQLException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnPet = new JButton("Pet");
        btnPet.setForeground(Color.WHITE);
        btnPet.setBackground(Color.decode("#303030"));
        btnPet.setOpaque(true);
        btnPet.setHorizontalAlignment(SwingConstants.CENTER);
        btnPet.setVerticalAlignment(SwingConstants.CENTER);
        btnPet.setBounds(275, 150, 100, height + 5);
        btnPet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPet.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnPet.setBackground(Color.decode("#1952da"));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPet.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                btnPet.setBackground(Color.decode("#303030"));
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String cod = txtCod.getText();
                if (cod == null || cod.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Selecione um cliente (dono do pet) por primeiro", "Ops...", JOptionPane.WARNING_MESSAGE);
                } else {
                    int idCliente = Integer.parseInt(cod);
                    try {
                        DaoAnimalEstimacao dae = new DaoAnimalEstimacao();
                        String[] options = new String[]{"Adicionar", "Excluir"};
                        int option = JOptionPane.showOptionDialog(null, "O que deseja fazer ?", "Aviso",
                                JOptionPane.NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                null, options, options[1]);
                        switch (option) {
                            case 0:
                                String newPet = JOptionPane.showInputDialog(null, "Insira um nome: ", "Cadastro: ", JOptionPane.INFORMATION_MESSAGE);
                                if (newPet == null || newPet.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Não é permitido campos vazios", "Ops...", JOptionPane.WARNING_MESSAGE);
                                } else {
                                    if (ValidacaoForms.verifyInfos(newPet)) {
                                        JOptionPane.showMessageDialog(null, "Não são permitidos caracteres especiais", "Ops...", JOptionPane.WARNING_MESSAGE);
                                    } else {
                                        dae.setIdCliente(idCliente);
                                        dae.setNome(newPet);
                                        dae.incluir();
                                        JOptionPane.showMessageDialog(null, "Pet cadastrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                                break;

                            case 1:
                                String noPet = JOptionPane.showInputDialog(null, "Insira o nome do pet que deseja excluir: ", "Excluir: ", JOptionPane.INFORMATION_MESSAGE);
                                if (noPet == null || noPet.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Não é permitido campos vazios", "Ops...", JOptionPane.WARNING_MESSAGE);
                                } else {
                                    if (ValidacaoForms.verifyInfos(noPet)) {
                                        JOptionPane.showMessageDialog(null, "Não são permitidos caracteres especiais", "Ops...", JOptionPane.WARNING_MESSAGE);
                                    } else {
                                        if (JOptionPane.showConfirmDialog(
                                                null,
                                                "Realmente deseja excluir esse pet ?", "Atenção",
                                                JOptionPane.YES_NO_OPTION,
                                                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                                            try {
                                                dae.setIdCliente(idCliente);
                                                dae.setNome(noPet);
                                                infoPet = dae.returnPet();
                                                for (int i = 0; i < infoPet.size(); i++) {
                                                    idPet = infoPet.get(i).getIdAnimalEstimacao();
                                                }
                                                dae.setIdAnimalEstimacao(idPet);
                                                if (!dae.excluir()) {
                                                    JOptionPane.showMessageDialog(null, "Pet excluido com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Ocorreu um erro, tente novamente", "Ops...", JOptionPane.WARNING_MESSAGE);
                                                }
                                            } catch (SQLException | ClassNotFoundException ex) {
                                                JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    }
                                }
                                break;
                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        lblCod = new JLabel("Código:", SwingConstants.RIGHT);
        lblCod.setBounds(25, 200, widthLabel, height);
        txtCod = new JTextField();
        txtCod.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));
        txtCod.setBounds(100, 200, 50, height);
        txtCod.setEditable(false);

        lblNome = new JLabel("Nome:", SwingConstants.RIGHT);
        lblNome.setBounds(25, 235, widthLabel, height);
        txtNome = new JTextField();
        txtNome.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));
        txtNome.setBounds(100, 235, widthInput, height);

        lblTelefone = new JLabel("Telefone:", SwingConstants.RIGHT);
        lblTelefone.setBounds(25, 270, widthLabel, height);
        txtTelefone = new JFormattedTextField();
        txtTelefone.setBounds(100, 270, 95, height);
        txtTelefone.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));
        mfTel = new MaskFormatter("(##)#####-####");
        mfTel.setPlaceholderCharacter('_');
        mfTel.setValidCharacters("0123456789");
        mfTel.setValueContainsLiteralCharacters(false);
        mfTel.setValueClass(String.class);
        DefaultFormatterFactory dffTel = new DefaultFormatterFactory(mfTel);
        txtTelefone.setFormatterFactory(dffTel);

        lblEndereco = new JLabel("Endereço:", SwingConstants.RIGHT);
        lblEndereco.setBounds(25, 305, widthLabel, height);
        txtEndereco = new JTextField();
        txtEndereco.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));
        txtEndereco.setBounds(100, 305, widthInput, height);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setBackground(Color.decode("#1920da"));
        btnSalvar.setOpaque(true);
        btnSalvar.setHorizontalAlignment(SwingConstants.CENTER);
        btnSalvar.setVerticalAlignment(SwingConstants.CENTER);
        btnSalvar.setBounds(420, 305, 100, height + 5);
        btnSalvar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnSalvar.setBackground(Color.decode("#1952da"));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                btnSalvar.setBackground(Color.decode("#1920da"));
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    if (txtNome.getText().isEmpty()
                            || txtTelefone.getText().isEmpty()
                            || txtTelefone.getText().equals("(__)_____-____")
                            || txtEndereco.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Ops...", JOptionPane.WARNING_MESSAGE);
                    } else {
                        if (txtNome.getText().length() > 50 || txtEndereco.getText().length() > 50) {
                            JOptionPane.showMessageDialog(null, "Não são permitidos mais de 50 caracteres", "Ops...", JOptionPane.WARNING_MESSAGE);
                            cleanFields();
                        } else {
                            if (ValidacaoForms.verifyInfos(txtNome.getText()) && ValidacaoForms.verifyInfos(txtEndereco.getText())) {
                                JOptionPane.showMessageDialog(null, "Não são permitidos caracteres especiais", "Ops...", JOptionPane.WARNING_MESSAGE);
                                cleanFields();
                            } else {
                                String nome = txtNome.getText();
                                String telefone = txtTelefone.getText();
                                telefone = telefone.replace("(", "");
                                telefone = telefone.replace(")", "");
                                telefone = telefone.replace("-", "");
                                String endereco = txtEndereco.getText();

                                DaoClientes dc = new DaoClientes();
                                dc.setNome(nome);
                                dc.setTelefone(telefone);
                                dc.setEndereco(endereco);

                                if (temRegistro) {
                                    int cod = Integer.parseInt(txtCod.getText());
                                    dc.setIdCliente(cod);
                                    dc.alterar();
                                    JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                                    temRegistro = false;
                                } else {
                                    dc.incluir();
                                    JOptionPane.showMessageDialog(null, "Cliente incluido com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                                    temRegistro = false;
                                }
                                list = dc.fetch();
                                reloadTable(list);
                                cleanFields();
                            }
                        }
                    }
                } catch (ParseException | SQLException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

        btnDeletar = new JButton("Deletar");
        btnDeletar.setForeground(Color.DARK_GRAY);
        btnDeletar.setBackground(Color.decode("#d4d4d4"));
        btnDeletar.setOpaque(true);
        btnDeletar.setHorizontalAlignment(SwingConstants.CENTER);
        btnDeletar.setVerticalAlignment(SwingConstants.CENTER);
        btnDeletar.setBounds(550, 305, 100, height + 5);
        btnDeletar.setEnabled(
                false);
        btnDeletar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeletar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnDeletar.setBackground(Color.decode("#1952da"));
                btnDeletar.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeletar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                btnDeletar.setBackground(Color.decode("#d4d4d4"));
                btnDeletar.setForeground(Color.DARK_GRAY);
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    if (temRegistro) {
                        int cod = Integer.parseInt(txtCod.getText());
                        DaoClientes dc = new DaoClientes();
                        dc.setIdCliente(cod);
                        if (JOptionPane.showConfirmDialog(
                                null,
                                "Realmente deseja excluir esse cliente ?", "Atenção",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                            DaoAnimalEstimacao dae = new DaoAnimalEstimacao();
                            int idCliente = Integer.parseInt(txtCod.getText());
                            dae.setIdCliente(idCliente);
                            pets = dae.selectPet();
                            for (int i = 0; i < pets.size(); i++) {
                                nomePet = pets.get(i).getNome();
                            }
                            dae.setNome(nomePet);
                            infoPet = dae.returnPetToDelete();
                            for (int i = 0; i < infoPet.size(); i++) {
                                idPet = infoPet.get(i).getIdAnimalEstimacao();
                                dae.setIdAnimalEstimacao(idPet);
                                if (dae.excluir()) {
                                    JOptionPane.showMessageDialog(null, "Ocorreu um erro, tente novamente", "Ops...", JOptionPane.WARNING_MESSAGE);
                                }
                            }
                            if (!dc.excluir()) {
                                JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Ocorreu um erro, tente novamente", "Ops...", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        temRegistro = false;
                        list = dc.fetch();
                        reloadTable(list);
                        cleanFields();
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione um cliente primeiro", "Ops...", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (ParseException | SQLException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um error: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

        setTable();
        createTable();
        createFieldSearch();

        pnlCliente.add(btnCliente);
        pnlCliente.add(btnFuncionario);
        pnlCliente.add(lblCod);
        pnlCliente.add(txtCod);
        pnlCliente.add(lblNome);
        pnlCliente.add(txtNome);
        pnlCliente.add(lblTelefone);
        pnlCliente.add(txtTelefone);
        pnlCliente.add(lblEndereco);
        pnlCliente.add(txtEndereco);
        pnlCliente.add(btnPet);
        pnlCliente.add(btnSalvar);
        pnlCliente.add(btnDeletar);
        pnlCliente.add(scroll);
        pnlCliente.add(txtSearch);
        pnlCliente.add(btnSearch);
        
        pnlCliente.setSize(730, 600);
        pnlCliente.setLayout(null);
        pnlCliente.setVisible(true);
        return pnlCliente;
    }

    public static void setTable() throws SQLException, ClassNotFoundException, ParseException {
        String[] columnNames = {"ID", "Nome", "Telefone", "Endereco"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(table.getColumnModel().getColumn(0).getPreferredWidth() - 500);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (list.isEmpty()) {
                    table.setEnabled(false);
                    scroll.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Não há registro por aqui", "Ops...", JOptionPane.WARNING_MESSAGE);
                } else {
                    int row = table.getSelectedRow();

                    int cod = ((int) model.getValueAt(row, 0));
                    String nome = ((String) model.getValueAt(row, 1));
                    String telefone = ((String) model.getValueAt(row, 2));
                    String endereco = ((String) model.getValueAt(row, 3));

                    txtCod.setText(Integer.toString(cod));
                    txtNome.setText(nome);
                    txtTelefone.setText(telefone);
                    txtEndereco.setText(endereco);

                    btnDeletar.setEnabled(true);
                    temRegistro = true;
                }
            }
        });

        scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(100, 340, 550, 150);
        scroll.setOpaque(false);

        DaoClientes dc = new DaoClientes();
        list = dc.fetch();
        for (int i = 0; i < list.size(); i++) {
            int cod = list.get(i).getIdCliente();
            String nome = list.get(i).getNome();
            String telefone = mfTel.valueToString(list.get(i).getTelefone());
            String endereco = list.get(i).getEndereco();
            model.addRow(new Object[]{cod, nome, telefone, endereco});
        }
    }

    public static void createTable() {
        try {
            DaoClientes dc = new DaoClientes();
            list = dc.fetch();
            reloadTable(list);
        } catch (ParseException | SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void cleanTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
    }

    public static void reloadTable(ArrayList<DaoClientes> list) throws ParseException {
        cleanTable();
        table.setEnabled(true);
        scroll.setEnabled(true);
        if (list.isEmpty()) {
            table.setEnabled(false);
            scroll.setEnabled(false);
        } else {
            DefaultTableModel dadosClientes = (DefaultTableModel) table.getModel();
            String[] Linha = new String[]{"", "", "", "", ""};
            int posicao = -1;
            for (DaoClientes daoClientes : list) {
                posicao++;
                dadosClientes.addRow(Linha);
                dadosClientes.setValueAt(daoClientes.getIdCliente(), posicao, 0);
                dadosClientes.setValueAt(daoClientes.getNome(), posicao, 1);
                dadosClientes.setValueAt(mfTel.valueToString(daoClientes.getTelefone()), posicao, 2);
                dadosClientes.setValueAt(daoClientes.getEndereco(), posicao, 3);
            }
        }
    }
    
    public static void createFieldSearch() {
        txtSearch = new JTextField("Pesquisar:");
        txtSearch.setBackground(Color.decode("#303030"));
        txtSearch.setForeground(Color.WHITE);
        txtSearch.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
        txtSearch.setBorder(new MatteBorder(0, 8, 0, 0, Color.decode("#303030")));
        txtSearch.setBounds(450, 50, widthInput - 20, 24);
        txtSearch.setEditable(true);
        txtSearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent fe) {
                txtSearch.setText("");
            }

            @Override
            public void focusLost(FocusEvent fe) {
                if (txtSearch.getText().length() <= 0) {
                    txtSearch.setText("Pesquisar:");
                }
            }
        });

        btnSearch = new JButton();
        btnSearch.setBackground(Color.decode("#303030"));
        btnSearch.setBounds(416, 47, 30, 30);
        try {
            Image img = ImageIO.read(btnSearch.getClass().getResource("/images/search.png"));
            btnSearch.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                try {
                    String nomeClienteSearch = txtSearch.getText();
                    if (nomeClienteSearch.equals("")
                            || nomeClienteSearch.equals(" ")
                            || nomeClienteSearch.equals("Pesquisar:")) {
                        DaoClientes dc = new DaoClientes();
                        list = dc.fetch();
                        reloadTable(list);
                    } else {
                        DaoClientes dc = new DaoClientes();
                        dc.setNome(nomeClienteSearch);
                        clientePesquisado = dc.pesquisar(dc);
                        reloadTable(clientePesquisado);
                    }
                } catch (ParseException | SQLException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void cleanFields() {
        txtCod.setText("");
        txtNome.setText("");
        txtTelefone.setText("");
        txtEndereco.setText("");
    }

    private static void reloadPnl() {
        pnlCliente.removeAll();
        pnlCliente.revalidate();
        pnlCliente.repaint();
    }
}
