/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// src/main/java/com/softguard/gui/InstallDialog.java
package com.softguard.gui;

import com.softguard.service.EquipamentoService;
import com.softguard.service.SoftwareService;
import com.softguard.model.Software;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

public class InstallDialog extends JDialog {
    private final EquipamentoService service;
    private final JComboBox<String> cbSoftwares;
    private final JTextField tfPatrimonio = new JTextField(20);
    private final JButton btnInstalar   = new JButton("Instalar");
    private final JButton btnDesinstalar= new JButton("Desinstalar");

    public InstallDialog(Frame owner, EquipamentoService service, SoftwareService softService) {
        super(owner, "Instalar/Desinstalar Software", true);
        this.service = service;

        // ** AGORA usamos o SoftwareService para listar todos os softwares **
        List<String> todosSeriais = softService
            .listarTodos()
            .stream()
            .map(Software::getCodigoSerial)
            .collect(Collectors.toList());

        cbSoftwares = new JComboBox<>(todosSeriais.toArray(new String[0]));
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(3, 2, 5, 5));
        add(new JLabel("Número de Patrimônio:")); add(tfPatrimonio);
        add(new JLabel("Software (Serial):"));        add(cbSoftwares);

        btnInstalar.addActionListener(this::onInstalar);
        add(btnInstalar);

        btnDesinstalar.addActionListener(this::onDesinstalar);
        add(btnDesinstalar);

        pack();
        setLocationRelativeTo(getOwner());
    }

    private void onInstalar(ActionEvent e) {
        String patr  = tfPatrimonio.getText().trim();
        String serial= (String) cbSoftwares.getSelectedItem();
        try {
            service.instalarSoftware(patr, serial);
            JOptionPane.showMessageDialog(this, "Software instalado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Erro: " + ex.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
        dispose();
    }

    private void onDesinstalar(ActionEvent e) {
        String patr  = tfPatrimonio.getText().trim();
        String serial= (String) cbSoftwares.getSelectedItem();
        try {
            service.desinstalarSoftware(patr, serial);
            JOptionPane.showMessageDialog(this, "Software desinstalado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Erro: " + ex.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
        dispose();
    }
}

