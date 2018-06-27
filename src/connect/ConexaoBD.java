package connect;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoBD {
    //Obter conexão
    public static Connection getConection() {
        //Caso não existir 'conexão' catch pega e exibe o erro
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3307/dbmercadinho","root","usbw");
            
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro ao conectar", ex);
        }
    }

    //fechando a conexão atráves de métodos sobrecarregados
 public static void closeConnection(Connection con) throws SQLException {
                if(con != null)
                    con.close();
        
    }
    public static void closeConnection(Connection con, PreparedStatement stmt) throws SQLException {
        closeConnection(con);
        try {
                if(stmt != null)
                    stmt.close();
                
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar", ex);
            }
        
    }
    
     public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) throws SQLException {
        closeConnection(con, stmt);
        try {
                if(rs != null)
                    rs.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar", ex);
            }
    }
    
}
