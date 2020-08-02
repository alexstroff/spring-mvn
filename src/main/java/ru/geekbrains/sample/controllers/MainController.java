package ru.geekbrains.sample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.geekbrains.sample.dto.Student;
import ru.geekbrains.sample.repository.StudentsRepository;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private StudentsRepository students;

   @GetMapping("/index")
   public String getIndexPage() {
      return "index";
   }

    @GetMapping("/students")
    public String getStudentPage(Model model) {
        model.addAttribute("students", students.findAll());
        //чтобы вывести список студентов нужно добавить бин StudentsRepository положить данные в Model
        return "student";
    }

    @PostMapping("/students")
    public String sendForm(@ModelAttribute Student student, Model model) {
           students.saveOrUpdateStudent(student);
           return "redirect:/students";
    }

    @PostMapping("/profile")
    public String findStudent(@ModelAttribute Student student, Model model) {
       System.out.println(student.getId());
        System.out.println(students.findAll());
       model.addAttribute(students.findById(student.getId()));
       return "profile";
    }
}