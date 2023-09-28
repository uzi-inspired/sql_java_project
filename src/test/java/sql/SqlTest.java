package sql;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SqlTest extends Base{

    // Retrieve the number of customers from the database
    // Check that the total number of customers is equal to 2
    @Test
    public void getAllCustomers_checkNumber_shouldBeTwo() {
        String sqlStatement = "SELECT COUNT(*) customer_id FROM customer_details;";

        try (Connection conn = connectToDB()) {
            ResultSet result = executeQuery(conn, sqlStatement);

            while (result != null && result.next()) {
                int id = result.getInt("customer_id");
                System.out.println(id);
                // You can add your validation logic here
                assertEquals(2,id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Retrieve all accounts for customer Sarah from the database
    // Check that the total number of accounts for her is 2
    @Test
    public void getAccountsForSarah_checkNumber_shouldBeTwo() {
        String SQLStatement = "SELECT COUNT(*) AS total_accounts FROM account_details WHERE customer_ID = 0002;";
        try (Connection conn = connectToDB()) {
            ResultSet result = executeQuery(conn, SQLStatement);

            while (result != null && result.next()) {
                int count = result.getInt("total_accounts");

                System.out.println(count);
                // You can add your validation logic here
                assertEquals(2, count);
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
        String SQLStatement ="SELECT SUM(amount) AS total_balance FROM transaction WHERE account_number = 3";
        try (Connection conn = connectToDB()) {
            ResultSet result = executeQuery(conn, SQLStatement);

            while (result != null && result.next()) {
                double totalAmount = result.getDouble("total_balance");

                System.out.println(totalAmount);
                // You can add your validation logic here
                assertEquals(0.0, totalAmount);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}

