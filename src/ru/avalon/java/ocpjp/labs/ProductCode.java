package ru.avalon.java.ocpjp.labs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;

/**
 * Класс описывает представление о коде товара и отражает соответствующую
 * таблицу базы данных Sample (таблица PRODUCT_CODE).
 *
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class ProductCode {

    /**
     * Код товара
     */
    private String code;
    /**
     * Кода скидки
     */
    private char discountCode;
    /**
     * Описание
     */
    private String description;
    /**
     * SQL-запрос на выбор всех данных из таблицы
     */
    private final static String SELECT_ALL = "select * from PRODUCT_CODE";
    /**
     * SQL-запрос на выбор одного кода продукта из таблицы, равного значеню поля
     * code
     */
    private final static String SELECT_BY_CODE = "select * from PRODUCT_CODE"
            + " where prod_code = ?";
    /**
     * SQL-запрос на вставку нового кода продукта в таблицу
     */
    private final static String INSERT = "insert into PRODUCT_CODE values(?, ?, ?)";
    /**
     * SQL-запрос на обновление существующего кода продукта
     */
    private final static String UPDATE
            = "update PRODUCT_CODE set discount_code = ?, description = ?"
            + " where prod_code = ?";

    /**
     * Основной конструктор типа {@link ProductCode}
     *
     * @param code код товара
     * @param discountCode код скидки
     * @param description описание
     */
    public ProductCode(String code, char discountCode, String description) {
        this.code = code;
        this.discountCode = discountCode;
        this.description = description;
    }

    /**
     * Инициализирует объект значениями из переданного {@link ResultSet}
     *
     * @param set {@link ResultSet}, полученный в результате запроса,
     * содержащего все поля таблицы PRODUCT_CODE базы данных Sample.
     */
    private ProductCode(ResultSet set) throws SQLException {
        /*
         * TODO #05 реализуйте конструктор класса ProductCode
         */
        this(set.getString("prod_code"),
                (set.getString("discount_code")).charAt(0),
                set.getString("description"));
    }

    /**
     * Возвращает код товара
     *
     * @return Объект типа {@link String}
     */
    public String getCode() {
        return code;
    }

    /**
     * Устанавливает код товара
     *
     * @param code код товара
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Возвращает код скидки
     *
     * @return Объект типа {@link String}
     */
    public char getDiscountCode() {
        return discountCode;
    }

    /**
     * Устанавливает код скидки
     *
     * @param discountCode код скидки
     */
    public void setDiscountCode(char discountCode) {
        this.discountCode = discountCode;
    }

    /**
     * Возвращает описание
     *
     * @return Объект типа {@link String}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание
     *
     * @param description описание
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Хеш-функция типа {@link ProductCode}.
     *
     * @return Значение хеш-кода объекта типа {@link ProductCode}
     */
    @Override
    public int hashCode() {
        /*
         * TODO #06 Реализуйте метод hashCode
         */
        final int prime = 23;
        int result = ((code == null) ? 0 : code.hashCode());
        return prime * result;
    }

    /**
     * Сравнивает некоторый произвольный объект с текущим объектом типа
     * {@link ProductCode}
     *
     * @param obj Объект, скоторым сравнивается текущий объект.
     * @return true, если объект obj тождественен текущему объекту. В обратном
     * случае - false.
     */
    @Override
    public boolean equals(Object obj) {
        /*
         * TODO #07 Реализуйте метод equals
         */
        if (!(obj instanceof ProductCode)) {
            return false;
        }
        ProductCode other = (ProductCode) obj;
        if (code.equals(other.getCode())) {
            return true;
        }
        return false;
    }

    /**
     * Возвращает строковое представление кода товара.
     *
     * @return Объект типа {@link String}
     */
    @Override
    public String toString() {
        /*
         * TODO #08 Реализуйте метод toString
         */
        return "Code: " + code + "; Discount Code: " + discountCode 
                + "Description: " + description;
    }

    /**
     * Возвращает запрос на выбор всех записей из таблицы PRODUCT_CODE базы
     * данных Sample
     *
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getSelectQuery(Connection connection) throws SQLException {
        /*
         * TODO #09 Реализуйте метод getSelectQuery
         */
        return connection.prepareStatement(SELECT_ALL);
    }

    /**
     * Возвращает запрос на выбор записи из таблицы PRODUCT_CODE базы
     * данных Sample по значению поля prod_code
     * 
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getSelectByCode(Connection connection) throws SQLException {
        return connection.prepareStatement(SELECT_BY_CODE);
    }

    /**
     * Возвращает запрос на добавление записи в таблицу PRODUCT_CODE базы данных
     * Sample
     *
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getInsertQuery(Connection connection) throws SQLException {
        /*
         * TODO #10 Реализуйте метод getInsertQuery
         */
        return connection.prepareStatement(INSERT);
    }

    /**
     * Возвращает запрос на обновление значений записи в таблице PRODUCT_CODE
     * базы данных Sample
     *
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getUpdateQuery(Connection connection) throws SQLException {
        /*
         * TODO #11 Реализуйте метод getUpdateQuery
         */
        return connection.prepareStatement(UPDATE);
    }

    /**
     * Преобразует {@link ResultSet} в коллекцию объектов типа
     * {@link ProductCode}
     *
     * @param set {@link ResultSet}, полученный в результате запроса,
     * содержащего все поля таблицы PRODUCT_CODE базы данных Sample
     * @return Коллекция объектов типа {@link ProductCode}
     * @throws SQLException
     */
    public static Collection<ProductCode> convert(ResultSet set) throws SQLException {
        /*
         * TODO #12 Реализуйте метод convert
         */

        Collection<ProductCode> coll = new ArrayList<>();
        while (set.next()) {
            coll.add(new ProductCode(set));
        }
        return coll;
    }

    /**
     * Сохраняет текущий объект в базе данных.
     * <p>
     * Если запись ещё не существует, то выполняется запрос типа INSERT.
     * <p>
     * Если запись уже существует в базе данных, то выполняется запрос типа
     * UPDATE.
     *
     * @param connection действительное соединение с базой данных
     */
    public void save(Connection connection) throws SQLException {
        /*
         * TODO #13 Реализуйте метод save
         */
        PreparedStatement pst = getSelectByCode(connection);
        pst.setString(1, code);
        ResultSet result = pst.executeQuery();
        boolean ProductCodeExists = false;
        while (result.next()) {
            ProductCodeExists = this.equals(new ProductCode(result));
        }
        if (ProductCodeExists) {
            pst = getUpdateQuery(connection);
            pst.setString(1, String.valueOf(discountCode));
            pst.setString(2, description);
            pst.setString(3, code);
        } else {
            pst = getInsertQuery(connection);
            pst.setString(2, String.valueOf(discountCode));
            pst.setString(3, description);
            pst.setString(1, code);
        }
        pst.execute();

    }

    /**
     * Возвращает все записи таблицы PRODUCT_CODE в виде коллекции объектов типа
     * {@link ProductCode}
     *
     * @param connection действительное соединение с базой данных
     * @return коллекция объектов типа {@link ProductCode}
     * @throws SQLException
     */
    public static Collection<ProductCode> all(Connection connection) throws SQLException {
        try (PreparedStatement statement = getSelectQuery(connection)) {
            try (ResultSet result = statement.executeQuery()) {
                return convert(result);
            }
        }
    }
}
