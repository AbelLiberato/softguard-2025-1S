/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.database;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initialize() {
        try (Connection conn = DatabaseConnection.connect(); Statement stmt = conn.createStatement()) {
            String createEquipamentos = """
                CREATE TABLE IF NOT EXISTS equipamentos (
                    id TEXT PRIMARY KEY,
                    nome TEXT,
                    tipo TEXT,
                    localizacao TEXT,
                    usuarioResponsavel TEXT
                );
            """;

            String createSoftwares = """
                CREATE TABLE IF NOT EXISTS softwares (
                    id TEXT PRIMARY KEY,
                    nome TEXT,
                    versao TEXT,
                    chaveLicenca TEXT,
                    equipamentoId TEXT,
                    FOREIGN KEY (equipamentoId) REFERENCES equipamentos(id)
                );
            """;

            stmt.execute(createEquipamentos);
            stmt.execute(createSoftwares);

        } catch (Exception e) {
            System.out.println("Erro ao inicializar o banco: " + e.getMessage());
        }
    }
}
