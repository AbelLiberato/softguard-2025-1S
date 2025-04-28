/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.model;

/**
 *
 * @author User
 */

import java.time.LocalDate;

public class Software {
    private String nome;
    private String versao;
    private LocalDate dataLicenca;
    private LocalDate validadeLicenca;

    public Software(String nome, String versao, LocalDate dataLicenca, LocalDate validadeLicenca) {
        this.nome = nome;
        this.versao = versao;
        this.dataLicenca = dataLicenca;
        this.validadeLicenca = validadeLicenca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public LocalDate getDataLicenca() {
        return dataLicenca;
    }

    public void setDataLicenca(LocalDate dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public LocalDate getValidadeLicenca() {
        return validadeLicenca;
    }

    public void setValidadeLicenca(LocalDate validadeLicenca) {
        this.validadeLicenca = validadeLicenca;
    }

    @Override
    public String toString() {
        return "Software{" +
                "nome='" + nome + '\'' +
                ", versao='" + versao + '\'' +
                ", dataLicenca=" + dataLicenca +
                ", validadeLicenca=" + validadeLicenca +
                '}';
    }
}
