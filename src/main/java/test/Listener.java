package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.IOException;

@Component
public class Listener {
    private static final Logger logger = LoggerFactory.getLogger(Listener.class);

    @Autowired
    private ReplicationService replicationService;

    @JmsListener(destination = "myQueue")
    public void receiveMessage(final Message jsonMessage) throws JMSException {
        if(jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)jsonMessage;

            try {
                test.entityAudit.Message message = new ObjectMapper().readValue(textMessage.getText(), test.entityAudit.Message.class);
                replicationService.execReplication(message);
            } catch (IOException e) {
                logger.error("Error listener", e);
            }

        }
    }

}
