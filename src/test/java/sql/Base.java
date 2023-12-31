package sql;

import java.sql.*;

public class Base {
    private static String DBconnUrl = "jdbc:mysql://sqljavaproject_oldcrowdno:92cd9151ff3473e5107253cbba84374d998eb545@58e.h.filess.io:3307/sqljavaproject_oldcrowdno";
    private static String DBusername = "sqljavaproject_oldcrowdno";
    private static String DBpassword = "92cd9151ff3473e5107253cbba84374d998eb545";

    //Helper function that establishes connection to SQL DB
    public static Connection connectToDB() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DBconnUrl, DBusername, DBpassword);
            if (conn == null) {
                System.out.println("No connection");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Helper function that enables easier handling of result set output
    public static ResultSet executeQuery(Connection conn, String sqlStatement) {
        ResultSet res = null;
        try {
            Statement statement = conn.createStatement();
            res = statement.executeQuery(sqlStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }
}
