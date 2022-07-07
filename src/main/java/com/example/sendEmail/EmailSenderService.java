package com.example.sendEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Optional;

@Service
public class EmailSenderService {

   @Autowired
    private JavaMailSender mailSender;
   @Autowired
    private Repository repository;

    public String getUserNameByid(int id){

        UserDB userDB=repository.findById(id).get();
        return userDB.getName();
    }
    public String getUserSurnameByid(int id){
       UserDB userDB=repository.findById(id).get();
        return userDB.getSurname();

    }
    public String getUserEmailById(int id){
        UserDB userDB=repository.findById(id).get();
        return userDB.getUser_email();
    }


   public void sendMail() throws MessagingException{
      /* SimpleMailMessage message = new SimpleMailMessage();

       message.setFrom("sendemailtutoriall@gmail.com");
       message.setTo(to);
       message.setText(body);
       message.setSubject(subject);
       mailSender.send(message);
       System.out.println("Mail sent..");*/
       String name=getUserNameByid(1);
       String surname=getUserSurnameByid(1);
       String email=getUserEmailById(1);
       String to=email;
       String body="Merhabalar sayın "+name +" "+surname ;
       String subject="FMSS Bilisim";
       MimeMessage mimeMessage1= mailSender.createMimeMessage();
       MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage1,true);
       mimeMessageHelper.setFrom("sendemailtutoriall@gmail.com");
       mimeMessageHelper.setTo(to);
       body+="<hr><img src='cid:logoImage' />";
       mimeMessageHelper.setText(body,true);


       FileSystemResource resource= new FileSystemResource("/Users/fmss/Desktop/fmss.png");
       mimeMessageHelper.addInline("logoImage",resource);
       mimeMessageHelper.setSubject(subject);
       mailSender.send(mimeMessage1);
       System.out.println("Mail sent..");
   }
   /*public void sendHtmlMail(String to,String body,String subject) {

   }*/

   public void sendMailWithAttachment(String to,String body,String subject,String attachment) throws MessagingException {
        MimeMessage mimeMessage= mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("sendemailtutoriall@gmail.com");
        mimeMessageHelper.setTo(to);
        body+="<hr><img src='cid:logoImage' />";
        mimeMessageHelper.setText(body,true);

       FileSystemResource resource= new FileSystemResource("/Users/fmss/Desktop/fmss.png");
       mimeMessageHelper.addInline("logoImage",resource);
        mimeMessageHelper.setSubject(subject);

       FileSystemResource fileSystem= new FileSystemResource(new File(attachment));

       mimeMessageHelper.addAttachment(fileSystem.getFilename(),fileSystem);
       mailSender.send(mimeMessage);
       System.out.println("Mail with attachment sent..");
   }


}
