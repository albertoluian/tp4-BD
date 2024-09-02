/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LUIAN
 */
public class DAO {
public ArrayList<Produto> selectProdutosLike(String pesquisa){
    ArrayList<Produto> produtos = new ArrayList<>();
    Connection conn = getConnect();
    try {
      ResultSet rs = conn.createStatement().executeQuery( "SELECT * from produtos where nome LIKE '"+"%"+pesquisa+"%'");
      while (rs.next()){
        Produto p = new Produto(rs.getString("nome"), rs.getDouble("preco"), rs.getInt("categoria"), rs.getInt("id"));
        produtos.add(p);
      }

        closeConnection(conn, rs, null);
      } catch(SQLException e){
      System.out.println(e.getMessage());
    }
    return produtos;
    }
public ArrayList<Categoria> selectCategoriasLike(String pesquisa){
    ArrayList<Categoria> categorias = new ArrayList<>();
    Connection conn = getConnect();
    try {
      ResultSet rs = conn.createStatement().executeQuery( "SELECT * from categorias where nome LIKE '"+"%"+pesquisa+"%'");
      while (rs.next()){
        Categoria c = new Categoria(rs.getString("nome"), rs.getInt("id"));
        categorias.add(c);
      }

        closeConnection(conn, rs, null);
      } catch(SQLException e){
      System.out.println(e.getMessage());
    }
    return categorias;
    }
public Produto selectProduto(int id){
    Produto p = null;
    Connection conn = getConnect();
    try {
      ResultSet rs = conn.createStatement().executeQuery( "SELECT * from produtos where id="+id);
      while (rs.next()){
        p = new Produto(rs.getString("nome"), rs.getDouble("preco"), rs.getInt("categoria"), rs.getInt("id"));
      }

        closeConnection(conn, rs, null);
      } catch(SQLException e){
      System.out.println(e.getMessage());
    }
    return p;
    }
public Categoria selectCategoria(int id) {
    Categoria c = null;
    Connection conn = getConnect();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        String sql = "SELECT * FROM categorias WHERE id = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        rs = stmt.executeQuery();

        if (rs.next()) {
            c = new Categoria(rs.getString("nome"), rs.getInt("id"));
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    } finally {
        closeConnection(conn, rs, stmt);
    }
    
    return c;
}
    public void deleteProduto(int id){
        Connection conn = getConnect();
    try {
        PreparedStatement stmt = conn.prepareStatement("delete from produtos where id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();

        closeConnection(conn, null, stmt);
      } catch(SQLException e){
      System.out.println(e.getMessage());   
    }
    }
    public void deleteCategoria(int id){
        Connection conn = getConnect();
    try {
        PreparedStatement stmt = conn.prepareStatement("delete from categorias where id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();

        closeConnection(conn, null, stmt);
      } catch(SQLException e){
      System.out.println(e.getMessage());   
    }
    }
    public void insertProdutos(ArrayList<Produto> produtos){
    for(Produto produto: produtos){
        insertProduto(produto);
    }
    }
    public void insertCategorias(ArrayList<Categoria> categorias){
    for(Categoria categoria: categorias){
        insertCategoria(categoria);
    }
    }
    public void updateProduto(Produto p){
    Connection conn = getConnect();
    try {
        PreparedStatement stmt = conn.prepareStatement("update produtos set nome=?, preco=?, categoria=? where id=?");
        stmt.setString(1, p.getNome());
        stmt.setDouble(2, p.getPreco());
        stmt.setInt(3, p.getCategoria());
        stmt.setInt(4, p.getId());
        stmt.executeUpdate();

        closeConnection(conn, null, stmt);
      } catch(SQLException e){
      System.out.println(e.getMessage());   
    }
    }
        public void insertProduto(Produto p){
    Connection conn = getConnect();
    try {
        PreparedStatement stmt = conn.prepareStatement("insert into produtos (nome, preco, categoria) values (?,?,?)");
        stmt.setString(1, p.getNome());
        stmt.setDouble(2, p.getPreco());
        stmt.setInt(3, p.getCategoria());
        stmt.executeUpdate();

      // SELECT no BD
//      ResultSet rs = conn.createStatement().executeQuery("SELECT P.id as idProduto, P.nome as nomeProduto, preco, C.nome as categoria from produtos as P Join categorias as C on P.categoria = C.id");
//      while (rs.next()){
//        System.out.println("id:  "+rs.getInt("idProduto") + "\nNome: " + rs.getString("nomeProduto") +"\ncategoria: "+rs.getString("categoria") +"\nPreco: "+rs.getDouble("preco")+"\n\n");
//      }
//      
        closeConnection(conn, null, stmt);
      } catch(SQLException e){
      System.out.println(e.getMessage());   
    }
    }
    public void updateCategoria(Categoria c){
    Connection conn = getConnect();
    try {
        PreparedStatement stmt = conn.prepareStatement("update categorias set nome=? where id=?");
        stmt.setString(1, c.getNome());
        stmt.setInt(2, c.getId());
        stmt.executeUpdate();

        closeConnection(conn, null, stmt);
      } catch(SQLException e){
      System.out.println(e.getMessage());   
    }
    }
     public void insertCategoria(Categoria c){
    Connection conn = getConnect();
    try {
        PreparedStatement stmt = conn.prepareStatement("insert into categorias (nome) values (?)");
        stmt.setString(1, c.getNome());
        stmt.executeUpdate();

      // SELECT no BD
//      ResultSet rs = stmt.executeQuery("SELECT id, nome from categorias");
//      while (rs.next()){
//        System.out.println("id:  "+rs.getInt("id") + " Nome: " + rs.getString("nome"));
//      }
//      
        closeConnection(conn, null, stmt);
      } catch(SQLException e){
      System.out.println(e.getMessage());   
    }
    }
    public static void closeConnection(Connection con, ResultSet rs, PreparedStatement stmt){
        try{
        if(con!=null){
            con.close();
        }
            if(rs!=null){
                rs.close();
            }
            if(stmt!=null){
                stmt.close();
            }
        }catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public Connection getConnect(){
    Connection conn = null;
    String url = "jdbc:sqlite:F:/Loja.db";
    try {
      //Class.forName("org.sqlite.JDBC");      
      conn = DriverManager.getConnection(url);
      return conn;
//      // Inserção no BD;
//      Statement statement = conn.createStatement();
//      statement.executeUpdate("insert into Aluno values (15,1,'Ana Beatriz')");      
//      // SELECT no BD
//      ResultSet rs = statement.executeQuery("SELECT * from Aluno");
//      while (rs.next()){
//        System.out.println("CPF:  "+rs.getInt("cpf") + " Matrícula: " + rs.getInt("matricula") +" Nome: "+rs.getString("nome"));
//      }
      
    } catch(SQLException e){
      System.out.println(e.getMessage());   
    }
        return null;
    }
}
