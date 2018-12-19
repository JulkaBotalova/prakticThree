package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.entity.*;
import test.repositories.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@RestController
public class HelloEndpoint {

    private final OrderRepository orderRepository;
    private final OrderPosRepository orderPosRepository;
    private final UserRepository userRepository;
    private final IsuePointRepository isuePointRepository;
    private final ProductRepository productRepository;
    private final ProductGroupRepository productGroupRepository;

    private Random random = new Random();

    @Autowired
    public HelloEndpoint(OrderRepository orderRepository, OrderPosRepository orderPosRepository, UserRepository userRepository, IsuePointRepository isuePointRepository, ProductRepository productRepository, ProductGroupRepository productGroupRepository){

        this.orderRepository = orderRepository;
        this.orderPosRepository = orderPosRepository;
        this.userRepository = userRepository;
        this.isuePointRepository = isuePointRepository;
        this.productRepository = productRepository;
        this.productGroupRepository = productGroupRepository;
    }

    @RequestMapping("hi/hello")
    String abc() {

        User user = new User();
        IsuePoint isuePoint = new IsuePoint();
        ProductGroup productGroup = new ProductGroup();

        user.setUsername("User" +String.valueOf(random.nextInt()));
        user.setUserphone("88005553535");

        userRepository.save(user);

        isuePoint.setIsuePointName("Point" +String.valueOf(random.nextInt()));
        isuePoint.setAddress("Lenina" +String.valueOf(random.nextInt()));
        isuePointRepository.save(isuePoint);

        productGroup.setProductGroupName("GoodProducts");
        productGroup.setRemark("FreshProducts");
        productGroupRepository.save(productGroup);


        for(Integer i = 0; i<=5; i++) {
            Order order1 = new Order();
            Product product = new Product();

            Set<Order> order = new HashSet<Order>();

            order1.setUser(user);
            order1.setIsuePoint(isuePoint);
            order1.setPhone("8- " + String.valueOf(random.nextInt()));
            order1.setRemark("Remark " + i.toString());

            user.setOrderUs(order);
            isuePoint.setOrder(order);
            orderRepository.save(order1);

            Set<Product> products = new HashSet<Product>();
            productGroup.setProducts(products);


            product.setProductGroup(productGroup);
            product.setProductName(String.valueOf(random.nextInt()));
            product.setProductPrice(random.nextInt());

            productRepository.save(product);

            if (i == 5) {
                for(int j=0;j<=5; j++){
                    OrderPos orderPos = new OrderPos();
                    order1.setOrderPoses(new HashSet<OrderPos>());
                    product.setOrderPoss(new HashSet<OrderPos>());


                    orderPos.setPrice(random.nextInt());
                    orderPos.setQuantity(random.nextInt());
                    orderPos.setGoodName(String.valueOf(random.nextInt()));

                    orderPos.setOrder(order1);
                    orderPos.setProduct(product);

                    orderPosRepository.save(orderPos);
                }


            }

        }

        return "OK";
    }
}
