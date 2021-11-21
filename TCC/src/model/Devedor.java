package model;

import java.sql.SQLException;
import java.util.Date;

public class Devedor implements IMetodosPadrao{
    
    private int idDevedor;
    private int idCliente;
    private Date dataPagamento;
    private double divida;

    public Devedor(){
        this(0, 0, new Date(), 0);
    }
    
    public Devedor(int idDevedor, int idCliente, Date dataPagamento, double divida) {
        this.idDevedor = idDevedor;
        this.idCliente = idCliente;
        this.dataPagamento = dataPagamento;
        this.divida = divida;
    }

    public double getDivida() {
        return divida;
    }

    public void setDivida(double divida) {
        this.divida = divida;
    }

    public int getIdDevedor() {
        return idDevedor;
    }

    public void setIdDevedor(int idDevedor) {
        this.idDevedor = idDevedor;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
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
