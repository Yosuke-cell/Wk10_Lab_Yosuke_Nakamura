package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmploymentDAO {

	/*
	 * for storing employment - preparing the insert method*/
	
	public int saveEmploymentFor(EmploymentForm employment) throws SQLException {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        connection = Database.getDBConnection();
	        connection.setAutoCommit(false);

	        String query = "INSERT INTO applicants(fullname, address, phone, email, education, gender, "
	                     + "date, position, salary, legal, relative, relative_detail) "
	                     + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";   

	        statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        int counter = 1;

	        // personal info
	        statement.setString(counter++, employment.getFullName());
	        statement.setString(counter++, employment.getaddress());
	        statement.setString(counter++, employment.getPhone());
	        statement.setString(counter++, employment.getEmail());
	        statement.setString(counter++, employment.getEducation());
	        statement.setString(counter++, employment.getGender());       

	        //employment eligibility
	        statement.setDate(counter++, employment.getDate());
	        statement.setString(counter++, employment.getPosition());    
	        statement.setDouble(counter++, employment.getSalary());      
	        statement.setBoolean(counter++, employment.isLegal());
	        statement.setBoolean(counter++, employment.isRelative());
	        statement.setString(counter++, employment.getRelative_detail());

	        statement.executeUpdate();
	        connection.commit();

	        resultSet = statement.getGeneratedKeys();
	        if (resultSet.next()) {
	            return resultSet.getInt(1);
	        }
	    } catch (SQLException exception) {
	        if (connection != null) {
	            connection.rollback();
	        }
	        throw exception;  
	    } finally {
	        if (resultSet != null) resultSet.close();
	        if (statement != null) statement.close();
	        if (connection != null) connection.close();
	    }
	    return 0;
	}
}