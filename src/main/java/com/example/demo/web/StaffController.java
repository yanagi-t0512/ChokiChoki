package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.Staff;
import com.example.demo.form.StaffForm;
import com.example.demo.service.StaffService;

@Controller
public class StaffController {

	@Autowired
	StaffService staffService;

	// スタッフ登録画面を表示
	@GetMapping(value = "/staff")
	public ModelAndView createStaff() {
		StaffForm form = createInitialForm();
		ModelAndView mv = new ModelAndView("staff");
		mv.addObject("staff", form);
		return mv;
	}
	private StaffForm createInitialForm() {
		String staffName = "";
		Integer fee = 0;
		return new StaffForm(staffName, fee);
	}

	// スタッフを1件作成
	@PostMapping(value = "/staff")
	public ModelAndView createOneStaff(@ModelAttribute StaffForm form,
			RedirectAttributes attrs) {
		createStaff(form);
		// フラッシュメッセージ
		attrs.addFlashAttribute("success", "スタッフを追加しました");
		return new ModelAndView("redirect:/reservation");
	}
	private void createStaff(StaffForm form) {
		String staffName = form.getStaffName();
		Integer fee = form.getFee();
		Staff staff = new Staff(staffName, fee);
		staffService.createStaff(staff);
	}

}
