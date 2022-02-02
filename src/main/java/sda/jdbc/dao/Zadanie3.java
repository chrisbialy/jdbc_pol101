package sda.jdbc.dao;

import sda.jdbc.przyklad4.Country;

import java.sql.SQLException;

public class Zadanie3 {
    public static void main(String[] args) throws SQLException {
        CountryDao countryDao = new CountryDaoImpl();

//        countryDao.getAll().forEach(country -> System.out.println(country.toString()));

//        System.out.println(countryDao.findById(2));

        //countryDao.addNewCountry(new Country(null, "UK", "UK"));

        //countryDao.deleteById(7);


    }

}
