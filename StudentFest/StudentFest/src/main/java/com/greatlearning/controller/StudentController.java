package com.greatlearning.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.entity.Student;
import com.greatlearning.service.StudentService;
import com.mysql.cj.x.protobuf.MysqlxNotice.ServerHello;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentServiceImpl;

	@RequestMapping("/list")
	public String listStudents(Model model) {

		List<Student> students = studentServiceImpl.findAll();
		model.addAttribute("Students", students);
		return "list-Students";
	}

	@RequestMapping("/showFormforAdd")
	public String showFormforAdd(Model model) {
		Student student = new Student();
		model.addAttribute("Student", student);
		return "Student-form";
	}

	@RequestMapping("/showFormforUpdate")
	public String showFormforUpdate(@RequestParam("studentId") int id, Model model) {

		Student student = studentServiceImpl.findById(id);
		model.addAttribute("Student", student);
		return "Student-form";
	}

	@PostMapping("/save")
	public String save(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("course") String course,
			@RequestParam("country") String country) {

		Student student;

		if (id != 0) {
			student = studentServiceImpl.findById(id);
			student.setFirstName(firstName);
			student.setLastName(lastName);
			student.setCourse(course);
			student.setCountry(country);

		} else {
			student = new Student(firstName, lastName, course, country);
		}
		studentServiceImpl.save(student);

		return "redirect:/student/list";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		studentServiceImpl.deleteById(id);
		return "redirect:/student/list";
	}

	@RequestMapping("/403")
	public ModelAndView accessDenied(Principal user) {

		ModelAndView modelAndView = new ModelAndView();

		if (user != null) {
			modelAndView.addObject("msg","Hello " + user.getName() + " !. You Dont have permission to access this Page");
			
		} else {
			modelAndView.addObject("msg","Hello!. You Dont have permission to access this Page");
		}

		modelAndView.setViewName("403");

		return modelAndView;
	}
}
