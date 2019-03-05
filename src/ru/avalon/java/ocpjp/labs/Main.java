package ru.avalon.java.ocpjp.labs;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;

/**
 * Лабораторная работа №3
 * <p>
 * Курс: "DEV-OCPJP. Подготовка к сдаче сертификационных экзаменов серии Oracle Certified Professional Java Programmer"
 * <p>
 * Тема: "JDBC - Java Database Connectivity" 
 *
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class Main {
    private final static String CONFIG_FILE_NAME = "db_connect.cfg";
    private final static String SEPARATOR;
    private final static String CONFIG_PATH;
    private final static String DRIVER;
    private final static String PORT;
    private final static String DB_NAME;
    private final static String USER;
    private final static String PASSWORD;
    private final static String URL;
    
    static {
        SEPARATOR = System.getProperty("file.separator");
        CONFIG_PATH = System.getProperty("user.dir") + SEPARATOR + CONFIG_FILE_NAME;
        Properties properties = getProperties();
        DRIVER = properties.getProperty("driver");
        PORT = properties.getProperty("port");
        DB_NAME = properties.getProperty("dbName");
        USER = properties.getProperty("user");
        PASSWORD = properties.getProperty("password");
        URL = properties.getProperty("url");
    }

    /**
     * Точка входа в приложение
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /*
         * TODO #01 Подключите к проекту все библиотеки, необходимые для соединения с СУБД.
         */
        Class.forName(DRIVER);
        try (Connection connection = getConnection()) {
            ProductCode code = new ProductCode("TH", 'K', "PackTOP");
            System.out.println(code);
            code.save(connection);
            printAllCodes(connection);

            code.setCode("TW");
            code.save(connection);
            printAllCodes(connection);
            
            assert code.getCode().equals("TW") : "Не код, а фуфло!";
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
        return URL + ":" + PORT + "/" + DB_NAME;
    }
    /**
     * Возвращает параметры соединения
     * 
     * @return Объект класса {@link Properties}, содержащий параметры user и 
     * password
     */
    private static Properties getProperties() {
        /*
         * TODO #03 Реализуйте метод getProperties
         */
        Properties prop = new Properties();
        try(FileInputStream  is = new FileInputStream (CONFIG_PATH)) {          
            prop.load(is);
        } catch(IOException e) {
            e.printStackTrace(System.err);
            System.exit(-1);
        }
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
           return DriverManager.getConnection(getUrl(), USER, PASSWORD);
    }  
}
