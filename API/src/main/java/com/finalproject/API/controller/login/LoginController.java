package com.finalproject.API.controller.login;

import com.finalproject.API.model.User;
import com.finalproject.API.service.login.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseEntity<User> authenticate(@RequestBody User user) {
        return new ResponseEntity<>(LoginService.authenticateUser(user), HttpStatus.OK);
    }
}
