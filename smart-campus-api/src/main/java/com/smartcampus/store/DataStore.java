/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.store;
/**
 *
 * @author Shehan Joel
 */
import com.smartcampus.model.Room;

import java.util.HashMap;
import java.util.Map;

public class DataStore {

    public static final Map<String, Room> rooms = new HashMap<>();

    private DataStore() {
    }
}
