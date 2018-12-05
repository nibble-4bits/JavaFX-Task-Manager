package com.finalproject.API.service.login;

import com.finalproject.API.datautil.UConnection;
import com.finalproject.API.model.User;
import com.finalproject.API.util.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
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

    public User authenticateUser(User user) {
        if (loggedInUsers.contains(user)) { // if the user is already logged in
            User alreadyLogged = new User();
            alreadyLogged.setEmail("alreadylogged");
            sendToken(user.getEmail(), user);
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

    public boolean checkSessionState(String email) {
        User user = new User();
        user.setEmail(email);

        return loggedInUsers.contains(user);
    }

    public String checkIfTokenValid(String token) {
        String result = "";
        Connection conn;
        ResultSet rs;

        try {
            conn = UConnection.getConnection();
            String query = "{CALL checkIfValid(?)}";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1, token);
            rs = stmt.executeQuery();

            while (rs.next()) {
                result = rs.getString(1);
                if (!result.equals("INVALID"))  {
                    User user = new User();
                    user.setEmail(result);
                    deauthenticateUser(user);
                }
            }

            rs.close();
            stmt.close();

            return result;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    private void sendToken(String to, User user) {
        String token = generateToken();
        email.sendMail(to, "Task manager app token", token);
        saveToken(token, user.getEmail());
    }

    private String generateToken() {
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
        return token;
    }

    private void saveToken(String token, String email) {
        Connection conn;

        try {
            conn = UConnection.getConnection();
            String query = "{CALL saveToken(?, ?)}";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1, token);
            stmt.setString(2, email);
            stmt.execute();
            stmt.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
