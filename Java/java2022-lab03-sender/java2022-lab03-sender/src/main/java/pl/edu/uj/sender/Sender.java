package pl.edu.uj.sender;

import java.security.NoSuchAlgorithmException;

public interface Sender {

    void send(Message message, Recipient recipient) throws SenderException, InterruptedException, NoSuchAlgorithmException;
}
