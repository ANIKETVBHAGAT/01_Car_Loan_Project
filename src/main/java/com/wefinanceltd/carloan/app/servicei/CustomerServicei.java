package com.wefinanceltd.carloan.app.servicei;

import java.util.List;

import com.wefinanceltd.carloan.app.model.Customer;

public interface CustomerServicei {

	public void SanctionLetterGenerated(Customer customer);

	public Customer setCustomerData(Customer customer);

	public List<Customer> getAllCustomerData();

	public Customer getCustomerwithcustomerAccno(Integer customerAccno);

	public List<Customer> getCustomerwithcustomerName(String customerName);

	public List<Customer> deleteCustomerbycustomerAccno(Integer customerName);

	public Customer getCustomerDetails(Integer id);

	public Customer updateSanctionedAmt(Integer id, Customer customer);

	public Customer disburseLoanAmt(Integer id, Customer customer);

}
