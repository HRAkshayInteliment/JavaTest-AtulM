package com.inteliment.rest.process;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inteliment.rest.helper.CounterAPIHelper;
import com.inteliment.rest.util.FileParserAndTextProcessor;

@Component
public class CounterAPIProcessor {

	@Autowired
	FileParserAndTextProcessor fileProc;
	@Autowired
	CounterAPIHelper helper;

	public Map<String, Integer> loadFileAndSearchData(String data){
		fileProc.readTextAndFormData("test.txt");
		Map<String, Integer> dataMap = fileProc.getWordMap();
		if(dataMap != null){
			dataMap = helper.searchRequestedData(dataMap, data);
		}
		return dataMap;
	}
	
	public List<Entry<String, Integer>> loadFileAndFindTopWords(Integer topWords){
		fileProc.readTextAndFormData("test.txt");
		List<Entry<String, Integer>> dataList = fileProc.getSortedList();
		if(dataList != null){
			dataList = helper.findTopWords(dataList, topWords);
		}
		return dataList;
	}
}
