package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoSQL {
    private static String servidor = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
    private static String urlBanco = "jdbc:sqlserver://localhost:50166;"; 
    private static String nomeBanco = "databaseName=db_deliciapet;";
    private static String usuario = "user=Admin;";
    private static String senha = "password=Admin123*";
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName(servidor);
        return DriverManager.getConnection(urlBanco + nomeBanco + usuario + senha);
    }
}
