package pl.edu.uj.sender;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.*;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public abstract class Message {

    abstract String getMessageTitle();

    abstract String getMessageBody();

    abstract void validateMessage() throws SenderException;

    String anonymizeMessageBody() {
        return DigestUtils.md5Hex(getMessageBody()).toUpperCase();
    }
}
