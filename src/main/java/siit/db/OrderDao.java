package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.Order;
import siit.model.OrderProduct;
import siit.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Order> getOrdersBy(int customerId) {
        return jdbcTemplate.query("SELECT * FROM orders WHERE customer_id = ?",
                this::getOrder, customerId);


    }



    public void deleteOrderBy(int orderId) {
        jdbcTemplate.update("DELETE FROM orders WHERE id = ?", orderId);
    }

    public void deleteOrderProduct(int orderId) {
        jdbcTemplate.update("DELETE FROM orders_products WHERE order_id = ?", orderId);
    }

    private Order getOrder(ResultSet resultSet, int rowNum) throws SQLException {
        Order order = new Order();

        order.setId(resultSet.getInt("id"));
        order.setNumber(resultSet.getString("number"));
        order.setPlaced(resultSet.getTimestamp("placed").toLocalDateTime());

        return order;
    }

    public void addOrder(Order order, int customerId) {
        String sql = ("INSERT INTO orders (number, placed , customer_id) VALUES (?, ?, ?)");
        jdbcTemplate.update(sql, order.getNumber(),order.getPlaced(), customerId);
    }

    public int orderValue(int orderID){

        int result = jdbcTemplate.queryForObject("SELECT SUM(quantity * price) AS vlaue FROM orders_products op  JOIN " +
                " products p ON op.product_id = p.id WHERE order_id = ?",  Integer.class, orderID) == null ?
                0 : jdbcTemplate.queryForObject("SELECT SUM(quantity * price) AS vlaue FROM orders_products op  JOIN " +
                " products p ON op.product_id = p.id WHERE order_id = ?",  Integer.class, orderID)  ;

        return result;
    }
}
