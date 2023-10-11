package pl.edu.uj.sender;

public class EmailRecipient extends Recipient {

  private final String recipientAddress;


  public EmailRecipient(String recipientAddress) {
    this.recipientAddress = recipientAddress;
  }

  public String getRecipientAddress() {
    return recipientAddress;
  }

  @Override
  public void validateRecipient() {
    int counter = 0;
    for (int i = 0; i < recipientAddress.length(); i++) {
      if (recipientAddress.charAt(i) == '@') {
        counter++;
      }
    }
    if (counter != 1) {
      throw new IllegalArgumentException("In email adress is more than 1 or 0 symbol of '@'");
    }
  }

  @Override
  String anonymize() {
    String anonymizedRecipientAddress;
    anonymizedRecipientAddress = recipientAddress.substring(recipientAddress.length() - 5, recipientAddress.length() - 1);
    for (int i = 0; i < recipientAddress.length() - 5; i++) {
      anonymizedRecipientAddress = '*' + anonymizedRecipientAddress;
    }
    return anonymizedRecipientAddress;
  }
}
