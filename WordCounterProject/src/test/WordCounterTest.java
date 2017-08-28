package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

import com.WordCounter;

public class WordCounterTest {

	@Test
	public void test() {
		//fail("Not yet implemented");
		try {
			Map<String,String> wordCounterMap = WordCounter.getWordLineOccurence("resource/test.txt");
			assertEquals("test succeeded", "{1:1,2:1 - 2}", wordCounterMap.get("is"));
			assertEquals("null value for punctuation characters succeded",null,wordCounterMap.get("."));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
