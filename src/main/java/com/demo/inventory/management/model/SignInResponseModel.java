package com.demo.inventory.management.model;

import java.io.Serializable;
import java.util.Set;

import com.demo.inventory.management.projection.MemberIdName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInResponseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String token;

	private MemberIdName memberIdName;

	private Set<String> authorities;

}
