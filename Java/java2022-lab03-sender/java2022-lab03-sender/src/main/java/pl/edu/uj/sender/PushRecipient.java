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

    void validateRecipient() {
        if (recipientAddress.matches("^[a-zA-Z0-9]*$")) {
        } else {
            throw new IllegalArgumentException("Push message have unknown symbols");
        }
    }


    @Override
    String anonymize() {
        String anonymizedRecipientAddress;
        anonymizedRecipientAddress = recipientAddress.substring(recipientAddress.length() - 5, recipientAddress.length() - 1);
        for (int i = 0; i < recipientAddress.length() - 5; i++) {
            anonymizedRecipientAddress = '.' + anonymizedRecipientAddress;
        }
        return anonymizedRecipientAddress;
    }
}
