package ru.avalon.java.ocpjp.labs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Лабораторная работа №3
 * <p>
 * Курс: "DEV-OCPJP. Подготовка к сдаче сертификационных экзаменов серии Oracle
 * Certified Professional Java Programmer"
 * <p>
 * Тема: "JDBC - Java Database Connectivity"
 *
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class Main {

    /**
     * Точка входа в приложение
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        /*
         * TODO #01 Подключите к проекту все библиотеки, необходимые для соединения с СУБД.
         */
        try (Connection connection = getConnection()) {
            if(connection == null) throw new SQLException("Соединение с БД не установлено!");
            ProductCode code = new ProductCode("MO", 'N', "Movies");
            code.save(connection);
            printAllCodes(connection);
            System.out.println("----------------------------");
            code.setCode("MV");
            code.save(connection);
            printAllCodes(connection);
        }
        /*
         * TODO #14 Средствами отладчика проверьте корректность работы программы
         */
    }

    /**
     * Выводит в кодсоль все коды товаров
     *
     * @param connection действительное соединение с базой данных
     * @throws SQLException
     */
    private static void printAllCodes(Connection connection) throws SQLException {
        Collection<ProductCode> codes = ProductCode.all(connection);
        for (ProductCode code : codes) {
            System.out.println(code);
        }
    }

    /**
     * Возвращает URL, описывающий месторасположение базы данных
     *
     * @return URL в виде объекта класса {@link String}
     */
    private static String getUrl() {
        /*
         * TODO #02 Реализуйте метод getUrl
         */
        return "jdbc:derby://localhost:1527/sample";
    }

    /**
     * Возвращает параметры соединения
     *
     * @return Объект класса {@link Properties}, содержащий параметры user и
     * password
     */
    private static Properties getProperties() throws IOException {
        /*
         * TODO #03 Реализуйте метод getProperties
         */
        Properties prop = new Properties();

        BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите имя пользователя:");
        prop.setProperty("user", is.readLine());
        System.out.println("Введите пароль:");
        prop.setProperty("password", is.readLine());
        return prop;
    }

    /**
     * Возвращает соединение с базой данных Sample
     *
     * @return объект типа {@link Connection}
     * @throws SQLException
     */
    private static Connection getConnection() throws SQLException {
        /*
         * TODO #04 Реализуйте метод getConnection
         */
        
        Driver driver = DriverManager.getDriver(getUrl());
        if (driver == null) throw new SQLException("Драйвер не найден");
        Connection con = null;
        try {
            con =  DriverManager.getConnection(getUrl(), getProperties());
        } catch (IOException ex) {
            System.err.println("Ошибка ввода пользоательских данных. "+ex.getMessage());
        }
        return con;
    }

}
