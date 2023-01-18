package com.wefinanceltd.carloan.app.servicei;

import org.springframework.web.multipart.MultipartFile;

import com.wefinanceltd.carloan.app.model.EmailSender;

public interface MailServicei {

	public void sendEmail(EmailSender emailSender);

	public void sendMailWithAttachment(EmailSender email, MultipartFile attachDacument);

	public void sendMailEnqDocument(EmailSender email);

	public void sendMailToCustomerForSactterLetter(EmailSender email);

}
