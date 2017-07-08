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
 *
 * Interface that abstracts all interactions that The App needs
 * to execute its persistence behavior.
 *
 * It acts as a contract that all persistence classes must implements.
 *
 * This project offers 2 different persistence classes as example.
 * Memory persistence and local storage persistence.
 * This interface isolates persistences tasks from visual layer.
 * Using this approach we can change the persistence implemetation
 * without change anything  in visual layer.
 *
 * As you can see, this interface defines one method for each basic CRUD operation:
 * Create ( addClient), Read (getClients), Update (updateClient) and Delete (removeClient).
 *
 * One method "getNextId" was added only for delegating ID generation
 * for new objects. In this way the persistence layer can decide
 * to use a strategy that fits better in each case.  
 * 
 */
public interface PersistenceInterface {

    Client addClient(Client c);

    Collection<Client> getClients();

    Client removeClient(Client c);

    Client updateClient(Client c);
    
    int getNextId();
    
}
