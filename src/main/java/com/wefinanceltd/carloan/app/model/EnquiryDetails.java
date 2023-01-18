package com.wefinanceltd.carloan.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnquiryDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String firstName;
	private String lastName;
	private Integer age;
	private String mobileNo;
	private String alternateMobileNo;
	private String email;
	private long aadharNo;
	private String pancardNo;
	private Integer cibil;
	private String status;
	private String remark;
	@Lob
	private byte[] sanctionLetter;

}
