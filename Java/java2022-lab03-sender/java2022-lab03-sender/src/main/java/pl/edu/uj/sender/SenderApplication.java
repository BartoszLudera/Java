package pl.edu.uj.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;

public class SenderApplication {

    private static final Logger logger = LoggerFactory.getLogger(SenderApplication.class);

    public static void main(String[] args) {
        {
            Message message = new PushMessage("TestTitle", "Test message body");
            Recipient recipient = new PushRecipient("bartoszludera");
            Sender sender = new PushSender();
            try {
                sender.send(message, recipient);
            } catch (SenderException | NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            logger.info("PushMessage sent");
        }


    }
}
