package test;

import com.google.gson.GsonBuilder;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import test.entity.ChangeController;
import test.entity.IsuePoint;
import test.repositories.*;

import javax.jms.*;
import java.util.Optional;

@RestController
public class FixedChangesToActiveMQ {
    private final ChangeControllerRepository changeControllerRepository;
    private final OrderRepository orderRepository;
    private final OrderPosRepository orderPosRepository;
    private final UserRepository userRepository;
    private final IsuePointRepository isuePointRepository;
    private final ProductRepository productRepository;
    private final ProductGroupRepository productGroupRepository;

    private static final String URL = "tcp://localhost:61616";
    private static final String QUEUE = "testQueue";

    @Autowired
    public FixedChangesToActiveMQ(ChangeControllerRepository changeControllerRepository, OrderRepository orderRepository, OrderPosRepository orderPosRepository, UserRepository userRepository, IsuePointRepository isuePointRepository, ProductRepository productRepository, ProductGroupRepository productGroupRepository) {
        this.changeControllerRepository = changeControllerRepository;
        this.orderRepository = orderRepository;
        this.orderPosRepository = orderPosRepository;
        this.userRepository = userRepository;
        this.isuePointRepository = isuePointRepository;
        this.productRepository = productRepository;
        this.productGroupRepository = productGroupRepository;
    }

    @GetMapping("/ComparisonIdInIsuePoint")
    String ComparisonId(){
        Integer idRecord = 1;
        ChangeController changeController = new ChangeController();
        Integer count = changeControllerRepository.findAll().size();
        Integer incr = count - idRecord;
        Integer idRecordIsuePoint;
        String messageText = null;
        if (incr != 0){
            if (count>idRecord) {
                try{
                    Connection connection = new ActiveMQConnectionFactory(URL).createConnection("admin", "admin");
                    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                    Destination destination = session.createQueue(QUEUE);
                    MessageProducer messageProducer = session.createProducer(destination);

                    Optional<ChangeController> maybeChangeController = changeControllerRepository.findById(count + 1);
                    changeController = maybeChangeController
                            .orElseThrow(() -> new ExpressionException(String.valueOf(count + 1)));
                    idRecordIsuePoint = changeController.getRecordChangedId();

                    Optional<IsuePoint> maybeIsuePoint = isuePointRepository.findById(idRecordIsuePoint);
                    IsuePoint isuePoint = maybeIsuePoint
                            .orElseThrow(() -> new ExpressionException(String.valueOf(idRecordIsuePoint)));

                    messageText = new GsonBuilder().create().toJson(isuePoint);

                    incr = incr - 1;
                    try {
                        TextMessage textMessage = session.createTextMessage(messageText);
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

            }


        return messageText;
    }

}
