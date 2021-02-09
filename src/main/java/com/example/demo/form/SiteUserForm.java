package com.example.demo.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiteUserForm {

	String siteUserName;

	String password;

	String email;

	Integer age;

	String role;



}
