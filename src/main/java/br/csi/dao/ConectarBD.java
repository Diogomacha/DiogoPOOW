package br.csi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBD {

    private static final String URL = "jdbc:postgresql://localhost:5432/Trabalho";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "1234";

    public static Connection getConnectionPostgres() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC do PostgreSQL não encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados PostgreSQL.");
            e.printStackTrace();
        }
        return null;
    }
}


