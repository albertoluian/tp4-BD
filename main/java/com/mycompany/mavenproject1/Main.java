package com.mycompany.mavenproject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class Main {

    
  public static void main(String[] args) {
    DAO d = new DAO();
//    Categoria c = new Categoria("Carnes");
//    d.insertCategoria(c);
//    Produto p = new Produto("Picanha", 60.99, 2);
//    d.insertProduto(p);
//    p = new Produto("Maminha", 45.99, 2);
//    d.insertProduto(p);
//    p = new Produto("Alcatra", 34.99, 2);
//    d.insertProduto(p);
//    p = new Produto("Pernil", 30.99, 2);
//    d.insertProduto(p);
//    Produto p = d.selectProduto(1);
//    Categoria c = d.selectCategoria(2);
//    System.out.println(p+"\n");
//    System.out.println(c);
//    ArrayList<Produto> produtos = d.selectProdutosLike("P");
//    for (Produto produto: produtos){
//    System.out.println(produto+"\n");
//    c.setNome("Carness");
//    d.updateCategoria(c);
//    System.out.println(d.selectCategoria(c.getId()));

    ArrayList<Categoria> categorias = new ArrayList<>();
    categorias.add(new Categoria("Higiene"));
    categorias.add(new Categoria("Limpeza"));
    categorias.add(new Categoria("Eletronicos"));
    d.insertCategorias(categorias);
    categorias = d.selectCategoriasLike("");
for (Categoria categoria : categorias) {
    System.out.println(categoria + "\n");
}
    }
    
}
