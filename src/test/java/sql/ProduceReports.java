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

        int validID = getID(id);
        if(validID != 0){
        System.out.println("Address Information: ");
        getAddress(validID);
        System.out.println("\nUser's Total Balance: ");
        getBalance(validID);
        System.out.println("\nUser Transactions: ");
        getTransactions(validID);
        }else{
            System.out.println("Invalid ID!");
        }
    }

    public static void getAddress(int validID) {
        String sqlStatement = "SELECT * FROM customer_details WHERE customer_id =" + validID;

        try (Connection conn = connectToDB()) {
            ResultSet result = executeQuery(conn, sqlStatement);

            while (result != null && result.next()) {

                //Printing out of the data
                int id = result.getInt("customer_id");
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

    public static void getBalance(int validId){
        String sqlStatement = "SELECT SUM(balance) AS total_balance FROM account_details WHERE customer_id = " + validId + ";";
        try (Connection conn = connectToDB()) {
            ResultSet result = executeQuery(conn, sqlStatement);

            while (result != null && result.next()) {

                //Printing out of the data
                int totalBalance = result.getInt("total_balance");

                System.out.println("ID: " + validId);
                System.out.println("Total Balance: " + totalBalance);
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void getTransactions(int validId){
        String sqlAccountNumber = "SELECT account_number FROM account_details WHERE customer_id = " + validId + ";";
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

    public static int getID(int id) {
        String getUserId = "SELECT customer_id FROM customer_details WHERE customer_id = " + id + ";";
        int customer_id = 0;
        try (Connection conn = connectToDB()) {
            ResultSet user_Id = executeQuery(conn, getUserId);

            customer_id = user_Id.getInt("customer_id");
            System.out.println(customer_id);
        } catch (SQLException e) {
            System.out.println("Invalid Customer ID!");
        }
        return customer_id; //FIX THIS
    }
}




