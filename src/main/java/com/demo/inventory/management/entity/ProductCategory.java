package com.demo.inventory.management.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.demo.inventory.management.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_category")
@Getter @Setter
public class ProductCategory extends UserDateAudit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4253549613954766758L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String categoryName;
    
    @JsonIgnore
    private Boolean isActive = true;

    @JsonIgnore
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

}
