package com.example.demo.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationForm {

	Integer menuId;

	Integer staffId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate reserveDate;

	String request;



}
