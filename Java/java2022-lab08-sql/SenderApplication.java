package pl.edu.uj.sender;

import static java.lang.Thread.sleep;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SenderApplication {

  private static final Logger logger = LoggerFactory.getLogger(SenderApplication.class);

  public static void main(String[] args) throws InterruptedException {


    if (args.length == 2) {
      final int numberOfEnqueuingThreads = Integer.parseInt(args[0]);
      final int numberOfSendingThreads = Integer.parseInt(args[1]);
      logger.info("There will be %d enqueuing threads and %d sender threads".formatted(numberOfEnqueuingThreads, numberOfSendingThreads));

      EmailSender emailSender = new EmailSender();
      EmailMessageProvider messageProvider = new EmailMessageProvider();
      RecipientProvider recipientProvider = new EmailRecipientProvider();

      List<EmailPackage> queue = new ArrayList<>();

      DbConnector.connect();

      List<Thread> threads = new ArrayList<>();
      for (int i = 0; i < numberOfEnqueuingThreads; i++) {
        threads.add(new Thread(new EmailEnquerRunnable(messageProvider, recipientProvider, queue)));
      }
      for (int i = 0; i < numberOfSendingThreads; i++) {
        threads.add(new Thread(new EmailSenderRunnable(queue, emailSender)));
      }

      for (Thread thread : threads) {
        thread.start();
      }
      for (Thread thread : threads) {
        thread.join();
      }
      DbConnector.disconnect();
    } else {
      logger.error("Params should be: enqueuing-threads-count sender-threads-count");
      System.exit(-1);
    }
  }

  private static class EmailPackage {

    final Message nextMessage;
    final Recipient nextRecipient;

    public EmailPackage(Message nextMessage, Recipient nextRecipient) {
      this.nextMessage = nextMessage;
      this.nextRecipient = nextRecipient;
    }
  }

  private static class EmailEnquerRunnable implements Runnable {

    private final EmailMessageProvider messageProvider;
    private final RecipientProvider recipientProvider;
    private final List<EmailPackage> queue;

    public EmailEnquerRunnable(EmailMessageProvider messageProvider, RecipientProvider recipientProvider, List<EmailPackage> queue) {
      this.messageProvider = messageProvider;
      this.recipientProvider = recipientProvider;
      this.queue = queue;
    }

    @Override
    public void run() {
      Message nextMessage;
      int i=1;
      long key=0;

      try {
        do {

          nextMessage = messageProvider.getNextMessage();
          if (nextMessage != null) { // Wyślemy 100 wiadomości

            Statement cStatement="INSERT INTO uj_sender.email_message VALUES ("+nextMessage.getMessageTitle()+","+nextMessage.getMessageBody()+");";
            QueryExecutor.executeQuery("INSERT INTO uj_sender.email_message VALUES ("+nextMessage.getMessageTitle()+","+nextMessage.getMessageBody()+");");
            final ResultSet rs = cStatemtn.getGeneratedKeys();// Get id of the last inserted value
              if (rs.next()) {return rs.getLong(1);
            }
            final Recipient nextRecipient = recipientProvider.getNextRecipient();
            QueryExecutor.executeQuery("INSERT INTO uj_sender.email_recipient VALUES ("+i+","+nextRecipient.getRecipientAddress()+");");

              logger.info("Enqueueing message.");

              QueryExecutor.executeQuery(" INTO uj_sender.email_queue (email_message_id,email_recipient_id) VALUES ("+i+","+i+");");
              queue.add(new EmailPackage(nextMessage, nextRecipient));

          }
          i++;
        } while (nextMessage != null);
      } catch (InterruptedException e) {
        logger.error("Couldn't enqueue message", e);
      }
    }
  }

  private static class EmailSenderRunnable implements Runnable {

    private final List<EmailPackage> queue;
    private final EmailSender emailSender;
    private int counter = 0;

    public EmailSenderRunnable(List<EmailPackage> queue, EmailSender emailSender) {
      this.queue = queue;
      this.emailSender = emailSender;
    }

    @Override
    public void run() {
      do {
        try {
          EmailPackage emailPackage = null;

          try {
            ResultSet result=QueryExecutor.executeSelect("SELECT * FROM uj_sender.email_queue WHERE status_id = 0 LIMIT 1;");
            result.next();
            QueryExecutor.executeQuery("UPDATE uj_sender.email_queue SET status_id = 1 WHERE email_queue_id  = 268 AND status_id = 0;");
          } catch (SQLException e) {
            throw new RuntimeException(e);
          }

          if (!queue.isEmpty()) {
              logger.info("Getting message package to send.");
              emailPackage = queue.remove(0);
//              SELECT * FROM uj_sender.email_queue WHERE status_id = 0 LIMIT 1;
//              UPDATE uj_sender.email_queue SET status_id = 1 WHERE email_queue_id  = 268 AND status_id = 0;
            }

          if (emailPackage != null) {
            logger.info("Delivering message to send.");
            emailSender.send(emailPackage.nextMessage, emailPackage.nextRecipient);
            QueryExecutor.executeQuery("DELETE FROM uj_sender.email_queue ");
            counter = 0;
          } else {
            logger.info("No email to send, waiting.");
            counter++;
            sleep(1000); // wait for new element in the queue
          }
        } catch (SenderException | InterruptedException e) {
          logger.error("Couldn't send a message", e);
        }
      } while (counter < 100);
    }
  }
}
