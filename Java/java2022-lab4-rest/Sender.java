package pl.edu.uj.sender;

import org.springframework.stereotype.Service;


public interface Sender {

  void send(Message message, Recipient recipient) throws SenderException, InterruptedException;
}
