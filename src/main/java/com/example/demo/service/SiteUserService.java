package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.SiteUser;
import com.example.demo.repository.SiteUserRepository;

@Service
public class SiteUserService {

	@Autowired
	SiteUserRepository siteUserRepository;

	@Transactional(readOnly = true)
	public List<SiteUser> findAllSiteUsers(){
		return siteUserRepository.findAll();
	}

	@Transactional(readOnly = false)
	public SiteUser createSiteUser(SiteUser siteUser) {
		return siteUserRepository.save(siteUser);
	}

	@Transactional(readOnly = true)
	public Optional<SiteUser> findOneSiteUser(Integer id){
		return siteUserRepository.findById(id);
	}

	@Transactional(readOnly = false)
	public void deleteSiteUser(Integer id) {
		siteUserRepository.deleteById(id);
	}
}
