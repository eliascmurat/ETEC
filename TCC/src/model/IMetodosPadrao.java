package model;

import java.sql.SQLException;

public interface IMetodosPadrao {
    public void incluir() throws SQLException, ClassNotFoundException;
    
    public void alterar() throws SQLException, ClassNotFoundException;
    
    public boolean excluir() throws SQLException, ClassNotFoundException;
}
