package com.demo.inventory.management.service;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.demo.inventory.management.model.SignInResponseModel;
import com.demo.inventory.management.model.TokenUserDetails;
import com.demo.inventory.management.projection.MemberIdName;
import com.demo.inventory.management.repository.MemberRepository;


@Service
public class AuthService
{

    @Autowired
    private MemberRepository memberRepository;

    public SignInResponseModel getUserDetails(TokenUserDetails principal)
    {
        MemberIdName memberIdName = memberRepository.findMemberIdNameById(principal.getId());

        Collection<GrantedAuthority> authorities = principal.getAuthorities();
        Set<String> authoritiySet = authorities.stream().filter(Objects::nonNull).map(GrantedAuthority::getAuthority)
            .collect(Collectors.toSet());

        SignInResponseModel respModel = new SignInResponseModel();
        respModel.setToken(principal.getToken());
        respModel.setMemberIdName(memberIdName);
        respModel.setAuthorities(authoritiySet);
        return respModel;
    }

}
