/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.gui;

import com.softguard.model.Software;
import com.softguard.service.SoftwareService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class SoftwareDialog extends JDialog {
    private final SoftwareService service;
    private final JTextField tfNome = new JTextField(20);
    private final JTextField tfVersao = new JTextField(20);
    private final JTextField tfDataLicenca = new JTextField(10);
    private final JTextField tfValidade = new JTextField(10);
    private final JTextField tfSerial = new JTextField(20);
    private final JTextField tfLogin = new JTextField(20);
    private final JPasswordField pfSenha = new JPasswordField(20);

    public SoftwareDialog(Frame owner, SoftwareService service) {
        super(owner, "Cadastrar Software", true);
        this.service = service;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(8, 2, 5, 5));
        add(new JLabel("Nome:")); add(tfNome);
        add(new JLabel("Versão:")); add(tfVersao);
        add(new JLabel("Data de Licença (YYYY-MM-DD):")); add(tfDataLicenca);
        add(new JLabel("Validade (YYYY-MM-DD):")); add(tfValidade);
        add(new JLabel("Código Serial:")); add(tfSerial);
        add(new JLabel("Login da Licença:")); add(tfLogin);
        add(new JLabel("Senha da Licença:")); add(pfSenha);

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
        try {
            var s = new Software(
                tfNome.getText(),
                tfVersao.getText(),
                LocalDate.parse(tfDataLicenca.getText()),
                LocalDate.parse(tfValidade.getText()),
                tfSerial.getText(),
                tfLogin.getText(),
                new String(pfSenha.getPassword())
            );
            service.cadastrarSoftware(s);
            JOptionPane.showMessageDialog(this, "Software cadastrado com sucesso!");
            dispose();
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
