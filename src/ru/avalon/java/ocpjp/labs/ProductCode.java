package ru.avalon.java.ocpjp.labs;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * Класс описывает представление о коде товара и отражает соответствующую
 * таблицу базы данных sample (таблица product_code).
 */
public class ProductCode {
    /**
     * Код товара
     */
    private String code;

    /**
     * Код скидки
     */
    private char discountCode;

    /**
     * Описание товара
     */
    private String description;

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
     * содержащего все поля таблицы product_code базы данных sample.
     */
    private ProductCode(ResultSet set) {
        try {
            this.code = set.getString(1);
            this.discountCode = set.getString(2).charAt(0);
            this.description = set.getString(3);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
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
        return Objects.hash(code, discountCode, description);
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
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProductCode that = (ProductCode) obj;
        return  discountCode == that.discountCode &&
                code.equals(that.code) &&
                description.equals(that.description);
    }

    /**
     * Возвращает строковое представление кода товара.
     *
     * @return Объект типа {@link String}
     */
    @Override
    public String toString() {
        return "code: " + code + ", discount code: " + discountCode +
                ", description: " + description;
    }

    /**
     * Возвращает запрос на выбор всех записей из таблицы product_code
     * базы данных sample
     *
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getSelectQuery(Connection connection) throws SQLException {
        String query = "select * from product_code";
        return connection.prepareStatement(query);
    }

    /**
     * Возвращает запрос на добавление записи в таблицу product_code
     * базы данных sample
     *
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getInsertQuery(Connection connection) throws SQLException {
        String query = "insert into product_code values (?, ?, ?)";
        return connection.prepareStatement(query);
    }

    /**
     * Возвращает запрос на обновление значений записи в таблице product_code
     * базы данных sample
     *
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getUpdateQuery(Connection connection) throws SQLException {
        String query = "update product_code set discount_code = ?, description = ? where code = ?";
        return connection.prepareStatement(query);
    }

    /**
     * Преобразует {@link ResultSet} в коллекцию объектов типа {@link ProductCode}
     *
     * @param set {@link ResultSet}, полученный в результате запроса, содержащего
     * все поля таблицы product_code базы данных sample
     * @return Коллекция объектов типа {@link ProductCode}
     * @throws SQLException
     */
    public static Collection<ProductCode> convert(ResultSet set) throws SQLException {
        Collection<ProductCode> codes = new HashSet<>();
        while(set.next()) {
            codes.add(new ProductCode(set));
        }
        return codes;
    }

    /**
     * Сохраняет текущий объект в базе данных.
     * <p>
     * Если запись ещё не существует, то выполняется запрос типа INSERT.
     * <p>
     * Если запись уже существует в базе данных, то выполняется запрос типа UPDATE.
     *
     * @param connection действительное соединение с базой данных
     */
    public void save(Connection connection) throws SQLException {
        try(PreparedStatement insertStatement = getInsertQuery(connection);
            PreparedStatement updateStatement = getUpdateQuery(connection)) {
            insertStatement.setString(1, getCode());
            insertStatement.setString(2, Character.toString(getDiscountCode()));
            insertStatement.setString(3, getDescription());

            try {
                insertStatement.executeUpdate();
            } catch(MySQLIntegrityConstraintViolationException e) {
                updateStatement.setString(1, Character.toString(getDiscountCode()));
                updateStatement.setString(2, getDescription());
                updateStatement.setString(3, getCode());
                updateStatement.executeUpdate();
            }
        }
    }

    /**
     * Возвращает все записи таблицы product_code в виде коллекции объектов
     * типа {@link ProductCode}
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
