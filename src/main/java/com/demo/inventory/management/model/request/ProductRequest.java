package com.demo.inventory.management.model.request;

import org.springframework.stereotype.Component;

import com.demo.inventory.management.entity.ProductCategory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
public class ProductRequest {

	private Integer id;

	private String productName;

	private String description;
	
	private Integer units;

	private ProductCategory productCategory;

	private Boolean isActive;

	private Boolean isDeleted;
}
