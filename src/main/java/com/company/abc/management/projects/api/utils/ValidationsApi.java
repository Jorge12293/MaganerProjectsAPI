package com.company.abc.management.projects.api.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.validation.BindingResult;

import com.company.abc.management.projects.api.enums.Status;


 public class ValidationsApi {
	 
	 public static Map<String, String> validate(BindingResult resultBinding) {
		Map<String, String> errors = new HashMap<>();
		resultBinding.getFieldErrors().forEach(error -> errors.put(error.getField(),"El campo " + error.getField() + " " + error.getDefaultMessage()));
		return errors;
	}
	 
	 public static boolean isValidStatus(String status) {
	        return Arrays.stream(Status.values())
	                     .anyMatch(enumValue -> enumValue.name().equals(status));
	  }
}


