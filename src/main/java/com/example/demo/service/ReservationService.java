package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Reservation;
import com.example.demo.domain.SiteUser;
import com.example.demo.form.ReservationQuery;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.SiteUserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReservationService {

	final static GrantedAuthority ROLE_ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN");
	final static GrantedAuthority ROLE_GENERAL = new SimpleGrantedAuthority("ROLE_GENERAL");

	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	SiteUserRepository siteUserRepository;

	@Transactional(readOnly = true)
	public List<Reservation> findAllReservations(User user){

		List<Reservation> reservations = null;

		if(user.getAuthorities().contains(ROLE_ADMIN)) {
			// ログインユーザーが管理者ユーザーの場合は、予約情報を全件取得
			reservations = reservationRepository.findAll();

		} else if (user.getAuthorities().contains(ROLE_GENERAL)) {
			// ログインユーザーが一般ユーザーの場合は、自分の予約情報のみ取得
			SiteUser siteUser = siteUserRepository.findBySiteUserName(user.getUsername());
			reservations = reservationRepository.findBySiteUserId(siteUser.getId());
		}
		return reservations;
	}

	@Transactional(readOnly = false)
	public Reservation createReservation(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	@Transactional(readOnly = true)
	public Optional<Reservation> findOneReservation(Integer id){
		return reservationRepository.findById(id);
	}

	@Transactional(readOnly = false)
	public void deleteReservation(Integer id) {
		reservationRepository.deleteById(id);
	}

	public int count(List<Reservation> reservations) {
		return reservations.size();
	}

	public List<Reservation> doQuery(ReservationQuery reservationQuery) {

		List<Reservation> reservations = null;
		if(reservationQuery.getMenuId() != null && reservationQuery.getMenuId() != -1) {
			// メニューで検索
			reservations = reservationRepository.findByMenuId(reservationQuery.getMenuId());

		} else if(reservationQuery.getStaffId() != null && reservationQuery.getStaffId() != -1) {
			// スタッフで検索
			reservations = reservationRepository.findByStaffId(reservationQuery.getStaffId());

		} else {
			// 入力条件が無ければ全件検索
			reservations = reservationRepository.findAll();
		}

		return reservations;
	}
}
