package com.pxp.awsmysql.service;

import com.pxp.awsmysql.entity.StudentEntity;
import com.pxp.awsmysql.model.StudentModel;
import com.pxp.awsmysql.repository.StudentRepository;
import com.pxp.awsmysql.template.HelloTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String addStudent(StudentModel student){
        StudentEntity studentEntity = new StudentEntity();
        BeanUtils.copyProperties(student, studentEntity);
        try {
            if (!studentRepository.existsByFirstNameAndLastName(student.getFirstName(), student.getLastName())) {
                studentRepository.save(studentEntity);
                return "Student " + student.getFirstName() + " " + student.getLastName() + " added successfully.";
            }else {
                return "Student " + student.getFirstName() + " " + student.getLastName() + " exists in the database already.";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "Student could not be added.";
        }
    }

    public List<StudentModel> getAllStudents(){
        List<StudentModel> studentModels = new ArrayList<>();
        try {
            studentRepository.findAll().stream().forEach(s -> {
                StudentModel studentModel = new StudentModel();
                BeanUtils.copyProperties(s, studentModel);
                studentModels.add(studentModel);
            });
            return studentModels;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public StudentModel getStudentById(long id){
        try {
            Optional<StudentEntity> studentEntity = studentRepository.findById(id);
            if (studentEntity.isPresent()){
                StudentModel studentModel = new StudentModel();
                BeanUtils.copyProperties(studentEntity.get(), studentModel);
                return studentModel;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String updateStudentById(StudentModel studentModel){
        try {
            Optional<StudentEntity> studentEntity = studentRepository.findById(studentModel.getId());
            if (studentEntity.isPresent()){
                StudentEntity studentEntity1 = new StudentEntity();
                BeanUtils.copyProperties(studentModel, studentEntity1);
                studentRepository.save(studentEntity1);
                return "Student " + studentModel.getFirstName() + " " + studentModel.getLastName() + " updated.";
            }else {
                return "No student exists with name " + studentModel.getFirstName() + " " + studentModel.getLastName();
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String getHtml(){
        HelloTemplate template = new HelloTemplate();
        return template.getHtml();
    }
}
