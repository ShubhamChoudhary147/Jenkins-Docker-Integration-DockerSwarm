package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Price;
import com.cg.repository.PriceRepository;

@Service
public class PriceService {
	@Autowired
	PriceRepository repo;

	public Price getPrice(Long pid) {
		return repo.findById(pid).orElse(null);
	}

	public Price addPrice(Price price) {
		return repo.save(price);
	}
}
