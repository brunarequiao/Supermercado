package model.dao;

import connect.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Produto;

public class ProdutoSave {
    
    //Método create que vai inserir no banco de dados
    public void create(Produto p) throws SQLException {
      Connection con = ConexaoBD.getConection();
      PreparedStatement stmt = null;
      
      //Valores passados por parametro
        try {
            stmt = con.prepareStatement("INSERT INTO produto(descricao,qtd,preco) VALUES (?,?,?)");
            stmt.setString(1,p.getDescricao());
            stmt.setInt(2, p.getQntd());
            stmt.setFloat(3, p.getPreco());
            
            //Execução sql
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar" + ex);
        } finally {
            ConexaoBD.closeConnection(con, stmt);
        }
    } 
    
    public void update(Produto p) throws SQLException {
      Connection con = ConexaoBD.getConection();
      PreparedStatement stmt = null;
      
      //Valores passados por parametro
        try {
            stmt = con.prepareStatement("UPDATE produto SET descricao = ?,qtd = ?,preco = ? WHERE id = ?");
            stmt.setString(1,p.getDescricao());
            stmt.setInt(2, p.getQntd());
            stmt.setFloat(3, p.getPreco());
            stmt.setInt(4, p.getId());
            
            //Execução sql
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar" + ex);
        } finally {
            ConexaoBD.closeConnection(con, stmt);
        }
    } 
    public void delete(Produto p) throws SQLException {
      Connection con = ConexaoBD.getConection();
      PreparedStatement stmt = null;
      
      //Valores passados por parametro
        try {
            stmt = con.prepareStatement("DELETE FROM produto WHERE id = ?");
            stmt.setInt(1, p.getId());
            
            //Execução sql
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir" + ex);
        } finally {
            ConexaoBD.closeConnection(con, stmt);
        }
    } 
    
    //Método que vai fazer leitura do banco e mostrar no JTable
    //vai ser um retorno de tipo list pra retorna esses dados do banco e inserir no jtable
    public List<Produto> read() throws SQLException {
         Connection con = ConexaoBD.getConection();
         PreparedStatement stmt = null; //ele executa
         ResultSet rs = null; //Result set vai executar e retornar dados
         
         //Colocando os dados na lista através do resultSet
         List<Produto> produtos = new ArrayList<>();
         
        try {
            //Execução SQL
            stmt = con.prepareStatement("SELECT * FROM produto");
            rs = stmt.executeQuery();
            
            //obtendo valor de rs e colocando dentro dos objetos
            while (rs.next()) {                
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setDescricao(rs.getString("descricao"));
                p.setQntd(rs.getInt("qtd"));
                p.setPreco(rs.getFloat("preco"));
                
                produtos.add(p);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoSave.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexaoBD.closeConnection(con, stmt, rs);
        }
        
        return produtos;
    }
    
    
     public List<Produto> readForDesc(String desc) throws SQLException {
        Connection con = ConexaoBD.getConection();        
        PreparedStatement stmt = null;
        ResultSet rs = null;

          //Colocando os dados na lista através do resultSet
        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto WHERE descricao LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setQntd(rs.getInt("qtd"));
                produto.setPreco(rs.getFloat("preco"));
                produtos.add(produto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoSave.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexaoBD.closeConnection(con, stmt, rs);
        }

        return produtos;

    }
    
}
