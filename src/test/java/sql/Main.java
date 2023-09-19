package sql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String connUrl = "jdbc:mysql://sqljavaproject_oldcrowdno:92cd9151ff3473e5107253cbba84374d998eb545@58e.h.filess.io:3307/sqljavaproject_oldcrowdno";
        String username= "sqljavaproject_oldcrowdno";
        String password= "92cd9151ff3473e5107253cbba84374d998eb545";
        String sqlStatement = "SELECT * FROM customer_details";

        try(Connection conn = DriverManager.getConnection(connUrl, username, password )){
            if(conn != null){
                System.out.println("Connected to server");
            }else {
                System.out.println("No connection");
            }
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            ResultSet res = statement.executeQuery();
            System.out.println(res);
            while (res.next()) {
                long id = res.getLong("customer_id");
                String name = res.getString("first_name");
                String lastName = res.getString("last_name");

                System.out.println(name);

                System.out.println(lastName);
                System.out.println(id);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
