package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Funcionarios;

public class DaoFuncionario extends Funcionarios {

    private Connection conexao;
    private PreparedStatement comandoSQL;

    @Override
    public void incluir() throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO tbl_funcionarios (nome, senha, telefone, endereco)"
                + "VALUES (?,?,?,?)";
        this.conexao = BancoSQL.getConnection();
        comandoSQL = conexao.prepareStatement(sql);
        comandoSQL.setString(1, this.getNome());
        comandoSQL.setString(2, this.getSenha());
        comandoSQL.setString(3, this.getTelefone());
        comandoSQL.setString(4, this.getEndereco());
        comandoSQL.execute();
        comandoSQL.close();
        this.conexao.close();
    }

    @Override
    public void alterar() throws SQLException, ClassNotFoundException {
        String sql = "UPDATE tbl_funcionarios SET"
                + " nome = ?,"
                + " senha = ?,"
                + " telefone = ?,"
                + " endereco = ?"
                + " WHERE idFuncionario = ?";
        this.conexao = BancoSQL.getConnection();
        comandoSQL = conexao.prepareStatement(sql);
        comandoSQL.setString(1, this.getNome());
        comandoSQL.setString(2, this.getSenha());
        comandoSQL.setString(3, this.getTelefone());
        comandoSQL.setString(4, this.getEndereco());
        comandoSQL.setInt(5, this.getIdFuncionario());
        comandoSQL.execute();
        comandoSQL.close();
        this.conexao.close();
    }

    @Override
    public boolean excluir() throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM tbl_funcionarios "
                + "WHERE idFuncionario = ?";
        try {
            this.conexao = BancoSQL.getConnection();
            comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setInt(1, this.getIdFuncionario());
            boolean apagou = comandoSQL.execute();
            comandoSQL.close();
            this.conexao.close();
            return apagou;
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public ArrayList<DaoFuncionario> pesquisar(DaoFuncionario funcionario) throws SQLException, ClassNotFoundException {
        DaoFuncionario funcionarioRetorno = null;
        String sql = "SELECT * FROM tbl_funcionarios WHERE nome LIKE ?";

        ArrayList<DaoFuncionario> listaFuncionario = new ArrayList<>();

        this.conexao = BancoSQL.getConnection();
        comandoSQL = conexao.prepareStatement(sql);
        comandoSQL.setString(1, "%" + getNome() + "%");

        try (ResultSet rs = comandoSQL.executeQuery()) {
            while (rs.next()) {
                funcionarioRetorno = new DaoFuncionario();
                funcionarioRetorno.setIdFuncionario(rs.getInt("idFuncionario"));
                funcionarioRetorno.setNome(rs.getString("nome"));
                funcionarioRetorno.setTelefone(rs.getString("telefone"));
                funcionarioRetorno.setEndereco(rs.getString("endereco"));

                listaFuncionario.add(funcionarioRetorno);
            }
        }
        comandoSQL.close();
        this.conexao.close();

        return listaFuncionario;
    }

    public ArrayList<DaoFuncionario> fetch() throws SQLException, ClassNotFoundException {
        DaoFuncionario funcionarioRetorno;
        String sql = "SELECT * FROM tbl_funcionarios ORDER BY nome ASC";

        ArrayList<DaoFuncionario> listaFuncionario = new ArrayList<>();

        this.conexao = BancoSQL.getConnection();
        comandoSQL = conexao.prepareStatement(sql);

        try (ResultSet rs = comandoSQL.executeQuery()) {
            while (rs.next()) {
                funcionarioRetorno = new DaoFuncionario();
                funcionarioRetorno.setIdFuncionario(rs.getInt("idFuncionario"));
                funcionarioRetorno.setNome(rs.getString("nome"));
                funcionarioRetorno.setSenha(rs.getString("senha"));
                funcionarioRetorno.setTelefone(rs.getString("telefone"));
                funcionarioRetorno.setEndereco(rs.getString("endereco"));
                listaFuncionario.add(funcionarioRetorno);
            }
        }
        comandoSQL.close();
        this.conexao.close();

        return listaFuncionario;
    }

    public boolean login() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM tbl_funcionarios "
                + "WHERE nome = ? AND senha = ?";
        try {
            this.conexao = BancoSQL.getConnection();
            comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setString(1, this.getNome());
            comandoSQL.setString(2, this.getSenha());

            ResultSet rs = comandoSQL.executeQuery();
            if (rs.next()) {
                comandoSQL.close();
                return true;
            } else {
                comandoSQL.close();
                this.conexao.close();
                return false;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean backup() throws SQLException, ClassNotFoundException {
        String sql = "USE db_deliciapet; \n" 
                + "BACKUP DATABASE db_deliciapet "
                + "TO DISK = 'C:\\Program Files\\Microsoft SQL Server\\MSSQL15.SQLEXPRESS\\MSSQL\\Backup\\db_deliciapet.bak'";
        try {
            this.conexao = BancoSQL.getConnection();
            comandoSQL = conexao.prepareStatement(sql);
            boolean status = comandoSQL.execute();
            comandoSQL.close();
            this.conexao.close();
            return status;
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
}
