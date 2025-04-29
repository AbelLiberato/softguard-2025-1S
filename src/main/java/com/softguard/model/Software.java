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
    private String codigoSerial;
    private String loginLicenca;
    private String senhaLicenca;

    public Software(String nome,
                    String versao,
                    LocalDate dataLicenca,
                    LocalDate validadeLicenca,
                    String codigoSerial,
                    String loginLicenca,
                    String senhaLicenca) {
        this.nome             = nome;
        this.versao           = versao;
        this.dataLicenca      = dataLicenca;
        this.validadeLicenca  = validadeLicenca;
        this.codigoSerial     = codigoSerial;
        this.loginLicenca     = loginLicenca;
        this.senhaLicenca     = senhaLicenca;
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

    public String getCodigoSerial() {
        return codigoSerial;
    }

    public void setCodigoSerial(String codigoSerial) {
        this.codigoSerial = codigoSerial;
    }

    public String getLoginLicenca() {
        return loginLicenca;
    }

    public void setLoginLicenca(String loginLicenca) {
        this.loginLicenca = loginLicenca;
    }

    public String getSenhaLicenca() {
        return senhaLicenca;
    }

    public void setSenhaLicenca(String senhaLicenca) {
        this.senhaLicenca = senhaLicenca;
    }

    @Override
    public String toString() {
        return "Software{" +
                "nome='" + nome + '\'' +
                ", versao='" + versao + '\'' +
                ", dataLicenca=" + dataLicenca +
                ", validadeLicenca=" + validadeLicenca +
                ", codigoSerial='" + codigoSerial + '\'' +
                ", loginLicenca='" + loginLicenca + '\'' +
                ", senhaLicenca='[PROTEGIDO]'" +
                '}';
    }
}

