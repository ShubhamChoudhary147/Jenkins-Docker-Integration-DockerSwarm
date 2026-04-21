package com.cg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecommendationService {

	@Autowired
	RestTemplate restTemplate;

	public List<Object> recommend(List<Long> pids) {
		List<Object> recommendations = new ArrayList<>();

		for (Long pid : pids) {
			Map product = restTemplate.getForObject("http://PRODUCT-CATALOG-SERVICE/catalogs/products/" + pid, Map.class);
			if (product == null)
				continue;

			String category = (String) product.get("pcategory");

			List<Map> sameCategory = restTemplate
					.getForObject("http://PRODUCT-CATALOG-SERVICE/catalogs/products/category/" + category, List.class);

			for (Map p : sameCategory) {
				if (!p.get("pid").equals(pid)) {
					recommendations.add(p);
				}
			}
		}
		return recommendations;
	}
}
