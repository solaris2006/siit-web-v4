package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import siit.model.Customer;
import siit.model.Order;
import siit.sevices.CustomerService;
import siit.sevices.OrderService;

import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/customers/{customerId}/")
public class MockController {
    @Autowired
    CustomerService customerService;

    @Autowired
    OrderService orderService;



    @GetMapping("/total")
    public double orderStotalValue(@PathVariable int customerId){
        int totalValue = 0;
        Customer customer = customerService.getCustomerById(customerId);
        List<Order> customerOrders = customer.getOrders();

        for (Order order : customerOrders){
            totalValue += customerService.getOrderValueById(order.getId());
        }



        return totalValue;
    }

}
