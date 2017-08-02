/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author hrugani
 */
public class PersistenceMem implements PersistenceInterface {
    
    HashMap<Integer,Client> map;
    
    public PersistenceMem() {        
        this.map = new HashMap<>();
        map.put(1, new Client(1,"Hélio", 56, Client.GEO_HOME));
        map.put(2, new Client(2,"Caio", 30, Client.GEO_WORK));
        map.put(3, new Client(3,"Barbara", 25, Client.GEO_7COMM));
        map.put(4, new Client(4,"Natalia", 28, Client.GEO_7COMM));
    }
   
    @Override
    public Collection<Client> getClients() {
        return map.values();
    }

    @Override
    public Client getClient(int id) {
        for (Client c : map.values()) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
    
    @Override
    public Client addClient(Client c) {
        return map.put(c.getId(), c);
    }

    @Override
    public Client removeClient(Client c) {
        return map.remove(c.getId());
    }
    
    @Override
    public Client updateClient(Client c) {
        return map.put(c.getId(), c);
    }

    @Override
    public int getNextId() {
        int max = 0;
        for (int key : map.keySet()) {
            if (key > max) {
                max = key;
            }
        }
        return max+1;
    }

    
}
