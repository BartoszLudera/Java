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
  void validateRecipient() throws SenderException {
    if(recipientAddress.indexOf('@')==-1 || recipientAddress.chars().filter(ch->ch =='@').count()!=1){
      throw new SenderException();
    }
  }

  @Override
  String anonymize() {

    String anonymizedRecipientAddress;
    anonymizedRecipientAddress=recipientAddress.substring(recipientAddress.length()-5,recipientAddress.length());
    for(int i=0;i<recipientAddress.length()-5;i++){
      anonymizedRecipientAddress='*'+anonymizedRecipientAddress;
    }
    return anonymizedRecipientAddress;
  }
}