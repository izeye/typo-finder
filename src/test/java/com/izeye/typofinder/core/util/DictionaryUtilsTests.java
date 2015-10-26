package com.izeye.typofinder.core.util;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Set;

/**
 * Created by izeye on 15. 10. 26..
 */
public class DictionaryUtilsTests {
	
	@Test
	public void testMergeDictionaryFilesToWordSet() {
//		String path1 = "dictionary/gradle_terms.txt";
//		String path2 = "dictionary/maven_terms.txt";
		String path1 = "dictionary/english_words.txt";
		String path2 = "dictionary/english_words_temp.txt";
		
		Resource resource1 = new ClassPathResource(path1);
		Resource resource2 = new ClassPathResource(path2);

		System.out.println("= Merged words =");
		Set<String> words = DictionaryUtils.mergeDictionaryFilesToWordSet(resource1, resource2);
		words.forEach(System.out::println);
	}
	
}
