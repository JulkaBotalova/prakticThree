package test;

public class Application{
    private static final String URL = "tcp://localhost:61616";
    private static final String QUEUE = "testQueue23";

    public static void main(String[] args)  {
     /*   String answer = null;
        try{
            Connection connection = new ActiveMQConnectionFactory(URL).createConnection("admin", "admin");
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE);
            MessageProducer messageProducer = session.createProducer(destination);
            FixedChangesToActiveMQ fixedChangesToActiveMQ = new FixedChangesToActiveMQ();

            String messageText = fixedChangesToActiveMQ.ComparisonId(1);
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
        }*/

    }


    }



