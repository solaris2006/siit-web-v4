package siit.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import siit.model.Product;
import siit.sevices.OrderService;
import siit.sevices.ProductService;

import java.util.List;

@RestController
public class ProductValueController {

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @GetMapping("/api/orders")
    public double getProductValueById(@RequestParam("customerId") int customerId) {

        double result = 0.0 ;
        return result;
    }
}
