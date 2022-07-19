package com.jcanare2.todoapp.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jcanare2.todoapp.exceptions.ToDoExceptions;
import com.jcanare2.todoapp.mapper.TaskDTOToTask;
import com.jcanare2.todoapp.persistence.entity.Task;
import com.jcanare2.todoapp.persistence.entity.TaskStatus;
import com.jcanare2.todoapp.persistence.repository.TaskRepository;
import com.jcanare2.todoapp.service.dto.TaskDTO;

@Service
public class TaskService {

	private final TaskRepository taskRepository;
	private final TaskDTOToTask mapper;

	public TaskService(TaskRepository taskRepository, TaskDTOToTask mapper) {
		this.mapper = mapper;
		this.taskRepository = taskRepository;
	}

	public Task createTask(TaskDTO taskDto) {

		Task task = mapper.map(taskDto);

		// Save the entity

		return this.taskRepository.save(task);
	}

	public List<Task> findAll() {

		return this.taskRepository.findAll();

	}

	public List<Task> findAllByStatus(TaskStatus status) {
		return this.taskRepository.findAllByTaskStatus(status);
	}

	@Transactional
	public void updateTaskAsFinished(Long id) {

		Optional<Task> optionalTask = this.taskRepository.findById(id);
		if (optionalTask.isEmpty()) {
			throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
		}
		this.taskRepository.markTaskAsFinished(id);

	}

	public void deleteById(Long id) {
		Optional<Task> optionalTask = this.taskRepository.findById(id);
		if (optionalTask.isEmpty()) {
			throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
		}

		this.taskRepository.deleteById(id);
	}
}
