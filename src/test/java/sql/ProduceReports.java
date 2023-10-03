package sql;


import org.junit.jupiter.api.extension.ExtensionContext;

import java.sql.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProduceReports extends Base {

    public static void main(String[] args) {

        //Creating a scanner object
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the customer ID");
        int id = scanner.nextInt();
        getAddress(id);
        getBalance(id);
        getTransactions(id);
    }

    public static void getAddress(int id) {
        String sqlStatement = "SELECT * FROM customer_details WHERE customer_id =" + id;

        try (Connection conn = connectToDB()) {
            ResultSet result = executeQuery(conn, sqlStatement);

            while (result != null && result.next()) {

                //Printing out of the data
                id = result.getInt("customer_id");
                String streetName = result.getString("street_name");
                int houseNumber = result.getInt("house_number");
                int zipCode = result.getInt("zip_code");
                String city = result.getString("city");
                String country = result.getString("country");

                System.out.println("ID: " + id);
                System.out.println("Street Name: " + streetName);
                System.out.println("House Number: " + houseNumber);
                System.out.println("Zip Code: " + zipCode);
                System.out.println("City: " + city);
                System.out.println("Country: " + country);
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void getBalance(int id){
        String sqlStatement = "SELECT SUM(balance) AS total_balance FROM account_details WHERE customer_id = " + id + ";";
        try (Connection conn = connectToDB()) {
            ResultSet result = executeQuery(conn, sqlStatement);

            while (result != null && result.next()) {

                //Printing out of the data
                int totalBalance = result.getInt("total_balance");

                System.out.println("ID: " + id);
                System.out.println("Total Balance: " + totalBalance);
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void getTransactions(int id){
        String sqlAccountNumber = "SELECT account_number FROM account_details WHERE customer_id = " + id + ";";
        try (Connection conn = connectToDB()){
            ResultSet accountNumbers = executeQuery(conn, sqlAccountNumber);

            while(accountNumbers != null && accountNumbers.next()){
                int accountNumber = accountNumbers.getInt("account_number");

                String sqlTransactions = "SELECT * FROM transaction WHERE account_number = " + accountNumber + ";";
                ResultSet result = executeQuery(conn, sqlTransactions);
                while (result != null && result.next()){
                    int accountNum = result.getInt("account_number");
                    String transactionDate = result.getString("date_of_transaction");
                    int transactionAmount = result.getInt("amount");
                    String transactionType = result.getString("transaction_type");


                    System.out.println("Account Number: " + accountNum);
                    System.out.println("Date of Transaction: " + transactionDate);
                    System.out.println("Amount: " + transactionAmount);
                    System.out.println("Type: " + transactionType);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}




