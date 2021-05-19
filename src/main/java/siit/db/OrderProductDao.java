package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.OrderProduct;
import siit.model.Product;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderProductDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<OrderProduct> getOrderProductBy(int orderId) {
        return jdbcTemplate.query(""
                + "SELECT  op.order_id,  op.quantity, p.name, "
                + "        op.quantity * p.price AS value, p.id as product_id, p.weight, p.price AS price "
                + "FROM ORDERS_PRODUCTS op "
                + "JOIN products p on p.id = op.product_id "
                + "WHERE op.order_id = ?", this::getOrderProduct, orderId);
    }

    private OrderProduct getOrderProduct(ResultSet resultSet, int rowNum) throws SQLException {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setId(resultSet.getInt("order_id"));
        orderProduct.setName(resultSet.getString("name"));
        orderProduct.setQuantity(resultSet.getBigDecimal("quantity"));
        orderProduct.setValue(resultSet.getBigDecimal("value"));

        Product product = new Product();
        product.setId(resultSet.getInt("product_id"));
        product.setName(resultSet.getString("name"));
        product.setWeight(resultSet.getBigDecimal("weight"));
        product.setPrice(resultSet.getBigDecimal("price"));

        orderProduct.setProduct(product);

        return orderProduct;
    }


    public void deleteOrderProductById(int orderId, int product_id) {
        String deleteQuery = "DELETE from ORDERS_PRODUCTS where order_id= ? AND product_id= ?" ;

        jdbcTemplate.update(deleteQuery, orderId, product_id );



    }

    public void updateProductQuantity(int orderId, int productId, BigDecimal quantity) {

        String updateQuery = "UPDATE orders_products SET quantity = ? WHERE order_id = ? AND product_id = ?";

        int temp = quantity.intValue();

        jdbcTemplate.update(updateQuery, temp, orderId, productId);





    }



    public void addProduct(OrderProduct orderProduct, int customerID, int orderId) {
           String sql = ("INSERT INTO ORDERS_PRODUCTS (order_id, product_id, quantity)  VALUES (?,?,?)");
           jdbcTemplate.update(sql, orderId, orderProduct.getProduct().getId(), orderProduct.getQuantity());

    }

}
