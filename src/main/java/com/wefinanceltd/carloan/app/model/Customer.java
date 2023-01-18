package com.wefinanceltd.carloan.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer customerAccno;
	private String customerFirstName;
	private String customerLastName;
	
	private String customerDateOfBirth;
	private String customerAge;
	private String customerGender;
	private String customerEmail;
	
	private String customerMobileNumber;
	private String customerAdditionalMobileNumber;
	private Double customerAmountSanctioned;
	private Double customerTotalLoanRequired;
	
	private Double amtDisburse;
	private Double roi;
	private Integer loanTenure;
	private Double emiAmount;
	
	private String educationalInfo;
	private String occupation;
	private String remark;
	private String status;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private PersonalDoc personalDoc;
	
	@OneToOne(cascade = CascadeType.ALL)
	private CustomerAddress customerAddress;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Guaranter guaranter;

	

}
