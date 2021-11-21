package model;

import java.sql.SQLException;

public class AnimalEstimacao implements IMetodosPadrao {

    private int idAnimalEstimacao;
    private String nome;
    private int idCliente;

    public AnimalEstimacao(){
        this(0, "", 0);
    }
    
    public AnimalEstimacao(int idAnimalEstimacao, String nome, int idCliente) {
        this.idAnimalEstimacao = idAnimalEstimacao;
        this.nome = nome;
        this.idCliente = idCliente;
    }
    
    public int getIdAnimalEstimacao() {
        return idAnimalEstimacao;
    }

    public void setIdAnimalEstimacao(int idAnimalEstimacao) {
        this.idAnimalEstimacao = idAnimalEstimacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    @Override
    public void incluir() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
