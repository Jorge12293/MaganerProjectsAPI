package com.company.abc.management.projects.api.dto.filters;
import java.util.List;
import com.company.abc.management.projects.api.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterNameProjectsTasksDTO {
	 private String name; 
	 private  List<Status> statusList;
}
