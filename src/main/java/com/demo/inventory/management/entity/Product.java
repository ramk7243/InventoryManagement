package com.demo.inventory.management.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.demo.inventory.management.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "products")
public class Product extends UserDateAudit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4828519578542358577L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    String productName;
    
    @Column(length = 2000)
    private String description;
    
    //private String productCategory;
    
    private Integer units;
    
    @JsonIgnore
    private Boolean isActive;

    @JsonIgnore
    private Boolean isDeleted;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_category_id")
    @JsonIgnore
    private ProductCategory productCategory;

}
