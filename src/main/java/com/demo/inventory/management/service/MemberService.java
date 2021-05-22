package com.demo.inventory.management.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.inventory.management.entity.Member;
import com.demo.inventory.management.entity.Role;
import com.demo.inventory.management.entity.RoleMember;
import com.demo.inventory.management.entity.RolePermission;
import com.demo.inventory.management.repository.MemberRepository;
import com.demo.inventory.management.repository.RoleMemberRepository;
import com.demo.inventory.management.security.CustomAuthority;

@Service
public class MemberService
{
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Autowired
    private RoleMemberRepository roleMemberRepository;

    public Set<CustomAuthority> getAuthorities(String memberId)
    {
        Set<CustomAuthority> authorities = null;
        Member member = memberRepository.findByIdAndIsActiveAndIsDeleted(memberId, Boolean.TRUE, Boolean.FALSE);
        List<RoleMember> roleMemberList = roleMemberRepository.findByMember(member);
        if (roleMemberList != null) {
            authorities = roleMemberList.stream().filter(Objects::nonNull).map(RoleMember::getRole)
                .map(Role::getRolePermissions).flatMap(Collection::stream).map(RolePermission::getPermission)
                .map(p -> new CustomAuthority("ROLE_PERMISSION#" + p.getPermissionName())).collect(Collectors.toSet());
        }
        return authorities;
    }

}

