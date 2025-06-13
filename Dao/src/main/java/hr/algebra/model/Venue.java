/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

/**
 *
 * @author kaish
 * 
 * CREATE TABLE Venue (
    IDVenue INT IDENTITY PRIMARY KEY,
    Name NVARCHAR(300),
    Address NVARCHAR(300),
    PostCode NVARCHAR(20),
    Capacity INT
    );
 * 
 */
public class Venue implements Comparable<Venue>{
    
    private int id;
    private String name;
    private String address;
    private String postCode;
    private int capacity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    public Venue() {
    }
    
    public Venue(String name, String address, String postCode, int capacity) {
        this.name = name;
        this.address = address;
        this.postCode = postCode;
        this.capacity = capacity;
    }

    public Venue(int id, String name, String address, String postCode, int capacity) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postCode = postCode;
        this.capacity = capacity;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Venue other = (Venue) obj;
        return this.id == other.id;
    }

    @Override
    public int compareTo(Venue o) {
        return Integer.compare(this.id, o.getId());
    }
}
