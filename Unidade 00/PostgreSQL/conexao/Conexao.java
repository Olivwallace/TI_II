package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
	
	private static final String DRIVE = "org.postgresql.Driver";
	private static final String SERVER_NAME = "localhost";
	private static final String SERVER_PORT = "5432";
	private static final String DATABASE = "produto";  
    private static final String URL = "jdbc:postgresql://" + SERVER_NAME + ":" + SERVER_PORT + "/" + DATABASE;
    private static final String USER = "postgres";
    private static final String PASS = "Oliv05";

    public static Connection getConnection(){
        Connection newConnection = null;
        
    	try {
            Class.forName(DRIVE);
            newConnection =  DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
        	System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
    	} catch (SQLException ex) {
    		System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + ex.getMessage());
        }
        
        return newConnection;
    }

    public static void closeConexao(Connection conexao) {
        try {
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConexao(Connection conexao, PreparedStatement statement) {
        if (conexao != null) {
            closeConexao(conexao);
        }

        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConexao(Connection conexao, PreparedStatement statement, ResultSet result) {

        closeConexao(conexao, statement);

        try {
            if (result != null) {
                result.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
