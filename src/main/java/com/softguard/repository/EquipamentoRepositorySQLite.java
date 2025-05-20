/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.repository;

import com.softguard.model.Equipamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EquipamentoRepositorySQLite implements EquipamentoRepository {
    private final String url = "jdbc:sqlite:softguard.db";

    public EquipamentoRepositorySQLite() {
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // Tabelas já são criadas pelo DatabaseInitializer
        } catch (SQLException e) {
            throw new RuntimeException("Erro iniciando tabela equipamentos", e);
        }
    }

    @Override
    public void save(Equipamento equipamento) {
        String sql = """
            INSERT OR REPLACE INTO equipamentos
              (numeroPatrimonio, nome, tipo, usuarioResponsavel)
            VALUES (?, ?, ?, ?);
        """;
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setString(1, equipamento.getNumeroPatrimonio());
            p.setString(2, equipamento.getNome());
            p.setString(3, equipamento.getTipo());
            p.setString(4, equipamento.getNomeUsuario());
            p.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro salvando equipamento", e);
        }
    }

    @Override
    public Optional<Equipamento> findByPatrimonio(String patrimonio) {
        String sql = "SELECT * FROM equipamentos WHERE numeroPatrimonio = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setString(1, patrimonio);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                return Optional.of(new Equipamento(
                    rs.getString("nome"),
                    rs.getString("numeroPatrimonio"),
                    rs.getString("tipo"),
                    rs.getString("usuarioResponsavel")
                ));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Erro buscando equipamento", e);
        }
    }

    @Override
    public List<Equipamento> findAll() {
        List<Equipamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM equipamentos";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Equipamento(
                    rs.getString("nome"),
                    rs.getString("numeroPatrimonio"),
                    rs.getString("tipo"),
                    rs.getString("usuarioResponsavel")
                ));
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro listando equipamentos", e);
        }
    }

    @Override
    public void deleteByPatrimonio(String patrimonio) {
        String sql = "DELETE FROM equipamentos WHERE numeroPatrimonio = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setString(1, patrimonio);
            p.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro removendo equipamento", e);
        }
    }

    // Métodos para associação:

    public void installSoftware(String patrimonio, String codigoSerial) {
        String sql = """
          INSERT OR IGNORE INTO equipamento_softwares
            (numeroPatrimonio, codigoSerial)
          VALUES (?, ?);
        """;
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setString(1, patrimonio);
            p.setString(2, codigoSerial);
            p.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro instalando software", e);
        }
    }

    public void uninstallSoftware(String patrimonio, String codigoSerial) {
        String sql = """
          DELETE FROM equipamento_softwares
           WHERE numeroPatrimonio = ? AND codigoSerial = ?;
        """;
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setString(1, patrimonio);
            p.setString(2, codigoSerial);
            p.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro desinstalando software", e);
        }
    }

    public List<String> findInstalledSerials(String patrimonio) {
        List<String> seriais = new ArrayList<>();
        String sql = """
          SELECT codigoSerial
            FROM equipamento_softwares
           WHERE numeroPatrimonio = ?;
        """;
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setString(1, patrimonio);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                seriais.add(rs.getString("codigoSerial"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro lendo instalações", e);
        }
        return seriais;
    }
}

