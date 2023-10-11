package pl.edu.uj.sender;

import com.mysql.cj.util.StringUtils;

public class EmailMessage extends Message {

  private final String messageTitle;
  private final String messageBody;

  public EmailMessage(String messageTitle, String messageBody) {
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


  public void validateMessage() throws  SenderException{
    if(StringUtils.isNullOrEmpty(messageBody)|| StringUtils.isNullOrEmpty(messageTitle)){
      throw new SenderException();
    }
  }

}