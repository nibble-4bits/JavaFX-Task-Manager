package com.finalproject.API.controller.login;

import com.finalproject.API.model.User;
import com.finalproject.API.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired private LoginService loginService;

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseEntity<User> authenticate(@RequestBody User user) {
        return new ResponseEntity<>(loginService.authenticateUser(user), HttpStatus.OK);
    }

    @RequestMapping(value="/login/deauth", method = RequestMethod.POST)
    public ResponseEntity<Boolean> deauthenticate(@RequestBody User user) {
        return new ResponseEntity<>(loginService.deauthenticateUser(user), HttpStatus.OK);
    }
}
