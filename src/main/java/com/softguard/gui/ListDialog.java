/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ListDialog<T> extends JDialog {
    public ListDialog(Frame owner, String title, List<T> items) {
        super(owner, title, true);
        JTextArea ta = new JTextArea(15, 30);
        ta.setEditable(false);
        items.forEach(i -> ta.append(i.toString() + "\n"));
        add(new JScrollPane(ta), BorderLayout.CENTER);
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(e -> dispose());
        add(btnOk, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(owner);
    }
}