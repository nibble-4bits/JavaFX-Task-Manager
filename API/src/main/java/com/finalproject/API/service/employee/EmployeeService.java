package com.finalproject.API.service.employee;

import com.finalproject.API.datautil.UConnection;
import com.finalproject.API.model.Task;
import com.finalproject.API.model.User;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    public List<Task> getTasksByUser(String email) {
        List<Task> tasks = new ArrayList<>();
        Connection conn;
        ResultSet rs;

        try {
            conn = UConnection.getConnection();
            String query = "{CALL getTasksByUserEmail(?)}";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("IdTask"));
                task.setTitle(rs.getString("Title"));
                task.setDescription(rs.getString("Description"));
                task.setStatus(rs.getInt("Status"));

                tasks.add(task);
            }

            rs.close();
            stmt.close();

            return tasks;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public int advanceTask(int taskId) {
        Connection conn;

        try {
            conn = UConnection.getConnection();
            String query = "{CALL advanceTask(?)}";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setInt(1, taskId);
            int rows = stmt.executeUpdate();

            stmt.close();

            return rows;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }
}
