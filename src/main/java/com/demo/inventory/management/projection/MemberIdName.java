package com.demo.inventory.management.projection;

import org.springframework.beans.factory.annotation.Value;

public interface MemberIdName {

	@Value("#{(target.title != null ? target.title : '')+' '+target.firstName+' '+ target.lastName}")
	String getName();

	@Value("#{target.id}")
	String getId();
}