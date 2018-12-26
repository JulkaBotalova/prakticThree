package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.entityAudit.*;
import test.repositories.*;

import java.io.IOException;

@Service
public class ReplicationService{

    @Autowired
    private ChangeControllerRepository changeControllerRepository;
    private OrderRepository orderRepository;
    private OrderPosRepository orderPosRepository;
    private UserRepository userRepository;
    private IsuePointRepository isuePointRepository;
    private  ProductRepository productRepository;
    private  ProductGroupRepository productGroupRepository;

    public void execReplication(Message message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        if (message.getName().equalsIgnoreCase("IsuePoint")) {
            IsuePointAudit isuePointAudit = objectMapper.readValue(message.getJson(), IsuePointAudit.class);
            if (isuePointAudit.getOperation().equalsIgnoreCase("D")) {
                isuePointRepository.delete(isuePointAudit.getIsuePoint());
            } else {
                isuePointRepository.save(isuePointAudit.getIsuePoint());
            }
        } else if (message.getName().equalsIgnoreCase("Order")) {
            OrderAudit orderAudit = objectMapper.readValue(message.getJson(), OrderAudit.class);
            if (orderAudit.getOperation().equalsIgnoreCase("D")) {
                orderRepository.delete(orderAudit.getOrder());
            } else {
                orderRepository.save(orderAudit.getOrder());
            }
        } else if (message.getName().equalsIgnoreCase("OrderPos")) {
            OrderPosAudit orderPosAudit = objectMapper.readValue(message.getJson(), OrderPosAudit.class);
            if (orderPosAudit.getOperation().equalsIgnoreCase("D")) {
                orderPosRepository.delete(orderPosAudit.getOrderPos());
            } else {
                orderPosRepository.save(orderPosAudit.getOrderPos());
            }

        } else if (message.getName().equalsIgnoreCase("Product")) {
            ProductAudit productAudit = objectMapper.readValue(message.getJson(), ProductAudit.class);
            if (productAudit.getOperation().equalsIgnoreCase("D")) {
                productRepository.delete(productAudit.getProduct());
            } else {
                productRepository.save(productAudit.getProduct());
            }

        } else if (message.getName().equalsIgnoreCase("ProductGroup")) {
            ProductGroupAudit productGroupAudit = objectMapper.readValue(message.getJson(), ProductGroupAudit.class);
            if (productGroupAudit.getOperation().equalsIgnoreCase("D")) {
                productGroupRepository.delete(productGroupAudit.getProductGroup());
            } else {
                productGroupRepository.save(productGroupAudit.getProductGroup());
            }
        } else if (message.getName().equalsIgnoreCase("User")) {
            UserAudit userAudit = objectMapper.readValue(message.getJson(), UserAudit.class);
            if (userAudit.getOperation().equalsIgnoreCase("D")) {
                userRepository.delete(userAudit.getUser());
            } else {
                userRepository.save(userAudit.getUser());
            }
        }
    }
}
