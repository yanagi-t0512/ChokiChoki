package com.example.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.SiteUser;
import com.example.demo.form.SiteUserForm;
import com.example.demo.service.SiteUserService;
import com.example.demo.util.Role;

@Controller
public class SiteUserController {

	@Autowired
	SiteUserService siteUserService;

	@Autowired
	PasswordEncoder passwordEncoder;

	// ユーザーを全件取得
	@GetMapping(value = "/userlist")
	public ModelAndView readAllSiteUser() {
		SiteUserForm form = createInitialForm();
		ModelAndView mv = new ModelAndView("userlist");
		mv.addObject("form", form);
		List<SiteUser> siteUsers = siteUserService.findAllSiteUsers();
		mv.addObject("siteUsers", siteUsers);
		return mv;

	}
	private SiteUserForm createInitialForm() {
		String userName = "";
		String password = "";
		String email = "";
		Integer age = 0;
		String role = "";
		return new SiteUserForm(
				userName, password, email, age, role);
	}

	// ユーザー登録画面を表示
	@GetMapping(value = "/signup")
	public ModelAndView createSiteUser() {
		SiteUserForm form = createInitialForm();
		ModelAndView mv = new ModelAndView("signup");
		mv.addObject("site_user", form);
		return mv;
	}

	// ユーザーを1件作成
	@PostMapping(value = "/signup")
	public ModelAndView createOneSiteUser(@ModelAttribute SiteUserForm form,
			RedirectAttributes attrs) {
		createSiteUser(form);
		// フラッシュメッセージ
		attrs.addFlashAttribute("success", "ユーザー登録を行いました");
		return new ModelAndView("redirect:/reservation");
	}
	private void createSiteUser(SiteUserForm form) {
		String userName = form.getUserName();
		String password = passwordEncoder.encode(form.getPassword());
		String email = form.getEmail();
		Integer age = form.getAge();
		String role;
		if(age == 0) {
			role = Role.ADMIN.name();
		} else {
			role = Role.GENERAL.name();
		}

		SiteUser siteUser = new SiteUser(userName, password, email, age, role);
		siteUserService.createSiteUser(siteUser);
	}


	// ユーザーを削除
	@DeleteMapping(value = "delete/{id}")
	public ModelAndView deleteOneSiteUser(@PathVariable Integer id) {
		deleteSiteUser(id);
		return new ModelAndView("redirect:/userlist");
	}
	private void deleteSiteUser(Integer id) {
		Optional<SiteUser> SiteUser
			= siteUserService.findOneSiteUser(id);
		if(SiteUser.isPresent()) {
			siteUserService.deleteSiteUser(id);
		}

	}
}
