package com.company.abc.management.projects.api.rest;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.abc.management.projects.api.dto.filters.FilterNameProjectsTasksDTO;
import com.company.abc.management.projects.api.dto.filters.FilterProjectsTasksDTO;
import com.company.abc.management.projects.api.enums.Status;
import com.company.abc.management.projects.api.services.api.FilterService;
import com.company.abc.management.projects.api.utils.ResponseResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/filters")
@AllArgsConstructor
@Tag(name="Filters")
public class FilterRest {
	

    private final FilterService filterService;


    @Operation(summary = "Filtrar tareas y proyectos por el id del usuario y status")
    @PostMapping(value= "/tasksAndProyects")
    public ResponseEntity<ResponseResult<?>> tasksAndProyects(
    		@RequestBody FilterNameProjectsTasksDTO filterNameProjectsTasksDTO
    ) {    	
        String nameFilter = filterNameProjectsTasksDTO.getName();
        List<Status> listStatusFilter = filterNameProjectsTasksDTO.getStatusList();
        if(nameFilter == null && listStatusFilter==null) {
        	FilterProjectsTasksDTO listEmpty = new FilterProjectsTasksDTO(Collections.emptyList(),Collections.emptyList());
        	return new ResponseEntity<>(new ResponseResult<>(true, "OK", listEmpty, null), HttpStatus.OK);
        }
        if(nameFilter==null) {
        	nameFilter = "";
        }
        if(listStatusFilter==null) {
        	listStatusFilter = Collections.emptyList();    
        }
    	FilterProjectsTasksDTO filterProjectsTasksDTO = filterService.filterProjectsTasksDTO(
    		nameFilter,listStatusFilter);
    	
		return new ResponseEntity<>(new ResponseResult<>(true, "OK", filterProjectsTasksDTO, null), HttpStatus.OK);
    }

}
