package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "site_user")
@Data
@AllArgsConstructor
public class SiteUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "siteuser_Name")
	public String siteUserName;

	@Column(name = "password")
	public String password;

	@Column(name = "email")
	public String email;

	@Column(name = "age")
	public Integer age;

	@Column(name = "role")
	public String role;

	public SiteUser() {

	}

	public SiteUser(String siteUserName,
					String password,
					String email,
					Integer age,
					String role) {
		this.siteUserName = siteUserName;
		this.password = password;
		this.email = email;
		this.age = age;
		this.role = role;
	}
}
