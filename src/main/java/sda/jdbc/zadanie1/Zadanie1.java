package sda.jdbc.zadanie1;

import sda.jdbc.util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Zadanie1 {
  /*
   * Napisz program który pobierze z bazy danych imiona oraz nazwiska wszystkich użytkowników - wyświetl je w konsoli
   * */

    public static final int FIRST_NAME_PARAMETER_INDEX = 1;
    public static final int LAST_NAME_PARAMETER_INDEX = 2;

  public static void main(String[] args) throws SQLException {
      DatabaseConnection databaseConnection = new DatabaseConnection();

      String query = "select USR_FIRSTNAME FN, USR_LASTNAME LN from user where USR_FIRSTNAME = ? AND USR_LASTNAME = ?;";

      PreparedStatement preparedStatement = databaseConnection
              .getConnection()
              .prepareStatement(query);

      preparedStatement.setString(FIRST_NAME_PARAMETER_INDEX, "Jan");
      preparedStatement.setString(LAST_NAME_PARAMETER_INDEX, "Kowalski");

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
      System.out.print(resultSet.getString("FN")+" ");
      System.out.println(resultSet.getString("LN"));
      }

  }
}
