package dao;

import model.Produto;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import app.MyIO;


public class ProdutoDAO {
    
    public void createProduto(Produto produto) {
        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;

        try {
            
            statement = conexao.prepareStatement("INSERT INTO produto(nome, marca, departamento, preco) VALUES (?, ?, ?, ?)");
            statement.setString(1, produto.getNome());
            statement.setString(2,produto.getMarca());
            statement.setString(3, produto.getDepartamento());
            statement.setFloat(4, (float)produto.getPreco());
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            MyIO.println("Erro ao Salvar Produto!");
        }finally{
            Conexao.closeConexao(conexao, statement);
        }
    } 
    
    public ArrayList<Produto> getAll() {
        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Produto> produtos = new ArrayList<>(); 

        try {
            statement = conexao.prepareStatement("SELECT * FROM produto");
            result = statement.executeQuery();

            while(result.next()){
                Produto novo = new Produto();

                novo.setIDProduto(Integer.parseInt(result.getString("id_Produto")));
                novo.setNome(result.getString("nome"));
                novo.setMarca(result.getString("marca"));
                novo.setDepartamento(result.getString("departamento"));
                novo.setPreco(result.getFloat("preco"));

                produtos.add(novo);
            }

        } catch (SQLException ex) {
        	MyIO.println("Erro ao Carregar Produto!");
        }finally{
            Conexao.closeConexao(conexao, statement, result);
        }

        return produtos;
    }
    
    public Produto getByID (int idProduto){
        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        Produto produto= null; 

        try {
            
            statement = conexao.prepareStatement("SELECT * FROM produto WHERE id_produto = ?");
            statement.setInt(1, idProduto);
            
            result = statement.executeQuery();

            while(result.next()){
                produto = new Produto();

                produto.setIDProduto(Integer.parseInt(result.getString("id_produto")));
                produto.setNome(result.getString("nome"));
                produto.setMarca(result.getString("marca"));
                produto.setDepartamento(result.getString("departamento"));
                produto.setPreco(result.getFloat("preco"));
                
            }

        } catch (SQLException ex) {
        	MyIO.println("Erro ao Carregar Produto!");
        }catch (RuntimeException ex){
        	MyIO.println("ID INVALIDO");
        }finally{
            Conexao.closeConexao(conexao, statement, result);
        }

        return produto;
    }
    
    public void deleteProduto(Produto p) {

         Connection conexao = Conexao.getConnection();
         PreparedStatement statement = null;

        try {
            statement = conexao.prepareStatement("DELETE FROM produto WHERE id_Produto = ?");
            statement.setInt(1, p.getIDProduto());

            statement.executeUpdate();

        } catch (SQLException ex) {
        	MyIO.println("Erro ao Delete Produto!");
        }catch (RuntimeException ex){
        	MyIO.println("PRODUTO NAO SELECIONADO");
        }finally {
            Conexao.closeConexao(conexao, statement);
        }

    }
    
    public void updateProduto(Produto p) {

        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;
        
       
        try {
            statement = conexao.prepareStatement("UPDATE produto SET nome = ?, marca = ?, departamento = ?, preco = ? WHERE id_produto = ?");
            statement.setString(1, p.getNome());
            statement.setString(2, p.getMarca());
            statement.setString(3, p.getDepartamento() );
            statement.setFloat(4, p.getPreco());
            statement.setInt(5, p.getIDProduto());
            statement.executeUpdate();

        } catch (SQLException ex) {
        	MyIO.println("Erro ao Atualizar Produto!");
        }catch (RuntimeException ex){
        	MyIO.println("PRODUTO NAO SELECIONADO");
        } finally {
            Conexao.closeConexao(conexao, statement);
        }

    }
	
}
