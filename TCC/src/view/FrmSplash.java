package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class FrmSplash {

    private final int LARGURA_IMG = 450;
    private final int ALTURA_IMG = 250;
    private final int TEMPO_DE_SPLASH = 30;
    private final String CAMINHO_IMG = "/images/splash.png";

    public static JPanel pnlSplash;
    public static JLabel lblBackground;
    public static JProgressBar progressBar;

    public FrmSplash() {
        JFrame frmSplash = new JFrame();
        frmSplash.dispose();
        ImageIcon img = new ImageIcon("src/images/paw.png");
        frmSplash.setIconImage(img.getImage());
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frmSplash.setBounds(
                (dimension.width - LARGURA_IMG) / 2,
                (dimension.height - ALTURA_IMG) / 2,
                LARGURA_IMG,
                ALTURA_IMG
        );
        frmSplash.setUndecorated(true);

        lblBackground = new JLabel(
                "",
                new ImageIcon(getClass().getResource(CAMINHO_IMG)),
                SwingConstants.CENTER
        );
        lblBackground.setBounds(0, 0, 450, 250);

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setBorderPainted(false);
        progressBar.setForeground(Color.decode("#1952da"));
        progressBar.setBounds(100, 175, 250, 15);

        pnlSplash = new JPanel();
        pnlSplash.setLayout(null);
        pnlSplash.setSize(450, 250);
        pnlSplash.setLocation(0, 0);
        pnlSplash.setOpaque(false);

        pnlSplash.add(lblBackground);
        pnlSplash.add(progressBar);

        frmSplash.add(pnlSplash);
        frmSplash.setVisible(true);

        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(TEMPO_DE_SPLASH);
                progressBar.setValue(i);
            }
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
        }
        frmSplash.dispose();
    }
}
