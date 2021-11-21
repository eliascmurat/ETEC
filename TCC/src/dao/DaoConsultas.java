package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Consultas;

public class DaoConsultas extends Consultas {

    private Connection conexao;
    private PreparedStatement comandoSQL;
    private Date dataConsulta;

    @Override
    public void incluir() throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO tbl_consultas (nome, pet, telefone, servico, dataConsulta)"
                + "VALUES (?,?,?,?,?)";
        this.conexao = BancoSQL.getConnection();
        comandoSQL = conexao.prepareStatement(sql);
        comandoSQL.setString(1, this.getNome());
        comandoSQL.setString(2, this.getPet());
        comandoSQL.setString(3, this.getTelefone());
        SimpleDateFormat sdfConsulta = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dataConsulta = new Date(getDataConsulta().getTime());
        comandoSQL.setString(4, this.getServico());
        comandoSQL.setString(5, sdfConsulta.format(dataConsulta));
        comandoSQL.execute();
        comandoSQL.close();
        this.conexao.close();
    }

    @Override
    public void alterar() throws SQLException, ClassNotFoundException {
        String sql = "UPDATE tbl_consultas SET"
                + " nome = ?,"
                + " pet = ?,"
                + " telefone = ?,"
                + " servico = ?,"
                + " dataConsulta = ?"
                + " WHERE idConsulta = ?";
        this.conexao = BancoSQL.getConnection();
        comandoSQL = conexao.prepareStatement(sql);
        comandoSQL.setString(1, this.getNome());
        comandoSQL.setString(2, this.getPet());
        comandoSQL.setString(3, this.getTelefone());
        comandoSQL.setString(4, this.getServico());
        SimpleDateFormat sdfConsulta = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dataConsulta = new Date(getDataConsulta().getTime());
        comandoSQL.setString(5, sdfConsulta.format(dataConsulta));
        comandoSQL.setInt(6, this.getIdConsulta());
        comandoSQL.execute();
        comandoSQL.close();
        this.conexao.close();
    }

    @Override
    public boolean excluir() throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM tbl_consultas "
                + "WHERE idConsulta = ?";
        try {
            this.conexao = BancoSQL.getConnection();
            comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setInt(1, this.getIdConsulta());
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

    public ArrayList<DaoConsultas> fetch() throws SQLException, ClassNotFoundException {
        DaoConsultas consultasRetorno;
        String sql = "SELECT * FROM tbl_consultas ORDER BY dataConsulta DESC";

        ArrayList<DaoConsultas> listaConsultas = new ArrayList<>();

        this.conexao = BancoSQL.getConnection();
        comandoSQL = conexao.prepareStatement(sql);

        ResultSet rs = comandoSQL.executeQuery();
        while (rs.next()) {
            consultasRetorno = new DaoConsultas();
            consultasRetorno.setIdConsulta(rs.getInt("idConsulta"));
            consultasRetorno.setNome(rs.getString("nome"));
            consultasRetorno.setPet(rs.getString("pet"));
            consultasRetorno.setTelefone(rs.getString("telefone"));
            consultasRetorno.setServico(rs.getString("servico"));
            consultasRetorno.setDataConsulta(rs.getTimestamp("dataConsulta"));
            listaConsultas.add(consultasRetorno);
        }
        rs.close();
        comandoSQL.close();
        this.conexao.close();

        return listaConsultas;
    }

    public ArrayList<DaoConsultas> pesquisar(String inicioDoMes, String fimDoMes, DaoConsultas consultas) throws SQLException, ClassNotFoundException, ParseException {
        DaoConsultas consultasRetorno;
        String sql = "SELECT * FROM tbl_consultas WHERE dataConsulta BETWEEN CONVERT(datetime, ?, 121) AND CONVERT(datetime, ?, 121);";

        ArrayList<DaoConsultas> listaConsultas = new ArrayList<>();

        this.conexao = BancoSQL.getConnection();
        comandoSQL = conexao.prepareStatement(sql);

        comandoSQL.setTimestamp(1, java.sql.Timestamp.valueOf(inicioDoMes));
        comandoSQL.setTimestamp(2, java.sql.Timestamp.valueOf(fimDoMes));

        try (ResultSet rs = comandoSQL.executeQuery()) {
            while (rs.next()) {
                consultasRetorno = new DaoConsultas();
                consultasRetorno.setIdConsulta(rs.getInt("idConsulta"));
                consultasRetorno.setNome(rs.getString("nome"));
                consultasRetorno.setPet(rs.getString("pet"));
                consultasRetorno.setTelefone(rs.getString("telefone"));
                consultasRetorno.setServico(rs.getString("servico"));
                consultasRetorno.setDataConsulta(rs.getTimestamp("dataConsulta"));
                listaConsultas.add(consultasRetorno);
            }
        }

        comandoSQL.close();
        this.conexao.close();

        return listaConsultas;
    }
    
    public ArrayList<DaoConsultas> pesquisarCliente(DaoConsultas consultas) throws SQLException, ClassNotFoundException, ParseException {
        DaoConsultas consultasRetorno;
        String sql = "SELECT * FROM tbl_consultas WHERE nome LIKE ?;";

        ArrayList<DaoConsultas> listaConsultas = new ArrayList<>();

        this.conexao = BancoSQL.getConnection();
        comandoSQL = conexao.prepareStatement(sql);

        comandoSQL.setString(1, "%" + this.getNome() + "%");
        
        try (ResultSet rs = comandoSQL.executeQuery()) {
            while (rs.next()) {
                consultasRetorno = new DaoConsultas();
                consultasRetorno.setIdConsulta(rs.getInt("idConsulta"));
                consultasRetorno.setNome(rs.getString("nome"));
                consultasRetorno.setPet(rs.getString("pet"));
                consultasRetorno.setTelefone(rs.getString("telefone"));
                consultasRetorno.setServico(rs.getString("servico"));
                consultasRetorno.setDataConsulta(rs.getTimestamp("dataConsulta"));
                listaConsultas.add(consultasRetorno);
            }
        }

        comandoSQL.close();
        this.conexao.close();

        return listaConsultas;
    }
}
