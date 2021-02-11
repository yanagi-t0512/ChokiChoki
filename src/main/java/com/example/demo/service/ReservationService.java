package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Reservation;
import com.example.demo.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	@Transactional(readOnly = true)
	public List<Reservation> findAllReservations(){
		return reservationRepository.findAll();
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

	public int count() {
		return (int) reservationRepository.count();
	}
}
