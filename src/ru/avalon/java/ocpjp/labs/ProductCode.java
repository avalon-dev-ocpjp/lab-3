package ru.avalon.java.ocpjp.labs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

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
     * Строка в БД, соответствующая экземпляру типа ProductCode
     */
    private ResultSet rs;

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
    private ProductCode(ResultSet set) {
        /*
         * TODO #05 реализуйте конструктор класса ProductCode
         */
        this(null, '0', null);
        try {
            this.setCode(set.getString(1));
            this.setDiscountCode(set.getString(2).charAt(0));
            this.setDescription(set.getString(3));

        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
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
        /*
         * TODO #06 Реализуйте метод hashCode
         */
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.code);
        hash = 59 * hash + this.discountCode;
        hash = 59 * hash + Objects.hashCode(this.description);
        return hash;
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
        if (obj instanceof ProductCode) {
            ProductCode other = (ProductCode) obj;
            boolean a = other.code.compareTo(code) == 0;
            boolean b = other.discountCode == discountCode;
            boolean c = other.description.compareTo(description) == 0;
            return a & b & c;
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
        StringBuilder sb = new StringBuilder();
        return sb.append(code).append(" -- ")
                .append(discountCode).append(" -- ")
                .append(description).toString();
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
        String sql = "SELECT * FROM APP.PRODUCT_CODE";
        return connection.prepareStatement(sql);
    }

    /**
     * Возвращает запрос на добавление записи в таблицу PRODUCT_CODE базы данных
     * Sample
     *
     * <PRE>
     * Шаблон для SQL запроса:
     * INSERT INTO APP.PRODUCT_CODE values ('<code>code</code>',
     * '<code>discountCode</code>','<code>description</code>')
     * </PRE>
     *
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getInsertQuery(Connection connection) throws SQLException {
        /*
         * TODO #10 Реализуйте метод getInsertQuery
         */
        String sql = "INSERT INTO APP.PRODUCT_CODE values (?,?,?)";
        return connection.prepareStatement(sql);
    }

    /**
     * Возвращает запрос на обновление значений записи в таблице PRODUCT_CODE
     * базы данных Sample
     *
     * <PRE>
     * Шаблон для SQL запроса:
     * UPDATE APP.PRODUCT_CODE set DISCOUNT_CODE = '<code>discountCode</code>',
     * DESCRIPTION = '<code>description</code>' where Prod_code = '<code>code</code>'
     * </PRE>
     *
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    
    /*данный метод не используется
    public static PreparedStatement getUpdateQuery(Connection connection) throws SQLException {
        /*
         * TODO #11 Реализуйте метод getUpdateQuery
        String sql = "UPDATE APP.PRODUCT_CODE set DISCOUNT_CODE = ?, DESCRIPTION = ? where Prod_code = ?";
        return connection.prepareStatement(sql);
    }
     */
    
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
        Collection<ProductCode> col = new ArrayList();
        while (set.next()) {
            col.add(new ProductCode(set));
        }
        return col;
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
        PreparedStatement statement = null;
        if (rs == null) {
            statement = getInsertQuery(connection);
            statement.setString(1, code);
            statement.setString(2, String.valueOf(discountCode));
            statement.setString(3, description);
            statement.execute();
        } else {
            rs.last();
            rs.updateString("PROD_CODE", code);
            rs.updateString("DISCOUNT_CODE", String.valueOf(discountCode));
            rs.updateString("DESCRIPTION", description);
            rs.updateRow();
        }
        rs = getProductFromDB(connection);
    }

    private ResultSet getProductFromDB(Connection connection) throws SQLException {
        String sql = "SELECT * FROM APP.PRODUCT_CODE WHERE PROD_CODE = ?";
        PreparedStatement statement = connection.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.setString(1, code);

        rs = statement.executeQuery();
        return rs;
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
