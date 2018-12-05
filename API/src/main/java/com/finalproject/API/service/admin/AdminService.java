package com.finalproject.API.service.admin;

import com.finalproject.API.datautil.UConnection;
import com.finalproject.API.model.Department;
import com.finalproject.API.model.Task;
import com.finalproject.API.model.User;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    public List<User> getAllEmployees() {
        List<User> users = new ArrayList<>();
        Connection conn;
        ResultSet rs;

        try {
            conn = UConnection.getConnection();
            String query = "{CALL getAllEmployees()}";
            CallableStatement stmt = conn.prepareCall(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setEmail(rs.getString("Email"));
                user.setType(rs.getInt("Type"));
                user.setSecurityQuestion(rs.getString("SecurityQuestion"));
                user.setSecurityAnswer(rs.getString("SecurityAnswer"));
                user.setPassword(rs.getString("Password"));
                user.setName(rs.getString("Name"));
                user.setLastName(rs.getString("LastName"));
                user.setId(rs.getInt("IdUser"));
                user.setHireDate(rs.getDate("HireDate").toLocalDate());
                user.setDateOfBirth(rs.getDate("DateOfBirth").toLocalDate());
                user.setDepartment(rs.getString("DepartmentName"));

                users.add(user);
            }

            rs.close();
            stmt.close();

            return users;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        Connection conn;
        ResultSet rs;

        try {
            conn = UConnection.getConnection();
            String query = "{CALL getAllDepartments()}";
            CallableStatement stmt = conn.prepareCall(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt("IdDepartment"));
                department.setName(rs.getString("Name"));

                departments.add(department);
            }

            rs.close();
            stmt.close();

            return departments;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public int addEmployee(int departmentId, User user) {
        Connection conn;

        try {
            conn = UConnection.getConnection();
            String query = "{CALL addEmployee(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setInt(1, departmentId);
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getLastName());
            stmt.setDate(5, Date.valueOf(user.getDateOfBirth()));
            stmt.setDate(6, Date.valueOf(user.getHireDate()));
            stmt.setString(7, user.getPassword());
            stmt.setString(8, user.getSecurityQuestion());
            stmt.setString(9, user.getSecurityAnswer());
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

    public int addDepartment(Department department) {
        Connection conn;

        try {
            conn = UConnection.getConnection();
            String query = "{CALL addDepartment(?)}";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1, department.getName());
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

    public int updateEmployee(int userId, int departmentId, User user) {
        Connection conn;

        try {
            conn = UConnection.getConnection();
            String query = "{CALL updateEmployee(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setInt(1, userId);
            stmt.setInt(2, departmentId);
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getName());
            stmt.setString(5, user.getLastName());
            stmt.setDate(6, Date.valueOf(user.getDateOfBirth()));
            stmt.setDate(7, Date.valueOf(user.getHireDate()));
            stmt.setString(8, user.getPassword());
            stmt.setString(9, user.getSecurityQuestion());
            stmt.setString(10, user.getSecurityAnswer());
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

    public int assignTask(int userId, Task task) {
        Connection conn;

        try {
            conn = UConnection.getConnection();
            String query = "{CALL assignTask(?, ?, ?)}";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setInt(1, userId);
            stmt.setString(2, task.getTitle());
            stmt.setString(3, task.getDescription());

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

    public Integer[] getUserTasksByStatus(int userId) {
        Integer[] count = new Integer[3];
        Connection conn;
        ResultSet rs;

        try {
            for (int i = 0; i < count.length; i++) {
                count[i] = 0;
            }

            conn = UConnection.getConnection();
            String query = "{CALL getUserTasksByStatus(?)}";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                count[rs.getInt("Status")] = rs.getInt("Count");
            }

            rs.close();
            stmt.close();

            return count;
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
