/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softguard.repository;

import com.softguard.model.Software;
import java.util.List;

public interface ISoftwareRepository {
    void salvar(Software software);
    List<Software> listar();
}
