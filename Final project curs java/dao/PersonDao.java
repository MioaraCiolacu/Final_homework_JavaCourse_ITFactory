package com.itfactory.sbdemo.dao;

import com.itfactory.sbdemo.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author droban
 * Aceasta clasa este desemnata strict pentru exchange-ul de date cu baza de date
 * Nu facem procesari / verificari ... etc. Incercam sa decuplam cat mai mult
 */
@Repository
public class PersonDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonDao.class);
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/itfactory_users";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "************";

    public boolean deletePerson(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            LOGGER.info("Connected to PostgreSQL database!");
            LOGGER.info("Prepare statement");

            PreparedStatement statement = connection.prepareStatement("DELETE FROM PERSON WHERE ID=?");
            statement.setInt(1, id);

            statement.execute();
            LOGGER.info("Delete executed successfully");
        } catch (SQLException e) {
            LOGGER.error("Connection failure", e);
        }
        return true;
    }

    public boolean updatePersonEmail(int id, String email) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            LOGGER.info("Connected to PostgreSQL database!");
            LOGGER.info("Prepare statement");

            PreparedStatement statement = connection.prepareStatement("UPDATE PERSON SET EMAIL=? WHERE ID=?");
            statement.setString(1, email);
            statement.setInt(2, id);

            statement.execute();
            LOGGER.info("Update executed successfully");
        } catch (SQLException e) {
            //e.printStackTrace();
            LOGGER.error("Connection failure", e);
        }
        return true;
    }

    public boolean updatePersonTelephone(int id, String newTelephone) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            LOGGER.info("Connected to PostgreSQL database!");
            LOGGER.info("Prepare statement");

            PreparedStatement statement = connection.prepareStatement("UPDATE PERSON SET telefon=? WHERE id=?");
            statement.setString(1, newTelephone);
            statement.setInt(2, id);

            statement.execute();
            LOGGER.info("Update executed successfully");
        } catch (SQLException e) {
            LOGGER.error("Connection failure", e);
        }
        return true;
    }

    public boolean insertPerson(Person person) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            LOGGER.info("Connected to PostgreSQL database!");
            LOGGER.info("Prepare statement");

            PreparedStatement statement = connection.prepareStatement("INSERT INTO PERSON VALUES(?,?,?,?)");
            statement.setInt(1, person.getId());
            statement.setString(2, person.getName());
            statement.setString(3, person.getEmail());
            statement.setInt(4, person.getJobId());

            statement.execute();
            LOGGER.info("Insert executed successfully");
        } catch (SQLException e) {
            LOGGER.error("Connection failure", e);
        }
        return true;
    }

    public List<Person> retrievePersons() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            LOGGER.info("Connected to PostgreSQL database!");
            LOGGER.info("Prepare statement");
            Statement statement = connection.createStatement();
            LOGGER.info("Executing query: SELECT * FROM person");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM person");

            List<Person> personList = new ArrayList<>();
            while (resultSet.next()) {
                personList.add(
                        new Person(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("email"),
                                resultSet.getInt("jobId")
                        )
                );
            }
            return personList;

        } catch (SQLException e) {
            LOGGER.error("Connection failure", e);
        }
        return null;
    }
}