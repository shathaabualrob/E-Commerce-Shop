package com.ejada.task2inventory.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductDto {
	
	@NotBlank
	@NotNull
	@Size(min = 3, max = 30)
	private String name;
	
	@NotBlank
	@NotNull
	@Size(min = 3)
	private String description;
	
	@NotNull
	@DecimalMin(value = "0.1", inclusive = false)
	private Float price;
	
	@Min(1)
	private Integer quantity;
	
	public ProductDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductDto(String name, String description, Float price, Integer quantity) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
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

	@Override
	public String toString() {
		return "ProductDto [name=" + name + ", description=" + description + ", price=" + price + ", quantity="
				+ quantity + "]";
	}
}