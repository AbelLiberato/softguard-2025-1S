/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.softguard.service;

import com.softguard.model.Equipamento;
import com.softguard.model.Software;
import com.softguard.repository.EquipamentoRepository;
import com.softguard.repository.SoftwareRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepo;
    private final SoftwareRepository softwareRepo;

    public EquipamentoService(EquipamentoRepository equipamentoRepo,
                               SoftwareRepository softwareRepo) {
        this.equipamentoRepo = equipamentoRepo;
        this.softwareRepo = softwareRepo;
    }

    public void cadastrarEquipamento(Equipamento e) {
        equipamentoRepo.save(e);
    }

    public Optional<Equipamento> buscarPorPatrimonio(String numeroPatrimonio) {
        return equipamentoRepo.findByPatrimonio(numeroPatrimonio);
    }

    public List<Equipamento> listarTodos() {
        return equipamentoRepo.findAll();
    }

    public void removerPorPatrimonio(String numeroPatrimonio) {
        equipamentoRepo.deleteByPatrimonio(numeroPatrimonio);
    }

    public void instalarSoftware(String patrimonio, String codigoSerial) {
        equipamentoRepo.findByPatrimonio(patrimonio)
                .orElseThrow(() -> new NoSuchElementException("Equipamento não encontrado"));
        softwareRepo.findBySerial(codigoSerial)
                .orElseThrow(() -> new NoSuchElementException("Software não encontrado"));

        equipamentoRepo.installSoftware(patrimonio, codigoSerial);
    }

    public void desinstalarSoftware(String patrimonio, String codigoSerial) {
        equipamentoRepo.findByPatrimonio(patrimonio)
                .orElseThrow(() -> new NoSuchElementException("Equipamento não encontrado"));
        softwareRepo.findBySerial(codigoSerial)
                .orElseThrow(() -> new NoSuchElementException("Software não encontrado"));

        equipamentoRepo.uninstallSoftware(patrimonio, codigoSerial);
    }

    public List<Software> listarSoftwaresInstalados(String patrimonio) {
        var seriais = equipamentoRepo.findInstalledSerials(patrimonio);
        return seriais.stream()
                .map(softwareRepo::findBySerial)
                .flatMap(Optional::stream)
                .toList();
    }
}


