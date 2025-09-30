package com.example.ezen.rest;

import java.util.List;

public class Items {
	private List<Item> item;

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "Items [item=" + item + "]";
	}
	
	
}
