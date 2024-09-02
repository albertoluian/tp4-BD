/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author LUIAN
 */
public class Produto{


private int id;
private String nome;
private Double preco;
private int categoria;

    public Produto(String nome, Double preco, int categoria, int id){
        this.categoria = categoria;
        this.nome = nome;
        this.preco = preco;
        this.id = id;
}
    public Produto(String nome, Double preco, int categoria){
        this.categoria = categoria;
        this.nome = nome;
        this.preco = preco;
}
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
     @Override
    public String toString() {
        DAO d = new DAO();
        return "id=" + id +
               "\nnome=" + nome  +
               "\npreco=" + preco +
                "\ncategoria="+ d.selectCategoria(categoria).getNome();
    }
}
