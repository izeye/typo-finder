package com.izeye.typofinder.core.service;

import com.izeye.typofinder.core.domain.WordToken;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by izeye on 15. 10. 23..
 */
public class WordTokenizerTests {
	
	@Test
	public void test() throws IOException {
//		String filename = "src/test/resources/samples/test.txt";
		String filename = "../spring-boot/spring-boot-docs/src/main/asciidoc/getting-started.adoc";
		
		String text = IOUtils.toString(new FileReader(filename));
		WordTokenizer wordTokenizer = new DefaultWordTokenizer(text);
		WordToken token;
		while ((token = wordTokenizer.next()) != null) {
			System.out.println(token);
		}
	}
	
}
