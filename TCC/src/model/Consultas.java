package model;

import java.sql.SQLException;
import java.util.Date;

public class Consultas implements IMetodosPadrao{
    
    private int idConsulta;
    private String nome;
    private String pet;
    private String telefone;
    private String servico;
    private Date dataConsulta;
    
    public Consultas(){
        this(0, "", "", "", "",new Date());
    }

    public Consultas(int idConsulta, String nome, String pet, String telefone, String servico,Date dataConsulta) {
        this.idConsulta = idConsulta;
        this.nome = nome;
        this.pet = pet;
        this.telefone = telefone;
        this.servico = servico;
        this.dataConsulta = dataConsulta;
    }
    
    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
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
