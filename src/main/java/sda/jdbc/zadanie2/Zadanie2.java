package sda.jdbc.zadanie2;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import sda.jdbc.util.DatabaseConnection;

/*napisz kod umożliwający dodawanie nowych kategorii to bazy danych*/

public class Zadanie2 {

  private static final int CAT_NAME_INDEX = 1;

  public static void main(String[] args) throws SQLException {

    String sqlInsertQuery = "Insert into category (CAT_NAME) values (?);";

    DatabaseConnection databaseConnection = new DatabaseConnection();

    PreparedStatement preparedStatement = databaseConnection
            .getConnection()
            .prepareStatement(sqlInsertQuery);

    preparedStatement.setString(CAT_NAME_INDEX, "Cars");

    int countUpdatedRecords = preparedStatement.executeUpdate();
    System.out.println("zaktualizowano "+countUpdatedRecords+ " wierszy");
    databaseConnection.getConnection().close();

  }
}
