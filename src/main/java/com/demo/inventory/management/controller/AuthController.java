package com.demo.inventory.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.inventory.management.model.SignInResponseModel;
import com.demo.inventory.management.model.TokenUserDetails;
import com.demo.inventory.management.model.response.Response;
import com.demo.inventory.management.service.AuthService;
import com.demo.inventory.management.utils.Constants;

@RestController
@RequestMapping(Constants.AUTH_URL)
public class AuthController
{

    @Autowired
    private AuthService authService;

    @GetMapping("/signin")
    public ResponseEntity<?> signin(@AuthenticationPrincipal TokenUserDetails principal)
    {
        SignInResponseModel responseModel = authService.getUserDetails(principal);
        return new ResponseEntity<>(new Response(responseModel), HttpStatus.OK);
    }
}
