package com.wefinanceltd.carloan.app.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wefinanceltd.carloan.app.exception.DataNotFoundException;
import com.wefinanceltd.carloan.app.model.Customer;
import com.wefinanceltd.carloan.app.repositary.CustomerRepositary;
import com.wefinanceltd.carloan.app.servicei.CustomerServicei;

@Service
public class CustomerServiceImpl implements CustomerServicei{

	@Autowired CustomerRepositary cr;

	@Override
	public Customer setCustomerData(Customer customer) {
		System.out.println(customer.getPersonalDoc().getId());
		customer.setStatus("Pending with Branch Manager to be Sanctioned");
		Customer cc=cr.save(customer);
		if(cc!=null)
		{
			return cc;
		}
		else {
			throw new DataNotFoundException("Data Not Saved");
		}
	}

	@Override
	public List<Customer> getAllCustomerData() {
		List<Customer> list=cr.findAll();
		if(list!=null)
		{
			return list;
		}
		else {
			throw new DataNotFoundException("There is Nothing in DataBase");
		}
	}

	@Override
	public Customer getCustomerwithcustomerAccno(Integer customerAccno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getCustomerwithcustomerName(String customerName) {
		List<Customer> findByCustomerName = cr.findByCustomerFirstName(customerName);
		List<Customer> list=findByCustomerName;
		if(list!=null)
		{
			return list;
		}
		else
		{
			throw new DataNotFoundException("No Data Available in DataBase");
		}
	}

	@Override
	public List<Customer> deleteCustomerbycustomerAccno(Integer customerId) {
		cr.deleteById(customerId);
		return cr.findAll();
	}

	@Override
	public Customer getCustomerDetails(Integer id) {
		Optional<Customer> findById = cr.findById(id);
		if(findById.isPresent())
		{
			Customer customer = findById.get();
			return customer;
		}
		else
		{
			throw new DataNotFoundException("No data Available for the Id");
		}
	}

	@Override
	public Customer updateSanctionedAmt(Integer id, Customer customer) {
		Optional<Customer> op = cr.findById(id);
			if(op.isPresent())
				{
					Customer oldCust = op.get();
					oldCust.setCustomerAmountSanctioned(customer.getCustomerAmountSanctioned());
					oldCust.setRoi(customer.getRoi());
					oldCust.setLoanTenure(customer.getLoanTenure());
					oldCust.setEmiAmount(customer.getEmiAmount());
					oldCust.setStatus("Loan Sanctioned Ready to Disburse ");
					oldCust.setRemark("Account Head will generate Sanction Letter");
					return cr.save(oldCust);
				}
			else
			{
				throw new DataNotFoundException("No Data Available");
			}
		
	}

	@Override
	public Customer disburseLoanAmt(Integer id, Customer customer) {
		Optional<Customer> op = cr.findById(id);
		if(op.isPresent())
		{
			Customer customer2 = op.get();
			customer2.setAmtDisburse(customer.getAmtDisburse());
			customer2.setStatus("Loan Disburse");
			return cr.save(customer2);
		}
		else
		{
			throw new DataNotFoundException("No Data Available");
		}
	}
	
	@Override
	public void SanctionLetterGenerated(Customer customer) {
		// for change remark to sanction letter generated
				Integer id = customer.getId();
				Optional<Customer> op = cr.findById(id);
				
				if(op.isPresent())
				{
					Customer oldCust = op.get();
					oldCust.setRemark("Sanction Letter Sent");
					 cr.save(oldCust);
					 System.out.println("Remark Set");
				}
				else
				{
					throw new DataNotFoundException("No remark set");
				}
	}
}
