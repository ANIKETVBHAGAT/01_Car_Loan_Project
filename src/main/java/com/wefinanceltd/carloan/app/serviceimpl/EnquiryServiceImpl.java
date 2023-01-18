package com.wefinanceltd.carloan.app.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wefinanceltd.carloan.app.exception.DataNotFoundException;
import com.wefinanceltd.carloan.app.model.EnquiryDetails;
import com.wefinanceltd.carloan.app.repositary.EnquiryRepositary;
import com.wefinanceltd.carloan.app.servicei.EnquiryServicei;

@Service
public class EnquiryServiceImpl implements EnquiryServicei{

	@Autowired EnquiryRepositary er;

	@Override
	public EnquiryDetails setEnqDetalis(EnquiryDetails eqd) {
		eqd.setRemark("Pending For CIBIL With Operational Exe");
		return er.save(eqd);
	}

	@Override
	public List<EnquiryDetails> getEnqDetails() {
		List<EnquiryDetails> list= er.findAll();
		if(list !=null)
		{
			return list;
		}
		else
		{
			throw new DataNotFoundException("Data is not Available in DataBase");
		}
	}

	@Override
	public void delData(Integer id) {
		er.deleteById(id);
	}

	@Override
	public EnquiryDetails getByIdEnq(Integer id) {
		Optional<EnquiryDetails> op = er.findById(id);
		if(op.isPresent()) 
		{
			EnquiryDetails enquiryDetails = op.get();
			return enquiryDetails;
		}
		return null;
	}

	@Override
	public EnquiryDetails editForCibilEnq(EnquiryDetails enqDetail, Integer id) {
		EnquiryDetails enq = er.save(enqDetail);
		return enq;
	}

	@Override
	public Integer cibilStatus(Integer id) {
		Integer min=600;
		Integer max=900;
		Integer b=(int)(Math.random()*(max-min+1)+min);
		
		if(b>750)
		{
			Optional<EnquiryDetails> op = er.findById(id);
			if(op.isPresent())
			{
				EnquiryDetails enquiryDetails = op.get();
				enquiryDetails.setCibil(b);
				enquiryDetails.setStatus("approved");
				enquiryDetails.setRemark("Documentation Pending With Relational Exe");
				er.save(enquiryDetails);
			}
			return b;
		}
		
		else 
		{
			Optional<EnquiryDetails> op = er.findById(id);
			if(op.isPresent())
			{
				EnquiryDetails enquiryDetails = op.get();
				enquiryDetails.setCibil(b);
				enquiryDetails.setStatus("rejected");
				er.save(enquiryDetails);
			}
			return b;
		}
	}

	@Override
	public EnquiryDetails updateSanctionLetter(int id1, EnquiryDetails enquiry) {
		Optional<EnquiryDetails> op=er.findById(id1);
		if(op.isPresent())
		{
			EnquiryDetails en=op.get();
			en.setFirstName(enquiry.getFirstName());
			en.setLastName(enquiry.getLastName());
			en.setAge(enquiry.getAge());
			en.setMobileNo(enquiry.getMobileNo());
			en.setAlternateMobileNo(enquiry.getAlternateMobileNo());
			en.setEmail(enquiry.getEmail());
			en.setAadharNo(enquiry.getAadharNo());
			en.setPancardNo(enquiry.getPancardNo());
			en.setCibil(enquiry.getCibil());
			en.setStatus("Sanction Letter Send");
			en.setRemark("approved");
			en.setSanctionLetter(enquiry.getSanctionLetter());
			return er.save(en);
		}
		else
		{
			throw new DataNotFoundException("No Data Available for the Id");
		}
	}
	
	
}
