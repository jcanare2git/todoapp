package com.jcanare2.todoapp.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.jcanare2.todoapp.persistence.entity.Task;
import com.jcanare2.todoapp.persistence.entity.TaskStatus;
import com.jcanare2.todoapp.service.dto.TaskDTO;

@Component
public class TaskDTOToTask implements IMapper<TaskDTO, Task>{

	@Override
	public Task map(TaskDTO in) {
		
		Task task = new Task();
		task.setTitle(in.getTitle());
		task.setDescription(in.getDescription());
		task.setEta(in.getEta());
		task.setCreatedDate(LocalDateTime.now());
		task.setTaskStatus(TaskStatus.ON_TIME);
		task.setFinished(false);
		
		return task;
	}
	
	

}
