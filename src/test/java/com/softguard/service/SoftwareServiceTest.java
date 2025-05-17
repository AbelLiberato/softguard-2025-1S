/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.service;

import com.softguard.model.Software;
import com.softguard.repository.SoftwareRepository;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SoftwareServiceTest {

    private SoftwareRepository repo;
    private SoftwareService service;

    @BeforeEach
    void setup() {
        // Stub em memória simples
        repo = new SoftwareRepository() {
            private final Map<String, Software> storage = new HashMap<>();

            @Override
            public void save(Software software) {
                storage.put(software.getCodigoSerial(), software);
            }

            @Override
            public Optional<Software> findBySerial(String serial) {
                return Optional.ofNullable(storage.get(serial));
            }

            @Override
            public List<Software> findAll() {
                return new ArrayList<>(storage.values());
            }

            @Override
            public void deleteBySerial(String serial) {
                storage.remove(serial);
            }
        };

        service = new SoftwareService(repo);
    }

    @Test
    @DisplayName("Cadastrar software com validade posterior à emissão")
    void cadastrarSoftwareValido() {
        Software s = new Software(
            "AppX", "2.0",
            LocalDate.of(2024,5,1),
            LocalDate.of(2024,6,1),
            "XYZ-789", "user", "pass"
        );

        // Não deve lançar
        assertDoesNotThrow(() -> service.cadastrarSoftware(s));

        // E agora o repo deve conter o software
        assertTrue(repo.findBySerial("XYZ-789").isPresent());
        assertEquals("AppX", repo.findBySerial("XYZ-789").get().getNome());
    }

    @Test
    @DisplayName("Cadastrar software com validade anterior lança exceção")
    void cadastrarSoftwareInvalido() {
        Software s = new Software(
            "AppX", "2.0",
            LocalDate.of(2024,6,1),
            LocalDate.of(2024,5,1),
            "XYZ-789", "user", "pass"
        );

        // Deve lançar IllegalArgumentException
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> service.cadastrarSoftware(s)
        );
        assertEquals(
            "Data de validade não pode ser anterior à data de emissão.",
            ex.getMessage()
        );
    }
}
