package it.develhope.jdbc;

import java.sql.*;

public class DriverJDBC {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/newdb";
    private static final String USER = "developer";
    private static final String PASSWORD = "developer1?";

    public static void main(String[] args) {

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            System.out.printf("Connected to database %s successfully.\n\n", conn.getCatalog());

            Statement statement = conn.createStatement();

            String createQuery = """
                    CREATE TABLE IF NOT EXISTS students
                    ( student_id INT(10) NOT NULL AUTO_INCREMENT,
                      last_name VARCHAR(30),
                      first_name VARCHAR(30),
                      CONSTRAINT students_pk PRIMARY KEY (student_id)
                    );
                    """;

            statement.executeUpdate(createQuery);

            System.out.printf("Created table students in the database %s.\n\n", conn.getCatalog());

            statement.executeUpdate("INSERT INTO students (last_name, first_name) VALUES ('Gallina', 'Pietro')");
            statement.executeUpdate("INSERT INTO students (last_name, first_name) VALUES ('Verdi', 'Giuseppe')");
            statement.executeUpdate("INSERT INTO students (last_name, first_name) VALUES ('Rossi', 'Mario')");
            statement.executeUpdate("INSERT INTO students (last_name, first_name) VALUES ('Marrone', 'Carlo')");

            System.out.println("Students inserted in the students table.");

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if(conn != null){
                    conn.close();
                }
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }

    }

}
