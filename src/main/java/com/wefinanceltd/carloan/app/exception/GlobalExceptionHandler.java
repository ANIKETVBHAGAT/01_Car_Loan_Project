package com.wefinanceltd.carloan.app.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wefinanceltd.carloan.app.model.ApiError;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ApiError> notFound(DataNotFoundException d, HttpServletRequest re){
		ApiError a=new ApiError();
		a.setCode(HttpStatus.NOT_FOUND.value());
		a.setError(HttpStatus.NOT_FOUND);
		a.setMessage(d.getMessage());
		a.setTimesDate(new Date());
		a.setPath(re.getRequestURI());
		return new ResponseEntity<ApiError>(a,HttpStatus.NOT_FOUND);
	}
}
