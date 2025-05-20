/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.gui;

import com.softguard.model.Equipamento;
import com.softguard.service.EquipamentoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EquipamentoDialog extends JDialog {
    private final EquipamentoService service;
    private final JTextField tfNome = new JTextField(20);
    private final JTextField tfPatrimonio = new JTextField(20);
    private final JTextField tfTipo = new JTextField(20);
    private final JTextField tfUsuario = new JTextField(20);

    public EquipamentoDialog(Frame owner, EquipamentoService service) {
        super(owner, "Cadastrar Equipamento", true);
        this.service = service;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(5, 2, 5, 5));
        add(new JLabel("Nome:")); add(tfNome);
        add(new JLabel("Número de Patrimônio:")); add(tfPatrimonio);
        add(new JLabel("Tipo:")); add(tfTipo);
        add(new JLabel("Nome do Usuário:")); add(tfUsuario);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(this::onSalvar);
        add(btnSalvar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);

        pack();
        setLocationRelativeTo(getOwner());
    }

    private void onSalvar(ActionEvent e) {
        service.cadastrarEquipamento(
            new Equipamento(
                tfNome.getText(),
                tfPatrimonio.getText(),
                tfTipo.getText(),
                tfUsuario.getText()
            )
        );
        JOptionPane.showMessageDialog(this, "Equipamento cadastrado com sucesso!");
        dispose();
    }
}
