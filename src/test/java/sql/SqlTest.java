package sql;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Scanner;

public class SqlTest extends Base{

    // Retrieve the number of customers from the database
    // Check that the total number of customers is equal to 2
    @Test
    public void getAllCustomers_checkNumber_shouldBeTwo() {
        String sqlStatement = "SELECT customer_id FROM customer_details;";

        try (Connection conn = connectToDB()) {
            ResultSet result = executeQuery(conn, sqlStatement);

            while (result != null && result.next()) {
                int id = result.getInt("customer_id");
                System.out.println("ID: " + id);
                // You can add your validation logic here
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    // Retrieve all accounts for customer Sarah from the database
    // Check that the total number of accounts for her is 2
    @Test
    public void getAccountsForSarah_checkNumber_shouldBeTwo() {
        String SQLStatement = "SELECT COUNT(*) AS total_accounts FROM account_details WHERE first_name = Sarah;";
        try (Connection conn = connectToDB()) {
            ResultSet result = executeQuery(conn, SQLStatement);

            while (result != null && result.next()) {
                int id = result.getInt("customer_id");
                System.out.println("ID: " + id);
                // You can add your validation logic here
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // A test that checks that the total of all transactions is equal to 0 for a given account.
    // Doing this for a single account is enough,
    // and you’re allowed to use the account ID for that account in the query
    @Test
    public void retrieveTransactionsForAccount_checkTotalBalance_shouldBeZero() {
        String SQLStatement ="";

    }

//    public String scanner () {
//
//        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
//        System.out.println("Enter customerID");
//        String id = scanner.next();
//
//        return id;
////        String userName = myObj.nextLine();  // Read user input
////        System.out.println("Username is: " + userName);  // Output user input
//    }
}

