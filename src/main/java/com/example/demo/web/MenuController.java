package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.Menu;
import com.example.demo.form.MenuForm;
import com.example.demo.service.MenuService;

@Controller
public class MenuController {

	@Autowired
	MenuService menuService;

	// メニュー登録画面を表示
	@GetMapping(value = "/menu")
	public ModelAndView createMenu() {
		MenuForm form = createInitialForm();
		ModelAndView mv = new ModelAndView("menu");
		mv.addObject("menu", form);
		return mv;
	}
	private MenuForm createInitialForm() {
		String kind = "";
		Integer price = 0;
		return new MenuForm(kind, price);
	}

	// メニューを1件作成
	@PostMapping(value = "/menu")
	public ModelAndView createOneMenu(@ModelAttribute MenuForm form,
			RedirectAttributes attrs) {
		createMenu(form);
		// フラッシュメッセージ
		attrs.addFlashAttribute("success", "メニューを追加しました");
		return new ModelAndView("redirect:/reservation");
	}
	private void createMenu(MenuForm form) {
		String kind = form.getKind();
		Integer price = form.getPrice();
		Menu menu = new Menu(kind, price);
		menuService.createMenu(menu);
	}

}
