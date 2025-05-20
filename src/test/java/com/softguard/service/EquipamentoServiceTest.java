/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.service;

import com.softguard.model.Equipamento;
import com.softguard.model.Software;
import com.softguard.repository.EquipamentoRepository;
import com.softguard.repository.SoftwareRepository;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class EquipamentoServiceTest {

    private EquipamentoRepository equipamentoRepo;
    private SoftwareRepository softwareRepo;
    private EquipamentoService service;

    @BeforeEach
    void setup() {
        equipamentoRepo = new EquipamentoRepository() {
            private final Map<String, Equipamento> storage = new HashMap<>();
            private final Map<String, List<String>> installs = new HashMap<>();

            @Override
            public void save(Equipamento equipamento) {
                storage.put(equipamento.getNumeroPatrimonio(), equipamento);
            }

            @Override
            public Optional<Equipamento> findByPatrimonio(String patrimonio) {
                return Optional.ofNullable(storage.get(patrimonio));
            }

            @Override
            public List<Equipamento> findAll() {
                return new ArrayList<>(storage.values());
            }

            @Override
            public void deleteByPatrimonio(String patrimonio) {
                storage.remove(patrimonio);
                installs.remove(patrimonio);
            }

            @Override
            public void installSoftware(String patrimonio, String codigoSerial) {
                installs.computeIfAbsent(patrimonio, k -> new ArrayList<>()).add(codigoSerial);
            }

            @Override
            public void uninstallSoftware(String patrimonio, String codigoSerial) {
                installs.getOrDefault(patrimonio, new ArrayList<>()).remove(codigoSerial);
            }

            @Override
            public List<String> findInstalledSerials(String patrimonio) {
                return installs.getOrDefault(patrimonio, new ArrayList<>());
            }
        };

        softwareRepo = new SoftwareRepository() {
            private final Map<String, Software> softwares = new HashMap<>();

            @Override
            public void save(Software software) {
                softwares.put(software.getCodigoSerial(), software);
            }

            @Override
            public Optional<Software> findBySerial(String serial) {
                return Optional.ofNullable(softwares.get(serial));
            }

            @Override
            public List<Software> findAll() {
                return new ArrayList<>(softwares.values());
            }

            @Override
            public void deleteBySerial(String serial) {
                softwares.remove(serial);
            }
        };

        service = new EquipamentoService(equipamentoRepo, softwareRepo);
    }

    @Test
    void cadastrarEListar() {
        Equipamento e = new Equipamento("Notebook", "123", "Portátil", "João");
        service.cadastrarEquipamento(e);

        List<Equipamento> lista = service.listarTodos();
        assertEquals(1, lista.size());
        assertEquals("123", lista.get(0).getNumeroPatrimonio());
    }

    @Test
    void excluirEquipamento() {
        Equipamento e = new Equipamento("Monitor", "321", "Tela", "Maria");
        service.cadastrarEquipamento(e);

        service.removerPorPatrimonio("321");
        assertTrue(service.listarTodos().isEmpty());
    }

    @Test
    void instalarEDesinstalar() {
        Equipamento e = new Equipamento("Desktop", "555", "Fixo", "Carlos");
        Software s = new Software("Antivírus", "1.0", null, null, "S123", "admin", "senha");

        service.cadastrarEquipamento(e);
        softwareRepo.save(s);

        assertDoesNotThrow(() -> service.instalarSoftware("555", "S123"));
        assertEquals(1, service.listarSoftwaresInstalados("555").size());

        assertDoesNotThrow(() -> service.desinstalarSoftware("555", "S123"));
        assertEquals(0, service.listarSoftwaresInstalados("555").size());
    }
}



