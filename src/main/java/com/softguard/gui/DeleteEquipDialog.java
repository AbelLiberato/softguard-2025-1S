/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.gui;

import com.softguard.service.EquipamentoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DeleteEquipDialog extends JDialog {
    private final EquipamentoService service;
    private final JTextField tfPatrimonio = new JTextField(20);

    public DeleteEquipDialog(Frame owner, EquipamentoService service) {
        super(owner, "Excluir Equipamento", true);
        this.service = service;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(2, 2, 5, 5));
        add(new JLabel("Número de Patrimônio:")); add(tfPatrimonio);
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
        service.removerPorPatrimonio(tfPatrimonio.getText());
        JOptionPane.showMessageDialog(this, "Equipamento excluído!");
        dispose();
    }
}