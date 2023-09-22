package sql;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class SqlTest {

    // Retrieve the number of customers from the database
    // Check that the total number of customers is equal to 2
    @Test
    public void getAllCustomers_checkNumber_shouldBeTwo() {

    }

    // Retrieve all accounts for customer Sarah from the database
    // Check that the total number of accounts for her is 2
    @Test
    public void getAccountsForSarah_checkNumber_shouldBeTwo() {

    }

    // A test that checks that the total of all transactions is equal to 0 for a given account.
    // Doing this for a single account is enough,
    // and you’re allowed to use the account ID for that account in the query
    @Test
    public void retrieveTransactionsForAccount_checkTotalBalance_shouldBeZero() {

    }
    @Test
    public void scanner () {

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter customerID");

        String userName = myObj.nextLine();  // Read user input
        System.out.println("Username is: " + userName);  // Output user input
    }
}

