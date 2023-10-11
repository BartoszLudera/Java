package pl.edu.uj.sender;

public class PushMessage extends Message {
  private final String messageTitle;
  private final String messageBody;

  public PushMessage(String messageTitle, String messageBody) {
    this.messageTitle = messageTitle;
    this.messageBody = messageBody;
  }

  @Override
  public String getMessageTitle() {
    return messageTitle;
  }

  @Override
  public String getMessageBody() {
    return messageBody;
  }

  @Override
  public void validateMessage() throws SenderException {
    if (messageBody.isEmpty() || messageTitle.isEmpty()) {
      throw new IllegalArgumentException("Message body or message title is empty");
    }
    if (messageBody.length() > 256) {
      throw new IllegalArgumentException("Message body length is more than 256 symbol");
    }
    if (Message.class == null || Recipient.class == null || Message.class.isMemberClass() == false || Recipient.class.isMemberClass() == false) {
      throw new IllegalArgumentException("Message class or Recipient class are null or aren't class");
    }
  }

  @Override
  void validateMessage() throws SenderException {

  }
}
