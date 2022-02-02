package sda.jdbc.dao;

import sda.jdbc.przyklad4.Country;
import sda.jdbc.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl implements  CountryDao{

    private final String selectAll = "select * from Country;";

    @Override
    public List<Country> getAll() throws SQLException {
        ResultSet resultSet = getConnection().createStatement().executeQuery(selectAll);

        List<Country> countries = new ArrayList<>();

        while (resultSet.next()) {
            countries.add(mapResultSetToCountry(resultSet));
        }
        return countries;
    }

    private Country mapResultSetToCountry(ResultSet resultSet) throws SQLException {
        return new Country(resultSet.getInt("CO_ID"),
                resultSet.getString("CO_NAME"),
                resultSet.getString("CO_ALIAS"));
    }


    private final String selectById = "select * from Country where CO_ID = ?;";

    public static final int CO_ID_PARAMETER_INDEX = 1;
    @Override
    public Country findById(int id) throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(selectById);

        preparedStatement.setInt(CO_ID_PARAMETER_INDEX, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            return mapResultSetToCountry(resultSet);
        }
        return null;
    }


    private final String addNewCountry = "Insert into Country (CO_NAME, CO_ALIAS) values (?, ?);";
    private final int CO_NAME_INDEX = 1;
    private final int CO_ALIAS_INDEX = 2;

    @Override
    public void addNewCountry(Country country) throws SQLException {

        PreparedStatement preparedStatement = getConnection().prepareStatement(addNewCountry);

        preparedStatement.setString(CO_NAME_INDEX, country.getName());
        preparedStatement.setString(CO_ALIAS_INDEX, country.getAlias());

        preparedStatement.executeUpdate();

    }

    private final String deleteById = "delete from Country where CO_ID = ?;";
    private final int CO_ID_INDEX = 1;

    @Override
    public void deleteById(int id) throws SQLException  {

        PreparedStatement preparedStatement = getConnection().prepareStatement(deleteById);
        preparedStatement.setInt(CO_ID_INDEX, id);
        preparedStatement.executeUpdate();
        preparedStatement.getConnection().close();

    }

    private final String updateCountry = "Update Country SET CO_NAME = ?, CO_ALIAS = ? where CO_ID = ?;";

    private static final int CO_ID_PARAMETER_INDEX_UPDATE = 3;
    private static final int CO_NAME_PARAMETER_INDEX = 1;
    private static final int CO_ALIAS_PARAMETER_INDEX = 2;

    @Override
    public void updateCountry(Country country) throws SQLException {
        if(findById(country.getId()) != null) {
            PreparedStatement preparedStatement = getConnection().prepareStatement(updateCountry);
            preparedStatement.setString(CO_NAME_PARAMETER_INDEX, country.getName());
            preparedStatement.setString(CO_ALIAS_PARAMETER_INDEX, country.getAlias());
            preparedStatement.setInt(CO_ID_PARAMETER_INDEX_UPDATE, country.getId());
            preparedStatement.executeUpdate();
        } else {
            addNewCountry(country);
        }


    }

    private Connection getConnection() {
        return new DatabaseConnection().getConnection();
    }
}