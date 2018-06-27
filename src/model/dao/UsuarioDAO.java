package model.dao;

import connect.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {
   //Validação login
     public boolean checkLogin(String login, String senha) throws SQLException {
         Connection con = ConexaoBD.getConection();
         PreparedStatement stmt = null; //ele executa
         ResultSet rs = null; //Result set vai executar e retornar dados
         boolean check = false;
         
        try {
            stmt = con.prepareStatement("SELECT * FROM usuarioo WHERE login = ? and senha = ?");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            
            //então ele executa e manda o resultado para resultset
            rs = stmt.executeQuery();
            
            //Se encontrar alguma coisa, vai pegar o check e mandar como true.
            if (rs.next()) {
                check = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoSave.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexaoBD.closeConnection(con, stmt, rs);
        }
        // no final retorna a checagem
        return check;
    }
    
}
