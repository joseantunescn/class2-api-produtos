package br.com.cotiinformatica.api_produtos.Factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    // method to get the connection string for the database
    public static Connection getConnection() throws Exception {
        var host = "jdbc:postgresql://localhost:5435/bd-products";
        var user = "coti";
        var password = "coti";

        return DriverManager.getConnection(host, user, password);
    }

}
