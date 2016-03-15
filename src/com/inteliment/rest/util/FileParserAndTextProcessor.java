package com.inteliment.rest.util;
/**
 * @author Atul
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.stereotype.Component;

@Component
public class FileParserAndTextProcessor {
	
	private static Map<String, Integer> wordMap = null;
	private static List<Entry<String, Integer>> sortedList = null;
	
	public void readTextAndFormData(String fileName) {
		String filePath =Thread.currentThread().getContextClassLoader().getResource(fileName).getFile();
		File file = new File(filePath);
		try (FileInputStream fis = new FileInputStream(file);
				Scanner sc = new Scanner(new InputStreamReader(fis, StandardCharsets.UTF_8));
				) {
			if(wordMap == null){
				wordMap = getWordCount(sc);
			}
			if(sortedList == null){
				sortedList = sortMapData(wordMap);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public Map<String, Integer> getWordCount(Scanner sc){
		Map<String, Integer> wordMap = null;
		StringTokenizer st = null;
		String line = null;
		String key = null;
		if(sc != null){
			wordMap = new HashMap<String, Integer>();
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				st = new StringTokenizer(line, " ");
				while (st.hasMoreTokens()) {
					key = st.nextToken();
					if (wordMap.containsKey(key)) {
						wordMap.put(key, wordMap.get(key) + 1);
					} else {
						wordMap.put(key, 1);
					}
				}
			}

		}
		return wordMap;
	}
	
	public List<Entry<String, Integer>> sortMapData(Map<String, Integer> wordMap){
		Set<Entry<String, Integer>> set = wordMap.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 ) {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        return list;
	}

	public Map<String, Integer> getWordMap() {
		return wordMap;
	}

	public List<Entry<String, Integer>> getSortedList() {
		return sortedList;
	}
	
	
}
