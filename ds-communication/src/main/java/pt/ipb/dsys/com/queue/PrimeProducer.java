package pt.ipb.dsys.com.queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import java.util.Random;

public class PrimeProducer {

    private static final Logger logger = LoggerFactory.getLogger(PrimeProducer.class);

    public static void main(String[] args) throws InterruptedException {
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection connection = factory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("prime");

            MessageProducer producer = session.createProducer(queue);

            int i = 10;
            Random random = new Random();
            while (i-- > 0) {
                int num = random.nextInt(100);
                ActiveMQTextMessage message = new ActiveMQTextMessage();
                logger.info("sending number: {}", i);
                message.setText(String.format("%d", num));
                producer.send(message);
            }

            producer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}
