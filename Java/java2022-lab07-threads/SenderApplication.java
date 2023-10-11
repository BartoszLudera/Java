package pl.edu.uj.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

public class SenderApplication {

  private static final int THREADS_COUNT = 4;
  static int messagesSent = 0;
  private static final Logger logger = LoggerFactory.getLogger(SenderApplication.class);

  public static void main(String[] args) throws InterruptedException {

    EmailSender emailSender = new EmailSender();
    MessageProvider messageProvider = new EmailMessageProvider();
    RecipientProvider recipientProvider = new EmailRecipientProvider();

    Runnable sendEmails =
            new Runnable() {

              @Override
              public void run() {
                synchronized (this) {
                  while (messagesSent < 100) {
                    Message message = messageProvider.getNextMessage();
                    Recipient recipient = recipientProvider.getNextRecipient();

                    try {
                      emailSender.send(message, recipient);

                    } catch (SenderException e) {
                      throw new RuntimeException(e);
                    }

                    logger.info("Messege %s sent to %s".formatted(message, recipient));
                    messagesSent++;
                  }
                }
              }
            };

    Thread[] threads = new Thread[THREADS_COUNT];

    for (int i = 0; i < THREADS_COUNT; i++) {
      Thread t = new Thread(sendEmails);
      threads[i] = t;
      t.start();
    }

    for (int i = 0; i < THREADS_COUNT; i++) {
      threads[i].join();
    }

    logger.info("Total %d messages sent".formatted(messagesSent));
  }
}
public class EmailRecipientProvider extends RecipientProvider {

  Random random = new Random();

  @Override
  public Recipient getNextRecipient() {
    final int i = random.nextInt(3);
    if(i==0){
      return new EmailRecipient("abc@mail.com");
    }if(i==1){
      return new EmailRecipient("cda@mail.pl");
    }if(i==2){
      return new EmailRecipient("gpu@mail.pl");
    }else throw new IllegalArgumentException();
  }
}

public class EmailMessageProvider extends MessageProvider {

  int actualMessagesCount = 0;

  @Override
  public Message getNextMessage() {
    synchronized (this) {
      if (actualMessagesCount < 100) {
        return new EmailMessage("Title: message nr %d".formatted(++actualMessagesCount), "Lorem ipsum...");
      }
      return null;
    }
  }
}