package com.example.odontologo.repository.impl;

import com.example.odontologo.model.Address;
import com.example.odontologo.model.Patient;
import com.example.odontologo.repository.IDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientDaoH2 implements IDao<Patient> {
    private static final Logger logger = Logger.getLogger(PatientDaoH2.class);
    private static final String JDBC_DRIVER_H2 = "org.h2.Driver";
    private static final String URL = "jdbc:h2:~/test";
    private static final String USER ="sa";
    private static final String PASSWORD = "";

    @Override
    public Patient save(Patient data) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(JDBC_DRIVER_H2);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO patient name,lastName,email,dni,dateOfEntry,address values(?,?,?,?,?,?)");

            preparedStatement.setString(1,data.getName());
            preparedStatement.setString(2,data.getLastName());
            preparedStatement.setString(3,data.getEmail());
            preparedStatement.setString(4,data.getDni());
            preparedStatement.setDate(5,Date.valueOf(data.getDateOfEntry()));
            preparedStatement.setObject(6,data.getAddress());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            logger.info("The Patient was saved with success");
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return data;
    }

    @Override
    public long delete(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(JDBC_DRIVER_H2);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            preparedStatement = connection.prepareStatement("DELETE FROM patient WHERE id = ?");

            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            logger.info("The patient was deleted with success");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        return id;
    }

    @Override
    public Patient update(Patient data) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(JDBC_DRIVER_H2);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE patient set name = ?, lastName = ?, email = ?,dni = ?,dateOfEntry = ?,address = ? WHERE id = ?");

            preparedStatement.setString(1,data.getName());
            preparedStatement.setString(2,data.getLastName());
            preparedStatement.setString(3,data.getEmail());
            preparedStatement.setString(4,data.getDni());
            preparedStatement.setDate(5,Date.valueOf(data.getDateOfEntry()));
            preparedStatement.setObject(6,data.getAddress());

            preparedStatement.executeUpdate();
            preparedStatement.close();

            logger.info("The patient was updated with success");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }

        return data;
    }

    @Override
    public Patient read(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Patient patient = null;

        try {
            Class.forName(JDBC_DRIVER_H2);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM dentist WHERE id = ?");

            ResultSet rs = preparedStatement.executeQuery();

            patient.setName(rs.getString("name"));
            patient.setLastName(rs.getString("lastName"));
            patient.setEmail(rs.getString("email"));
            patient.setDni(rs.getString("dni"));
            patient.setDateOfEntry((Date) rs.getDate("dateOfEntry"));
            patient.setAddress((Address)rs.getObject("address"));

            preparedStatement.executeUpdate();
            preparedStatement.close();

            logger.info("The patient was found success");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        return patient;
    }

    @Override
    public List<Patient> showAll() {
        ArrayList<Patient> patients = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(JDBC_DRIVER_H2);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM patient");

            ResultSet rs = preparedStatement.executeQuery();

            while(!rs.next()) {
                Patient patient = null;
                patient.setName(rs.getString("name"));
                patient.setLastName(rs.getString("lastName"));
                patient.setEmail(rs.getString("email"));
                patient.setDni(rs.getString("dni"));
                patient.setDateOfEntry((Date) rs.getDate("dateOfEntry"));
                patient.setAddress((Address) rs.getObject("address"));
                patients.add(patient);
                preparedStatement.executeUpdate();
            }
            preparedStatement.close();
            logger.info("All patients were consulted with success");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        return patients;
    }
}
