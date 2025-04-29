/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.service;

/**
 *
 * @author User
 */

import com.softguard.model.Software;
import com.softguard.repository.SoftwareRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class SoftwareService {

    private final SoftwareRepository repo;

    public SoftwareService(SoftwareRepository repo) {
        this.repo = repo;
    }

    /**
     * Valida e salva um Software.
     * @throws IllegalArgumentException se validadeLicenca for antes de dataLicenca.
     */
    public void cadastrarSoftware(Software s) {
        if (s.getValidadeLicenca().isBefore(s.getDataLicenca())) {
            throw new IllegalArgumentException(
                "Data de validade não pode ser anterior à data de emissão."
            );
        }
        repo.save(s);
    }

    public Optional<Software> buscarPorSerial(String codigoSerial) {
        return repo.findBySerial(codigoSerial);
    }

    public List<Software> listarTodos() {
        return repo.findAll();
    }

    public void removerPorSerial(String codigoSerial) {
        repo.deleteBySerial(codigoSerial);
    }
}

