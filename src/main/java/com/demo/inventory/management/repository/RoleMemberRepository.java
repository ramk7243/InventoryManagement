package com.demo.inventory.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.inventory.management.entity.Member;
import com.demo.inventory.management.entity.RoleMember;

public interface RoleMemberRepository extends JpaRepository<RoleMember, String> {
	List<RoleMember> findByMember(Member member);
}