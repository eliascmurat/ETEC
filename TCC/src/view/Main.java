package view;

public class Main {

    public static void main(String[] args) {
        FrmSplash frmSplash = new FrmSplash();
        FrmLogin frmLogin = new FrmLogin();
        frmLogin.dispose();
        frmLogin.setSize(450, 250);
        frmLogin.setUndecorated(true);
        frmLogin.setVisible(true);
    }

}
