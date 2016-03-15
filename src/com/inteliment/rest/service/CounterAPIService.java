package com.inteliment.rest.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inteliment.rest.process.CounterAPIProcessor;
import com.inteliment.rest.util.CounterAPIUtil;

@Service
public class CounterAPIService {
	
	@Autowired
	CounterAPIProcessor processor;
	@Autowired
	CounterAPIUtil util;
	
	public String searchWords(String data){
		Map<String, Integer> dataMap = null;
		String response = "";
		dataMap = processor.loadFileAndSearchData(data);
		if(dataMap != null){
			response = util.convertMapToJsonString(dataMap);
		}
		return response;
	}
	
	public List<Entry<String, Integer>> findTopWords(Integer topCount){
		List<Entry<String, Integer>> topWords = null;
		topWords = processor.loadFileAndFindTopWords(topCount);
		return topWords;
	}
}
