package siit.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.OrderDao;
import siit.db.OrderProductDao;
import siit.model.Order;
import siit.model.OrderProduct;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderProductDao orderProductDao;
    @Autowired
    OrderDao orderDao;

    public void deleteOrderBy(int orderId) {
        orderDao.deleteOrderProduct(orderId);
        orderDao.deleteOrderBy(orderId);
    }

    public List<OrderProduct> getOrderProductBy(int customerId, int orderId) {
        return orderProductDao.getOrderProductBy(orderId);
    }

    public Order getOrderBy(int customerId, int orderId) {
        for (Order order : orderDao.getOrdersBy(customerId)) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public void deleteOrderProductbyId(int orderId, int productId){

            orderProductDao.deleteOrderProductById(orderId, productId);

    }

    public OrderProduct addOrderProduct(OrderProduct orderProduct, int customerId, int orderId){

       List<OrderProduct> orderProducts = orderProductDao.getOrderProductBy(orderId);
            for (OrderProduct orderProductTemp :orderProducts) {

                if (orderProductTemp.getProduct().getId() == orderProduct.getProduct().getId()) {
                    BigDecimal quantity = orderProductTemp.getQuantity().add(orderProduct.getQuantity());
                    orderProductDao.updateProductQuantity(orderProductTemp.getId(), orderProductTemp.getProduct().getId(), quantity);
                    orderProducts = orderProductDao.getOrderProductBy(orderId);
                    for (OrderProduct temp : orderProducts){
                        if (temp.getProduct().getId() == orderProduct.getProduct().getId()){
                            return temp;
                        }
                    }
                }


            }

                orderProductDao.addProduct(orderProduct, customerId, orderId);
                orderProducts = orderProductDao.getOrderProductBy(orderId);
                for (OrderProduct temp : orderProducts){
                    if (temp.getProduct().getId() == orderProduct.getProduct().getId()){
                        return temp;
                    }
                }

                return orderProduct;


    }


    public void addOrder(Order order, int customerId) {

        orderDao.addOrder(order, customerId);
    }
}
