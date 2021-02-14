package com.example.demo.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReservationQuery {

	Integer menuId;

	Integer staffId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate reserveDateFrom;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate reserveDateTo;

	String request;

	public ReservationQuery() {

		menuId = 1;
		staffId = 1;
		reserveDateFrom = LocalDate.now();
		reserveDateTo = LocalDate.now();
		request = "";
	}
}
