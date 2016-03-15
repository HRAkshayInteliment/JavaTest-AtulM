package com.inteliment.rest.util;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CounterAPIUtil {

	public String convertMapToJsonString(Map<?, ?> dataMap){
		String response = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			response = mapper.writeValueAsString(dataMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return response;
	}

	public String convertListToJsonString(List<?> dataList){
		String response = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			response = mapper.writeValueAsString(dataList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public String [] splitString(String input, String pattern){
		if(input != null && !"".equals(input) &&
				pattern != null && !"".equals(pattern)){
			return input.split(pattern);
		}
		return null;
	}
}
