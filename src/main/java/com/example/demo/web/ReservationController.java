package com.example.demo.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.Reservation;
import com.example.demo.form.ReservationForm;
import com.example.demo.service.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	ReservationService reservationService;

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
	public ModelAndView readAllReservation(Authentication loginUser) {
		ReservationForm form = createInitialForm();
		ModelAndView mv = new ModelAndView("reservation");
		mv.addObject("form", form);
		List<Reservation> reservations = reservationService.findAllReservations();
		mv.addObject("reservations", reservations);
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
			RedirectAttributes attrs) {
		createReservation(form);
		// フラッシュメッセージ
		attrs.addFlashAttribute("success", "予約が完了しました");
		return new ModelAndView("redirect:/reservation");
	}
	private void createReservation(ReservationForm form) {
		Integer customerId = 1;
		Integer menuId = form.getMenuId();
		Integer staffId = form.getStaffId();
		LocalDate reserveDate = form.getReserveDate();
		String request = form.getRequest();
		Reservation reservation = new Reservation(customerId, menuId, staffId, reserveDate, request);
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
}