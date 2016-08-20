/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import java.util.Collection;

/**
 *
 * @author hrugani
 */
public interface PersistenceInterface {

    Client addClient(Client c);

    Collection<Client> getClients();

    Client removeClient(Client c);

    Client updateClient(Client c);
    
    int getNextId();
    
}
