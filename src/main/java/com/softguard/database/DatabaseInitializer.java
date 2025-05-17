/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.database;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initialize() {
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {

            // Tabela de Equipamentos
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS equipamentos (
                    numeroPatrimonio TEXT PRIMARY KEY,
                    nome TEXT NOT NULL,
                    tipo TEXT,
                    usuarioResponsavel TEXT
                );
            """);

            // Tabela de Softwares
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS softwares (
                    codigoSerial TEXT PRIMARY KEY,
                    nome TEXT NOT NULL,
                    versao TEXT,
                    dataLicenca TEXT,
                    validadeLicenca TEXT,
                    loginLicenca TEXT,
                    senhaLicenca TEXT
                );
            """);

            // Tabela de Associação Equipamento ↔ Software
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS equipamento_softwares (
                    numeroPatrimonio TEXT NOT NULL,
                    codigoSerial TEXT NOT NULL,
                    PRIMARY KEY(numeroPatrimonio, codigoSerial),
                    FOREIGN KEY(numeroPatrimonio) REFERENCES equipamentos(numeroPatrimonio),
                    FOREIGN KEY(codigoSerial)     REFERENCES softwares(codigoSerial)
                );
            """);

        } catch (Exception e) {
            System.err.println("Erro ao inicializar o banco: " + e.getMessage());
        }
    }
}