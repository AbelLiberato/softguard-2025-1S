/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseInitializer {
    private static Connection connection;

    public static void initialize() {
        try {
            connection = DatabaseConnection.connect();

            try (Statement stmt = connection.createStatement()) {
                // Tabela de Equipamentos
                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS equipamentos (
                        numeroPatrimonio TEXT PRIMARY KEY,
                        nome TEXT NOT NULL,
                        tipo TEXT NOT NULL,
                        nomeUsuario TEXT NOT NULL
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
                        codigoSerial     TEXT NOT NULL,
                        PRIMARY KEY(numeroPatrimonio, codigoSerial),
                        FOREIGN KEY(numeroPatrimonio) REFERENCES equipamentos(numeroPatrimonio),
                        FOREIGN KEY(codigoSerial)     REFERENCES softwares(codigoSerial)
                    );
                """);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao inicializar o banco: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}

