package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.AnimalEstimacao;

public class DaoAnimalEstimacao extends AnimalEstimacao {

    private Connection conexao;
    private PreparedStatement comandoSQL;

    @Override
    public void incluir() throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO tbl_animalestimacao (nome, idCliente)"
                + "VALUES (?,?)";
        this.conexao = BancoSQL.getConnection();
        comandoSQL = conexao.prepareStatement(sql);
        comandoSQL.setString(1, this.getNome());
        comandoSQL.setInt(2, this.getIdCliente());
        comandoSQL.execute();
        comandoSQL.close();
        this.conexao.close();
    }

    @Override
    public void alterar() throws SQLException, ClassNotFoundException {
        String sql = "UPDATE tbl_animalestimacao SET"
                + "nome = ?,"
                + "idCliente = ?"
                + "WHERE idAnimalEstimacao = ?";
        this.conexao = BancoSQL.getConnection();
        comandoSQL = conexao.prepareStatement(sql);
        comandoSQL.setString(1, this.getNome());
        comandoSQL.setInt(2, this.getIdCliente());
        comandoSQL.setInt(3, this.getIdAnimalEstimacao());
        comandoSQL.execute();
        comandoSQL.close();
        this.conexao.close();
    }

    @Override
    public boolean excluir() throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM tbl_animalestimacao "
                + "WHERE idAnimalEstimacao = ?";
        try {
            this.conexao = BancoSQL.getConnection();
            comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setInt(1, this.getIdAnimalEstimacao());
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
    
    public ArrayList<DaoAnimalEstimacao> returnPetToDelete() throws SQLException, ClassNotFoundException {
        DaoAnimalEstimacao petsRetorno;
        String sql = "SELECT * FROM tbl_animalestimacao WHERE idCliente = ?";

        ArrayList<DaoAnimalEstimacao> listaPets = new ArrayList<>();

        this.conexao = BancoSQL.getConnection();
        comandoSQL = conexao.prepareStatement(sql);
        comandoSQL.setInt(1, this.getIdCliente());

        ResultSet rs = comandoSQL.executeQuery();
        while (rs.next()) {
            petsRetorno = new DaoAnimalEstimacao();
            petsRetorno.setIdAnimalEstimacao(rs.getInt("idAnimalEstimacao"));
            petsRetorno.setNome(rs.getString("nome"));
            petsRetorno.setIdCliente(rs.getInt("idCliente"));
            listaPets.add(petsRetorno);
        }
        rs.close();
        comandoSQL.close();
        this.conexao.close();

        return listaPets;
    }

    public ArrayList<DaoAnimalEstimacao> returnPet() throws SQLException, ClassNotFoundException {
        DaoAnimalEstimacao petsRetorno;
        String sql = "SELECT * FROM tbl_animalestimacao WHERE idCliente = ? AND nome = ?";

        ArrayList<DaoAnimalEstimacao> listaPets = new ArrayList<>();

        this.conexao = BancoSQL.getConnection();
        comandoSQL = conexao.prepareStatement(sql);
        comandoSQL.setInt(1, this.getIdCliente());
        comandoSQL.setString(2, this.getNome());

        ResultSet rs = comandoSQL.executeQuery();
        while (rs.next()) {
            petsRetorno = new DaoAnimalEstimacao();
            petsRetorno.setIdAnimalEstimacao(rs.getInt("idAnimalEstimacao"));
            petsRetorno.setNome(rs.getString("nome"));
            petsRetorno.setIdCliente(rs.getInt("idCliente"));
            listaPets.add(petsRetorno);
        }
        rs.close();
        comandoSQL.close();
        this.conexao.close();

        return listaPets;
    }
    
    public ArrayList<DaoAnimalEstimacao> selectPet() throws SQLException, ClassNotFoundException {
        DaoAnimalEstimacao petsRetorno;
        String sql = "SELECT nome FROM tbl_animalestimacao WHERE idCliente = ?";

        ArrayList<DaoAnimalEstimacao> listaPets = new ArrayList<>();

        this.conexao = BancoSQL.getConnection();
        comandoSQL = conexao.prepareStatement(sql);
        comandoSQL.setInt(1, this.getIdCliente());

        ResultSet rs = comandoSQL.executeQuery();
        while (rs.next()) {
            petsRetorno = new DaoAnimalEstimacao();
            petsRetorno.setNome(rs.getString("nome"));
            listaPets.add(petsRetorno);
        }
        rs.close();
        comandoSQL.close();
        this.conexao.close();

        return listaPets;
    }
}
