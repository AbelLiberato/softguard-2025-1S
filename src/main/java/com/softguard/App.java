/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.softguard;

import com.softguard.repository.SoftwareRepository;
import com.softguard.repository.EquipamentoRepository;
import com.softguard.view.Menu;

public class App {
    public static void main(String[] args) {
        SoftwareRepository softwareRepo = new SoftwareRepository();
        EquipamentoRepository equipamentoRepo = new EquipamentoRepository();
        Menu menu = new Menu(softwareRepo, equipamentoRepo);
        menu.exibir();
    }
}
