/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.softguard.service;

import com.softguard.model.Equipamento;
import com.softguard.model.Software;
import com.softguard.repository.EquipamentoRepository;
import com.softguard.repository.SoftwareRepository;

import java.util.*;

public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepo;
    private final SoftwareRepository softwareRepo;
    /** Map de patrimônio → lista de códigos seriais de Software */
    private final Map<String, List<String>> instalacoes = new HashMap<>();

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
        instalacoes.remove(numeroPatrimonio);
    }

    /** Associa um software a um equipamento */
    public void instalarSoftware(String patrimonio, String codigoSerial) {
        // valida existência
        Equipamento e = equipamentoRepo.findByPatrimonio(patrimonio)
            .orElseThrow(() -> new NoSuchElementException("Equipamento não encontrado"));
        Software s = softwareRepo.findBySerial(codigoSerial)
            .orElseThrow(() -> new NoSuchElementException("Software não encontrado"));

        instalacoes
            .computeIfAbsent(patrimonio, k -> new ArrayList<>())
            .add(codigoSerial);
    }

    /** Lista Softwares instalados em um equipamento */
    public List<Software> listarSoftwaresInstalados(String patrimonio) {
        List<String> seriais = instalacoes.getOrDefault(patrimonio, Collections.emptyList());
        List<Software> resultado = new ArrayList<>();
        for (String serial : seriais) {
            softwareRepo.findBySerial(serial).ifPresent(resultado::add);
        }
        return resultado;
    }
}

