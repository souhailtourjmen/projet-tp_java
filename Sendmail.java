package src;
import java.io.File;
import java.io.IOException;
import java.util.Properties;    
import javax.mail.*;   
import javax.mail.internet.*;

import org.jsoup.Jsoup; 
class Sendmail{  
    
    public static void send(String to,String msg){  
                  //Get properties object 
                  String from="travelword09@gmail.com";
                  String password="lcstd2g4";
                  String sub="Flight Confirmation";   
                  Properties props = new Properties();    
                  props.put("mail.smtp.host", "smtp.gmail.com");    
                  props.put("mail.smtp.socketFactory.port", "465");    
                  props.put("mail.smtp.socketFactory.class",    
                            "javax.net.ssl.SSLSocketFactory");    
                  props.put("mail.smtp.auth", "true");    
                  props.put("mail.smtp.port", "587");
                  //props.put("mail.smtp.starttls.required", "true");
                  props.put("mail.smtp.ssl.protocols", "TLSv1.2");    
                  //get Session   
                  Session session = Session.getDefaultInstance(props,    
                   new javax.mail.Authenticator() {    
                   protected PasswordAuthentication getPasswordAuthentication() {    
                   return new PasswordAuthentication(from,password);  
                   }    
                  });    
                  //compose message    
                  try {    
                   MimeMessage message = new MimeMessage(session);    
                   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
                   message.setSubject(sub); 
                   message.setContent(msg,"text/html" );    
                  // message.setText(msg,"text/html");    
                   //send message  
                   Transport.send(message);    
                   System.out.println("message sent successfully");    
                  } catch (MessagingException e) {throw new RuntimeException(e);}    
                     
        }  
         
          
              
         
public static void main(String[] args) throws AddressException, MessagingException {
    
    String content="";
    try {
        content = Jsoup.parse(new File("src/mail/Mail1.html"), "UTF-8").outerHtml();
       // System.out.println(content);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    Sendmail.send("nourchenebenabdallah3@gmail.com",content);  
}

    
} 