/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.repository;

import com.softguard.model.Equipamento;

import java.sql.*;
import java.util.*;

public class EquipamentoRepositorySQLite implements EquipamentoRepository {

    private final Connection connection;

    public EquipamentoRepositorySQLite(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Equipamento equipamento) {
        String sql = """
            INSERT OR REPLACE INTO equipamentos 
              (numeroPatrimonio, nome, tipo, nomeUsuario)
            VALUES (?, ?, ?, ?)
        """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, equipamento.getNumeroPatrimonio());
            stmt.setString(2, equipamento.getNome());
            stmt.setString(3, equipamento.getTipo());
            stmt.setString(4, equipamento.getNomeUsuario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar equipamento: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Equipamento> findByPatrimonio(String patrimonio) {
        String sql = "SELECT * FROM equipamentos WHERE numeroPatrimonio = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, patrimonio);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Equipamento eq = new Equipamento(
                        rs.getString("nome"),
                        rs.getString("numeroPatrimonio"),
                        rs.getString("tipo"),
                        rs.getString("nomeUsuario")
                    );
                    return Optional.of(eq);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar equipamento: " + e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<Equipamento> findAll() {
        String sql = "SELECT * FROM equipamentos";
        List<Equipamento> lista = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Equipamento eq = new Equipamento(
                    rs.getString("nome"),
                    rs.getString("numeroPatrimonio"),
                    rs.getString("tipo"),
                    rs.getString("nomeUsuario")
                );
                lista.add(eq);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar equipamentos: " + e.getMessage(), e);
        }
        return lista;
    }

    @Override
    public void deleteByPatrimonio(String patrimonio) {
        String sql = "DELETE FROM equipamentos WHERE numeroPatrimonio = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, patrimonio);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir equipamento: " + e.getMessage(), e);
        }
    }

    @Override
    public void installSoftware(String patrimonio, String codigoSerial) {
        String sql = """
            INSERT OR IGNORE INTO equipamento_softwares
              (numeroPatrimonio, codigoSerial)
            VALUES (?, ?)
        """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, patrimonio);
            stmt.setString(2, codigoSerial);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao instalar software: " + e.getMessage(), e);
        }
    }

    @Override
    public void uninstallSoftware(String patrimonio, String codigoSerial) {
        String sql = """
            DELETE FROM equipamento_softwares
             WHERE numeroPatrimonio = ?
               AND codigoSerial     = ?
        """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, patrimonio);
            stmt.setString(2, codigoSerial);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao desinstalar software: " + e.getMessage(), e);
        }
    }

    @Override
    public List<String> findInstalledSerials(String patrimonio) {
        String sql = """
            SELECT codigoSerial
              FROM equipamento_softwares
             WHERE numeroPatrimonio = ?
        """;
        List<String> seriais = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, patrimonio);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    seriais.add(rs.getString("codigoSerial"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar softwares instalados: " + e.getMessage(), e);
        }
        return seriais;
    }
}

