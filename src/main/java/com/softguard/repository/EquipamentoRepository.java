/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.repository;

/**
 *
 * @author User
 */

import com.softguard.model.Equipamento;

import java.util.*;

/**
 * Repositório em memória para objetos Equipamento,
 * identificados pelo numeroPatrimonio.
 */
public class EquipamentoRepository {

    private final Map<String, Equipamento> storage = new HashMap<>();

    public void save(Equipamento equipamento) {
        storage.put(equipamento.getNumeroPatrimonio(), equipamento);
    }

    public List<Equipamento> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Equipamento> findByPatrimonio(String numeroPatrimonio) {
        return Optional.ofNullable(storage.get(numeroPatrimonio));
    }

    public void deleteByPatrimonio(String numeroPatrimonio) {
        storage.remove(numeroPatrimonio);
    }

    public void clear() {
        storage.clear();
    }
}
