package com.finalproject.API.service.login;

import com.finalproject.API.datautil.UConnection;
import com.finalproject.API.model.User;
import com.finalproject.API.util.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class LoginService {

    private static List<User> loggedInUsers = new ArrayList<>();

    @Autowired private Email email;

    private void sendToken(String to) {
        Random rnd = new Random();
        String token = "";

        for (int i = 0; i < 5; i++) {
            if (rnd.nextInt(2) == 0) { // int
                token += rnd.nextInt(10);
            }
            else { // char
                token += (char) (65 + rnd.nextInt(26));
            }
        }
        email.sendMail(to, "Task manager app token", token);
    }

    public User authenticateUser(User user) {
        if (loggedInUsers.contains(user)) { // if the user is already logged in
            User alreadyLogged = new User();
            alreadyLogged.setEmail("alreadylogged");
            sendToken(user.getEmail());
            return alreadyLogged;
        }

        User authUser = new User();
        Connection conn;
        ResultSet rs;

        try {
            conn = UConnection.getConnection();
            String query = "{CALL authenticateUser(?, ?)}";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (!rs.getMetaData().getColumnName(1).equals("IdUser")) {
                    if (rs.getInt(1) == 1) { // user doesn't exist
                        User nonExistent = new User();
                        nonExistent.setEmail("nonexistent");
                        return nonExistent;
                    } else if (rs.getInt(1) == 2) { // user exists but passwords don't match
                        User passwordMismatch = new User();
                        passwordMismatch.setEmail("passmismatch");
                        return passwordMismatch;
                    }
                }
                else {
                    authUser.setId(rs.getInt("IdUser"));
                    authUser.setEmail(rs.getString("Email"));
                    authUser.setType(rs.getInt("Type"));
                }
            }

            rs.close();
            stmt.close();

            loggedInUsers.add(authUser);
            return authUser;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public boolean deauthenticateUser(User user) {
        return loggedInUsers.remove(user);
    }
}
