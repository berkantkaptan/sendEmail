package com.example.sendEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.MessagingException;

@SpringBootApplication
@EnableScheduling
public class SendEmailApplication {
	@Autowired
	private EmailSenderService emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(SendEmailApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	@Scheduled(fixedRate = 50000) //50 saniyede 1
	public void sendMail() throws MessagingException {
		//emailSenderService.sendMail("berkant.kaptan@hotmail.com","Mesaj..","This is subject");

		//emailSenderService.sendMailWithAttachment("berkant.kaptan@hotmail.com","This is body","This is subject","/Users/fmss/Desktop/Unknown.png");
		emailSenderService.sendMail();
	}
}
