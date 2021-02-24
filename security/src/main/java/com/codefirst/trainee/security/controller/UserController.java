package com.codefirst.trainee.security.controller;

import com.codefirst.trainee.security.config.TokenProvider;
import com.codefirst.trainee.security.model.AuthToken;
import com.codefirst.trainee.security.model.LoginUser;
import com.codefirst.trainee.security.model.UserDto;
import com.codefirst.trainee.security.model.Userc;
import com.codefirst.trainee.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Userc saveUser(@RequestBody UserDto user) {
        return userService.saveUser(user);
    }


    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/userping", method = RequestMethod.GET)
    public String userPing() {
        return "Any User Can Read This";
    }

    @GetMapping("/protectedLinks")
    public String getAnonymousPage() {
        return "protectedLinks";
    }

    @GetMapping("/userPage")
    public String getUserPage() {
        return "userPage";
    }

    @GetMapping("/adminPage")
    public String getAdminPage() {
        return "adminPage";
    }

    @GetMapping("/loginAdmin")
    public String getAdminLoginPage() {
        return "src/main/webapp/WEB-INF/view/admin_login";
    }

    @GetMapping("/loginUser")
    public String getUserLoginPage() {
        return "src/main/webapp/WEB-INF/view/user_login";
    }

    @GetMapping("/loginOperator")
    public String getOperatorLoginPage() {
        return "src/main/webapp/WEB-INF/view/operator_login";
    }

    @GetMapping("/home")
    public String getMainPage() {
        return "Main";
    }

    @GetMapping("/403")
    public String getAccessDeniedPage() {
        return "403";
    }

}