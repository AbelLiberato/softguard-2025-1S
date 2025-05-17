/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.softguard;

import com.softguard.database.DatabaseInitializer;
import com.softguard.repository.EquipamentoRepository;
import com.softguard.repository.SoftwareRepository;
import com.softguard.service.EquipamentoService;
import com.softguard.service.SoftwareService;
import com.softguard.view.Menu;

public class App {
    public static void main(String[] args) {
        System.out.println("Iniciando o sistema SoftGuard...");

        // Inicializa o banco de dados SQLite e cria as tabelas
        DatabaseInitializer.initialize();

        // Instancia os repositórios, serviços e exibe o menu
        SoftwareRepository sr = new SoftwareRepository();
        EquipamentoRepository er = new EquipamentoRepository();
        SoftwareService ss = new SoftwareService(sr);
        EquipamentoService es = new EquipamentoService(er, sr);
        Menu menu = new Menu(ss, es);
        menu.exibir();
    }
}

