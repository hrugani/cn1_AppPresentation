/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.io.Externalizable;
import com.codename1.io.Util;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author hrugani
 */
public class Client implements Externalizable, Comparable<Client> {
    
    final static public String GEO_HOME = "-23.5362310,-46.6849740";
    final static public String GEO_WORK = "-23.5710660,-46.6393240";
    final static public String GEO_7COMM = "-23.5452890,-46.6369840";
    final static public String GEO_IBM = "-23.5806370,-46.6501660";
    
    private int id;
    private String name;
    private int age;
    private String geoLocation;    
    
    public Client() {
    }

    /**
     *
     * @param id
     * @param name
     * @param age
     * @param geoLocation
     */
    public Client(
            int id,
            String name,
            int age,
            String geoLocation) {
        
        this.id = id;
        this.name = name;
        this.age = age;
        this.geoLocation = geoLocation;
        
    }    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the geoLocation
     */
    public String getGeoLocation() {
        return geoLocation;
    }

    /**
     * @param geoLocation the geoLocation to set
     */
    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // BEGIN - Externalizable Interface Implementation
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public void externalize(DataOutputStream out) throws IOException {
        out.writeInt(getId());
        Util.writeUTF(getName(), out);
        out.writeInt(getAge());
        Util.writeUTF(getGeoLocation(), out);
    }

    @Override
    public void internalize(int version, DataInputStream in)
            throws IOException {
        setId(in.readInt());
        setName(Util.readUTF(in));
        setAge(in.readInt());
        setGeoLocation(Util.readUTF(in));
    }

    @Override
    public String getObjectId() {
        return "Client"; 
    }

    
    ////////////////////////////////////////////////////////////////////////////
    // END - Externalizable Interface Implementation
    ////////////////////////////////////////////////////////////////////////////

    @Override
    public int compareTo(Client o) {

        if (this.getId() < o.getId()) {
            return -1;
        }
        else if (this.getId() > o.getId()) {
            return 1;
        }
        else {
            return 0;
        }
    
    }

   
    
    
}
