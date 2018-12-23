package test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Application{
    private static final String URL = "tcp://localhost:61616";
    private static final String QUEUE = "testQueue";

    public static void main(String[] args)  {
        //System.out.println("Hi");
        try{
            Connection connection = new ActiveMQConnectionFactory(URL).createConnection("admin", "admin");
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE);
            MessageProducer messageProducer = session.createProducer(destination);

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


