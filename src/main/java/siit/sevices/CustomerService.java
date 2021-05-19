package siit.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.ValidationException;
import siit.db.CustomerDao;
import siit.db.OrderDao;
import siit.model.Customer;
import siit.model.Order;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    OrderDao orderDao;

    public List<Customer> getCustomers() {
        return customerDao.getAllCustomers();
    }

    public Customer getCustomerById(int id) {
        Customer customer = customerDao.getCustomerById(id);
        List<Order> orders = orderDao.getOrdersBy(id);

        customer.setOrders(orders);
        return customer;
    }

    public void updateCustomer(Customer customer) {
        if (customer.getPhone() != null && customer.getPhone().matches("\\+?\\d+")) {
            customerDao.updateCustomer(customer);
        } else {
            throw new ValidationException("Invalid phone number");
        }
    }

    public double getOrderValueById(int orderId) {
        return (double) orderDao.orderValue(orderId);
    }
}
