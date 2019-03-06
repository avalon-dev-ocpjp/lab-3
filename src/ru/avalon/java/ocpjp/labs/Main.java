package ru.avalon.java.ocpjp.labs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
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
    
    /**
     * Точка входа в приложение
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException {
        /*
         * TODO #01 Подключите к проекту все библиотеки, необходимые для соединения с СУБД.
         */
        try (Connection connection = getConnection()) {
            ProductCode code = new ProductCode("MO", 'N', "Movies");
            code.save(connection);
            printAllCodes(connection);

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
        return "jdbc:derby://localhost:1527/sample";
    }
    /**
     * Возвращает параметры соединения
     * 
     * @return Объект класса {@link Properties}, содержащий параметры user и 
     * password
     */
    private static Properties getProperties() throws FileNotFoundException, IOException { // не работает  загрузка ресурсов по URL. предполагаю, что из-за русских букв в пути
//        URL url = ClassLoader.getSystemResource("resources/Properties.txt");
//        Properties prop = new Properties();
//        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(url.getPath())))) {            
//            prop.load(in);            
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//        return prop;
//    }
        String path = "C:\\Users\\Павлик\\Documents\\NetBeansProjects\\Repository\\lab-3\\src\\resources\\Properties.txt";
        File f = new File("C:\\test\\new\\config.cfg");
        Properties properties = new Properties();
        InputStream stream = ClassLoader.getSystemResourceAsStream(f.getPath());
//        URL url = properties.getClass().getClassLoader().getResource(path);
        properties.load(new FileReader(f));
        return properties;
    }
    
    /**
     * Возвращает соединение с базой данных Sample
     * 
     * @return объект типа {@link Connection}
     * @throws SQLException 
     */
    private static Connection getConnection() throws SQLException, IOException {
        return DriverManager.getConnection(getUrl(), getProperties());
    }
    
}
