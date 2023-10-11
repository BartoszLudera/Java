package pl.edu.uj.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static pl.edu.uj.sender.EmailSender.*;

@Service
@RestController
public class SenderController {

   // @Autowired
  //  SenderService senderService;

    @RequestMapping("/email")
    public @ResponseBody
    String getIndexE(@RequestParam(value = "adress") String textA,@RequestParam(value="title") String textT,@RequestParam(value="message") String textM){
        try {
            EmailMessage eMes = new EmailMessage(textT, textM);
            EmailRecipient eRec = new EmailRecipient(textA);
            EmailSender eSender = new EmailSender();
            eSender.send(eMes, eRec);
        }
        catch(Exception ex){
            return "ERROR";
        }
        return "OK";
    }


    @RequestMapping("/push")
    public @ResponseBody
    String getIndexP(@RequestParam(value = "adress") String textA,@RequestParam(value="title") String textT,@RequestParam(value="message") String textM){
       try {
           PushMessage pMes = new PushMessage(textT, textM);
           PushRecipient pRec = new PushRecipient(textA);
           PushSender pSender = new PushSender();
           pSender.send(pMes, pRec);
       }
       catch(Exception ex){
           ex.printStackTrace();
       }
       return "OK";
    }
}
