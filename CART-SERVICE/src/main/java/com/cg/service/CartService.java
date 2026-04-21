package com.cg.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.entity.Cart;
import com.cg.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	CartRepository repo;
	
	@Autowired
	RestTemplate restTemplate;

	public Cart addProduct(Long cartId, Long pid) {

		Cart cart = repo.findById(cartId).orElse(null);

		// If cart doesn't exist → create new
		if (cart == null) {
			cart = new Cart();
		}

		// Add product to existing list
		cart.getProductIds().add(pid);

		return repo.save(cart);
	}

	public Map<String, Object> getCart(Long id) {

		Cart cart = repo.findById(id).orElse(null);

		List<Object> products = new ArrayList<>();
		for (Long pid : cart.getProductIds()) {
			Object product = restTemplate.getForObject("http://PRODUCT-CATALOG-SERVICE/catalogs/products/" + pid, Object.class);
			products.add(product);
		}

		// Get recommendations
		List<Map<String, Object>> recommendations = restTemplate.postForObject("http://RECOMMENDATION-SERVICE/recommendations",cart.getProductIds(), List.class);

		// FILTER OUT products already in cart
		List<Map<String, Object>> filtered = new ArrayList<>();

		for (Map<String, Object> rec : recommendations) {
			Long recPid = ((Number) rec.get("pid")).longValue();

			if (!cart.getProductIds().contains(recPid)) {
				filtered.add(rec);
			}
		}

		Map<String, Object> response = new HashMap<>();
		response.put("products", products);
		response.put("recommendations", filtered);

		return response;
	}
}
