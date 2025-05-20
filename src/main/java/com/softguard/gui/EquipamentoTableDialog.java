/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.gui;

import com.softguard.model.Equipamento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EquipamentoTableDialog extends JDialog {
    public EquipamentoTableDialog(Frame owner, List<Equipamento> lista) {
        super(owner, "Lista de Equipamentos", true);

        // Definição das colunas
        String[] colunas = {
            "Patrimônio", "Nome", "Tipo", "Usuário"
        };

        // Modelo de tabela não editável
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            @Override public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        // Preenche as linhas
        for (Equipamento e : lista) {
            modelo.addRow(new Object[]{
                e.getNumeroPatrimonio(),
                e.getNome(),
                e.getTipo(),
                e.getNomeUsuario()
            });
        }

        JTable tabela = new JTable(modelo);
        tabela.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(tabela);

        // Layout
        getContentPane().setLayout(new BorderLayout(5,5));
        getContentPane().add(scroll, BorderLayout.CENTER);

        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(ae -> dispose());
        JPanel sul = new JPanel();
        sul.add(btnOk);
        getContentPane().add(sul, BorderLayout.SOUTH);

        setSize(600, 300);
        setLocationRelativeTo(owner);
    }
}