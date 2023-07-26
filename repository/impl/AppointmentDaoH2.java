package com.example.odontologo.repository.impl;


import com.example.odontologo.model.Appointment;
import com.example.odontologo.model.Dentist;
import com.example.odontologo.model.Patient;
import com.example.odontologo.repository.IDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AppointmentDaoH2 implements IDao<Appointment> {
    private static final Logger logger = Logger.getLogger(AppointmentDaoH2.class);
    private static final String JDBC_DRIVER_H2 = "org.h2.Driver";
    private static final String URL = "jdbc:h2:~/test";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    @Override
    public Appointment save(Appointment data) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(JDBC_DRIVER_H2);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO appointment dentist,patient values(?,?)");

            preparedStatement.setObject(1,data.getDentist());
            preparedStatement.setObject(2,data.getPatient());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            logger.info("The appointment was inserted with success");
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.warn(e.getMessage());
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
            preparedStatement = connection.prepareStatement("DELETE FROM appointment where id = ?");

            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            logger.info("The appointment with id "+id+" was deleted with success");
        } catch (ClassNotFoundException e) {
            logger.warn(e.getMessage());
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }

        return id;
    }

    @Override
    public Appointment update(Appointment data) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(JDBC_DRIVER_H2);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE appointment where id = ?");

            preparedStatement.setObject(1,data);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            logger.info("The appointment was updated with success");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }

        return data;
    }

    @Override
    public Appointment read(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Appointment appointment = null;

        try {
            Class.forName(JDBC_DRIVER_H2);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM appointment WHERE id = ?");

            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            appointment.setDentist((Dentist)rs.getObject("dentist"));
            appointment.setPatient((Patient) rs.getObject("patient"));

            preparedStatement.executeUpdate();
            preparedStatement.close();

            logger.info("The appointment with id "+id+" was found with success");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        return appointment;
    }

    @Override
    public List<Appointment> showAll() {
        ArrayList<Appointment> appointments = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(JDBC_DRIVER_H2);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM appointment");

            ResultSet rs = preparedStatement.executeQuery();

            while(!rs.next()) {
                Appointment appointment = null;
                appointment.setPatient((Patient) rs.getObject("patient"));
                appointment.setDentist((Dentist) rs.getObject("dentist"));
                appointments.add(appointment);
                preparedStatement.executeUpdate();
            }

            preparedStatement.close();
            logger.info("the list of the appointments were searched");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }

        return appointments;
    }
}
