/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.gui;

import com.softguard.service.SoftwareService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DeleteSoftDialog extends JDialog {
    private final SoftwareService service;
    private final JTextField tfSerial = new JTextField(20);

    public DeleteSoftDialog(Frame owner, SoftwareService service) {
        super(owner, "Excluir Software", true);
        this.service = service;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(2, 2, 5, 5));
        add(new JLabel("Código Serial:")); add(tfSerial);
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(this::onExcluir);
        add(btnExcluir);
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);
        pack();
        setLocationRelativeTo(getOwner());
    }

    private void onExcluir(ActionEvent e) {
        service.removerPorSerial(tfSerial.getText());
        JOptionPane.showMessageDialog(this, "Software excluído!");
        dispose();
    }
}