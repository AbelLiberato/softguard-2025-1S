/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.repository;

import com.softguard.model.Equipamento;
import java.util.List;
import java.util.Optional;

public interface EquipamentoRepository {
    void save(Equipamento equipamento);
    Optional<Equipamento> findByPatrimonio(String patrimonio);
    List<Equipamento> findAll();
    void deleteByPatrimonio(String patrimonio);
}
