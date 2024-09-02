/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author LUIAN
 */
public class Categoria{


private int id;
private String nome;

    public Categoria(String nome, int id){
        this.nome = nome;
        this.id = id;
}
    public Categoria(String nome){
        this.nome = nome;
}
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    @Override
    public String toString() {
        return "id=" + id +
               "\nnome=" + nome;
    }
}
