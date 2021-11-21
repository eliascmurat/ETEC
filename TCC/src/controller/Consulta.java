package controller;

import dao.DaoAnimalEstimacao;
import dao.DaoClientes;
import dao.DaoConsultas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class Consulta {

    public static JPanel pnlConsulta;
    public static Border margin;

    public static MaskFormatter mfTel;
    public static MaskFormatter mfDia;
    public static MaskFormatter mfHorario;

    public static JTable table;
    public static JScrollPane scroll;

    public static int widthInput;
    public static int widthLabel;
    public static int height;

    public static int cDay;
    public static int cMonth;
    public static int cYear;

    public static JLabel lblCod;
    public static JLabel lblNome;
    public static JLabel lblPet;
    public static JLabel lblTelefone;
    public static JLabel lblServico;
    public static JLabel lblDia;
    public static JLabel lblHorario;

    public static JTextField txtCod;
    public static JComboBox txtNome;
    public static JComboBox txtPet;
    public static JFormattedTextField txtTelefone;
    public static JComboBox txtServico;
    public static JFormattedTextField txtDia;
    public static JComboBox txtHorario;
    public static JTextField txtSearch;

    public static String[] cmbServicos = {
        "Banho e Tosa",
        "Banho",
        "Tosa"
    };
    public static String[] cmbHorarios = {
        "08:30",
        "09:30",
        "10:30",
        "11:30",
        "12:30",
        "13:30",
        "14:30",
        "15:30",
        "16:30",
        "17:30"
    };

    public static JButton btnSearch;
    public static JButton btnSalvar;
    public static JButton btnDeletar;

    public static ArrayList<DaoConsultas> list;
    public static ArrayList<DaoClientes> listCod;
    public static ArrayList<DaoConsultas> listAgenda;
    public static ArrayList<DaoClientes> clientes;
    public static ArrayList<DaoClientes> telefones;
    public static ArrayList<DaoAnimalEstimacao> pets;
    public static ArrayList<DaoConsultas> clientePesquisado;

    public static List<String> listCli;
    public static List<String> listTel;
    public static List<String> listPet;

    public static String[] cliArray;
    public static String[] telArray;
    public static String[] petArray;

    public static boolean haveConsulta;
    public static String dataMarcada;

    public static JPanel createNewConsulta(boolean temAgenda, String inicioDoMes, String fimDoMes, String diaConsulta, String horaConsulta) throws ParseException, SQLException, ClassNotFoundException {
        haveConsulta = temAgenda;

        Calendar calendar = new GregorianCalendar();
        cYear = calendar.get(Calendar.YEAR);
        cMonth = calendar.get(Calendar.MONTH);
        cDay = calendar.get(Calendar.DATE);

        pnlConsulta = new JPanel();
        pnlConsulta.setSize(750, 450);
        pnlConsulta.setLocation(0, 0);
        pnlConsulta.setOpaque(false);

        int any = 20;
        int marginValue = 10;

        margin = new EmptyBorder(marginValue, marginValue, marginValue, marginValue);
        pnlConsulta.setBorder(BorderFactory.createEmptyBorder(any, any, any, any));

        widthInput = 250;
        widthLabel = 65;
        height = 25;

        lblCod = new JLabel("Código:", SwingConstants.RIGHT);
        lblCod.setBounds(25, 200, widthLabel, height);
        txtCod = new JTextField();
        txtCod.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));
        txtCod.setBounds(100, 200, 65, height);
        txtCod.setEditable(false);

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

        lblServico = new JLabel("Serviço:", SwingConstants.RIGHT);
        lblServico.setBounds(25, 340, widthLabel, height);
        txtServico = new JComboBox(cmbServicos);
        txtServico.setSelectedIndex(0);
        txtServico.setBounds(100, 340, widthInput, height);
        txtServico.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));

        lblDia = new JLabel("Dia:", SwingConstants.RIGHT);
        lblDia.setBounds(25, 375, widthLabel, height);
        txtDia = new JFormattedTextField();
        txtDia.setBounds(100, 375, 65, height);
        txtDia.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));
        if (haveConsulta) {
            txtDia.setEnabled(false);
        } else {
            txtDia.setEnabled(true);
        }
        mfDia = new MaskFormatter("##/##/####");
        mfDia.setPlaceholderCharacter('_');
        mfDia.setValueContainsLiteralCharacters(false);
        mfDia.setValueClass(String.class);
        DefaultFormatterFactory dffDia = new DefaultFormatterFactory(mfDia);
        txtDia.setFormatterFactory(dffDia);

        lblHorario = new JLabel("Horário:", SwingConstants.RIGHT);
        lblHorario.setBounds(210, 375, widthLabel, height);
        txtHorario = new JComboBox(cmbHorarios);
        txtHorario.setSelectedIndex(0);
        txtHorario.setBounds(285, 375, 65, height);
        txtHorario.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));
        if (haveConsulta) {
            txtHorario.setEnabled(false);
        } else {
            txtHorario.setEnabled(true);
        }

        btnSalvar = new JButton("Salvar");
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setBackground(Color.decode("#1920da"));
        btnSalvar.setOpaque(true);
        btnSalvar.setHorizontalAlignment(SwingConstants.CENTER);
        btnSalvar.setVerticalAlignment(SwingConstants.CENTER);
        btnSalvar.setBounds(420, 375, 100, height + 5);
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
                    if (txtPet.getSelectedItem().toString().equals("Nenhum pet encontrado") || txtTelefone.getText().equals("(__)_____-____") || txtDia.getText().equals("__/__/____")) {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Ops...", JOptionPane.WARNING_MESSAGE);
                    } else {
                        String nome = txtNome.getSelectedItem().toString();
                        String pet = txtPet.getSelectedItem().toString();
                        String telefone = txtTelefone.getText();
                        telefone = telefone.replace("(", "");
                        telefone = telefone.replace(")", "");
                        telefone = telefone.replace("-", "");
                        String servico = txtServico.getSelectedItem().toString();

                        String data = txtDia.getText().replace("/", "-");
                        String horario = txtHorario.getSelectedItem().toString();

                        String dia = data.substring(0, 2);
                        String mes = data.substring(3, 5);
                        String ano = data.substring(6, 10);

                        int diaI = Integer.parseInt(dia);
                        int mesI = Integer.parseInt(mes);
                        int anoI = Integer.parseInt(ano);

                        if (anoI < cYear) {
                            JOptionPane.showMessageDialog(null, "Não é permitido agendar uma consulta em datas anteriores.", "Ops...", JOptionPane.WARNING_MESSAGE);
                        } else {
                            if (mesI < (cMonth + 1)) {
                                JOptionPane.showMessageDialog(null, "Não é permitido agendar uma consulta em datas anteriores.", "Ops...", JOptionPane.WARNING_MESSAGE);
                            } else {
                                if (diaI < cDay) {
                                    JOptionPane.showMessageDialog(null, "Não é permitido agendar uma consulta em datas anteriores.", "Ops...", JOptionPane.WARNING_MESSAGE);
                                } else {
                                    String sData = (ano + "-" + mes + "-" + dia) + " " + (horario + ":00");

                                    String padrao = "yyyy-MM-dd HH:mm:ss";
                                    SimpleDateFormat sdf = new SimpleDateFormat(padrao);
                                    Date dData = sdf.parse(sData);

                                    DaoConsultas dc = new DaoConsultas();
                                    listAgenda = dc.pesquisar(sData, sData, dc);
                                    for (int i = 0; i < listAgenda.size(); i++) {
                                        dataMarcada = sdf.format(listAgenda.get(i).getDataConsulta());
                                    }

                                    dc.setNome(nome);
                                    dc.setPet(pet);
                                    dc.setTelefone(telefone);
                                    dc.setServico(servico);
                                    dc.setDataConsulta(dData);

                                    if (haveConsulta) {
                                        try {
                                            int codConsulta = Integer.parseInt(txtCod.getText());
                                            dc.setIdConsulta(codConsulta);
                                            dc.alterar();
                                            JOptionPane.showMessageDialog(null, "Consulta alterada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                                            txtDia.setEnabled(true);
                                            txtHorario.setEnabled(true);
                                            list = dc.fetch();
                                            reloadTable(list);
                                            cleanFields();
                                            haveConsulta = false;
                                        } catch (SQLException | ClassNotFoundException ex) {
                                            JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } else {
                                        try {
                                            if (sData.equals(dataMarcada)) {
                                                JOptionPane.showMessageDialog(null, "Desculpe...\nJá existe uma consulta marcada nesse horário\nTente novamente", "Ops...", JOptionPane.WARNING_MESSAGE);
                                            } else {
                                                dc.incluir();
                                                JOptionPane.showMessageDialog(null, "Consulta cadastrada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                                            }
                                            txtDia.setEnabled(true);
                                            txtHorario.setEnabled(true);
                                            list = dc.fetch();
                                            reloadTable(list);
                                            cleanFields();
                                        } catch (SQLException | ClassNotFoundException ex) {
                                            JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                }
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
        btnDeletar.setBounds(550, 375, 100, height + 5);
        if (haveConsulta) {
            btnDeletar.setEnabled(true);
        } else {
            btnDeletar.setEnabled(false);
        }
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
                if (haveConsulta) {
                    try {
                        int cod = Integer.parseInt(txtCod.getText());
                        DaoConsultas dc = new DaoConsultas();
                        dc.setIdConsulta(cod);
                        if (JOptionPane.showConfirmDialog(
                                null,
                                "Realmente deseja excluir essa consulta ?", "Atenção",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                            if (!dc.excluir()) {
                                JOptionPane.showMessageDialog(null, "Consulta apagada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Ocorreu um erro, tente novamente", "Ops...", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        txtDia.setEnabled(true);
                        txtHorario.setEnabled(true);
                        haveConsulta = false;
                        list = dc.fetch();
                        reloadTable(list);
                        cleanFields();
                        btnDeletar.setEnabled(false);
                    } catch (ParseException | SQLException | ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma consulta primeiro", "Ops...", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        );

        DaoClientes dCliente = new DaoClientes();
        clientes = dCliente.selectNome();
        listCli = new ArrayList<>();
        for (int i = 0; i < clientes.size(); i++) {
            String nome = clientes.get(i).getNome();
            listCli.add(nome);
        }
        cliArray = listCli.toArray(new String[clientes.size()]);
        if (cliArray.length > 0) {
            txtNome = new JComboBox(cliArray);
            txtNome.setEnabled(true);
            txtNome.addActionListener((ActionEvent ae) -> {
                try {
                    String nomeSelecionado = txtNome.getSelectedItem().toString();
                    dCliente.setNome(nomeSelecionado);
                    telefones = dCliente.selectTel();
                    listTel = new ArrayList<>();
                    for (int i = 0; i < telefones.size(); i++) {
                        String tel = telefones.get(i).getTelefone();
                        listTel.add(tel);
                    }
                    telArray = listTel.toArray(new String[telefones.size()]);
                    for (String telArray1 : telArray) {
                        txtTelefone.setText(telArray1);
                    }
                    DaoClientes dCli = new DaoClientes();
                    listCod = new ArrayList<>();
                    dCli.setNome(txtNome.getSelectedItem().toString());
                    listCod = dCli.selectCliente();
                    ArrayList<String> arrCod = new ArrayList<>();
                    for (int i = 0; i < listCod.size(); i++) {
                        int cod = listCod.get(i).getIdCliente();
                        arrCod.add(Integer.toString(cod));
                    }
                    String codigo = String.join("", arrCod);
                    setPets(codigo);
                } catch (SQLException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
                }
            });
        } else {
            String[] noneCli = {"Cadastre um cliente primeiro"};
            txtNome = new JComboBox(noneCli);
            txtCod.setEnabled(false);
            txtNome.setEnabled(false);
            txtTelefone.setEnabled(false);
            setPets("x");
            txtPet.setEnabled(false);
            txtDia.setEnabled(false);
            txtHorario.setEnabled(false);
            txtServico.setEnabled(false);
            btnSalvar.setEnabled(false);
            btnDeletar.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Não há clientes cadastrados\nCadastre um novo cliente para agendar uma nova consulta", "Atenção", JOptionPane.ERROR_MESSAGE);
        }
        lblNome = new JLabel("Nome:", SwingConstants.RIGHT);
        lblNome.setBounds(25, 235, widthLabel, height);
        txtNome.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));
        txtNome.setBounds(100, 235, widthInput, height);
        txtNome.setSelectedIndex(0);

        lblPet = new JLabel("Pet:", SwingConstants.RIGHT);
        lblPet.setBounds(25, 270, widthLabel, height);

        if (haveConsulta) {
            try {
                String dataSelecionada = diaConsulta + " " + horaConsulta;
                DaoConsultas dc = new DaoConsultas();
                list = dc.pesquisar(dataSelecionada, dataSelecionada, dc);
                for (int i = 0; i < list.size(); i++) {
                    int cod = list.get(i).getIdConsulta();
                    String nome = list.get(i).getNome();
                    String pet = list.get(i).getPet();
                    String telefone = list.get(i).getTelefone();
                    String servico = list.get(i).getServico();
                    Date dData = list.get(i).getDataConsulta();
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    String sData = df.format(dData);
                    String[] dataSeparada = sData.split(" ");
                    String dia = dataSeparada[0];
                    String hora = dataSeparada[1];

                    for (int j = 0; j < cliArray.length; j++) {
                        if (nome.equals(cliArray[j])) {
                            txtNome.setSelectedIndex(j);
                        }
                    }

                    for (int j = 0; j < petArray.length; j++) {
                        if (pet.equals(petArray[j])) {
                            txtPet.setSelectedIndex(j);
                        }
                    }

                    for (int j = 0; j < cmbServicos.length; j++) {
                        if (servico.equals(cmbServicos[j])) {
                            txtServico.setSelectedIndex(j);
                        }
                    }

                    for (int j = 0; j < cmbHorarios.length; j++) {
                        if (hora.equals(cmbHorarios[j])) {
                            txtHorario.setSelectedIndex(j);
                        }
                    }

                    txtCod.setText(Integer.toString(cod));
                    txtTelefone.setText(telefone);
                    txtDia.setText(dia);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if (!diaConsulta.equals("") || !horaConsulta.equals("")) {
                String data = diaConsulta.replace("-", "/");
                String dia = data.substring(8, 10);
                String mes = data.substring(5, 7);
                String ano = data.substring(0, 4);
                String sData = dia + "/" + mes + "/" + ano;

                horaConsulta = horaConsulta.substring(0, 5);

                for (int j = 0; j < cmbHorarios.length; j++) {
                    if (horaConsulta.equals(cmbHorarios[j])) {
                        txtHorario.setSelectedIndex(j);
                    }
                }

                txtDia.setText(sData);
            }
        }

        setTable();
        createTable();
        createFieldSearch();

        pnlConsulta.add(btnSearch);
        pnlConsulta.add(txtSearch);
        pnlConsulta.add(lblCod);
        pnlConsulta.add(txtCod);
        pnlConsulta.add(lblNome);
        pnlConsulta.add(txtNome);
        pnlConsulta.add(lblTelefone);
        pnlConsulta.add(txtTelefone);
        pnlConsulta.add(lblPet);
        pnlConsulta.add(lblServico);
        pnlConsulta.add(txtServico);
        pnlConsulta.add(lblDia);
        pnlConsulta.add(txtDia);
        pnlConsulta.add(lblHorario);
        pnlConsulta.add(txtHorario);
        pnlConsulta.add(btnSalvar);
        pnlConsulta.add(btnDeletar);
        pnlConsulta.add(scroll);

        pnlConsulta.setSize(730, 600);
        pnlConsulta.setLayout(null);
        pnlConsulta.setVisible(true);
        return pnlConsulta;
    }

    public static void setTable() throws SQLException, ClassNotFoundException, ParseException {
        String[] columnNames = {"ID", "Nome", "Pet", "Telefone", "Serviço", "Data/Horário"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(table.getColumnModel().getColumn(0).getPreferredWidth() - 200);
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
                    String pet = ((String) model.getValueAt(row, 2));
                    String telefone = ((String) model.getValueAt(row, 3));
                    String servico = ((String) model.getValueAt(row, 4));
                    String dataConsulta = ((String) model.getValueAt(row, 5));
                    String dia = dataConsulta.substring(0, 10);
                    String hora = dataConsulta.substring(11, 16);

                    txtCod.setText(Integer.toString(cod));
                    txtTelefone.setText(telefone);
                    txtDia.setText(dia);

                    for (int j = 0; j < cliArray.length; j++) {
                        if (nome.equals(cliArray[j])) {
                            txtNome.setSelectedIndex(j);
                        }
                    }

                    for (int j = 0; j < petArray.length; j++) {
                        if (pet.equals(petArray[j])) {
                            txtPet.setSelectedIndex(j);
                        }
                    }

                    for (int j = 0; j < cmbServicos.length; j++) {
                        if (servico.equals(cmbServicos[j])) {
                            txtServico.setSelectedIndex(j);
                        }
                    }

                    for (int j = 0; j < cmbHorarios.length; j++) {
                        if (hora.equals(cmbHorarios[j])) {
                            txtHorario.setSelectedIndex(j);
                        }
                    }

                    btnDeletar.setEnabled(true);
                    haveConsulta = true;
                }
            }
        });

        scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(100, 410, 550, 150);
        scroll.setOpaque(false);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        DaoConsultas dc = new DaoConsultas();
        list = dc.fetch();
        for (int i = 0; i < list.size(); i++) {
            int cod = list.get(i).getIdConsulta();
            String nome = list.get(i).getNome();
            String pet = list.get(i).getPet();
            String telefone = mfTel.valueToString(list.get(i).getTelefone());
            String servico = list.get(i).getServico();
            String dataConsulta = (sdf.format(list.get(i).getDataConsulta()));
            model.addRow(new Object[]{cod, nome, pet, telefone, servico, dataConsulta});
        }
    }

    public static void createTable() {
        try {
            DaoConsultas dc = new DaoConsultas();
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

    public static void reloadTable(ArrayList<DaoConsultas> list) throws ParseException {
        cleanTable();
        table.setEnabled(true);
        scroll.setEnabled(true);
        if (list.isEmpty()) {
            table.setEnabled(false);
            scroll.setEnabled(false);
        } else {
            DefaultTableModel dadosConsulta = (DefaultTableModel) table.getModel();
            String[] Linha = new String[]{"", "", "", "", ""};
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            int posicao = -1;
            for (DaoConsultas daoConsultas : list) {
                posicao++;
                dadosConsulta.addRow(Linha);
                dadosConsulta.setValueAt(daoConsultas.getIdConsulta(), posicao, 0);
                dadosConsulta.setValueAt(daoConsultas.getNome(), posicao, 1);
                dadosConsulta.setValueAt(daoConsultas.getPet(), posicao, 2);
                dadosConsulta.setValueAt(mfTel.valueToString(daoConsultas.getTelefone()), posicao, 3);
                dadosConsulta.setValueAt(daoConsultas.getServico(), posicao, 4);
                dadosConsulta.setValueAt(sdf.format(daoConsultas.getDataConsulta().getTime()), posicao, 5);
            }
        }
    }

    public static void setPets(String cod) throws SQLException, ClassNotFoundException {
        if (cod.equals("x")) {
            String[] noPets = {"Pets não encontados"};
            txtPet = new JComboBox(noPets);
            txtPet.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));
            txtPet.setBounds(100, 270, widthInput, height);
            txtPet.setEnabled(true);
            pnlConsulta.add(txtPet);
        } else {
            DaoAnimalEstimacao dp = new DaoAnimalEstimacao();
            dp.setIdCliente(Integer.parseInt(cod));
            pets = dp.selectPet();

            listPet = new ArrayList<>();
            for (int i = 0; i < pets.size(); i++) {
                String nome = pets.get(i).getNome();
                listPet.add(nome);
            }

            petArray = listPet.toArray(new String[pets.size()]);

            if (petArray.length > 0) {
                if (txtPet != null) {
                    txtPet.removeAllItems();
                    pnlConsulta.remove(txtPet);
                    txtPet = new JComboBox(petArray);
                    txtPet.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));
                    txtPet.setBounds(100, 270, widthInput, height);
                    txtPet.setEnabled(true);
                    pnlConsulta.add(txtPet);
                    for (int j = 0; j < petArray.length; j++) {
                        String pet = txtPet.getSelectedItem().toString();
                        if (pet.equals(petArray[j])) {
                            txtPet.setSelectedIndex(j);
                        }
                    }
                } else {
                    txtPet = new JComboBox(petArray);
                    txtPet.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));
                    txtPet.setBounds(100, 270, widthInput, height);
                    txtPet.setEnabled(true);
                    pnlConsulta.add(txtPet);
                }
            } else {
                if (txtPet != null) {
                    txtPet.removeAllItems();
                    pnlConsulta.remove(txtPet);
                    String[] nonePet = {"Nenhum pet encontrado"};
                    txtPet = new JComboBox(nonePet);
                    txtPet.setBounds(100, 270, widthInput, height);
                    txtPet.setEnabled(false);
                    pnlConsulta.add(txtPet);
                } else {
                    String[] nonePet = {"Nenhum pet encontrado"};
                    txtPet = new JComboBox(nonePet);
                    txtPet.setBounds(100, 270, widthInput, height);
                    txtPet.setEnabled(false);
                    pnlConsulta.add(txtPet);
                }
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
                        DaoConsultas dc = new DaoConsultas();
                        list = dc.fetch();
                        reloadTable(list);
                    } else {
                        DaoConsultas daoConsultas = new DaoConsultas();
                        daoConsultas.setNome(nomeClienteSearch);
                        clientePesquisado = daoConsultas.pesquisarCliente(daoConsultas);
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
        txtDia.setText("");
        txtHorario.setSelectedIndex(0);
        txtNome.setSelectedIndex(0);
        txtPet.setSelectedIndex(0);
        for (String telArray1 : telArray) {
            txtTelefone.setText(telArray1);
        }
    }
}
