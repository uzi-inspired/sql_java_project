package sql;


import org.junit.jupiter.api.extension.ExtensionContext;

import java.sql.*;
import java.util.Scanner;

public class ProduceReports {
    static String DBconnUrl = "jdbc:mysql://sqljavaproject_oldcrowdno:92cd9151ff3473e5107253cbba84374d998eb545@58e.h.filess.io:3307/sqljavaproject_oldcrowdno";
    static String DBusername= "sqljavaproject_oldcrowdno";
    static String DBpassword= "92cd9151ff3473e5107253cbba84374d998eb545";
    static ExtensionContext.Store.CloseableResource connection;
    public static void main(String[] args) throws Throwable{

        //Creating a scanner object
        Scanner scanner = new Scanner (System.in);

        System.out.println("Enter the customer ID");
        int id = scanner.nextInt();

        // SQLQuery
        String sqlStatement = "SELECT * FROM customer_details WHERE customer_id =" + id;

        //creates connection to the server
        try(Connection conn = DriverManager.getConnection(DBconnUrl, DBusername, DBpassword )){
            if(conn != null){
                System.out.println("Connected to server");
            }else {
                System.out.println("No connection");
            }

            //creates the statement object
            Statement statement = conn.createStatement();

            //executes a SQL statement
            ResultSet res = statement.executeQuery(sqlStatement);
            System.out.println(res);

            //Process the results
            while (res.next()) {
                id = res.getInt("customer_id");
                String name = res.getString("first_name");
                String lastName = res.getString("last_name");
                Date dateOfBirth = res.getDate("date_of_birth");
                String streetName = res.getString("street_name");
                int houseNumber = res.getInt("house_number");
                int zipCode = res.getInt("zip_code");
                String city = res.getString("city");
                String country = res.getString("country");

                System.out.println("ID: " + id);
                System.out.println("First Name: " + name);
                System.out.println("Last Name: " + lastName);
                System.out.println("D.O.B: " + dateOfBirth);
                System.out.println("Street Name: " + streetName);
                System.out.println("House Number: " + houseNumber);
                System.out.println("Zip Code: " + zipCode);
                System.out.println("City: " + city);
                System.out.println("Country: " + country);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());

            //closes the connection
            connection.close();
        }
    }

}




