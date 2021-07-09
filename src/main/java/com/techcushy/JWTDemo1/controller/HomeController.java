package com.techcushy.JWTDemo1.controller;

import com.techcushy.JWTDemo1.models.AuthenticationRequest;
import com.techcushy.JWTDemo1.models.AuthenticationResponce;
import com.techcushy.JWTDemo1.service.MyUserService;
import com.techcushy.JWTDemo1.utility.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserService userDetilesService;

    @Autowired
    private JWTUtil jwtTokenUtil;

    @RequestMapping("/hello")
    public String home() {
        return "Hello world";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getUserName()));
        }
        catch(BadCredentialsException e){
         throw new Exception("Incorrect userName or password",e);
        }

        final UserDetails userDetails = userDetilesService.loadUserByUsername(authenticationRequest.getUserName());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponce(jwt));
    }
}

