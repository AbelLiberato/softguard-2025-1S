/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.repository;

/**
 *
 * @author User
 */

import com.softguard.model.Software;

import java.util.*;

/**
 * Repositório em memória para objetos Software,
 * identificados pelo códigoSerial.
 */
public class SoftwareRepository {

    // Mapa que simula o armazenamento pelo código serial
    private final Map<String, Software> storage = new HashMap<>();

    /** Salva ou atualiza um Software pelo seu códigoSerial */
    public void save(Software software) {
        storage.put(software.getCodigoSerial(), software);
    }

    /** Retorna todos os Software cadastrados */
    public List<Software> findAll() {
        return new ArrayList<>(storage.values());
    }

    /** Procura um Software pelo códigoSerial */
    public Optional<Software> findBySerial(String codigoSerial) {
        return Optional.ofNullable(storage.get(codigoSerial));
    }

    /** Remove um Software pelo códigoSerial */
    public void deleteBySerial(String codigoSerial) {
        storage.remove(codigoSerial);
    }

    /** Limpa todo o repositório (útil para testes) */
    public void clear() {
        storage.clear();
    }
}
