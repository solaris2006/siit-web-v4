package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Product> getProductsBy(String term) {
        return jdbcTemplate.query("SELECT * FROM products WHERE LOWER(name) LIKE ?",
                this::getOrderProduct, "%" + term.toLowerCase() + "%");

    }

    private Product getOrderProduct(ResultSet resultSet, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setWeight(resultSet.getBigDecimal("weight"));
        product.setPrice(resultSet.getBigDecimal("price"));

        return product;
    }

     public List<Product> getProductsByOrderId(int orderId){
        return jdbcTemplate.query("SELECT p.id , name , weight, price  FROM orders_products op  JOIN products p " +
                " on p.id = op.product_id " +
                " WHERE op.order_id LIKE ?", this::getOrderProduct, orderId);
     }




}
