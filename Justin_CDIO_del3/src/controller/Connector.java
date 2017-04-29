/*
 * 
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// TODO: Auto-generated Javadoc
/**
 * The Class Connector.
 *
 * @author Ronnie Dalsgaard
 */
public class Connector {

	/** The host. */
	private final String HOST = "Localhost";

	/** The port. */
	private final int PORT = 3306;

	/** The database. */
	private final String DATABASE = "matador2";

	/** The username. */
	private final String USERNAME = "root";

	/** The password. */
	private final String PASSWORD = "";

	/** The connection. */
	private Connection connection;

	/**
	 * Instantiates a new connector.
	 */
	public Connector() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
			connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Do query.
	 *
	 * @param query the query
	 * @return the result set
	 * @throws SQLException the SQL exception
	 */
	public ResultSet doQuery(String query) throws SQLException {
		Statement stmt = connection.createStatement();
		ResultSet res = stmt.executeQuery(query);
		return res;
	}

	/**
	 * Do update.
	 *
	 * @param query the query
	 * @throws SQLException the SQL exception
	 */
	public void doUpdate(String query) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate(query);

	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}
	public PreparedStatement prep(String query) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(query);
		return stmt;
		
	}

	/**
	 * Ps.
	 *
	 * @param query the query
	 * @throws SQLException the SQL exception
	 */
	public ResultSet psRead(String query) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet res = stmt.executeQuery(query);
		return res;

	}
}
