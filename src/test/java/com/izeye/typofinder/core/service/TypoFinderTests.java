package com.izeye.typofinder.core.service;

import com.izeye.typofinder.Application;
import com.izeye.typofinder.core.domain.WordToken;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

/**
 * Created by izeye on 15. 7. 2..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class TypoFinderTests {
	
	@Autowired
	TypoFinder typoFinder;
	
	@Test
	public void testFindTypos() throws IOException {
//		String filename = "src/test/resources/samples/test.txt";
//		String filename = "/Users/izeye/IdeaProjects/spring-boot/spring-boot-docs/src/main/asciidoc/documentation-overview.adoc";
//		String filename = "/Users/izeye/IdeaProjects/spring-boot/spring-boot-docs/src/main/asciidoc/getting-started.adoc";
//		String filename = "/Users/izeye/IdeaProjects/spring-boot/spring-boot-docs/src/main/asciidoc/using-spring-boot.adoc";
//		String filename = "/Users/izeye/IdeaProjects/spring-boot/spring-boot-docs/src/main/asciidoc/spring-boot-features.adoc";
//		String filename = "/Users/izeye/IdeaProjects/spring-boot/spring-boot-docs/src/main/asciidoc/production-ready-features.adoc";
		String filename = "/Users/izeye/IdeaProjects/spring-boot/spring-boot-docs/src/main/asciidoc/deployment.adoc";
		
		String text = IOUtils.toString(new FileReader(filename));
		Set<WordToken> typos = this.typoFinder.findTypos(text);
		System.out.println("\n= Typos =");
		typos.stream().forEach(typo -> System.out.println(typo.getLowerCaseWord()));
		System.out.println("\nTotal typos: " + typos.size());
		
		System.out.println("\n= Typos for analysis =");
		typos.stream().forEach(System.out::println);
	}
	
}
