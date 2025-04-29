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
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SoftwareServiceTest {

    private SoftwareRepository repo;
    private SoftwareService service;

    @BeforeEach
    void setup() {
        repo = new SoftwareRepository();
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
        assertDoesNotThrow(() -> service.cadastrarSoftware(s));
        assertTrue(repo.findBySerial("XYZ-789").isPresent());
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
