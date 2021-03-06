package com.company;

import java.sql.*;

public class Main {
    public static final String DB_NAME = "test.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\bhalchad\\IdeaProjects\\TestDB\\" + DB_NAME;

    public static final String TABLE_CONTACTS = "contacts";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
                    "(" + COLUMN_NAME + " text," +
                    COLUMN_PHONE + " integer," +
                    COLUMN_EMAIL + " text)");

            insertContact(statement, "Brian", 5674930, "brian@email.com");
            insertContact(statement, "Joe", 3291093, "joe@anywhere.com");
            insertContact(statement, "Ashley", 1234567, "ashley@somewhere.com");
            insertContact(statement, "Jane", 32301903, "dog@email.com");

//            statement.execute("INSERT INTO " + TABLE_CONTACTS +
//                    " (" + COLUMN_NAME + ", " +
//                    COLUMN_PHONE + ", " +
//                    COLUMN_EMAIL + ")" +
//                    " VALUES ('Joe', 3291093, 'joe@anywhere.com')");
//
//            statement.execute("INSERT INTO " + TABLE_CONTACTS +
//                    " (" + COLUMN_NAME + ", " +
//                    COLUMN_PHONE + ", " +
//                    COLUMN_EMAIL + ")" +
//                    " VALUES ('Ashley', 1234567, 'ashley@somewhere.com')");
//
//            statement.execute("INSERT INTO " + TABLE_CONTACTS +
//                    " (" + COLUMN_NAME + ", " +
//                    COLUMN_PHONE + ", " +
//                    COLUMN_EMAIL + ")" +
//                    " VALUES ('Jane', 32301903, 'dog@email.com')");

            statement.execute("UPDATE " + TABLE_CONTACTS + " SET " +
                    COLUMN_PHONE + " = 9876543 WHERE " +
                    COLUMN_NAME + " = 'Jane'");

            statement.execute("DELETE FROM " + TABLE_CONTACTS + " WHERE " +
                    COLUMN_NAME + " = 'Joe'");

            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);
            while (results.next()) {
                System.out.println(results.getString(COLUMN_NAME) + " " +
                        results.getInt(COLUMN_PHONE) + " " +
                        results.getString(COLUMN_EMAIL));
            }
            results.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertContact(Statement statement, String name, int phone, String email) throws SQLException {
        statement.execute("INSERT INTO " + TABLE_CONTACTS +
                " (" + COLUMN_NAME + ", " +
                COLUMN_PHONE + ", " +
                COLUMN_EMAIL + ")" +
                " VALUES ('" + name + "', " + phone + ", '" + email + "')");
    }
}
