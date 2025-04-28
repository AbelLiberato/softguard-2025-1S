/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.model;

/**
 *
 * @author User
 */

public class Equipamento {
    private String nome;
    private String numeroPatrimonio;
    private String tipo;
    private String nomeUsuario;

    public Equipamento(String nome,
                       String numeroPatrimonio,
                       String tipo,
                       String nomeUsuario) {
        this.nome             = nome;
        this.numeroPatrimonio = numeroPatrimonio;
        this.tipo             = tipo;
        this.nomeUsuario      = nomeUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroPatrimonio() {
        return numeroPatrimonio;
    }

    public void setNumeroPatrimonio(String numeroPatrimonio) {
        this.numeroPatrimonio = numeroPatrimonio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    @Override
    public String toString() {
        return "Equipamento{" +
                "nome='" + nome + '\'' +
                ", numeroPatrimonio='" + numeroPatrimonio + '\'' +
                ", tipo='" + tipo + '\'' +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                '}';
    }
}


