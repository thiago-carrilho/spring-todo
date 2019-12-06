package com.example.demo.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.models.Task;
import com.example.demo.repository.TaskRepository;

@Controller
@RequestMapping("/task")
@SessionAttributes("task")
public class TaskController {
	private final TaskRepository taskRepo;
	@Autowired
	public TaskController(TaskRepository taskRepo) {
		this.taskRepo=taskRepo;
	}
	@GetMapping("/new")
	public String showNewForm(Model model) {
		System.out.println("Oi");
		model.addAttribute("task", new Task());
		return "newTask";
	}
	@GetMapping
	public String showAllTasks(@ModelAttribute("tasks") ArrayList<Task> tasks) {
		//List<Task> tasks = new ArrayList<Task>();
		taskRepo.findAll().forEach(task->tasks.add(task));
		System.out.println(tasks);
		return "viewAll";
	}
	@ModelAttribute("tasks")
	public ArrayList<Task> tasks(){
		return new ArrayList<Task>();
	}
	@PostMapping
	public String processTask(Task task) {
		taskRepo.save(task);
		return "redirect:/task";
	}
}
