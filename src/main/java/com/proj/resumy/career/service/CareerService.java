package com.proj.resumy.career.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.resumy.career.domain.CareerDAO;
import com.proj.resumy.career.domain.CareerDTO;


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

	public List<CareerDTO> view(String userid) {
		return dao.select(userid);
	}
	
	public int write(CareerDTO dto) {
		return dao.insert(dto); 
	}
	
	public int update(CareerDTO dto) {
		return dao.update(dto);
	}
	
	public int delete(CareerDTO dto) {	
		return dao.delete(dto);
	}
	

	

}
