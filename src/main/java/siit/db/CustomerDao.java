package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Customer> getAllCustomers(){
        return jdbcTemplate.query("SELECT * FROM customers",
                this::getCustomer);
    }

    public Customer getCustomerById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM customers WHERE id = ?",
                this::getCustomer, id);
    }

    public void updateCustomer(Customer customer) {
        jdbcTemplate.update("UPDATE customers SET name=?, phone=? WHERE id=?",
                customer.getName(), customer.getPhone(), customer.getId());
//        namedParameterJdbcTemplate.update("update customer set name=:name, phone=:phone where id=:id",
//                Map.of("name", customer.getName(), ...))
    }

    private Customer getCustomer (ResultSet resultSet, int rowNum) throws SQLException{
        Customer customer = new Customer();
        customer.setId(resultSet.getInt("id"));
        customer.setName(resultSet.getString("name"));
        customer.setEmail(resultSet.getString("email"));
        customer.setPhone(resultSet.getString("phone"));
        customer.setBirthDate(resultSet.getDate("birthday").toLocalDate());
        return customer;
    }

}
