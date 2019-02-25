package ru.avalon.java.ocpjp.labs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;

/**
 * Лабораторная работа №3
 * <p>
 * Тема: "JDBC - Java Database Connectivity"
 */
public class Main {
    /**
     * Точка входа в приложение
     *
     * @param args
     */
    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            ProductCode code = new ProductCode("MO", 'N', "Movies");
            code.save(connection);
            printAllCodes(connection);

            code.setCode("MV");
            code.save(connection);
            printAllCodes(connection);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    /**
     * Выводит в кодсоль все коды товаров
     *
     * @param connection действительное соединение с базой данных
     * @throws SQLException
     */
    private static void printAllCodes(Connection connection) throws SQLException {
        Collection<ProductCode> codes = ProductCode.all(connection);
        codes.forEach(System.out::println);
    }

    /**
     * Возвращает URL, описывающий месторасположение базы данных
     *
     * @return URL в виде объекта класса {@link String}
     */
    private static String getUrl() {
        return "jdbc:mysql://localhost:3306/sample";
    }

    /**
     * Возвращает параметры соединения
     *
     * @return Объект класса {@link Properties}, содержащий параметры user и
     * password
     */
    private static Properties getProperties() {
        Properties params = new Properties();
        params.setProperty("user", "root");
        params.setProperty("password", "root");
        return params;
    }

    /**
     * Возвращает соединение с базой данных sample
     *
     * @return объект типа {@link Connection}
     * @throws SQLException
     */
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getUrl(), getProperties());
    }
}
