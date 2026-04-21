package com.cg.entity;

import jakarta.persistence.*;

@Entity
@Table(name="stocks")
public class Stock {
	@Id
	private Long pid;
	private int noOfItemsLeft;

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public int getNoOfItemsLeft() {
		return noOfItemsLeft;
	}

	public void setNoOfItemsLeft(int noOfItemsLeft) {
		this.noOfItemsLeft = noOfItemsLeft;
	}
}