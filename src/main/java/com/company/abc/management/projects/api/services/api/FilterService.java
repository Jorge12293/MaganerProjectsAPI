package com.company.abc.management.projects.api.services.api;
import java.util.List;

import com.company.abc.management.projects.api.dto.filters.FilterProjectsTasksDTO;
import com.company.abc.management.projects.api.enums.Status;;

public interface FilterService {
	FilterProjectsTasksDTO filterProjectsTasksDTO(String name, List<Status> statusList);
}

