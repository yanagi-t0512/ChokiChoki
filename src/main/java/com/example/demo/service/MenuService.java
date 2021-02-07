package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Menu;
import com.example.demo.repository.MenuRepository;

@Service
public class MenuService {

	@Autowired
	MenuRepository menuRepository;

	@Transactional(readOnly = false)
	public Menu createMenu(Menu menu) {
		return menuRepository.save(menu);
	}

}
