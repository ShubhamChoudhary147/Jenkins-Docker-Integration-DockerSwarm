package com.cg.entity;

import jakarta.persistence.*;

@Entity
@Table(name="prices")
public class Price {
	@Id
	private Long pid;
	private double price;
	private double discount;

	public double getDiscountedPrice() {
		return price - (price * discount / 100);
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
