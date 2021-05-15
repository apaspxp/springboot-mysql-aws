package com.pxp.awsmysql.controller;

import com.pxp.awsmysql.model.StudentModel;
import com.pxp.awsmysql.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello(){
        return studentService.getHtml();
    }

    @RequestMapping(value = "addstudent", method = RequestMethod.POST)
    public String addStudent(@RequestBody StudentModel student){
        return studentService.addStudent(student);
    }

    @RequestMapping(value = "getallstudents", method = RequestMethod.GET)
    public List<StudentModel> getAllStudents(){
        return studentService.getAllStudents();
    }

    @RequestMapping(value = "updatestudent", method = RequestMethod.PUT)
    public String updateStudent(@RequestBody StudentModel studentModel){
        return studentService.updateStudentById(studentModel);
    }
}
