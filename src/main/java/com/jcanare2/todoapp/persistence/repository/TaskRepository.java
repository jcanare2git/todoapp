package com.jcanare2.todoapp.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jcanare2.todoapp.persistence.entity.Task;
import com.jcanare2.todoapp.persistence.entity.TaskStatus;

public interface TaskRepository extends JpaRepository<Task, Long> {

	public List<Task> findAllByTaskStatus(TaskStatus status);
	
	@Modifying
	@Query(value="UPDATE task SET finished = true WHERE id=:id", nativeQuery = true)
	public void markTaskAsFinished(@Param("id") Long id);
}
