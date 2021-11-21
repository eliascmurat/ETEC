package view;

import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class FrmAbout extends javax.swing.JFrame {

    public FrmAbout() {
        initComponents();
        ImageIcon img = new ImageIcon("src/images/paw.png");
        this.setIconImage(img.getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlInfos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtInfos = new javax.swing.JTextArea();
        lblExitIcon = new javax.swing.JLabel();
        lblAboutFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(900, 600));
        setName("frmAbout"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(900, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlInfos.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setEnabled(false);
        jScrollPane1.setFocusable(false);
        jScrollPane1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jScrollPane1.setHorizontalScrollBar(null);

        txtInfos.setEditable(false);
        txtInfos.setColumns(20);
        txtInfos.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtInfos.setRows(5);
        txtInfos.setText("\t\n\n\tDesenvolvido por: Spinner Splane System, 3IIEM.\n\tCom apoio de: ETEC Fernando Prestes.\n\n\tEquipe:\n\t     Eduardo Guerra Sabino\n\t     Elias de Camargo Murat\n\t     Emanuella Dellape Xavier\n\t     Murillo Justino dos Santos\n\t     Renan Miranda Silva\n\n\tContato:\n\t     eduardo.sabino@etec.sp.gov.br\n\t     elias.murat@etec.sp.gov.br\n\t     emanuella.xavier01@etec.sp.gov.br\n\t     murillo.santos28@etec.sp.gov.br\n\t     renan.silva664@etec.sp.gov.br\n\n\t");
        txtInfos.setBorder(null);
        txtInfos.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(txtInfos);

        javax.swing.GroupLayout pnlInfosLayout = new javax.swing.GroupLayout(pnlInfos);
        pnlInfos.setLayout(pnlInfosLayout);
        pnlInfosLayout.setHorizontalGroup(
            pnlInfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfosLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        pnlInfosLayout.setVerticalGroup(
            pnlInfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        getContentPane().add(pnlInfos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 570, 440));

        lblExitIcon.setBackground(new java.awt.Color(255, 0, 0));
        lblExitIcon.setForeground(new java.awt.Color(255, 255, 255));
        lblExitIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExitIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        lblExitIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExitIconMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblExitIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblExitIconMouseExited(evt);
            }
        });
        getContentPane().add(lblExitIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, -1, -1));

        lblAboutFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login.png"))); // NOI18N
        getContentPane().add(lblAboutFundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblExitIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitIconMouseClicked
        if (JOptionPane.showConfirmDialog(
                this,
                "Deseja voltar para a tela de login ?", "Atenção",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            this.dispose();
            FrmLogin frmLogin = new FrmLogin();
            frmLogin.dispose();
            frmLogin.setSize(900, 600);
            frmLogin.setUndecorated(true);
            frmLogin.setVisible(true);
        }
    }//GEN-LAST:event_lblExitIconMouseClicked

    private void lblExitIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitIconMouseEntered
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblExitIconMouseEntered

    private void lblExitIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitIconMouseExited
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_lblExitIconMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmAbout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAbout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAbout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAbout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAbout().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAboutFundo;
    private javax.swing.JLabel lblExitIcon;
    private javax.swing.JPanel pnlInfos;
    private javax.swing.JTextArea txtInfos;
    // End of variables declaration//GEN-END:variables
}
