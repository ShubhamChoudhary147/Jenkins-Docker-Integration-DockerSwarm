package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Stock;
import com.cg.repository.StockRepository;

@Service
public class StockService {
	@Autowired
	StockRepository repo;

	public Stock getInventory(Long pid) {
		return repo.findById(pid).orElse(null);
	}

	public Stock addStock(Stock inventory) {
		return repo.save(inventory);
	}
}
