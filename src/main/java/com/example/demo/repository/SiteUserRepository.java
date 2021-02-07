package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.SiteUser;

@Repository
public interface SiteUserRepository
	extends JpaRepository<SiteUser, Integer>{

	SiteUser findByUserName(String userName);
//	boolean existByUserName(String userName);

}
