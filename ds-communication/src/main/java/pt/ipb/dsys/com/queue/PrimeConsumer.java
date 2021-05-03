package pt.ipb.dsys.com.queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class PrimeConsumer {

    private static final Logger logger = LoggerFactory.getLogger(PrimeConsumer.class);

    public static void main(String[] args) throws InterruptedException {
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection connection = factory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("prime");
            MessageConsumer consumer = session.createConsumer(queue);

            Message message;
            while ((message = consumer.receive()) != null) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                if ("bye".equals(text))
                    break;
                logger.info("message received: {}", text);
                try {
                    int number = Integer.parseInt(text);
                    boolean prime = isPrime(number);
                    logger.info("{} is{} prime", number, prime ? "" : " NOT");
                } catch (NumberFormatException e) {
                    logger.error("{} is not a number", text);
                }
            }

            consumer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private static boolean isPrime(int number) {
        if (number % 2 == 0)
            return false;

        int candidate = number / 2;
        do {
            if (number % candidate == 0)
                return false;
        } while (candidate-- > 2);

        return true;
    }

}
