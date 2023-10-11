package pl.edu.uj.sender;

public class EmailMessageProvider extends MessageProvider {

  int actualMessagesCount = 0;

  @Override
  public Message getNextMessage() {
    synchronized (this) {
      if (actualMessagesCount < 100) {
        return new EmailMessage("Title: message nr %d".formatted(actualMessagesCount++), "aabbbdsfsadf...");
      }
      return null;
    }
  }
}