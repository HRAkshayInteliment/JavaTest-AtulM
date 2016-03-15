package com.inteliment.rest.controller;

import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.inteliment.rest.service.CounterAPIService;

@RestController
@RequestMapping("rest")
public class CounterAPIController {
	@Autowired
	CounterAPIService service;
	
	@RequestMapping(value = "/search/{searchText}", method = RequestMethod.POST, headers="Accept=*/*")
	public String searchWords(@PathVariable("searchText") String words){
		String response = "";
		if(words != null && !"".equals(words.trim())){
			response = service.searchWords(words);
		}
		return response;
	}
	
	@RequestMapping(value = "/top/{top}", method = RequestMethod.GET, produces="text/csv")
	public List<Entry<String, Integer>> countTopWords(@PathVariable("top") Integer topCount){
		List<Entry<String, Integer>> response = null;
		if(topCount != null && topCount != 0){
			response = service.findTopWords(topCount);
		}
		return response;
	}
}
