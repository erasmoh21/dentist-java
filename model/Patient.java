package com.example.odontologo.model;
import java.time.LocalDate;
import java.util.Date;


public class Patient {
    private long id;
    private String name;
    private String lastName;
    private String email;
    private String dni;
    private LocalDate dateOfEntry;
    private Address address;

    public Patient(long id,String name,String lastName,String email,String dni,java.sql.Date dateOfEntry,Address address) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.dni = dni;
        this.dateOfEntry = dateOfEntry.toLocalDate();
        this.address = address;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String newLastName) {
        this.lastName = newLastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String newDni) {
        this.dni = newDni;
    }

    public LocalDate getDateOfEntry() {
        return this.dateOfEntry;
    }

    public void setDateOfEntry(java.sql.Date newDate) {
        this.dateOfEntry = newDate.toLocalDate();
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address newAddress) {
        this.address = newAddress;
    }
}
