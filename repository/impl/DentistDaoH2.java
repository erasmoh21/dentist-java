package com.example.odontologo.repository.impl;

import com.example.odontologo.model.Dentist;
import com.example.odontologo.repository.IDao;
import org.apache.log4j.Logger;
import org.h2.command.ddl.CreateSchema;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DentistDaoH2 implements IDao<Dentist> {
    private static final Logger logger = Logger.getLogger(DentistDaoH2.class);
    private static final String JDBC_DRIVER_H2 = "org.h2.Driver";
    private final static String USER = "sa";
    private final static String PASSWORD = "";
    private final static String URL = "jdbc:h2:~/test;INIT=RUNSCRIPT FROM 'create.sql'";

    @Override
    public Dentist save(Dentist data) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Dentist dentist = null;

        try {
            Class.forName(JDBC_DRIVER_H2);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO dentist (name,lastName,age,professionalCard) values(?,?,?,?)");

            preparedStatement.setString(1,data.getName());
            preparedStatement.setString(2,data.getLastName());
            preparedStatement.setInt(3,data.getAge());
            preparedStatement.setString(4,data.getProfessionalCard());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            logger.info("Se registro con exito el usuario");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
            preparedStatement = connection.prepareStatement("DELETE FROM dentist WHERE id = ?");

            preparedStatement.setLong(1,id);

           preparedStatement.executeUpdate();
           preparedStatement.close();
           logger.info("The dentist was deleted with sucess");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Dentist update(Dentist data) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(JDBC_DRIVER_H2);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE dentist SET name = ?,lastName = ?,age = ?,professionalCard = ? WHERE id = ?");

            preparedStatement.setString(1,data.getName());
            preparedStatement.setString(2,data.getLastName());
            preparedStatement.setInt(3,data.getAge());
            preparedStatement.setString(4,data.getProfessionalCard());
            preparedStatement.setLong(5,data.getId());

            preparedStatement.executeUpdate();
            preparedStatement.close();

            logger.info("The dentist was updated with sucess");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        return data;
    }

    @Override
    public Dentist read(long ID) {
        Connection connection = null;
        Statement statement = null;
        Dentist dentist = new Dentist("","",0,"");

        try {
            Class.forName(JDBC_DRIVER_H2);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT name,lastName,age,professionalCard FROM dentist WHERE id = "+ID);

            while(rs.next()) {
                dentist.setName(rs.getString("name"));
                dentist.setLastName(rs.getString("lastName"));
                dentist.setAge(rs.getInt("age"));
                dentist.setProfessionalCard(rs.getString("professionalCard"));
            }
            logger.info("The Dentist was found");
            statement.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }

        return dentist;

    }

    @Override
    public List<Dentist> showAll() {
        ArrayList<Dentist> arrayOfDentist = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(JDBC_DRIVER_H2);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM dentist");

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastName=rs.getString("lastName");
                int age = rs.getInt("age");
                String professionalCard = rs.getString("professionalCard");
                Dentist dentist = new Dentist(id,name,lastName,age,professionalCard);
                arrayOfDentist.add(dentist);
            }

            logger.info("The users were found with success");
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }

        return arrayOfDentist;
    }
}
