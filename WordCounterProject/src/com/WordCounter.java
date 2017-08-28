package com;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class WordCounter {
	public static final String COLON=":";
	public static final String TOTAL_COUNT_SEPERATOR=" - ";
	
	public static void main(String[] args){
		try {
			Map<String,String> wordListMap = getWordLineOccurence("resource/test.txt");
            for(String key:wordListMap.keySet()){
            	StringBuffer sb = new StringBuffer(key).append("=").append(wordListMap.get(key));
            	System.out.println(sb.toString());
            }
	    } catch (Exception e) {
            e.printStackTrace();
        }

	}
	
	/*
	 * Reads the file from the location specified and returns the required output
	 * 
	 */
	public static Map<String,String> getWordLineOccurence(String fileLocation) throws IOException{
		 File f = new File(fileLocation);
         BufferedReader b = new BufferedReader(new FileReader(f));
		 String readLine;
		 Map<String,String> wordListMap = new TreeMap<String,String>();
         int lineNumber = 0;
         while ((readLine = b.readLine()) != null) {
             lineNumber++;
             if(readLine!=null && readLine.trim()!=""){
            	readLine = readLine.replaceAll("[^a-zA-Z\\s]", "").replaceAll("\\s+", " ");
             	String[] wordsArray = readLine.split(" ");
             	Map<String,Integer> wordCountMap = new HashMap<String,Integer>();
             	int count=0;
             	for(int i=0;i<wordsArray.length;i++){
             		if(wordCountMap.get(wordsArray[i])!=null){
             			count=1+wordCountMap.get(wordsArray[i]);
             		}else{
             			count = 1;
             		}
             		wordCountMap.put(wordsArray[i], count);
             		count =0;
             	}
             	for(String s:wordCountMap.keySet()){
            		wordListMap.put(s,createEntry(lineNumber, wordCountMap.get(s),wordListMap.get(s)));
            	}
             }
         }
         return wordListMap;
	}
	
	/*
	 * Method outputs the count and the occurence of line in the required format
	 */
	public static String createEntry(int lineNumber, int count, String prevEntry){
		StringBuffer entryBuffer = new StringBuffer();
		
		if(prevEntry ==null){
			entryBuffer.append("{").append(lineNumber).append(COLON).append(count).append(TOTAL_COUNT_SEPERATOR).append(count).append("}");
		}else{
			int countSeperatorIndex = prevEntry.indexOf(TOTAL_COUNT_SEPERATOR);
			String prevStringModified = prevEntry.substring(0,countSeperatorIndex);
			entryBuffer.append(prevStringModified);
			String prevCountStr = prevEntry.substring(countSeperatorIndex+2,prevEntry.indexOf("}"));
			int prevCount = Integer.parseInt(prevEntry.substring(countSeperatorIndex+2,prevEntry.indexOf("}")).trim());
			int totalCount = prevCount+count;
			entryBuffer.append(",").append(lineNumber).append(COLON).append(count).append(TOTAL_COUNT_SEPERATOR).append(totalCount).append("}");
			
		}
		return entryBuffer.toString();
	}
}
