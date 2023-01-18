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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wefinanceltd.carloan.app.model.EnquiryDetails;
import com.wefinanceltd.carloan.app.servicei.EnquiryServicei;

@CrossOrigin("*")
@RestController
@RequestMapping("/enq")
public class EnquiryController {
	
	@Autowired EnquiryServicei es;
	
	@PostMapping("/setEnq")
	public ResponseEntity<EnquiryDetails>  setEnqDetalis(@RequestBody EnquiryDetails eqd)
	{
		EnquiryDetails eq =es.setEnqDetalis(eqd);
		return new ResponseEntity<EnquiryDetails>(eq,HttpStatus.CREATED);
	}
	
	@GetMapping("/getenq")
	public ResponseEntity<List<EnquiryDetails>> getEnqDetails()
	{
		List<EnquiryDetails>  list1=es.getEnqDetails();
		
		return new ResponseEntity<List<EnquiryDetails>>(list1,HttpStatus.OK);
	}
	
	@DeleteMapping("/delEnq/{id}")
	public ResponseEntity<String> delData(@PathVariable Integer id)
	{
		es.delData(id);
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/getByIdEnq/{id}")
	public ResponseEntity<EnquiryDetails> getByIdEnq(@PathVariable Integer id)
	{
		EnquiryDetails enq=es.getByIdEnq(id);
		return new ResponseEntity<EnquiryDetails>(enq,HttpStatus.OK);
	}
	
	@PutMapping("/editCibil/{id}")
	public ResponseEntity<EnquiryDetails> editForCibilEnq(@PathVariable Integer id, @RequestBody EnquiryDetails enqDetail)
	{
		EnquiryDetails enq=es.editForCibilEnq(enqDetail,id);
		return new ResponseEntity<EnquiryDetails>(enq,HttpStatus.CREATED);
	}
	
	@GetMapping("/cib/{id}")
	public ResponseEntity<Integer> cibilStatus(@PathVariable Integer id)
	{
		int i=es.cibilStatus(id);
		return new ResponseEntity<Integer>(i, HttpStatus.OK);
	}
	
	@PutMapping(value = "/sanctionletter/{id}",consumes = MediaType.ALL_VALUE)
	public ResponseEntity<EnquiryDetails> updateSanctionLetter(@PathVariable String id,@RequestPart(value = "enqiryjson",required = true) String enquiryJson,
			@RequestPart(value = "sanctionLetter",required = true) MultipartFile doc1)throws Exception
	{
		
		try 
		{
			ObjectMapper om=new ObjectMapper(); 
			int id1=Integer.parseInt(id);
		
			EnquiryDetails enq=om.readValue(enquiryJson, EnquiryDetails.class);
			
			EnquiryDetails enquiry=new EnquiryDetails();
			enquiry.setFirstName(enq.getFirstName());
			enquiry.setLastName(enq.getLastName());
			enquiry.setAge(enq.getAge());
			enquiry.setMobileNo(enq.getMobileNo());
			enquiry.setAlternateMobileNo(enq.getAlternateMobileNo());
			enquiry.setEmail(enq.getEmail());
			enquiry.setAadharNo(enq.getAadharNo());
			enquiry.setPancardNo(enq.getPancardNo());
			enquiry.setCibil(enq.getCibil());
			enquiry.setStatus(enq.getStatus());
			enquiry.setSanctionLetter(doc1.getBytes());
			
			EnquiryDetails ee=es.updateSanctionLetter(id1,enquiry);
			return new ResponseEntity<EnquiryDetails>(ee,HttpStatus.OK);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

}
