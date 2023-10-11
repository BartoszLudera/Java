package pl.edu.uj.sender;

public class PushRecipient extends Recipient {
  private final String recipientAddress;

  public PushRecipient(String recipientAddress) {
    this.recipientAddress = recipientAddress;
  }

  @Override
  public String getRecipientAddress() {
    return recipientAddress;
  }

  @Override
  void validateRecipient() throws SenderException {
    for(int i=0;i<recipientAddress.length()-1;i++){
      if(recipientAddress.charAt(i)<48 || (recipientAddress.charAt(i)>57 && recipientAddress.charAt(i)<65) || recipientAddress.charAt(i)>90){
        throw new SenderException();
      }
    }
  }

  @Override
  String anonymize() {

    String anonymizedRecipientAddress;
    anonymizedRecipientAddress=recipientAddress.substring(recipientAddress.length()-5,recipientAddress.length()-1);
    for(int i=0;i<recipientAddress.length()-5;i++){
      anonymizedRecipientAddress='.'+anonymizedRecipientAddress;
    }
    return anonymizedRecipientAddress;
  }
}