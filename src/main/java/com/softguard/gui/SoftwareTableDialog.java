/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.gui;

import com.softguard.model.Software;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SoftwareTableDialog extends JDialog {
    public SoftwareTableDialog(Frame owner, List<Software> lista) {
        super(owner, "Lista de Softwares", true);

        String[] colunas = {
            "Nome", "Versão", "Data Licença",
            "Validade", "Serial", "Login", "Senha"
        };

        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            @Override public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        for (Software s : lista) {
            modelo.addRow(new Object[]{
                s.getNome(),
                s.getVersao(),
                s.getDataLicenca(),
                s.getValidadeLicenca(),
                s.getCodigoSerial(),
                s.getLoginLicenca(),
                s.getSenhaLicenca()  // exibe senha completa
            });
        }

        JTable tabela = new JTable(modelo);
        tabela.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(tabela);

        getContentPane().setLayout(new BorderLayout(5, 5));
        getContentPane().add(scroll, BorderLayout.CENTER);

        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(e -> dispose());
        JPanel sul = new JPanel();
        sul.add(btnOk);
        getContentPane().add(sul, BorderLayout.SOUTH);

        setSize(700, 300);
        setLocationRelativeTo(owner);
    }
}
