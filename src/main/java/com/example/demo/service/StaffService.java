package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Staff;
import com.example.demo.repository.StaffRepository;

@Service
public class StaffService {

	@Autowired
	StaffRepository staffRepository;

	@Transactional(readOnly = false)
	public Staff createStaff(Staff staff) {
		return staffRepository.save(staff);
	}

}
