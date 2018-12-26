package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.entityAudit.Message;
import test.repositories.OrderAuditRepository;
import test.repositories.*;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class FixedChanges implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(FixedChanges.class);

    @Autowired
    private OrderAuditRepository orderAuditRepository;
    private  OrderPosAuditRepository orderPosAuditRepository;
    private  UserAuditRepository userAuditRepository;
    private  IsuePointAuditRepository isuePointAuditRepository;
    private  ProductAuditRepository productAuditRepository;
    private ProductGroupAuditRepository productGroupAuditRepository;


    public List<Message> collectChange() {
        List<Message> messages = new CopyOnWriteArrayList<>();

        isuePointAuditRepository.findAll().parallelStream()
                .forEach(isuePointAudit -> {
                    isuePointAudit.setRead(true);
                    isuePointAuditRepository.save(isuePointAudit);
                    try {
                        String value = new ObjectMapper().writeValueAsString(isuePointAudit);
                        messages.add(new Message("IsuePoint", value, isuePointAudit.getStamp()));
                    } catch (JsonProcessingException e) {
                        logger.error("Error", e);
                    }
                });

        orderPosAuditRepository.findAll().parallelStream()
                .forEach(orderPosAudit -> {
                    orderPosAudit.setRead(true);
                    orderPosAuditRepository.save(orderPosAudit);
                    try {
                        String value = new ObjectMapper().writeValueAsString(orderPosAudit);
                        messages.add(new Message("OrderPos", value, orderPosAudit.getStamp()));
                    } catch (JsonProcessingException e) {
                        logger.error("Error", e);
                    }
                });

        orderAuditRepository.findAll().parallelStream()
                .forEach(orderAudit -> {
                    orderAudit.setRead(true);
                    orderAuditRepository.save(orderAudit);
                    try {
                        String value = new ObjectMapper().writeValueAsString(orderAudit);
                        messages.add(new Message("Order", value, orderAudit.getStamp()));
                    } catch (JsonProcessingException e) {
                        logger.error("Error", e);
                    }
                });

        productAuditRepository.findAll().parallelStream()
                .forEach(productAudit -> {
                    productAudit.setRead(true);
                    productAuditRepository.save(productAudit);
                    try {
                        String value = new ObjectMapper().writeValueAsString(productAudit);
                        messages.add(new Message("Product", value, productAudit.getStamp()));
                    } catch (JsonProcessingException e) {
                        logger.error("Error", e);
                    }
                });

        productGroupAuditRepository.findAll().parallelStream()
                .forEach(productGroupAudit -> {
                    productGroupAudit.setRead(true);
                    productGroupAuditRepository.save(productGroupAudit);
                    try {
                        String value = new ObjectMapper().writeValueAsString(productGroupAudit);
                        messages.add(new Message("ProductGroup", value, productGroupAudit.getStamp()));
                    } catch (JsonProcessingException e) {
                        logger.error("Error", e);
                    }
                });

        userAuditRepository.findAll().parallelStream()
                .forEach(userAudit -> {
                    userAudit.setRead(true);
                    userAuditRepository.save(userAudit);
                    try {
                        String value = new ObjectMapper().writeValueAsString(userAudit);
                        messages.add(new Message("User", value, userAudit.getStamp()));
                    } catch (JsonProcessingException e) {
                        logger.error("Error", e);
                    }
                });

        return messages;
    }

  /*  String SerializedRecord(){
        String answer = null;

        Optional<ChangeController> maybeChangeController = changeControllerRepository.findById(idRecord + 1);
        Integer finalIdRecord = idRecord;
        ChangeController changeController = maybeChangeController
                .orElseThrow(() -> new ExpressionException(String.valueOf(finalIdRecord + 1)));
        Integer idRecordInRep = changeController.getRecordChangedId();
        String nameEntity = changeController.getNameTableOfChanged().replaceAll("\\s", "");

        if (nameEntity.equals("IsuePoint")){
            answer = SerializedRecordIsuePoint(idRecordInRep);
        }
        if (nameEntity.equals("Users")){
            answer = SerializedRecordUser(idRecordInRep);
        }
        if (nameEntity.equals("ProductGroup")){
            answer = SerializedRecordProductGroup(idRecordInRep);
        }
        if (nameEntity.equals("Order")){
            answer = SerializedRecordOrder(idRecordInRep);
        }
        if (nameEntity.equals("Product")){
            answer = SerializedRecordProduct(idRecordInRep);
        }
        if (nameEntity.equals("OrderPos")){
            answer = SerializedRecordOrderPos(idRecordInRep);
        }

        idRecord = idRecord+1;

        return answer;
    }

    String ComparisonId(Integer idRecord) {
        Integer count = 2;
        incr = count - idRecord;
        String answer = null;
            if (incr != 0) {
                answer = SerializedRecord();
                incr = incr - 1;
            }
        return answer;
    }


    String SerializedRecordUser(Integer idRecordInRep) {
        String messageText = null;
        Optional<User> user = userRepository.findById(idRecordInRep);
        messageText = new GsonBuilder().create().toJson(user);
        return messageText;
    }

    String SerializedRecordIsuePoint(Integer idRecordInRep) {
        String messageText = null;
        Optional<IsuePoint> isuePoint = isuePointRepository.findById(idRecordInRep);
        messageText = new GsonBuilder().create().toJson(isuePoint);
        return messageText;
    }

    String SerializedRecordProductGroup(Integer idRecordInRep) {
        String messageText = null;
        Optional<ProductGroup> productGroup = productGroupRepository.findById(idRecordInRep);
        messageText = new GsonBuilder().create().toJson(productGroup);
        return messageText;
    }

    String SerializedRecordProduct(Integer idRecordInRep) {
        String messageText = null;
        Optional<Product> product = productRepository.findById(idRecordInRep);
        messageText = new GsonBuilder().create().toJson(product);
        return messageText;
    }

    String SerializedRecordOrder(Integer idRecordInRep) {
        String messageText = null;
        Optional<Order> order = orderRepository.findById(idRecordInRep);
        messageText = new GsonBuilder().create().toJson(order);
        return messageText;
    }

    String SerializedRecordOrderPos(Integer idRecordInRep) {
        String messageText = null;
        Optional<OrderPos> orderPos = orderPosRepository.findById(idRecordInRep);
        messageText = new GsonBuilder().create().toJson(orderPos);
        return messageText;
    }*/
}