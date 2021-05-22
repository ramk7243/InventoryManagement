package com.demo.inventory.management.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.demo.inventory.management.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "member")
@Getter
@Setter
public class Member extends DateAudit implements Serializable {

	private static final long serialVersionUID = -4004372760659146212L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	private String title;

	private String firstName;

	private String lastName;
	
	private String email;

	@JsonIgnore
	private String password;

	private Boolean isAdmin;

	private Boolean isActive;

	@JsonIgnore
	private Boolean isDeleted;
}
