package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.cg.service.RecommendationService;

import java.util.*;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

	@Autowired
	RecommendationService service;

	@PostMapping
	public List<Object> recommend(@RequestBody List<Long> pids) {
		return service.recommend(pids);
	}
}