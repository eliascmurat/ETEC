package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Clientes;

public class DaoClientes extends Clientes {

    private Connection conexao;
    private PreparedStatement comandoSQL;

    @Override
    public void incluir() throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO tbl_clientes (nome, telefone, endereco)"
                + "VALUES (?,?,?)";
        this.conexao = BancoSQL.getConnection();
        comandoSQL = conexao.prepareStatement(sql);
        comandoSQL.setString(1, this.getNome());
        comandoSQL.setString(2, this.getTelefone());
        comandoSQL.setString(3, this.getEndereco());
        comandoSQL.execute();
        comandoSQL.close();
        this.conexao.close();
    }

    @Override
    public void alterar() throws SQLException, ClassNotFoundException {
        String sql = "UPDATE tbl_clientes SET"
                + " nome = ?,"
                + " telefone = ?,"
                + " endereco = ?"
                + " WHERE idCliente = ?";
        this.conexao = BancoSQL.getConnection();
        comandoSQL = conexao.prepareStatement(sql);
        comandoSQL.setString(1, this.getNome());
        comandoSQL.setString(2, this.getTelefone());
        comandoSQL.setString(3, this.getEndereco());
        comandoSQL.setInt(4, this.getIdCliente());
        comandoSQL.execute();
        comandoSQL.close();
        this.conexao.close();
    }

    @Override
    public boolean excluir() throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM tbl_clientes "
                + "WHERE idCliente = ?";
        try {
            this.conexao = BancoSQL.getConnection();
            comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setInt(1, this.getIdCliente());
            boolean apagou = comandoSQL.execute();
            comandoSQL.close();
            this.conexao.close();
            return apagou;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex, "Error:", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public ArrayList<DaoClientes> selectNome() throws SQLException, ClassNotFoundException {
        DaoClientes clientesRetorno;
        ArrayList<DaoClientes> listaClientes = new ArrayList<>();

        String sql = "SELECT nome FROM tbl_clientes ORDER BY nome ASC";
        this.conexao = BancoSQL.getConnection();
        comandoSQL = conexao.prepareStatement(sql);

        ResultSet rs = comandoSQL.executeQuery();
        while (rs.next()) {
            clientesRetorno = new DaoClientes();
            clientesRetorno.setNome(rs.getString("nome"));
            listaClientes.add(clientesRetorno);
        }
        rs.close();
        comandoSQL.close();
        this.conexao.close();

        return listaClientes;
    }

    public ArrayList<DaoClientes> selectTel() throws SQLException, ClassNotFoundException {
        DaoClientes clientesRetorno;
        String sql = "SELECT telefone FROM tbl_clientes WHERE nome = ?";

        ArrayList<DaoClientes> listaClientes = new ArrayList<>();

        this.conexao = BancoSQL.getConnection();
        comandoSQL = conexao.prepareStatement(sql);
        comandoSQL.setString(1, this.getNome());

        ResultSet rs = comandoSQL.executeQuery();
        while (rs.next()) {
            clientesRetorno = new DaoClientes();
            clientesRetorno.setTelefone(rs.getString("telefone"));
            listaClientes.add(clientesRetorno);
        }
        rs.close();
        comandoSQL.close();
        this.conexao.close();

        return listaClientes;
    }

    public ArrayList<DaoClientes> selectCliente() throws SQLException, ClassNotFoundException {
        DaoClientes clientesRetorno;
        ArrayList<DaoClientes> listaClientes = new ArrayList<>();

        String sql = "SELECT idCliente FROM tbl_clientes WHERE nome = ?";
        
        this.conexao = BancoSQL.getConnection();
        comandoSQL = conexao.prepareStatement(sql);
        comandoSQL.setString(1, this.getNome());

        ResultSet rs = comandoSQL.executeQuery();
        while (rs.next()) {
            clientesRetorno = new DaoClientes();
            clientesRetorno.setIdCliente(rs.getInt("idCliente"));
            listaClientes.add(clientesRetorno);
        }
        rs.close();
        comandoSQL.close();
        this.conexao.close();

        return listaClientes;
    }

    public ArrayList<DaoClientes> fetch() throws SQLException, ClassNotFoundException {
        DaoClientes clientesRetorno;
        ArrayList<DaoClientes> listaClientes = new ArrayList<>();

        String sql = "SELECT * FROM tbl_clientes ORDER BY nome ASC";
        this.conexao = BancoSQL.getConnection();
        comandoSQL = conexao.prepareStatement(sql);

        ResultSet rs = comandoSQL.executeQuery();
        while (rs.next()) {
            clientesRetorno = new DaoClientes();
            clientesRetorno.setIdCliente(rs.getInt("idCliente"));
            clientesRetorno.setNome(rs.getString("nome"));
            clientesRetorno.setTelefone(rs.getString("telefone"));
            clientesRetorno.setEndereco(rs.getString("endereco"));
            listaClientes.add(clientesRetorno);
        }
        rs.close();
        comandoSQL.close();
        this.conexao.close();

        return listaClientes;
    }

    public ArrayList<DaoClientes> pesquisar(DaoClientes clientes) throws SQLException, ClassNotFoundException {
        DaoClientes clienteRetorno = null;
        String sql = "SELECT * FROM tbl_clientes WHERE nome LIKE ?";

        ArrayList<DaoClientes> listaClientes = new ArrayList<>();

        this.conexao = BancoSQL.getConnection();
        comandoSQL = conexao.prepareStatement(sql);
        comandoSQL.setString(1, "%" + getNome() + "%");

        try (ResultSet rs = comandoSQL.executeQuery()) {
            while (rs.next()) {
                clienteRetorno = new DaoClientes();
                clienteRetorno.setIdCliente(rs.getInt("idCliente"));
                clienteRetorno.setNome(rs.getString("nome"));
                clienteRetorno.setTelefone(rs.getString("telefone"));
                clienteRetorno.setEndereco(rs.getString("endereco"));

                listaClientes.add(clienteRetorno);
            }
        }
        comandoSQL.close();
        this.conexao.close();

        return listaClientes;
    }
}
