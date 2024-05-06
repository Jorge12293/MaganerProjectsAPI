package com.company.abc.management.projects.api.respositories.api;
import java.util.List;
import java.util.Optional;

import com.company.abc.management.projects.api.enums.Status;
import com.company.abc.management.projects.api.models.Task;

public interface TaskRepository {
	Optional<Task> save(Task task);
	Optional<Task> update(Task task,Long id);
	void delete(Long id);
	List<Task> list();
	List<Task> listByProjectId(Long projectId);
	List<Task> listByUserId(Long userId);
	List<Task> listByNameStatus(String name,List<Status> listStatus);
	Optional<Task> getById(Long id);
	
}

