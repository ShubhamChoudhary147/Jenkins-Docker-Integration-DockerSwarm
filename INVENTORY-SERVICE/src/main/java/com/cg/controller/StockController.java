package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.cg.entity.Stock;
import com.cg.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {

	@Autowired
	StockService service;

	@GetMapping("/{pid}")
	public Stock getInventory(@PathVariable Long pid) {
		return service.getInventory(pid);
	}

	@PostMapping
	public Stock addStock(@RequestBody Stock inventory) {
		return service.addStock(inventory);
	}
}