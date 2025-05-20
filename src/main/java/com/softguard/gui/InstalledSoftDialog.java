/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.gui;

import com.softguard.model.Software;
import com.softguard.service.EquipamentoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class InstalledSoftDialog extends JDialog {
    private final EquipamentoService service;
    private final JComboBox<String> cbPatrimonio;
    private final DefaultTableModel model = new DefaultTableModel(
        new String[]{"Serial", "Nome", "Versão", "Login", "Senha"}, 0
    );

    public InstalledSoftDialog(Frame owner, EquipamentoService service) {
        super(owner, "Softwares Instalados", true);
        this.service = service;

        // popula combo com todos os patrimônios existentes
        List<String> patrList = service.listarTodos()
            .stream()
            .map(e -> e.getNumeroPatrimonio())
            .toList();
        cbPatrimonio = new JComboBox<>(patrList.toArray(new String[0]));

        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(5,5));

        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topo.add(new JLabel("Patrimônio:"));
        topo.add(cbPatrimonio);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> refreshTable());
        topo.add(btnBuscar);
        add(topo, BorderLayout.NORTH);

        JTable tabela = new JTable(model);
        tabela.setFillsViewportHeight(true);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(e -> dispose());
        JPanel sul = new JPanel();
        sul.add(btnOk);
        add(sul, BorderLayout.SOUTH);

        setSize(600, 350);
        setLocationRelativeTo(getOwner());
    }

    private void refreshTable() {
        model.setRowCount(0);
        String patr = (String) cbPatrimonio.getSelectedItem();
        if (patr == null) return;
        List<Software> lista = service.listarSoftwaresInstalados(patr);
        for (Software s : lista) {
            model.addRow(new Object[]{
                s.getCodigoSerial(),
                s.getNome(),
                s.getVersao(),
                s.getLoginLicenca(),
                s.getSenhaLicenca()
            });
        }
    }
}
