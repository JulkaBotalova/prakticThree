package test;

import com.google.gson.GsonBuilder;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.expression.ExpressionException;
import test.entity.*;
import test.repositories.*;

import javax.jms.*;
import java.io.Serializable;
import java.util.Optional;

public class FixedChangesToActiveMQ implements Serializable {
    private final ChangeControllerRepository changeControllerRepository;
    private final OrderRepository orderRepository;
    private final OrderPosRepository orderPosRepository;
    private final UserRepository userRepository;
    private final IsuePointRepository isuePointRepository;
    private final ProductRepository productRepository;
    private final ProductGroupRepository productGroupRepository;
    public Integer idRecord = 0;
    Integer incr = null;

    private static final String URL = "tcp://localhost:61616";
    private static final String QUEUE = "testQueue";

    public Integer getIdRecord(){
        return  idRecord;
    }

    public FixedChangesToActiveMQ(ChangeControllerRepository changeControllerRepository, OrderPosRepository orderPosRepository, OrderRepository orderRepository, UserRepository userRepository, IsuePointRepository isuePointRepository, ProductRepository productRepository, ProductGroupRepository productGroupRepository) {
        this.changeControllerRepository = changeControllerRepository;
        this.orderPosRepository = orderPosRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;

        this.isuePointRepository = isuePointRepository;
        this.productRepository = productRepository;
        this.productGroupRepository = productGroupRepository;
    }

    public static void main(String[] args)  {
        String answer = null;
        try{
            Connection connection = new ActiveMQConnectionFactory(URL).createConnection("admin", "admin");
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE);
            MessageProducer messageProducer = session.createProducer(destination);
           // answer = ComparisonId(); ?????????????????????????????
            try {
                TextMessage textMessage = session.createTextMessage();
                messageProducer.send(textMessage);
            } catch (JMSException jmsException) {
                jmsException.printStackTrace();
            } finally {
                session.close();
                connection.close();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }


    }

    String SerializedRecord(){
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
        Integer count = changeControllerRepository.findAll().size();
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
    }
}