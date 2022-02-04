package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.model.Student;
import com.example.schoolmanagement.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/school")
@CrossOrigin

public class StudentController {
    StudentRepo studentRepo;

    @Autowired
    public StudentController(StudentRepo studentRepo){
        this.studentRepo=studentRepo;
    }

    @ResponseStatus
    @GetMapping
    public List<Student> getAll(Student student){
        return studentRepo.findAll().stream().sorted((s1,s2)->s1.getName().compareTo(s2.getName())).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    Student addStudent(@RequestBody Student stud) {
        this.studentRepo.save(stud);
        return stud ;
    }
    @ResponseStatus
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id){
            Optional<Student> s=studentRepo.findById(id);
            s.ifPresent(student->{
                studentRepo.delete(student);
            });
    }


    @PutMapping("/{id}")
    @ResponseStatus(
            code=HttpStatus.OK
    )
    public void updateStudent(@RequestBody Student nstud,@PathVariable Long id) throws Exception {
        this.studentRepo.findById(id).map(s->{
            s.setName(nstud.getName());
            s.setAddress(nstud.getAddress());
            s.setEmail(nstud.getEmail());
            return studentRepo.save(s);

        }).orElseThrow(()->new Exception("Student not found"));
    }

    @GetMapping("/place/{fname}")
    @ResponseStatus(
            code=HttpStatus.OK
    )
    public List<Student> filtStud(@PathVariable String fname) {
        return studentRepo.findAll().stream().
                filter(b->b.getName().contains(fname)).collect(Collectors.toList());
    }
}
