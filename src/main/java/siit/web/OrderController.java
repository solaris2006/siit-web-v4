package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import siit.model.Order;
import siit.model.OrderProduct;
import siit.sevices.OrderService;
import siit.sevices.ProductService;

import java.util.List;

@RestController
@RequestMapping("api/customers/{customerId}/orders/{orderId}")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @GetMapping
    public Order getOrderBy(@PathVariable int customerId, @PathVariable int orderId) {
        return orderService.getOrderBy(customerId, orderId);

    }

    @GetMapping("/products")
    public List<OrderProduct> getOrderProducts(@PathVariable int customerId, @PathVariable int orderId) {


        return orderService.getOrderProductBy(customerId, orderId);


    }

    @GetMapping("/add")
    public Order addOrder(@PathVariable int customerId, int orderId){
        return new Order();
    }

    @PostMapping("/products")
    public OrderProduct addProduct(@RequestBody OrderProduct orderProduct, @PathVariable int customerId, @PathVariable int orderId) {
        //OrderProduct mockedOrderProduct = new OrderProduct();
//        HINT TEMA: Logica de insert/update product va sta in service

//        orderService.addOrderProduct(orderProduct);
//        mockedOrderProduct.setId(10000);
//        mockedOrderProduct.setName("MockedName");
//        mockedOrderProduct.setQuantity(BigDecimal.ONE);
//        mockedOrderProduct.setValue(BigDecimal.ONE);
//
//        Product product = new Product();
//        product.setId(10000);
//        product.setName("MockedProductNameLala");
//        product.setWeight(BigDecimal.ONE);
//        product.setPrice(BigDecimal.ONE);
//
//        mockedOrderProduct.setProduct(product);

        return orderService.addOrderProduct(orderProduct, customerId, orderId);
        //List<OrderProduct> orderProducts = orderService.getOrderProductBy(customerId, orderId);




    }

    @DeleteMapping( "/products/{productId}")
    public List<OrderProduct> deleteOrderProductById(@PathVariable int customerId,
                                          @PathVariable int orderId, @PathVariable int productId) {

        orderService.deleteOrderProductbyId(orderId, productId);
        return orderService.getOrderProductBy(customerId,orderId);


    }



}
