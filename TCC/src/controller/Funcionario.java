package controller;

import dao.DaoFuncionario;
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
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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

public class Funcionario {

    public static JPanel pnlFuncionario;
    public static Border margin;

    public static int height;
    public static int widthInput;
    public static int widthLabel;

    public static JButton btnCliente;
    public static JButton btnFuncionario;

    public static JLabel lblCod;
    public static JLabel lblNome;
    public static JLabel lblSenha;
    public static JLabel lblTelefone;
    public static JLabel lblEndereco;

    public static JTextField txtCod;
    public static JTextField txtNome;
    public static JPasswordField txtSenha;
    public static JFormattedTextField txtTelefone;
    public static JTextField txtEndereco;
    public static JTextField txtSearch;

    public static MaskFormatter mfTel;

    public static JButton btnSearch;
    public static JButton btnSalvar;
    public static JButton btnDeletar;

    public static JScrollPane scroll;
    public static JTable table;
    public static ArrayList<DaoFuncionario> list;
    public static ArrayList<DaoFuncionario> funcionarioPesquisado;

    public static boolean temRegistro;

    public static JPanel createNewFuncionario() throws ParseException, SQLException, ClassNotFoundException {
        temRegistro = false;

        pnlFuncionario = new JPanel();
        pnlFuncionario.setSize(730, 600);
        pnlFuncionario.setLocation(0, 0);
        pnlFuncionario.setOpaque(false);

        int any = 20;
        int marginValue = 10;

        margin = new EmptyBorder(marginValue, marginValue, marginValue, marginValue);
        pnlFuncionario.setBorder(BorderFactory.createEmptyBorder(any, any, any, any));

        widthInput = 250;
        widthLabel = 65;
        height = 25;

        btnCliente = new JButton("Cliente");
        btnCliente.setForeground(Color.WHITE);
        btnCliente.setBackground(Color.decode("#303030"));
        btnCliente.setOpaque(true);
        btnCliente.setHorizontalAlignment(SwingConstants.CENTER);
        btnCliente.setVerticalAlignment(SwingConstants.CENTER);
        btnCliente.setBounds(25, 150, 100, height + 5);
        btnCliente.setEnabled(true);
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

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    reloadPnl();
                    pnlFuncionario.add(Cliente.createNewClient());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnFuncionario = new JButton("Funcionário");
        btnFuncionario.setForeground(Color.GRAY);
        btnFuncionario.setBackground(Color.decode("#303030"));
        btnFuncionario.setOpaque(true);
        btnFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
        btnFuncionario.setVerticalAlignment(SwingConstants.CENTER);
        btnFuncionario.setBounds(150, 150, 100, height + 5);
        btnFuncionario.setEnabled(false);
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
        txtNome.setBounds(100, 235, widthInput, height);
        txtNome.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));
        
        lblSenha = new JLabel("Senha: ", SwingConstants.RIGHT);
        lblSenha.setBounds(25, 270, widthLabel, height);
        txtSenha = new JPasswordField();
        txtSenha.setBounds(100, 270, widthInput, height);
        txtSenha.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));

        lblTelefone = new JLabel("Telefone:", SwingConstants.RIGHT);
        lblTelefone.setBounds(25, 305, widthLabel, height);
        txtTelefone = new JFormattedTextField();
        txtTelefone.setBounds(100, 305, 95, height);
        txtTelefone.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));
        mfTel = new MaskFormatter("(##)#####-####");
        mfTel.setPlaceholderCharacter('_');
        mfTel.setValidCharacters("0123456789");
        mfTel.setValueContainsLiteralCharacters(false);
        mfTel.setValueClass(String.class);
        DefaultFormatterFactory dffTel = new DefaultFormatterFactory(mfTel);
        txtTelefone.setFormatterFactory(dffTel);

        lblEndereco = new JLabel("Endereço:", SwingConstants.RIGHT);
        lblEndereco.setBounds(25, 340, widthLabel, height);
        txtEndereco = new JTextField();
        txtEndereco.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));
        txtEndereco.setBounds(100, 340, widthInput, height);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setBackground(Color.decode("#1920da"));
        btnSalvar.setOpaque(true);
        btnSalvar.setHorizontalAlignment(SwingConstants.CENTER);
        btnSalvar.setVerticalAlignment(SwingConstants.CENTER);
        btnSalvar.setBounds(420, 340, 100, height + 5);
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
                            || txtSenha.getText().isEmpty()
                            || txtTelefone.getText().isEmpty()
                            || txtTelefone.getText().equals("(__)_____-____")
                            || txtEndereco.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Ops...", JOptionPane.WARNING_MESSAGE);
                    } else {
                        if (txtNome.getText().length() > 50
                                || txtEndereco.getText().length() > 50
                                || txtSenha.getText().length() > 50) {
                            JOptionPane.showMessageDialog(null, "Não são permitidos mais de 50 caracteres", "Ops...", JOptionPane.WARNING_MESSAGE);
                            cleanFields();
                        } else {
                            if (ValidacaoForms.verifyInfos(txtNome.getText())
                                    && ValidacaoForms.verifyInfos(txtEndereco.getText())
                                    && ValidacaoForms.verifyInfos(txtSenha.getText())) {
                                JOptionPane.showMessageDialog(null, "Não são permitidos caracteres especiais", "Ops...", JOptionPane.WARNING_MESSAGE);
                                cleanFields();
                            } else {

                                String nome = txtNome.getText();
                                String senha = txtSenha.getText();
                                String telefone = txtTelefone.getText();
                                telefone = telefone.replace("(", "");
                                telefone = telefone.replace(")", "");
                                telefone = telefone.replace("-", "");
                                String endereco = txtEndereco.getText();

                                DaoFuncionario df = new DaoFuncionario();
                                df.setNome(nome);
                                df.setSenha(senha);
                                df.setTelefone(telefone);
                                df.setEndereco(endereco);

                                if (temRegistro) {
                                    int cod = Integer.parseInt(txtCod.getText());
                                    df.setIdFuncionario(cod);
                                    df.alterar();
                                    JOptionPane.showMessageDialog(null, "Funcionário alterado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    df.incluir();
                                    JOptionPane.showMessageDialog(null, "Funcionário incluido com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                                }
                                temRegistro = false;
                                list = df.fetch();
                                reloadTable(list);
                                cleanFields();
                            }
                        }
                    }
                } catch (ParseException | SQLException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnDeletar = new JButton("Deletar");
        btnDeletar.setForeground(Color.DARK_GRAY);
        btnDeletar.setBackground(Color.decode("#d4d4d4"));
        btnDeletar.setOpaque(true);
        btnDeletar.setHorizontalAlignment(SwingConstants.CENTER);
        btnDeletar.setVerticalAlignment(SwingConstants.CENTER);
        btnDeletar.setBounds(550, 340, 100, height + 5);
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
                        DaoFuncionario df = new DaoFuncionario();
                        df.setIdFuncionario(cod);
                        if (JOptionPane.showConfirmDialog(
                                null,
                                "Realmente deseja excluir esse funcionário ?", "Atenção",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                            if (!df.excluir()) {
                                JOptionPane.showMessageDialog(null, "Funcionário excluido com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Ocorreu um erro, tente novamente", "Ops...", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        temRegistro = false;
                        list = df.fetch();
                        reloadTable(list);
                        cleanFields();
                    }
                } catch (ParseException | SQLException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setTable();
        createTable();
        createFieldSearch();

        pnlFuncionario.add(btnCliente);
        pnlFuncionario.add(btnFuncionario);
        pnlFuncionario.add(lblCod);
        pnlFuncionario.add(txtCod);
        pnlFuncionario.add(lblNome);
        pnlFuncionario.add(txtNome);
        pnlFuncionario.add(lblSenha);
        pnlFuncionario.add(txtSenha);
        pnlFuncionario.add(lblTelefone);
        pnlFuncionario.add(txtTelefone);
        pnlFuncionario.add(lblEndereco);
        pnlFuncionario.add(txtEndereco);
        pnlFuncionario.add(btnSalvar);
        pnlFuncionario.add(btnDeletar);
        pnlFuncionario.add(scroll);
        pnlFuncionario.add(txtSearch);
        pnlFuncionario.add(btnSearch);

        pnlFuncionario.setSize(730,600);
        pnlFuncionario.setLayout(null);
        pnlFuncionario.setVisible(true);
        return pnlFuncionario;
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
        scroll.setBounds(100, 375, 550, 150);
        scroll.setOpaque(false);

        DaoFuncionario df = new DaoFuncionario();
        list = df.fetch();
        for (int i = 0; i < list.size(); i++) {
            int cod = list.get(i).getIdFuncionario();
            String nome = list.get(i).getNome();
            String telefone = mfTel.valueToString(list.get(i).getTelefone());
            String endereco = list.get(i).getEndereco();
            model.addRow(new Object[]{cod, nome, telefone, endereco});
        }
    }

    public static void createTable() {
        try {
            DaoFuncionario dc = new DaoFuncionario();
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

    public static void reloadTable(ArrayList<DaoFuncionario> list) throws ParseException {
        cleanTable();
        table.setEnabled(true);
        scroll.setEnabled(true);
        if (list.isEmpty()) {
            table.setEnabled(false);
            scroll.setEnabled(false);
        } else {
            DefaultTableModel dadosFuncionario = (DefaultTableModel) table.getModel();
            String[] Linha = new String[]{"", "", "", ""};
            int posicao = -1;
            for (DaoFuncionario daoFuncionario : list) {
                posicao++;
                dadosFuncionario.addRow(Linha);
                dadosFuncionario.setValueAt(daoFuncionario.getIdFuncionario(), posicao, 0);
                dadosFuncionario.setValueAt(daoFuncionario.getNome(), posicao, 1);
                dadosFuncionario.setValueAt(mfTel.valueToString(daoFuncionario.getTelefone()), posicao, 2);
                dadosFuncionario.setValueAt(daoFuncionario.getEndereco(), posicao, 3);
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
                        DaoFuncionario df = new DaoFuncionario();
                        list = df.fetch();
                        reloadTable(list);
                    } else {
                        DaoFuncionario df = new DaoFuncionario();
                        df.setNome(nomeClienteSearch);
                        funcionarioPesquisado = df.pesquisar(df);
                        reloadTable(funcionarioPesquisado);
                    }
                } catch (ParseException | SQLException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }


    public static void cleanFields() {
        txtCod.setText("");
        txtSenha.setText("");
        txtNome.setText("");
        txtTelefone.setText("");
        txtEndereco.setText("");
    }

    private static void reloadPnl() {
        pnlFuncionario.removeAll();
        pnlFuncionario.revalidate();
        pnlFuncionario.repaint();
    }
}
