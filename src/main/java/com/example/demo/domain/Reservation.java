package com.example.demo.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "reservation")
@Data
@AllArgsConstructor
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@Column(name = "site_user_Id")
	Integer siteUserId;

	@Column(name = "menu_Id")
	Integer menuId;

	@Column(name = "staff_Id")
	Integer staffId;

	@Column(name = "reserve_Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate reserveDate;

	@Column(name = "request")
	String request;

	@ManyToOne
	@JoinColumn(name = "menu_id", insertable = false, updatable = false)
	private Menu menu;

	@ManyToOne
	@JoinColumn(name = "staff_id", insertable = false, updatable = false)
	private Staff staff;


	public Reservation() {

	}

	public Reservation(Integer siteUserId,
					Integer menuId,
					Integer staffId,
					LocalDate reserveDate,
					String request) {
		this.siteUserId = siteUserId;
		this.menuId = menuId;
		this.staffId = staffId;
		this.reserveDate = reserveDate;
		this.request = request;

	}


}
