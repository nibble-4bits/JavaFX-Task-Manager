package com.finalproject.API.controller.login;

import com.finalproject.API.model.User;
import com.finalproject.API.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value="/login/passRecovery", method = RequestMethod.POST)
    public ResponseEntity<User> getSecurityFields(@RequestBody User user) {
        return new ResponseEntity<>(loginService.getSecurityFields(user), HttpStatus.OK);
    }

    @RequestMapping(value="/login/passRecovery/send/{email}", method = RequestMethod.GET)
    public ResponseEntity<Integer> sendPasswordRecoveryEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(loginService.sendPasswordRecoveryEmail(email), HttpStatus.OK);
    }

    @RequestMapping(value="/login/state/{email}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkSessionState(@PathVariable("email") String email) {
        return new ResponseEntity<>(loginService.checkSessionState(email), HttpStatus.OK);
    }

    @RequestMapping(value="/login/token/{tkn}", method = RequestMethod.POST)
    public ResponseEntity<String> checkIfValid(@PathVariable("tkn") String token) {
        return new ResponseEntity<>(loginService.checkIfTokenValid(token), HttpStatus.OK);
    }
}
