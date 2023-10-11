package pl.edu.uj.sender;

import java.util.Random;
public class EmailRecipientProvider extends RecipientProvider {

    Random random = new Random();

    @Override
    public Recipient getNextRecipient() {
        final int i = random.nextInt(3);
        if(i==0){
            return new EmailRecipient("abc@mail.com");
        }if(i==1){
            return new EmailRecipient("cda@mail.pl");
        }if(i==2){
            return new EmailRecipient("gpu@mail.pl");
        }else throw new IllegalArgumentException();
    }
}