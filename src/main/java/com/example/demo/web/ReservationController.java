package com.example.demo.web;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.Reservation;
import com.example.demo.domain.ReservationCount;
import com.example.demo.form.ReservationForm;
import com.example.demo.form.ReservationQuery;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.ReservationService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j

public class ReservationController {

	@Autowired
	ReservationService reservationService;
	@Autowired
	ReservationRepository rep;

	@GetMapping(value = "/")
	public ModelAndView welcome(){
		return new ModelAndView("index");
	}

	@GetMapping(value = "/index")
	public ModelAndView index(){
		return new ModelAndView("index");
	}

	@PostMapping(value = "/index")
	public ModelAndView postIndex(){
		return new ModelAndView("index");
	}

	// 予約を全件取得
	@GetMapping(value = "/reservation")
	public ModelAndView readAllReservation(
			@AuthenticationPrincipal User user
			) {

		// ログインユーザー情報の表示
		log.info("ログ開始");
		log.info(user.getUsername());
		log.info(user.getAuthorities().toString());
		log.info("ログ終了");

		ReservationForm form = createInitialForm();
		ModelAndView mv = new ModelAndView("reservation");
		mv.addObject("form", form);
		List<Reservation> reservations = reservationService.findAllReservations(user);
		mv.addObject("reservations", reservations);
		// 検索フォーム
		mv.addObject("query", new ReservationQuery());
		// 予約件数
		int count = reservationService.count(reservations);
		mv.addObject("count", count);
		// メニューでグループ化
		Collection<ReservationCount> group = rep.groupByMenu();
		mv.addObject("group", group);
		return mv;
	}
	private ReservationForm createInitialForm() {
		Integer menuId = 1;
		Integer staffId = 1;
		LocalDate reserveDate = LocalDate.now();
		String request = "";
		return new ReservationForm(
				menuId, staffId, reserveDate, request);
	}

	// 予約を1件作成
	@PostMapping(value = "/reservation")
	public ModelAndView createOneReservation(@ModelAttribute ReservationForm form,
			@AuthenticationPrincipal User user,
			RedirectAttributes attrs) {

		Integer userId = 2;
		createReservation(form, userId);
		// フラッシュメッセージ
		attrs.addFlashAttribute("success", "予約が完了しました");
		return new ModelAndView("redirect:/reservation");
	}
	private void createReservation(ReservationForm form, Integer userId) {
		Integer siteUserId = userId;
		Integer menuId = form.getMenuId();
		Integer staffId = form.getStaffId();
		LocalDate reserveDate = form.getReserveDate();
		String request = form.getRequest();
		Reservation reservation = new Reservation(siteUserId, menuId, staffId, reserveDate, request);
		reservationService.createReservation(reservation);
	}


	// 予約を削除
	@DeleteMapping(value = "reservation/{id}")
	public ModelAndView deleteOneReservation(@PathVariable Integer id,
			RedirectAttributes attrs) {

		deleteReservation(id);
		// フラッシュメッセージ
		attrs.addFlashAttribute("delete", "予約をキャンセルしました");
		return new ModelAndView("redirect:/reservation");
	}
	private void deleteReservation(Integer id) {
		Optional<Reservation> reservation
			= reservationService.findOneReservation(id);
		if(reservation.isPresent()) {
			reservationService.deleteReservation(id);
		}
	}

	// 検索処理
	@PostMapping("/reservation/query")
	public ModelAndView queryReservation(@ModelAttribute ReservationQuery reservationQuery,
			BindingResult result) {

		ModelAndView mv = new ModelAndView("reservation");
		List<Reservation> reservations = null;
		reservations = reservationService.doQuery(reservationQuery);
		mv.addObject("query", reservationQuery);
		mv.addObject("reservations", reservations);
		// 予約件数
		int count = reservationService.count(reservations);
		mv.addObject("count", count);
		// メニューでグループ化
		Collection<ReservationCount> group = rep.groupByMenu();
		mv.addObject("group", group);
		return mv;
	}
}
