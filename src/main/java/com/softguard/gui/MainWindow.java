/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.gui;

import com.softguard.service.EquipamentoService;
import com.softguard.service.SoftwareService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {
    private final SoftwareService servSoft;
    private final EquipamentoService servEquip;

    public MainWindow(SoftwareService ss, EquipamentoService es) {
        super("SoftGuard – Gestão de TI");
        this.servSoft     = ss;
        this.servEquip    = es;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);

        // Agora em 5 linhas x 2 colunas para comportar o novo botão
        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));

        painel.add(botao("Cadastrar Equipamento", this::onCadEquip));
        painel.add(botao("Listar Equipamentos",  this::onListEquip));
        painel.add(botao("Excluir Equipamento",  this::onDelEquip));

        painel.add(botao("Cadastrar Software",   this::onCadSoft));
        painel.add(botao("Listar Softwares",     this::onListSoft));
        painel.add(botao("Excluir Software",     this::onDelSoft));

        painel.add(botao("Instalar/Desinstalar", this::onManageInstall));
        painel.add(botao("Listar Instalados",    this::onListInstalled));

        getContentPane().add(painel, BorderLayout.CENTER);
    }

    // método auxiliar para criar botão
    private JButton botao(String texto, java.util.function.Consumer<ActionEvent> acao) {
        JButton b = new JButton(texto);
        b.addActionListener(acao::accept);
        return b;
    }

    private void onCadEquip(ActionEvent e) {
        new EquipamentoDialog(this, servEquip).setVisible(true);
    }

    private void onListEquip(ActionEvent e) {
        new EquipamentoTableDialog(this, servEquip.listarTodos()).setVisible(true);
    }

    private void onDelEquip(ActionEvent e) {
        new DeleteEquipDialog(this, servEquip).setVisible(true);
    }

    private void onCadSoft(ActionEvent e) {
        new SoftwareDialog(this, servSoft).setVisible(true);
    }

    private void onListSoft(ActionEvent e) {
        new SoftwareTableDialog(this, servSoft.listarTodos()).setVisible(true);
    }

    private void onDelSoft(ActionEvent e) {
        new DeleteSoftDialog(this, servSoft).setVisible(true);
    }

    private void onManageInstall(ActionEvent e) {
        new InstallDialog(this, servEquip, servSoft).setVisible(true);
    }

    private void onListInstalled(ActionEvent e) {
        new InstalledSoftDialog(this, servEquip).setVisible(true);
    }

    public static void main(String[] args) {
        // Nimbus L&F
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) { }

        // Init banco e serviços
        com.softguard.database.DatabaseInitializer.initialize();
        var repoSoft        = new com.softguard.repository.SoftwareRepositorySQLite();
        var repoEquip       = new com.softguard.repository.EquipamentoRepositorySQLite();
        var servSoftware    = new com.softguard.service.SoftwareService(repoSoft);
        var servEquipamento = new com.softguard.service.EquipamentoService(repoEquip, repoSoft);

        SwingUtilities.invokeLater(() ->
            new MainWindow(servSoftware, servEquipamento).setVisible(true)
        );
    }
}
