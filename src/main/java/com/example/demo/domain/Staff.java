package com.example.demo.domain;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "staff")
public class Staff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Integer id;

	@Column(name = "staff_Name")
	String staffName;

	@Column(name = "fee")
	Integer fee;

	@OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
	private Collection<Reservation> reservations;


	public Staff() {

	}

	public Staff(String staffName, Integer fee) {
		this.staffName = staffName;
		this.fee = fee;
	}
}
