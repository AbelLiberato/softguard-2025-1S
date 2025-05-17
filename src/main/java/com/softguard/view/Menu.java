/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.view;

import com.softguard.model.Equipamento;
import com.softguard.model.Software;
import com.softguard.service.EquipamentoService;
import com.softguard.service.SoftwareService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu {

    private final SoftwareService softwareService;
    private final EquipamentoService equipamentoService;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(SoftwareService softwareService, EquipamentoService equipamentoService) {
        this.softwareService = softwareService;
        this.equipamentoService = equipamentoService;
    }

    public void exibir() {
        int opc;
        do {
            System.out.println("\n=== SoftGuard ===");
            System.out.println("1. Cadastrar Software");
            System.out.println("2. Listar Softwares");
            System.out.println("3. Cadastrar Equipamento");
            System.out.println("4. Listar Equipamentos");
            System.out.println("5. Instalar Software em Equipamento");
            System.out.println("6. Listar Softwares Instalados");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opc = Integer.parseInt(scanner.nextLine());

            switch (opc) {
                case 1 -> cadastrarSoftware();
                case 2 -> listarSoftwares();
                case 3 -> cadastrarEquipamento();
                case 4 -> listarEquipamentos();
                case 5 -> instalarSoftwareEmEquipamento();
                case 6 -> listarSoftwaresPorEquipamento();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opc != 0);
    }

    private void cadastrarSoftware() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Versão: ");
        String versao = scanner.nextLine();
        System.out.print("Data de licença (YYYY-MM-DD): ");
        LocalDate dataLicenca = LocalDate.parse(scanner.nextLine());
        System.out.print("Validade da licença (YYYY-MM-DD): ");
        LocalDate validade = LocalDate.parse(scanner.nextLine());
        System.out.print("Código Serial: ");
        String serial = scanner.nextLine();
        System.out.print("Login da licença: ");
        String login = scanner.nextLine();
        System.out.print("Senha da licença: ");
        String senha = scanner.nextLine();

        softwareService.cadastrarSoftware(
            new Software(nome, versao, dataLicenca, validade, serial, login, senha)
        );
        System.out.println("Software cadastrado com sucesso!");
    }

    private void listarSoftwares() {
        List<Software> list = softwareService.listarTodos();
        if (list.isEmpty()) {
            System.out.println("Nenhum software cadastrado.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private void cadastrarEquipamento() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Número de Patrimônio: ");
        String patr = scanner.nextLine();
        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();
        System.out.print("Nome do usuário: ");
        String usuario = scanner.nextLine();

        equipamentoService.cadastrarEquipamento(
            new Equipamento(nome, patr, tipo, usuario)
        );
        System.out.println("Equipamento cadastrado com sucesso!");
    }

    private void listarEquipamentos() {
        List<Equipamento> list = equipamentoService.listarTodos();
        if (list.isEmpty()) {
            System.out.println("Nenhum equipamento cadastrado.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private void instalarSoftwareEmEquipamento() {
        System.out.print("Patrimônio do equipamento: ");
        String patr = scanner.nextLine();
        System.out.print("Código serial do software: ");
        String serial = scanner.nextLine();
        try {
            equipamentoService.instalarSoftware(patr, serial);
            System.out.println("Software instalado com sucesso!");
        } catch (NoSuchElementException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarSoftwaresPorEquipamento() {
        System.out.print("Patrimônio do equipamento: ");
        String patr = scanner.nextLine();
        List<Software> instalados = equipamentoService.listarSoftwaresInstalados(patr);
        if (instalados.isEmpty()) {
            System.out.println("Nenhum software instalado neste equipamento.");
        } else {
            instalados.forEach(System.out::println);
        }
    }
}