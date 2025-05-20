/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.softguard.repository;

import com.softguard.model.Software;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SoftwareRepositorySQLite implements SoftwareRepository {

    private final Connection connection;

    public SoftwareRepositorySQLite(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Software software) {
        String sql = """
            INSERT OR REPLACE INTO softwares
              (codigoSerial, nome, versao, dataLicenca, validadeLicenca, loginLicenca, senhaLicenca)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;
        try (PreparedStatement p = connection.prepareStatement(sql)) {
            p.setString(1, software.getCodigoSerial());
            p.setString(2, software.getNome());
            p.setString(3, software.getVersao());
            p.setString(4, software.getDataLicenca().toString());
            p.setString(5, software.getValidadeLicenca().toString());
            p.setString(6, software.getLoginLicenca());
            p.setString(7, software.getSenhaLicenca());
            p.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro salvando software", e);
        }
    }

    @Override
    public Optional<Software> findBySerial(String serial) {
        String sql = "SELECT * FROM softwares WHERE codigoSerial = ?";
        try (PreparedStatement p = connection.prepareStatement(sql)) {
            p.setString(1, serial);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                return Optional.of(new Software(
                    rs.getString("nome"),
                    rs.getString("versao"),
                    LocalDate.parse(rs.getString("dataLicenca")),
                    LocalDate.parse(rs.getString("validadeLicenca")),
                    rs.getString("codigoSerial"),
                    rs.getString("loginLicenca"),
                    rs.getString("senhaLicenca")
                ));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Erro buscando software", e);
        }
    }

    @Override
    public List<Software> findAll() {
        List<Software> lista = new ArrayList<>();
        String sql = "SELECT * FROM softwares";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Software(
                    rs.getString("nome"),
                    rs.getString("versao"),
                    LocalDate.parse(rs.getString("dataLicenca")),
                    LocalDate.parse(rs.getString("validadeLicenca")),
                    rs.getString("codigoSerial"),
                    rs.getString("loginLicenca"),
                    rs.getString("senhaLicenca")
                ));
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro listando softwares", e);
        }
    }

    @Override
    public void deleteBySerial(String serial) {
        String sql = "DELETE FROM softwares WHERE codigoSerial = ?";
        try (PreparedStatement p = connection.prepareStatement(sql)) {
            p.setString(1, serial);
            p.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro removendo software", e);
        }
    }
}

