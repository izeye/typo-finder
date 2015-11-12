package com.izeye.typofinder.core.util;

import org.junit.Test;

import java.io.File;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by izeye on 15. 11. 12..
 */
public class FileUtilsTests {
	
	@Test
	public void testExtractFileExtension() {
		String filename = "/Users/izeye/IdeaProjects/spring-boot/spring-boot-docs/src/main/asciidoc/index.adoc";
		String extractedFileExtension = FileUtils.extractFileExtension(new File(filename));
		assertThat(extractedFileExtension, is("adoc"));
	}
	
	@Test
	public void testFindAllFiles() {
		File directory = new File("/Users/izeye/IdeaProjects/spring-boot/spring-boot-docs/src/main/asciidoc/");
		List<File> allFiles = FileUtils.findAllFiles(directory, Collections.singleton("adoc"), false);
		allFiles.forEach(System.out::println);
	}

	@Test
	public void testFindAllFilesIncludingSubdirectories() {
		File directory = new File("/Users/izeye/IdeaProjects/spring-boot/");
		List<File> allFiles = FileUtils.findAllFiles(directory, Collections.singleton("java"));
		allFiles.forEach(System.out::println);
		System.out.println("Total count: " + allFiles.size());
	}
	
}
