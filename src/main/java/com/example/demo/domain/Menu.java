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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "menu")

public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Integer id;

	@Column(name = "kind")
	String kind;

	@Column(name = "price")
	Integer price;

	@OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
	private Collection<Reservation> reservations;


	public Menu() {

	}

	public Menu(String kind, Integer price) {
		this.kind = kind;
		this.price = price;
	}
}
