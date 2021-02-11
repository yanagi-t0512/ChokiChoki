package com.example.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Reservation;
import com.example.demo.domain.ReservationCount;

@Repository
public interface ReservationRepository
	extends JpaRepository<Reservation, Integer>{

	@Query("SELECT m.kind AS menu, COUNT(r.id) AS number "
			+ "FROM Reservation r LEFT JOIN Menu m ON m.id = r.menuId "
			+ "GROUP BY m.kind")
	Collection<ReservationCount> groupByMenu();

}
