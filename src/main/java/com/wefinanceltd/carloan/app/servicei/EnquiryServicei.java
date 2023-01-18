package com.wefinanceltd.carloan.app.servicei;

import java.util.List;

import com.wefinanceltd.carloan.app.model.EnquiryDetails;

public interface EnquiryServicei {

	public EnquiryDetails setEnqDetalis(EnquiryDetails eqd);

	public List<EnquiryDetails> getEnqDetails();

	public void delData(Integer id);

	public EnquiryDetails getByIdEnq(Integer id);

	public EnquiryDetails editForCibilEnq(EnquiryDetails enqDetail, Integer id);

	public Integer cibilStatus(Integer id);

	public EnquiryDetails updateSanctionLetter(int id1, EnquiryDetails enquiry);

}
