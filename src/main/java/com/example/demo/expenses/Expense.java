package com.example.demo.expenses;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Expense {
	@Id
	@GeneratedValue
	private long id;
	private String username;
	private Date date;
	private double quantity;
	private String category;
	private String description;
	public Expense() {
		super();
	}
	public Expense(long id, String username, Date date, double quantity, String category, String description) {
		super();
		this.id = id;
		this.username = username;
		this.date = date;
		this.quantity = quantity;
		this.category = category;
		this.description = description;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Expense [id=" + id + ", username=" + username + ", date=" + date + ", quantity=" + quantity
				+ ", category=" + category + ", description=" + description + "]";
	}
	
	

}
