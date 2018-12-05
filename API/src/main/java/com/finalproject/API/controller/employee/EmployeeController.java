package com.finalproject.API.controller.employee;

import com.finalproject.API.model.Task;
import com.finalproject.API.model.User;
import com.finalproject.API.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired private EmployeeService employeeService;

    @RequestMapping(value="/employee/{email}", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getEmployeeTasks(@PathVariable("email")String email) {
        return new ResponseEntity<>(employeeService.getTasksByUser(email), HttpStatus.OK);
    }

    @RequestMapping(value="/employee/advance/{taskid}", method = RequestMethod.POST)
    public ResponseEntity<Integer> advanceTask(@PathVariable("taskid")int taskId) {
        return new ResponseEntity<>(employeeService.advanceTask(taskId), HttpStatus.OK);
    }
}
