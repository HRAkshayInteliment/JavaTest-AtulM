package com.inteliment.rest.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inteliment.rest.util.CounterAPIConstant;
import com.inteliment.rest.util.CounterAPIUtil;

@Component
public class CounterAPIHelper {
	
	@Autowired
	CounterAPIUtil util;
	
	public Map<String, Integer> searchRequestedData(Map<String, Integer> dataMap, String input){
		Map<String, Integer> result = new HashMap<String, Integer>();
		String [] splitInput = util.splitString(input, ",");
		if(splitInput != null && splitInput.length > 0){
			for(String key : splitInput ){
				result.put(key.trim(), dataMap.get(key.trim()) == null ? 0 : dataMap.get(key.trim()));
			}
		}
		return result;
	}
	
	public List<Entry<String, Integer>> findTopWords(List<Entry<String, Integer>> dataList, Integer topWords){
		List<Entry<String, Integer>> result = new ArrayList<Entry<String, Integer>>();
		result = dataList.subList(CounterAPIConstant.INITAL_INDEX, topWords);
		return result;
	}
}
