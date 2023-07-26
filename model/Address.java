package com.example.odontologo.model;

public class Address {
    private String street;
    private String number;
    private String locality;
    private String providence;

    public Address(String street,String number,String locality,String providence) {
        this.street = street;
        this.number = number;
        this.locality = locality;
        this.providence = providence;
    }
}
