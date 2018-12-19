package test;

import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.*;
import test.entity.ChangeController;
import test.entity.Order;
import test.entity.OrderPos;
import test.entity.Product;
import test.repositories.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("ControllerAll")
public class RControllerOrderPos {

    private final OrderRepository orderRepository;
    private final OrderPosRepository orderPosRepository;
    private final UserRepository userRepository;
    private final IsuePointRepository isuePointRepository;
    private final ProductRepository productRepository;
    private final ProductGroupRepository productGroupRepository;
    private final ChangeControllerRepository changeControllerRepository;

    private Random random = new Random();

    public RControllerOrderPos(OrderRepository orderRepository, OrderPosRepository orderPosRepository, UserRepository userRepository, IsuePointRepository isuePointRepository, ProductRepository productRepository, ProductGroupRepository productGroupRepository, ChangeControllerRepository changeControllerRepository) {
        this.orderRepository = orderRepository;
        this.orderPosRepository = orderPosRepository;
        this.userRepository = userRepository;
        this.isuePointRepository = isuePointRepository;
        this.productRepository = productRepository;
        this.productGroupRepository = productGroupRepository;
        this.changeControllerRepository = changeControllerRepository;
    }

    ////////ORDERPOS///////////
    @GetMapping("/orderPos")
    Iterable<OrderPos> getAllOrderPos() {
        return (List<OrderPos>) orderPosRepository.findAll();
    }

    @GetMapping("/orderPos/{orderPosId}")
    Optional<OrderPos> getorderPosId(@PathVariable Integer orderPosId){
        return orderPosRepository.findById(orderPosId);
    }

    @RequestMapping("/orderPosCreate")
    OrderPos createOrderPos(@RequestParam(name = "idOrder", defaultValue = "") Integer idOrder,
                            @RequestParam(name = "idProduct", defaultValue = "") Integer idProduct,
                            @RequestParam(name  = "quantity", defaultValue = "") Integer quantity,
                            @RequestParam(name  = "price", defaultValue = "") Integer price,
                            @RequestParam(name  = "name", defaultValue = "") String name) {
        OrderPos orderPos = new OrderPos();
        Optional<Product> maybeProduct = productRepository.findById(idProduct);
        Product product = maybeProduct
                .orElseThrow(() -> new ExpressionException(String.valueOf(idProduct)));

        Optional<Order> maybeOrder = orderRepository.findById(idOrder);
        Order order = maybeOrder
                .orElseThrow(() -> new ExpressionException(String.valueOf(idOrder)));

        product.setOrderPoss(new HashSet<OrderPos>());
        order.setOrderPoses(new HashSet<OrderPos>());

        orderPos.setProduct(product);
        orderPos.setOrder(order);

        orderPos.setQuantity(quantity);
        orderPos.setPrice(price);
        orderPos.setGoodName(name);

        order.getOrderPoses().add(orderPos);
        product.getOrderPoss().add(orderPos);
        orderPos = orderPosRepository.save(orderPos);

        ChangeController changeController = new ChangeController();
        changeController.setNameTableOfChanged("OrderPos");
        changeController.setRecordChangedId(orderPos.getId());
        changeController.setActionWithTable("Create");

        changeControllerRepository.save(changeController);
        return orderPos;
    }

    @RequestMapping("/orderPosUp")
    OrderPos updateOrderPos(@RequestParam(name = "id", defaultValue = "") Integer id,
                            @RequestParam(name = "idOrder", defaultValue = "") Integer idOrder,
                            @RequestParam(name = "idProduct", defaultValue = "") Integer idProduct,
                            @RequestParam(name  = "quantity", defaultValue = "") Integer quantity,
                            @RequestParam(name  = "price", defaultValue = "") Integer price,
                            @RequestParam(name  = "name", defaultValue = "") String name) {
        OrderPos orderPosition = new OrderPos();
        if (orderPosRepository.findAll().size()<id){
            orderPosition = createOrderPos(idOrder, idProduct, quantity, price, name);
        }
        else{
            Optional<OrderPos> maybeOrder = orderPosRepository.findById(id);
            OrderPos orderPos = maybeOrder
                .orElseThrow(() -> new ExpressionException(String.valueOf(idOrder)));
            orderPos.setQuantity(quantity);
            orderPos.setPrice(price);
            orderPos.setGoodName(name);
            orderPosition = orderPos;
            orderPosRepository.save(orderPos);
        }

        ChangeController changeController = new ChangeController();
        changeController.setNameTableOfChanged("OrderPos");
        changeController.setRecordChangedId(id);
        changeController.setActionWithTable("Update");

        changeControllerRepository.save(changeController);

        return orderPosition;
    }

    @GetMapping("/orderPosDel/{orderPosId}")
    OrderPos deleteOrderPos(@PathVariable Integer orderPosId) throws Exception {
        OrderPos orderPos = orderPosRepository.findById(orderPosId)
                .orElseThrow(() -> new ExpressionException(String.valueOf(orderPosId)));
        orderPosRepository.delete(orderPos);

        ChangeController changeController = new ChangeController();
        changeController.setNameTableOfChanged("OrderPos");
        changeController.setRecordChangedId(orderPosId);
        changeController.setActionWithTable("Delete");

        changeControllerRepository.save(changeController);

        return  orderPos;
    }



}
