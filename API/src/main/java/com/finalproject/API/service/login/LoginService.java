package com.finalproject.API.service.login;

import com.finalproject.API.datautil.UConnection;
import com.finalproject.API.model.User;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class LoginService {

    public static User authenticateUser(User user) {
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
                    authUser.setType(rs.getInt("Type"));
                }
            }

            rs.close();
            stmt.close();

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
}
