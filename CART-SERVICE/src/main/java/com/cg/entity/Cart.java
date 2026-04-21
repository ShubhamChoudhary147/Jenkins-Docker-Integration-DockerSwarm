package com.cg.entity;

import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name="carts")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;

	@ElementCollection
	private List<Long> productIds = new ArrayList<>();

	public Long getCartId() {
		return cartId;
	}

	public List<Long> getProductIds() {
		return productIds;
	}
}