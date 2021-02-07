package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LoginController {

//	private final SiteUserRepository siteUserRepository;
//	private final BCryptPasswordEncoder PasswordEncoder;

	// ログイン画面
	@GetMapping(value = "/login")
	public String getLogin(Model model) {
		return "login/login";
	}

	@PostMapping(value = "/login")
	public String postLogin(Model model) {
		return "login/login";
	}
}
