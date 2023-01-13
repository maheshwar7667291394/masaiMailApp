package com.masaiemail.exception;

import java.time.LocalDate;

public class MyErrorDetails {
	
		private String name;
		private LocalDate localDate;
		public MyErrorDetails() {
			super();
		
	}
		
	public MyErrorDetails(String name, LocalDate localDate) {
		super();
		this.name = name;
		this.localDate = localDate;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getLocalDate() {
		return localDate;
	}
	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}
	

}
