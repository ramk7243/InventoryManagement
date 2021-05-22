package com.demo.inventory.management.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.inventory.management.config.ProductInventoryConfig;
import com.demo.inventory.management.entity.Product;
import com.demo.inventory.management.exception.InventoryException;
import com.demo.inventory.management.model.request.ProductRequest;
import com.demo.inventory.management.model.response.Response;
import com.demo.inventory.management.repository.ProductRepository;
import com.demo.inventory.management.utils.Constants;
import com.demo.inventory.management.utils.ErrorType;
import com.demo.inventory.management.utils.InventoryUtils;
import com.demo.inventory.management.utils.SchemaValidationUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductInventoryConfig productInventoryConfig;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	
	public ResponseEntity<Response> getProductsbyCategory(String category, HttpHeaders httpHeaders) {
		List<Product> products;
		try {
			products = productRepository.findAllByProductCategoryCategoryName(category);
		} catch (Exception e) {
			throw new InventoryException("Error while fetching Products", HttpStatus.INTERNAL_SERVER_ERROR,
					ErrorType.INTERNAL_SERVER_ERROR);
		}
		if (!products.isEmpty()) {
			return new ResponseEntity<>(InventoryUtils.getObjectResponse(products), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(InventoryUtils.getZeroRecordResponse("No Products Found"), HttpStatus.OK);
		}
	}

	public ResponseEntity<Response> getProducts(HttpHeaders httpHeaders) {
		List<Product> products;
		try {
			products = productRepository.findAllByIsActiveAndIsDeleted(Boolean.TRUE, Boolean.FALSE);
		} catch (Exception e) {
			throw new InventoryException("Error while fetching Products", HttpStatus.INTERNAL_SERVER_ERROR,
					ErrorType.INTERNAL_SERVER_ERROR);
		}
		if (!products.isEmpty()) {
			return new ResponseEntity<>(InventoryUtils.getObjectResponse(products), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(InventoryUtils.getZeroRecordResponse("No Products Found"), HttpStatus.OK);
		}
	}

	public ResponseEntity<Response> saveProduct(String product, HttpHeaders httpHeaders) {

		// Validation
		ProductRequest productRequestObject = getProductRequestObjectAndValidation(product);
		// Save it to Database.
		Product productToDatabase = modelMapper.map(productRequestObject, Product.class);
		productToDatabase.setIsActive(Boolean.TRUE);
		productToDatabase.setIsDeleted(Boolean.FALSE);

		try {
			productRepository.save(productToDatabase);

		} catch (Exception e) {
			System.out.println("Error while Saving Value");
			throw new InventoryException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
					ErrorType.INTERNAL_SERVER_ERROR);
		}
		// Success Response Generation
		return new ResponseEntity<>(InventoryUtils.getSuccessResponse(Constants.PRODUCT_FORM_SAVED), HttpStatus.OK);
	}

	private ProductRequest getProductRequestObjectAndValidation(String productForm) {
		// Schema Validation First.
		SchemaValidationUtils.isJsonValid(productInventoryConfig.productFormSchema(), productForm);

		// Convert to Json Object.
		Gson gson = new Gson();

		ProductRequest productRequestObject = null;

		try {
			productRequestObject = gson.fromJson(productForm, ProductRequest.class);
		} catch (JsonParseException e) {
			throw new InventoryException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
					ErrorType.INTERNAL_SERVER_ERROR);
		}
		return productRequestObject;
	}
	
	public ResponseEntity<Response> updateProduct(String productForm, HttpHeaders httpHeaders)
    {


        // Validation
        ProductRequest productRequestObject = getProductRequestObjectAndValidation(productForm);

        Product productFromRequest = modelMapper.map(productRequestObject, Product.class);
        Product productFromDatabase = productRepository.findByIdAndIsActiveAndIsDeleted(productRequestObject.getId(),
            Boolean.TRUE, Boolean.FALSE);

        if (null == productFromDatabase) {
            throw new InventoryException("Product not found.", HttpStatus.BAD_REQUEST, ErrorType.INTERNAL_SERVER_ERROR);
        }

        // Save it to Database.
        BeanUtils.copyProperties(productFromRequest, productFromDatabase, "isActive", "isDeleted");

        try {
            productRepository.save(productFromDatabase);
        } catch (Exception e) {
            System.out.println("Error while Saving Value");
            throw new InventoryException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorType.INTERNAL_SERVER_ERROR);
        }
        // Success Response Generation
        return new ResponseEntity<>(InventoryUtils.getSuccessResponse(Constants.PRODUCT_FORM_UPDATED), HttpStatus.OK);
    }

}
