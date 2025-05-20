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

@DisplayName("Testes de SoftwareService")
class SoftwareServiceTest {

    private SoftwareRepository repo;
    private SoftwareService service;

    @BeforeEach
    void setup() {
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
    @DisplayName("cadastrarSoftware: válido não lança e persiste")
    void cadastrarSoftwareValido() {
        var s = new Software(
            "AppX","2.0",
            LocalDate.of(2024,5,1),
            LocalDate.of(2024,6,1),
            "XYZ-789","user","pass"
        );
        // não lança
        assertDoesNotThrow(() -> service.cadastrarSoftware(s));
        // verifica persistência
        var found = repo.findBySerial("XYZ-789");
        assertTrue(found.isPresent());
        assertEquals("AppX", found.get().getNome());
    }

    @Test
    @DisplayName("cadastrarSoftware: validade antes da emissão lança")
    void cadastrarSoftwareInvalido() {
        var s = new Software(
            "AppX","2.0",
            LocalDate.of(2024,6,1),
            LocalDate.of(2024,5,1),
            "XYZ-789","user","pass"
        );
        var ex = assertThrows(IllegalArgumentException.class,
            () -> service.cadastrarSoftware(s));
        assertEquals(
            "Data de validade não pode ser anterior à data de emissão.",
            ex.getMessage()
        );
    }

    @Test
    @DisplayName("findAll e deleteBySerial")
    void listarERemover() {
        var s1 = new Software("A","1.0",
            LocalDate.now(), LocalDate.now().plusDays(1),
            "AA","u","p");
        var s2 = new Software("B","1.1",
            LocalDate.now(), LocalDate.now().plusDays(1),
            "BB","u","p");
        service.cadastrarSoftware(s1);
        service.cadastrarSoftware(s2);

        var all = service.listarTodos();
        assertEquals(2, all.size());

        // remove um
        service.removerPorSerial("AA");
        var allAfter = service.listarTodos();
        assertEquals(1, allAfter.size());
        assertEquals("BB", allAfter.get(0).getCodigoSerial());
    }

    @Test
    @DisplayName("buscarPorSerial retorna Optional correto")
    void buscarPorSerial() {
        var s = new Software("X","9.9",
            LocalDate.now(), LocalDate.now().plusDays(1),
            "ZZ","u","p");
        service.cadastrarSoftware(s);
        var opt = service.buscarPorSerial("ZZ");
        assertTrue(opt.isPresent());
        assertEquals("X", opt.get().getNome());

        assertTrue(service.buscarPorSerial("NAO_EXISTE").isEmpty());
    }
}
