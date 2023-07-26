package com.example.odontologo.model;

public class Appointment {
    private Dentist dentist;
    private Patient patient;

    public Appointment(Dentist dentist,Patient patient) {
        this.dentist = dentist;
        this.patient = patient;
    }

    public Dentist getDentist() {
        return this.dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
