package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Menu;

@Repository
public interface MenuRepository
	extends JpaRepository<Menu, Integer>{

}
