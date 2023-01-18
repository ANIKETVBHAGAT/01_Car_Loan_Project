package com.wefinanceltd.carloan.app.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wefinanceltd.carloan.app.model.EnquiryDetails;

@Repository
public interface EnquiryRepositary extends JpaRepository<EnquiryDetails, Integer>{

}
