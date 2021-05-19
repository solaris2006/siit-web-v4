package siit.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.OrderProductDao;
import siit.db.ProductDao;
import siit.model.Product;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    OrderProductDao orderProductDao;

    public List<Product> getProductsBy(String term){
        return productDao.getProductsBy(term);
    }

    public List<Product> getProductByOrderId(int orderId) {return  productDao.getProductsByOrderId(orderId);}



}
