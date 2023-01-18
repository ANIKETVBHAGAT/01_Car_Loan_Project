package com.wefinanceltd.carloan.app.serviceimpl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wefinanceltd.carloan.app.model.EmailSender;
import com.wefinanceltd.carloan.app.servicei.MailServicei;

@Service
public class MailServiceImpl implements MailServicei{

	@Autowired private JavaMailSender jms;
	
	@Override
	public void sendEmail(EmailSender emailSender) {
		 SimpleMailMessage mssg = new SimpleMailMessage();
    	 mssg.setFrom(emailSender.getFromEmail());
    	 mssg.setTo(emailSender.getToEmail());
    	 mssg.setSubject(emailSender.getSubject());
    	 mssg.setText(emailSender.getTextBody());
    	 jms.send(mssg);
	}

	@Override
	public void sendMailWithAttachment(EmailSender email, MultipartFile attachDacument) {
		MimeMessage createMimeMessage = jms.createMimeMessage();
		
	    try 
	    {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(createMimeMessage, true);
			mimeMessageHelper.setFrom(email.getFromEmail());
			mimeMessageHelper.setTo(email.getToEmail());
			mimeMessageHelper.setSubject(email.getSubject());
			mimeMessageHelper.setText(email.getTextBody());
			mimeMessageHelper.addAttachment(attachDacument.getOriginalFilename(), attachDacument);
			
			jms.send(createMimeMessage);
			System.out.println("send Message");
			
		} catch (Exception e) {
			System.out.println("not send Message");
			e.printStackTrace();
		}
		
	}

	@Override
	public void sendMailEnqDocument(EmailSender email) {
		 SimpleMailMessage mssg = new SimpleMailMessage();
		 mssg.setFrom(email.getFromEmail());
		 mssg.setTo(email.getToEmail());
		 mssg.setSubject(email.getSubject());
		 mssg.setText(email.getTextBody());
		 
		 jms.send(mssg);
		
	}

	//SanctionLetter
	@Override
	public void sendMailToCustomerForSactterLetter(EmailSender email) {
		
		MimeMessage createMimeMessage = jms.createMimeMessage();
		try 
		{
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(createMimeMessage, true);
			mimeMessageHelper.setFrom(email.getFromEmail());
			mimeMessageHelper.setTo(email.getToEmail());
			mimeMessageHelper.setSubject(email.getSubject());
			mimeMessageHelper.setText(email.getTextBody());
			
			FileSystemResource resource=new FileSystemResource("C:/Users/HP/Downloads/"+email.getToEmail()+".pdf");
			mimeMessageHelper.addAttachment(resource.getFilename(), resource);
			jms.send(createMimeMessage);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
//		SimpleMailMessage mssg = new SimpleMailMessage();
//		 mssg.setFrom(email.getFromEmail());
//		 mssg.setTo(email.getToEmail());
//		 mssg.setSubject(email.getSubject());
//		 mssg.setText(email.getTextBody());
//
//		 jms.send(mssg);
//		 System.out.println("Mail Sent from Service for Sanction Letter to Customer");
		
	}

}
