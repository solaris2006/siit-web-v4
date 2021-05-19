package siit.sevices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.OrderDao;
import siit.db.OrderProductDao;

@Service
public class ProductValueService {


    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderProductDao orderProductDao;


}
