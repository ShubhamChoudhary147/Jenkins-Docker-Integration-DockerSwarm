package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cg.entity.Price;
import com.cg.service.PriceService;

@RestController
@RequestMapping("/price")
public class PriceController {

	@Autowired
	PriceService service;

	@GetMapping("/{pid}")
	public Price getPrice(@PathVariable Long pid) {
		return service.getPrice(pid);
	}

	@PostMapping
	public Price addPrice(@RequestBody Price price) {
		return service.addPrice(price);
	}
}
