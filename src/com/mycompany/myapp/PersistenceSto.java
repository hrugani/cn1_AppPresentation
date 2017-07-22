/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.io.Storage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author hrugani
 */
public class PersistenceSto implements PersistenceInterface {
    

    Storage sto;
    
    static private PersistenceSto p = new PersistenceSto();

    private PersistenceSto() {
        this.sto = Storage.getInstance();
    }
   
    public static PersistenceSto getInstance() {
        return p;
    }
    
    final private String CLIENT_KEY_PREFIX = "Client_"; 
    private String createKey(Client c) {
        return CLIENT_KEY_PREFIX + c.getId();
    }
    
    
    @Override
    public Collection<Client> getClients() {
        ArrayList<Client> clients = new ArrayList<>(); 
        for (String entry : sto.listEntries()) {
            if (entry.startsWith(CLIENT_KEY_PREFIX)) {
                clients.add((Client) sto.readObject(entry));
            }
        }
        Collections.sort(clients);
        return clients;
    }
    
    @Override
    public Client getClient(int id) {
        for (String entry : sto.listEntries()) {
            if (entry.startsWith(CLIENT_KEY_PREFIX)) {
                Client c = (Client) sto.readObject(entry);
                if (c.getId() == id) {
                    return c;
                }
            }
        }
        return null;
    }

    @Override
    public Client addClient(Client c) {
        boolean b = sto.writeObject(createKey(c), c);
        return (b)? c : null;
    }

    @Override
    public Client removeClient(Client c) {
        sto.deleteStorageFile(createKey(c));
        return c;
    }
    
    @Override
    public Client updateClient(Client c) {
        boolean b = sto.writeObject(createKey(c), c);
        return (b)? c : null;
    }

    @Override
    public int getNextId() {
        int max = 0;
        for (Client c : getClients()) {
            if (c.getId() > max) {
                max = c.getId();
            }
        }
        return max + 1;
    }
    
    
}
