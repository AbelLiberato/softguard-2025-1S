/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.softguard.repository;

import com.softguard.model.Software;
import java.util.List;
import java.util.Optional;

public interface SoftwareRepository {
    void save(Software software);
    Optional<Software> findBySerial(String serial);
    List<Software> findAll();
    void deleteBySerial(String serial);
}
