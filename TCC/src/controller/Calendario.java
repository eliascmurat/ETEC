package controller;

import static controller.Cliente.widthInput;
import dao.DaoConsultas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class Calendario {

    public static int cDay;
    public static int cMonth;
    public static int cYear;
    public static int selectedYear;
    public static int selectedMonth;
    public static int selectedDay;
    public static int days;
    public static int startInWeek;
    public static int totalWeeks;
    public static int count;

    public static String[] months = {
        "JANEIRO",
        "FEVEREIRO",
        "MARÇO",
        "ABRIL",
        "MAIO",
        "JUNHO",
        "JULHO",
        "AGOSTO",
        "SETEMBRO",
        "OUTUBRO",
        "NOVEMBRO",
        "DEZEMBRO"
    };
    public static String[] hours = {
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

    public static JLabel lblDias;
    public static JLabel lblCurrentDate;

    public static JPanel pnlConsulta;
    public static JPanel pnlCalendar;
    public static JPanel pnlHorario;

    public static Border border;
    public static Border margin;

    public static ArrayList<Date> agenda;

    public static JPanel pnlRaiz;
    
    public static JPanel createScheduleCalendar() throws ParseException {
        count = 1;
        int top = 75;
        int bottom = 50;
        int marginValue = 10;

        int leftC = 25;
        int rightC = 0;
        int leftH = 5;
        int rightH = 5;

        margin = new EmptyBorder(marginValue, marginValue, marginValue, marginValue);

        pnlHorario = new JPanel();
        pnlHorario.setSize(150, 450);
        pnlHorario.setLocation(445, 150);

        pnlCalendar = new JPanel();
        pnlCalendar.setSize(420, 450);
        pnlCalendar.setLocation(25, 150);

        pnlHorario.setBorder(BorderFactory.createEmptyBorder(top, leftH, bottom, rightH));
        pnlCalendar.setBorder(BorderFactory.createEmptyBorder(top, leftC, bottom, rightC));

        Calendar calendar = new GregorianCalendar();
        Calendario.cDay = calendar.get(Calendar.DATE);
        Calendario.cMonth = calendar.get(Calendar.MONTH);
        Calendario.cYear = calendar.get(Calendar.YEAR);
        Calendario.selectedDay = calendar.get(Calendar.DAY_OF_MONTH);
        Calendario.selectedMonth = calendar.get(Calendar.MONTH);
        Calendario.selectedYear = calendar.get(Calendar.YEAR);

        setSchedule();

        renderBtn();
        pnlCalendar.add(renderSpace());
        renderDays();
        renderCalendar();

        String passaData = selectedYear + "-" + (selectedMonth + 1) + "-" + getDay(selectedDay);
        setHours(passaData);

        pnlHorario.setOpaque(false);
        pnlCalendar.setOpaque(false);

        pnlRaiz = new JPanel();
        pnlRaiz.setSize(730, 600);
        pnlRaiz.setLayout(null);
        pnlRaiz.setOpaque(false);
        pnlRaiz.add(pnlCalendar);
        pnlRaiz.add(pnlHorario);

        return pnlRaiz;
    }

    private static JLabel createLblTrace(int i) {
        String text = "-";
        String cor = "#212121";
        return createLabel(i, 50, 40, text, cor, false);
    }

    private static JLabel createLblCurrentDay(int i, boolean temAgenda) {
        String text = getDay(count - startInWeek + 1);
        if (temAgenda) {
            String cor = "#1920da";
            return createLabel(i, 50, 40, text, cor, true);
        } else {
            String cor = "#451eb7";
            return createLabel(i, 50, 40, text, cor, true);
        }
    }

    private static JLabel createLblDay(int i, boolean temAgenda) {
        String text = getDay(count - startInWeek + 1);
        if (temAgenda) {
            String cor = "#1920da";
            return createLabel(i, 50, 40, text, cor, false);
        } else {
            String cor = "#e6e6e6";
            return createLabel(i, 50, 40, text, cor, false);
        }
    }

    private static JLabel createLabel(int i, int w, int h, String text, String cor, boolean hoje) {
        JLabel lblDay = new JLabel();
        lblDay.setName("lbl" + Integer.toString(i));
        lblDay.setPreferredSize(new Dimension(w, h));
        lblDay.setText(text);
        lblDay.setBackground(Color.decode(cor));
        if (cor.equals("#1920da") || hoje) {
            lblDay.setForeground(Color.decode("#d2d2d2"));
        } else {
            if (!text.equals("-")) {
                lblDay.setForeground(Color.decode("#808080"));
            } else {
                lblDay.setForeground(Color.white);
            }
        }
        lblDay.setOpaque(true);
        lblDay.setHorizontalAlignment(SwingConstants.CENTER);
        lblDay.setVerticalAlignment(SwingConstants.CENTER);
        lblDay.getBorder();
        lblDay.setBorder(new CompoundBorder(border, margin));
        lblDay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                if (cor.equals("#1920da") || hoje) {
                    lblDay.setBackground(Color.decode("#451eb7"));
                    lblDay.setForeground(Color.white);
                } else if (!text.equals("-")) {
                    lblDay.setBackground(Color.white);
                    lblDay.setForeground(Color.decode("#808080"));
                } else if (text.equals("-")) {
                    lblDay.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDay.setBackground(Color.decode(cor));
                if (cor.equals("#1920da") || hoje) {
                    lblDay.setForeground(Color.decode("#d2d2d2"));
                } else if (!text.equals("-")) {
                    lblDay.setForeground(Color.decode("#808080"));
                } else if (text.equals("-")) {
                    lblDay.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (!text.equals("-")) {
                    reloadHours();
                    String data = text + " / " + months[selectedMonth] + " / " + selectedYear;
                    lblCurrentDate.setText(data);

                    String passaData = selectedYear + "-" + (selectedMonth + 1) + "-" + text;
                    setHours(passaData);
                }
            }
        });
        return lblDay;
    }

    private static void renderCalendar() {
        count = 1;
        int[] a = setSchedule();
        int dia = a[0];
        int inicia = a[1];
        int total = a[2];

        for (int i = 1; i <= total; i++) {
            for (int j = 1; j <= 7; j++) {
                if (count < inicia || (count - inicia + 1) > dia) {
                    pnlCalendar.add(createLblTrace(i));
                } else {
                    if (cDay == (count - inicia + 1) && cMonth == selectedMonth && cYear == selectedYear) {
                        try {
                            agenda = setAgendamentos();
                            pnlCalendar.add(createLblCurrentDay(i, verifyDay(agenda)));
                        } catch (ParseException | SQLException | ClassNotFoundException ex) {
                            JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        try {
                            agenda = setAgendamentos();
                            pnlCalendar.add(createLblDay(i, verifyDay(agenda)));
                        } catch (ParseException | SQLException | ClassNotFoundException ex) {
                            JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                count++;
            }
        }
    }

    private static JLabel renderSpace() {
        JLabel lblSpace = new JLabel();
        lblSpace.setPreferredSize(new Dimension(400, 10));
        lblSpace.setText("");
        return lblSpace;
    }

    private static void renderDays() {
        String[] dias = {"DOM", "SEG", "TER", "QUA", "QUI", "SEX", "SAB"};
        for (String dia : dias) {
            lblDias = new JLabel();
            lblDias.setPreferredSize(new Dimension(50, 25));
            lblDias.setForeground(Color.WHITE);
            lblDias.setBackground(Color.decode("#1952da"));
            lblDias.setOpaque(true);
            lblDias.setHorizontalAlignment(SwingConstants.CENTER);
            lblDias.setVerticalAlignment(SwingConstants.CENTER);
            lblDias.setText(dia);
            pnlCalendar.add(lblDias);
        }
    }

    private static void renderBtn() {
        lblCurrentDate = new JLabel();
        lblCurrentDate.setPreferredSize(new Dimension(270, 25));
        lblCurrentDate.setBackground(Color.decode("#e6e6e6"));
        lblCurrentDate.setForeground(Color.decode("#595959"));
        lblCurrentDate.setOpaque(true);
        lblCurrentDate.setHorizontalAlignment(SwingConstants.CENTER);
        lblCurrentDate.setVerticalAlignment(SwingConstants.CENTER);
        lblCurrentDate.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JButton btnPreMonth = new JButton("<");
        btnPreMonth.setBackground(Color.decode("#1952da"));
        btnPreMonth.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnPreMonth.setForeground(Color.WHITE);
        btnPreMonth.setBorder(null);
        btnPreMonth.setPreferredSize(new Dimension(50, 25));
        btnPreMonth.setVisible(true);
        btnPreMonth.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPreMonth.setBackground(Color.decode("#1920da"));
                btnPreMonth.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPreMonth.setBackground(Color.decode("#1952da"));
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedMonth--;
                if (selectedMonth < 0) {
                    selectedMonth = 11;
                    selectedYear--;
                }
                changeDays();
                String passaData = selectedYear + "-" + (selectedMonth + 1) + "-" + getDay(selectedDay);
                setHours(passaData);
            }
        });
        JButton btnProMonth = new JButton(">");
        btnProMonth.setBackground(Color.decode("#1952da"));
        btnProMonth.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnProMonth.setForeground(Color.WHITE);
        btnProMonth.setBorder(null);
        btnProMonth.setPreferredSize(new Dimension(50, 25));
        btnProMonth.setVisible(true);
        btnProMonth.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnProMonth.setBackground(Color.decode("#1920da"));
                btnProMonth.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnProMonth.setBackground(Color.decode("#1952da"));
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedMonth++;
                if (selectedMonth > 11) {
                    selectedMonth = 0;
                    selectedYear++;
                }
                changeDays();
                String passaData = selectedYear + "-" + (selectedMonth + 1) + "-" + getDay(selectedDay);
                setHours(passaData);
            }
        });
        pnlCalendar.add(btnPreMonth);
        pnlCalendar.add(changeMonth(lblCurrentDate, selectedDay, months[selectedMonth], selectedYear));
        pnlCalendar.add(btnProMonth);
    }

    private static void reloadDay() {
        pnlCalendar.removeAll();
        pnlCalendar.revalidate();
        pnlCalendar.repaint();
    }

    private static void reloadHours() {
        pnlHorario.removeAll();
        pnlHorario.revalidate();
        pnlHorario.repaint();
    }

    private static void reloadPnl() {
        pnlRaiz.removeAll();
        pnlRaiz.revalidate();
        pnlRaiz.repaint();
    }

    private static String getDay(int i) {
        String sDate = Integer.toString(i);
        if (sDate.length() == 1) {
            sDate = "0" + sDate;
            return sDate;
        }
        return sDate;
    }

    private static JLabel changeMonth(JLabel lblCurrentDate, int dia, String mes, int ano) {
        lblCurrentDate.setText(getDay(dia) + " / " + mes + " / " + ano);
        return lblCurrentDate;
    }

    private static void changeDays() {
        reloadDay();
        reloadHours();
        changeMonth(lblCurrentDate, selectedDay, months[selectedMonth], selectedYear);
        renderBtn();
        pnlCalendar.add(renderSpace());
        renderDays();
        renderCalendar();
    }

    public static void setHours(String data) {
        for (int i = 0; i < hours.length; i++) {
            try {
                String hora = hours[i] + ":00";
                agenda = setAgendamentos();
                JLabel lblAgenda = new JLabel();
                lblAgenda.setText(hours[i]);
                lblAgenda.setPreferredSize(new Dimension(100, 25));
                lblAgenda.setForeground(Color.decode("#595959"));
                lblAgenda.setOpaque(true);
                lblAgenda.setHorizontalAlignment(SwingConstants.CENTER);
                lblAgenda.setVerticalAlignment(SwingConstants.CENTER);
                lblAgenda.setFont(new Font("SansSerif", Font.PLAIN, 12));

                if (verifyHour(agenda, hora, data)) {
                    lblAgenda.setBackground(Color.decode("#1952da"));
                    lblAgenda.setForeground(Color.decode("#e6e6e6"));
                    lblAgenda.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                            lblAgenda.setBackground(Color.decode("#451eb7"));
                            lblAgenda.setForeground(Color.white);
                            lblAgenda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }

                        @Override
                        public void mouseExited(java.awt.event.MouseEvent evt) {
                            lblAgenda.setBackground(Color.decode("#1952da"));
                            lblAgenda.setForeground(Color.decode("#e6e6e6"));
                        }

                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            try {
                                reloadPnl();
                                String inicioDoMes = selectedYear + "-" + (selectedMonth + 1) + "-01" + " 08:30:00";
                                String fimDoMes = selectedYear + "-" + (selectedMonth + 1) + "-" + days + " 17:30:00";
                                pnlConsulta = Consulta.createNewConsulta(true, inicioDoMes, fimDoMes, data, hora);
                                pnlRaiz.add(pnlConsulta);
                            } catch (ParseException | SQLException | ClassNotFoundException ex) {
                                JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                } else {
                    lblAgenda.setBackground(Color.decode("#e6e6e6"));
                    lblAgenda.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                            lblAgenda.setBackground(Color.decode("#1952da"));
                            lblAgenda.setForeground(Color.decode("#e6e6e6"));
                            lblAgenda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }

                        @Override
                        public void mouseExited(java.awt.event.MouseEvent evt) {
                            lblAgenda.setBackground(Color.decode("#e6e6e6"));
                            lblAgenda.setForeground(Color.decode("#595959"));
                        }

                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            try {
                                reloadPnl();
                                String inicioDoMes = selectedYear + "-" + (selectedMonth + 1) + "-01" + " 08:30:00";
                                String fimDoMes = selectedYear + "-" + (selectedMonth + 1) + "-" + days + " 17:30:00";
                                pnlConsulta = Consulta.createNewConsulta(false, inicioDoMes, fimDoMes, data, hora);
                                pnlRaiz.add(pnlConsulta);
                            } catch (ParseException | SQLException | ClassNotFoundException ex) {
                                JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                }
                pnlHorario.add(lblAgenda);
            } catch (ParseException | SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static int[] setSchedule() {
        GregorianCalendar gCalendar = new GregorianCalendar(selectedYear, selectedMonth, 1);
        days = gCalendar.getActualMaximum(Calendar.DATE);
        startInWeek = gCalendar.get(Calendar.DAY_OF_WEEK);

        gCalendar = new GregorianCalendar(selectedYear, selectedMonth, days);
        totalWeeks = gCalendar.getActualMaximum(Calendar.WEEK_OF_MONTH);

        int[] infos = {days, startInWeek, totalWeeks};
        return infos;
    }

    public static ArrayList<Date> setAgendamentos() throws SQLException, ClassNotFoundException, ParseException {
        DaoConsultas dc = new DaoConsultas();

        String inicioDoMes = selectedYear + "-" + (selectedMonth + 1) + "-01" + " 08:30:00";
        String fimDoMes = selectedYear + "-" + (selectedMonth + 1) + "-" + days + " 17:30:00";

        ArrayList<DaoConsultas> list = dc.pesquisar(inicioDoMes, fimDoMes, dc);
        ArrayList<Date> listaAgendamento = new ArrayList<>();

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Date intermediario = list.get(i).getDataConsulta();
                listaAgendamento.add(intermediario);
            }
        }

        return listaAgendamento;
    }

    public static boolean verifyDay(ArrayList<Date> listaAgendamento) {
        for (int j = 0; j < agenda.size(); j++) {
            String data = selectedYear + "-" + (selectedMonth + 1) + "-" + getDay(count - startInWeek + 1);

            Date date = new Date();
            date.setTime(agenda.get(j).getTime());
            String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);

            if (data.equals(formattedDate)) {
                return true;
            }
        }
        return false;
    }

    public static boolean verifyHour(ArrayList<Date> listaAgendamento, String hour, String data) {
        for (int j = 0; j < agenda.size(); j++) {
            Date date = new Date();
            date.setTime(agenda.get(j).getTime());
            String formattedHour = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            if (formattedHour.equals(data + " " + hour)) {
                return true;
            }
        }
        return false;
    }
}
