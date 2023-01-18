package com.wefinanceltd.carloan.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailSender {
	
	private String fromEmail;
	private String toEmail;
	private String textBody;
	private String subject;	

}
