package com.ejada.task2shop.dto;

public class ProductDto {
	
	private Integer id;
	private String name;
	private String description;
	private Float price;
	private Integer quantity;
	private Boolean inStock;
	
	public ProductDto() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductDto(Integer id, String name, String description, Float price, Integer quantity, Boolean inStock) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.inStock = inStock;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getInStock() {
		return inStock;
	}

	public void setInStock(Boolean inStock) {
		this.inStock = inStock;
	}

	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", quantity=" + quantity + ", inStock=" + inStock + "]";
	}

	
	
	
}
