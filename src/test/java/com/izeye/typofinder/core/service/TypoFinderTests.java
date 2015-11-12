package com.izeye.typofinder.core.service;

import com.izeye.typofinder.Application;
import com.izeye.typofinder.core.domain.WordToken;
import com.izeye.typofinder.core.util.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

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
//		String filename = "/Users/izeye/IdeaProjects/spring-boot/spring-boot-docs/src/main/asciidoc/index.adoc";
//		String filename = "/Users/izeye/IdeaProjects/spring-boot/spring-boot-docs/src/main/asciidoc/documentation-overview.adoc";
//		String filename = "/Users/izeye/IdeaProjects/spring-boot/spring-boot-docs/src/main/asciidoc/getting-started.adoc";
//		String filename = "/Users/izeye/IdeaProjects/spring-boot/spring-boot-docs/src/main/asciidoc/using-spring-boot.adoc";
//		String filename = "/Users/izeye/IdeaProjects/spring-boot/spring-boot-docs/src/main/asciidoc/spring-boot-features.adoc";
//		String filename = "/Users/izeye/IdeaProjects/spring-boot/spring-boot-docs/src/main/asciidoc/production-ready-features.adoc";
//		String filename = "/Users/izeye/IdeaProjects/spring-boot/spring-boot-docs/src/main/asciidoc/deployment.adoc";
//		String filename = "/Users/izeye/IdeaProjects/spring-boot/spring-boot-docs/src/main/asciidoc/spring-boot-cli.adoc";
//		String filename = "/Users/izeye/IdeaProjects/spring-boot/spring-boot-docs/src/main/asciidoc/build-tool-plugins.adoc";
//		String filename = "/Users/izeye/IdeaProjects/spring-boot/spring-boot-docs/src/main/asciidoc/howto.adoc";
		String filename = "/Users/izeye/IdeaProjects/spring-boot/spring-boot-docs/src/main/asciidoc/appendix.adoc";
		
		Set<WordToken> typos = this.typoFinder.findTypos(new File(filename));
		System.out.println("\n= Typos =");
		typos.stream().forEach(typo -> System.out.println(typo.getLowerCaseWord()));
		System.out.println("\nTotal typos: " + typos.size());
		
		System.out.println("\n= Typos for analysis =");
		typos.stream().forEach(System.out::println);
	}

	@Test
	public void testFindTyposInDirectory() throws IOException {
//		File directory = new File("/Users/izeye/IdeaProjects/spring-boot/spring-boot-docs/src/main/asciidoc/");
		File directory = new File("/Users/izeye/IdeaProjects/spring-boot/");
		
		Set<String> fileExtensions = new HashSet<>();
		fileExtensions.add("java");
		fileExtensions.add("adoc");

		List<File> allFiles = FileUtils.findAllFiles(directory, fileExtensions);
		int size = allFiles.size();
		for (int i = 0; i < size; i++) {
			File file = allFiles.get(i);
			System.out.println("Target file: " + file);
			
			Set<WordToken> typos = this.typoFinder.findTypos(file);
			System.out.println("\n= Typos =");
			typos.stream().forEach(typo -> System.out.println(typo.getLowerCaseWord()));
			System.out.println("\nTotal typos: " + typos.size());

			System.out.println("\n= Typos for analysis =");
			typos.stream().forEach(System.out::println);
			
			assertTrue(typos.isEmpty());

			System.out.printf("%d of %d file(s) succeeded.%n", i + 1, size);
		}
	}
	
}
