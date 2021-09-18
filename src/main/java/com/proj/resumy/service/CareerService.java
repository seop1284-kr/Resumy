package com.proj.resumy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.resumy.domain.CareerDAO;
import com.proj.resumy.domain.CareerDTO;


@Service
public class CareerService {
	CareerDAO dao;

	@Autowired
	public void setDao(CareerDAO dao) {
		this.dao = dao;
	}
	
	public CareerService() {
		System.out.println("CareerService() 생성");
		
	}

	public List<CareerDTO> list() {
		
		return dao.select();
	}

}