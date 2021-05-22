package com.demo.inventory.management.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.inventory.management.entity.Member;
import com.demo.inventory.management.model.TokenUserDetails;
import com.demo.inventory.management.repository.MemberRepository;
import com.demo.inventory.management.security.CustomAuthority;
import com.demo.inventory.management.security.TokenService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private MemberService memberService;

	@Override
	public UserDetails loadUserByUsername(String userName) {
		try {
			
			return getUserDetails(userName);
		} catch (AuthenticationException ex) {
			log.error("Account for '" + userName + "' not found");
			throw new UsernameNotFoundException("Account for '" + userName + "' not found", ex);
		}
	}

	private TokenUserDetails getUserDetails(String email) {
		log.debug("Trying to authenticate {}", email);
		Member member = memberRepository.findByEmail(email);
		if (member == null) {
			throw new UsernameNotFoundException("User not found");
		}
		validateMember(member);
		Set<CustomAuthority> authorities = memberService.getAuthorities(member.getId());
		
		return new TokenUserDetails(member.getId(), member.getEmail(), member.getPassword(),
				tokenService.encode(member), authorities);

	}

	private void validateMember(Member member) {
		if (!member.getIsActive()) {
			throw new DisabledException("Member inactive");
		}

	}

}
