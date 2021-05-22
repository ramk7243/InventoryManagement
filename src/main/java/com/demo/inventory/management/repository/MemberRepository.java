package com.demo.inventory.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.inventory.management.entity.Member;
import com.demo.inventory.management.projection.MemberIdName;

public interface MemberRepository extends JpaRepository<Member, String> {

	Member findByEmail(String email);
	Member findByIdAndIsActiveAndIsDeleted(String memberId, Boolean isActive, Boolean isDeleted);
    @Query("SELECT m.id as id, m.title as title, m.firstName as firstName, m.lastName as lastName"
            + " FROM Member m WHERE m.id=?1")
	MemberIdName findMemberIdNameById(String id);
}
