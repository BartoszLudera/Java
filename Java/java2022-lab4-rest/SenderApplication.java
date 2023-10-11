package pl.edu.uj.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import pl.edu.uj.textindexer.TextIndexerRestfulApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static pl.edu.uj.sender.EmailSender.*;
@SpringBootApplication
public class SenderApplication {

  public static void main(String[] args) {
    SpringApplication.run(SenderApplication.class, args);
  }
}