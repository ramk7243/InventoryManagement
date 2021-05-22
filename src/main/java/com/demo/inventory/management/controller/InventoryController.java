package com.demo.inventory.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.inventory.management.model.response.Response;
import com.demo.inventory.management.service.ProductService;
import com.demo.inventory.management.utils.Constants;

@RequestMapping(Constants.API_V1_URL)
@RestController
public class InventoryController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/{categoryName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getProductsbyCategoryName(@RequestHeader HttpHeaders httpHeaders,
			@PathVariable String categoryName) {
		return productService.getProductsbyCategory(categoryName, httpHeaders);
	}

	@GetMapping(value = "/get-all-products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getProducts(@RequestHeader HttpHeaders httpHeaders) {
		return productService.getProducts(httpHeaders);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> saveProduct(@RequestBody String product, @RequestHeader HttpHeaders httpHeaders) {
		return productService.saveProduct(product, httpHeaders);

	}

	@PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> updateProduct(@RequestBody String product, @RequestHeader HttpHeaders httpHeaders) {
		return productService.updateProduct(product, httpHeaders);
	}

}
