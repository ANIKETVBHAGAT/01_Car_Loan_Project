package com.wefinanceltd.carloan.app.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wefinanceltd.carloan.app.model.Customer;

@Repository
public interface CustomerRepositary extends JpaRepository<Customer, Integer>{

	public List<Customer> findByCustomerFirstName(String customerName);

}
