package com.example.odontologo.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Dentist {
    private long id;
    private String name;
    private String lastName;
    private int age;
    private String professionalCard;

    public Dentist(@JsonProperty("id")long id,@JsonProperty("name")String name,@JsonProperty("lastName")String lastName,@JsonProperty("age")int age,@JsonProperty("professionalCard")String professionalCard) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.professionalCard = professionalCard;
    }
    public Dentist(String name, String lastName, int age, String professionalCard) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.professionalCard = professionalCard;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long newId) {
        this.id = newId;
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

    public int getAge() {
        return this.age;
    }

    public void setAge(int newAge) {
        this.age = newAge;
    }

    public String getProfessionalCard() {
        return this.professionalCard;
    }

    public void setProfessionalCard(String newProfessionalCard) {
        this.professionalCard = newProfessionalCard;
    }
}
