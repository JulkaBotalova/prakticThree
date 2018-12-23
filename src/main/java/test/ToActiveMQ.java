package test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Serializable;

public class ToActiveMQ implements Serializable {

    private static final String URL = "tcp://localhost:61616";
    private static final String QUEUE = "testQueue";

    public void ToActiveMQ(String[] args) {

        try{
            Connection connection = new ActiveMQConnectionFactory(URL).createConnection("admin", "admin");
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE);
            MessageProducer messageProducer = session.createProducer(destination);
            //FixedChangesToActiveMQ fixedChangesToActiveMQ = new FixedChangesToActiveMQ(changeControllerRepository, orderPosRepository, orderRepository, userRepository, isuePointRepository, productRepository, productGroupRepository);
            //String messageText = fixedChangesToActiveMQ.ComparisonId();
            try {
                TextMessage textMessage = session.createTextMessage(String.valueOf(""));
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
