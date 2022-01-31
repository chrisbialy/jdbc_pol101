package sda.jdbc.przyklad1;

import sda.jdbc.util.DatabaseConnection;

import java.sql.*;

public class Przyklad1 {
  public static void main(String[] args) {

    final String query = "Select * from Country;";
    final String query2 = "select PRO_ID, PRO_NAME, PRO_DESCRIPTION, PRO_PRICE, PRO_CAT_ID, CAT_NAME from product p join category c on c.CAT_ID = p.PRO_CAT_ID";


    DatabaseConnection databaseConnection = new DatabaseConnection();


    try (Connection connection = databaseConnection.getConnection();
         Statement statement = connection.createStatement()) {

      ResultSet resultSet = statement.executeQuery(query2);
      System.out.println("PRO_ID   PRO_NAME    PRO_DESCRIPTION    PRO_PRICE    PRO_CAT_ID   CAT_NAME");

        while (resultSet.next()) {
        int PRO_ID = resultSet.getInt("PRO_ID");
        String PRO_NAME =  resultSet.getString("PRO_NAME");
        String PRO_DESCRIPTION =  resultSet.getString("PRO_DESCRIPTION");
        String PRO_PRICE =  resultSet.getString("PRO_PRICE");
        int  PRO_CAT_ID = resultSet.getInt("PRO_CAT_ID");
        String CAT_NAME = resultSet.getString("CAT_NAME");
        System.out.println(PRO_ID+"         "+PRO_NAME+"         "+PRO_DESCRIPTION+"        "+PRO_PRICE+"               "+PRO_CAT_ID+"        "+CAT_NAME);

      }



//      ResultSet resultSet = statement.executeQuery(query);
//
//      while (resultSet.next()) {
//        System.out.println(resultSet.getInt("CO_ID"));
//        System.out.println(resultSet.getString("CO_NAME"));
//        System.out.println(resultSet.getString("CO_ALIAS"));
//
//      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }


  }
}
