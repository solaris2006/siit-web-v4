package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import siit.ValidationException;
import siit.model.Customer;
import siit.model.Order;
import siit.sevices.CustomerService;
import siit.sevices.OrderService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    CustomerService customerService;

    OrderService orderService;

    @Autowired
    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView displayCustomers(){
        ModelAndView mav = new ModelAndView();

        List<Customer> customerList = customerService.getCustomers();

        mav.setViewName("customers-list");
        mav.addObject("customers", customerList);

        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/edit")
    public ModelAndView displayCustomerEditForm(@PathVariable int id) {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("customer-edit");
        mav.addObject("customer", customerService.getCustomerById(id));
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/edit")
    public ModelAndView performCustomerEdit(@ModelAttribute Customer customer) {
        ModelAndView mav = new ModelAndView();
        try {
            customerService.updateCustomer(customer);
            mav.setViewName("redirect:/customers");
        } catch (ValidationException ex) {
            mav.setViewName("customer-edit");
            mav.addObject("error", ex.getMessage());
        }

        return mav;
    }

    @GetMapping("/{customer_id}/orders")
    public ModelAndView displayCustomerOrders(@PathVariable("customer_id") int customerId){
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = customerService.getCustomerById(customerId);
        modelAndView.setViewName("customer-orders");
        modelAndView.addObject("customer", updateOrderValue(customer, customerId));
        return modelAndView;
    }

    @GetMapping("/{customer_id}/orders/add")
    public ModelAndView addOrder(@PathVariable("customer_id") int customerId){

        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.addObject("customer", customerService.getCustomerById(customerId));
        modelAndView.addObject("order", new Order());
        modelAndView.setViewName("add-order-form");
        return modelAndView;
    }

    @PostMapping("/{customer_id}/orders/add")
    public ModelAndView saveOrder(@PathVariable("customer_id") int customerId, @Valid @ModelAttribute("order") Order order){

        ModelAndView modelAndView = new ModelAndView();
        orderService.addOrder(order, customerId);
        modelAndView.addObject(order);
        modelAndView.setViewName("redirect:/customers/{customer_id}/orders");
        return modelAndView;
    }

    @GetMapping("/{customer_id}/orders/{order_id}/delete")
    public ModelAndView deleteCustomerOrder(@PathVariable("customer_id") int customerId, @PathVariable("order_id") int orderId){
        orderService.deleteOrderBy(orderId);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = customerService.getCustomerById(customerId);
        modelAndView.setViewName("customer-orders");
        modelAndView.addObject("customer", updateOrderValue(customer, customerId));
        return modelAndView;
    }

    public Customer updateOrderValue(Customer customer, int customerId){
        customer = customerService.getCustomerById(customerId);
        for (Order order : customer.getOrders()){
            order.setValue(customerService.getOrderValueById(order.getId()));
        }
        return customer;
    }



}
