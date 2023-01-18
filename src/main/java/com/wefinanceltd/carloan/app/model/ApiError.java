package com.wefinanceltd.carloan.app.model;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiError {

	private Integer code;
	private String message;
	private Date timesDate;
	private String path;
	private HttpStatus error;
}
