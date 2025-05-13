/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.view;

import com.softguard.model.Software;
import com.softguard.model.Equipamento;
import com.softguard.repository.SoftwareRepository;
import com.softguard.repository.EquipamentoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {

    private final SoftwareRepository softwareRepo;
    private final EquipamentoRepository equipamentoRepo;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(SoftwareRepository softwareRepo, EquipamentoRepository equipamentoRepo) {
        this.softwareRepo = softwareRepo;
        this.equipamentoRepo = equipamentoRepo;
    }

    public void exibir() {
        int opc;
        do {
            System.out.println("\n=== SoftGuard ===");
            System.out.println("1. Cadastrar Software");
            System.out.println("2. Listar Softwares");
            System.out.println("3. Cadastrar Equipamento");
            System.out.println("4. Listar Equipamentos");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opc = Integer.parseInt(scanner.nextLine());

            switch (opc) {
                case 1 -> cadastrarSoftware();
                case 2 -> listarSoftwares();
                case 3 -> cadastrarEquipamento();
                case 4 -> listarEquipamentos();
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

        Software s = new Software(nome, versao, dataLicenca, validade, serial, login, senha);
        softwareRepo.save(s);
        System.out.println("Software cadastrado com sucesso!");
    }

    private void listarSoftwares() {
        List<Software> list = softwareRepo.findAll();
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

        Equipamento e = new Equipamento(nome, patr, tipo, usuario);
        equipamentoRepo.save(e);
        System.out.println("Equipamento cadastrado com sucesso!");
    }

    private void listarEquipamentos() {
        List<Equipamento> list = equipamentoRepo.findAll();
        if (list.isEmpty()) {
            System.out.println("Nenhum equipamento cadastrado.");
        } else {
            list.forEach(System.out::println);
        }
    }
}
