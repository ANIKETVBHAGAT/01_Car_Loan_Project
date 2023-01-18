package com.wefinanceltd.carloan.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wefinanceltd.carloan.app.model.Customer;
import com.wefinanceltd.carloan.app.model.CustomerAddress;
import com.wefinanceltd.carloan.app.model.Guaranter;
import com.wefinanceltd.carloan.app.model.PersonalDoc;
import com.wefinanceltd.carloan.app.servicei.CustomerServicei;

@CrossOrigin("*")
@RestController
public class CustomerController {
	
	@Autowired CustomerServicei cs;
	
	@PostMapping(value = "/setCustomer",consumes = MediaType.ALL_VALUE)
	public ResponseEntity<Customer> setCustomerData(@RequestPart(value = "custjson",required = true) String customerJson,
			@RequestPart(value = "addressProof",required = true) MultipartFile doc1,
			@RequestPart(value = "panCard",required = true) MultipartFile doc2,
			@RequestPart(value = "IncomeTax",required = true) MultipartFile doc3,
			@RequestPart(value = "addharCard",required = true) MultipartFile doc4,
			@RequestPart(value = "photo",required = true) MultipartFile doc5,
			@RequestPart(value = "signature",required = true) MultipartFile doc6,
			@RequestPart(value = "thumb",required = true) MultipartFile doc7,
			@RequestPart(value = "bankCheque",required = true) MultipartFile doc8,
			@RequestPart(value = "salarySlips",required = true) MultipartFile doc9,
			@RequestPart(value = "guaranranterPan",required = true) MultipartFile doc10,
			@RequestPart(value = "guaranranterAadhar",required = true) MultipartFile doc11,
			@RequestPart(value = "guaranranterSignature",required = true) MultipartFile doc12
			)
	{
		
		ObjectMapper om=new ObjectMapper();
		try {
			Customer cust=om.readValue(customerJson, Customer.class);	
			
			String json=om.writeValueAsString(cust);
			System.out.println(json);
			
			Customer customer=new Customer();
			customer.setCustomerAccno(cust.getCustomerAccno());
			customer.setCustomerFirstName(cust.getCustomerFirstName());
			customer.setCustomerLastName(cust.getCustomerLastName());
			customer.setCustomerDateOfBirth(cust.getCustomerDateOfBirth());
			customer.setCustomerAge(cust.getCustomerAge());
			customer.setCustomerGender(cust.getCustomerGender());
			customer.setCustomerEmail(cust.getCustomerEmail());
			customer.setCustomerMobileNumber(cust.getCustomerMobileNumber());
			customer.setCustomerAdditionalMobileNumber(cust.getCustomerAdditionalMobileNumber());
			customer.setCustomerAmountSanctioned(cust.getCustomerAmountSanctioned());
			customer.setCustomerTotalLoanRequired(cust.getCustomerTotalLoanRequired());
			customer.setAmtDisburse(cust.getAmtDisburse());
			customer.setLoanTenure(cust.getLoanTenure());
			customer.setRoi(cust.getRoi());
			customer.setEducationalInfo(cust.getEducationalInfo());
			customer.setOccupation(cust.getOccupation());
			
			CustomerAddress custad=new CustomerAddress();
			custad.setId(cust.getCustomerAddress().getId());
			custad.setCityName(cust.getCustomerAddress().getCityName());
			custad.setAreaName(cust.getCustomerAddress().getAreaName());
			custad.setPincode(cust.getCustomerAddress().getPincode());
			custad.setState(cust.getCustomerAddress().getState());
			
			Guaranter gua=new Guaranter();
			gua.setGuaranterName(cust.getGuaranter().getGuaranterName());
			gua.setGuaranterMob(cust.getGuaranter().getGuaranterMob());
			gua.setGuaranterEmail(cust.getGuaranter().getGuaranterEmail());
			
			
			customer.setCustomerAddress(custad);
			customer.setGuaranter(gua);
			
			PersonalDoc perDoc=new PersonalDoc();
			perDoc.setAddressProof(doc1.getBytes());
			perDoc.setPanCard(doc2.getBytes());
			perDoc.setIncomeTax(doc3.getBytes());
			perDoc.setAddharCard(doc4.getBytes());
			perDoc.setPhoto(doc5.getBytes());
			perDoc.setSignature(doc6.getBytes());
			perDoc.setThumb(doc7.getBytes());
			perDoc.setBankCheque(doc8.getBytes());
			perDoc.setSalarySlips(doc9.getBytes());
			perDoc.setGuaranterPan(doc10.getBytes());
			perDoc.setGuaranterAadhar(doc11.getBytes());
			perDoc.setGuaranterSignature(doc12.getBytes());
			
			System.out.println(customer);
			customer.setPersonalDoc(perDoc);
			Customer custom=cs.setCustomerData(customer);
			
			return new ResponseEntity<Customer>(custom,HttpStatus.CREATED);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/getAllValue")
	  public ResponseEntity<List<Customer>> getAllCustomerData()
	  {
		 List<Customer> list = cs.getAllCustomerData();
		return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);  
	  }
	  
	  @GetMapping("/getValue/{customerAccno}")
	  public ResponseEntity< Customer> getCustomerwithcustomerAccno(@PathVariable("customerAccno") Integer customerAccno )
	  {
		  Customer optionwithfindBycustomerAccno = cs.getCustomerwithcustomerAccno(customerAccno);
		return new ResponseEntity<Customer>(optionwithfindBycustomerAccno, HttpStatus.OK);
		  
	  }
	  
	  @GetMapping("/getValueByName/{customerName}")
	  public ResponseEntity< List<Customer>> getCustomerwithcustomerName(@PathVariable("customerName") String customerName )
	  {
		  List<Customer> findBycustomerAccnocustomerName = cs.getCustomerwithcustomerName(customerName);
		return new ResponseEntity<List<Customer>>(findBycustomerAccnocustomerName, HttpStatus.OK);  
	  }
	
	  @DeleteMapping("/deleteValueBycustomerAccno/{customerAccno}")
	  public ResponseEntity< List<Customer>> deleteCustomerbycustomerAccno(@PathVariable("customerAccno") Integer customerId )
	  {
		  List<Customer> deleteBycustomerAccnocustomerId = cs.deleteCustomerbycustomerAccno(customerId);
		return new ResponseEntity<List<Customer>>(deleteBycustomerAccnocustomerId, HttpStatus.OK);  
	  }
	  
	  @GetMapping("/getCustomerDetailsBiId/{id}")
	  public ResponseEntity<Customer> getCustomerDetails(@PathVariable Integer id)
	  {
		  Customer cust=cs.getCustomerDetails(id);
		  return new ResponseEntity<Customer>(cust,HttpStatus.OK);
	  }
	  
	  @PutMapping("/updateSanctionedAmt/{id}")
	  public ResponseEntity<Customer> updateSanctionedAmt(@PathVariable Integer id,@RequestBody Customer customer)
	  {
		  Customer cust=cs.updateSanctionedAmt(id,customer);
		  return new ResponseEntity<Customer>(cust,HttpStatus.OK);
	  }
	  
	  @PutMapping("/disburseAmt/{id}")
	  public ResponseEntity<Customer> disburseLoanAmt(@PathVariable Integer id,@RequestBody Customer customer)
	  {
		 Customer c= cs.disburseLoanAmt(id,customer);
		  return new ResponseEntity<Customer>(c,HttpStatus.OK); 
	  }

	
	}

