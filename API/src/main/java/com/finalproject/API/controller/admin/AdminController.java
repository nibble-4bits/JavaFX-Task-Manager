package com.finalproject.API.controller.admin;

import com.finalproject.API.model.Department;
import com.finalproject.API.model.Task;
import com.finalproject.API.model.User;
import com.finalproject.API.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired AdminService adminService;

    @RequestMapping(value="/admin", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllEmployees() {
        return new ResponseEntity<>(adminService.getAllEmployees(), HttpStatus.OK);
    }

    @RequestMapping(value="/admin/departments", method = RequestMethod.GET)
    public ResponseEntity<List<Department>> getAllDepartments() {
        return new ResponseEntity<>(adminService.getAllDepartments(), HttpStatus.OK);
    }

    @RequestMapping(value="/admin/add/{depId}", method = RequestMethod.POST)
    public ResponseEntity<Integer> addEmployee(@PathVariable("depId")int departmentId, @RequestBody User user) {
        return new ResponseEntity<>(adminService.addEmployee(departmentId, user), HttpStatus.OK);
    }

    @RequestMapping(value="/admin/addDept", method = RequestMethod.POST)
    public ResponseEntity<Integer> addDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(adminService.addDepartment(department), HttpStatus.OK);
    }

    @RequestMapping(value="/admin/update/{depId}/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<Integer> updateEmployee(@PathVariable("userId")int userId, @PathVariable("depId")int departmentId, @RequestBody User user) {
        return new ResponseEntity<>(adminService.updateEmployee(userId, departmentId, user), HttpStatus.OK);
    }

    @RequestMapping(value="/admin/task/{userId}", method = RequestMethod.POST)
    public ResponseEntity<Integer> assignTask(@PathVariable("userId")int userId, @RequestBody Task task) {
        return new ResponseEntity<>(adminService.assignTask(userId, task), HttpStatus.OK);
    }

    @RequestMapping(value="/admin/userPerformance/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Integer[]> getUserTasksByStatus(@PathVariable("userId")int userId) {
        return new ResponseEntity<>(adminService.getUserTasksByStatus(userId), HttpStatus.OK);
    }
}
