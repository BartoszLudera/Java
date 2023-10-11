package pl.edu.uj.sender;

import static java.lang.Thread.sleep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailSender implements Sender {

  private static final Logger logger = LoggerFactory.getLogger(EmailSender.class);

  @Override
  public void send(Message message, Recipient recipient)
          throws SenderException, InterruptedException {

    if(message instanceof EmailMessage == false|| recipient instanceof EmailRecipient == false ){
      throw new SenderException();
    }


    message.validateMessage();

    recipient.validateRecipient();

    String bodyMD5 = message.anonymizeMessageBody();
    String anonymizedRecipientAddress = recipient.anonymize();


    sleep(5000); // sending

    /* Use System.out to graphically distinguish sending from logging */
    System.out.printf("[Email] Message sent, title= '%s', bodyMD5= '%s', recipient= '%s'%n",
            message.getMessageTitle(), bodyMD5, anonymizedRecipientAddress);
  }
}