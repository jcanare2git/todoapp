package com.jcanare2.todoapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcanare2.todoapp.persistence.entity.Task;
import com.jcanare2.todoapp.persistence.entity.TaskStatus;
import com.jcanare2.todoapp.service.TaskService;
import com.jcanare2.todoapp.service.dto.TaskDTO;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@PostMapping
	public Task create(@RequestBody TaskDTO taskDto) {

		return this.taskService.createTask(taskDto);

	}

	@GetMapping
	public List<Task> getAll() {
		return this.taskService.findAll();
	}

	@GetMapping("/byStatus/{status}")
	public List<Task> getAllByStatus(@PathVariable("status") TaskStatus status) {
		return this.taskService.findAllByStatus(status);
	}

	@PatchMapping("/mark_as_finished/{id}")
	public ResponseEntity<Void> markAsFinished(@PathVariable("id") Long id) {
		this.taskService.updateTaskAsFinished(id);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		this.taskService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
