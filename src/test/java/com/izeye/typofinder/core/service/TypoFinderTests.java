package com.izeye.typofinder.core.service;

import com.izeye.typofinder.core.domain.ValidationReport;
import com.izeye.typofinder.core.domain.WordToken;
import com.izeye.typofinder.core.util.FileUtils;
import com.izeye.typofinder.core.util.StringFormatValidationUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by izeye on 15. 7. 2..
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TypoFinderTests {
	
	@Autowired
	TypoFinder typoFinder;
	
	@Test
	public void testFindTypos() throws IOException {
//		String filename = "src/test/resources/samples/test.txt";
//		String filename = "../spring-boot/spring-boot-docs/src/main/asciidoc/index.adoc";
//		String filename = "../spring-boot/spring-boot-docs/src/main/asciidoc/documentation-overview.adoc";
//		String filename = "../spring-boot/spring-boot-docs/src/main/asciidoc/getting-started.adoc";
//		String filename = "../spring-boot/spring-boot-docs/src/main/asciidoc/using-spring-boot.adoc";
//		String filename = "../spring-boot/spring-boot-docs/src/main/asciidoc/spring-boot-features.adoc";
//		String filename = "../spring-boot/spring-boot-docs/src/main/asciidoc/production-ready-features.adoc";
//		String filename = "../spring-boot/spring-boot-docs/src/main/asciidoc/deployment.adoc";
//		String filename = "../spring-boot/spring-boot-docs/src/main/asciidoc/spring-boot-cli.adoc";
//		String filename = "../spring-boot/spring-boot-docs/src/main/asciidoc/build-tool-plugins.adoc";
//		String filename = "../spring-boot/spring-boot-docs/src/main/asciidoc/howto.adoc";
		String filename = "../spring-boot/spring-boot-docs/src/main/asciidoc/appendix.adoc";
		
		Set<WordToken> typos = this.typoFinder.findTypos(new File(filename));
		System.out.println("\n= Typos =");
		typos.stream().forEach(typo -> System.out.println(typo.getLowerCaseWord()));
		System.out.println("\nTotal typos: " + typos.size());
		
		System.out.println("\n= Typos for analysis =");
		typos.stream().forEach(System.out::println);
	}

	@Test
	public void testFindTyposInDirectory() throws IOException {
//		File directory = new File("../spring-boot/spring-boot-docs/src/main/asciidoc/");
		File directory = new File("../spring-boot/");
//		File directory = new File("../spring-restdocs/");
//		File directory = new File("../tensorflow/tensorflow/g3doc/tutorials/deep_cnn/");
		
		Set<String> fileExtensions = new HashSet<>();
		fileExtensions.add("java");
		fileExtensions.add("adoc");
		fileExtensions.add("vm");
		fileExtensions.add("md");
		
		Set<String> exclusions = new HashSet<>();
		exclusions.add("target");
		exclusions.add("MimeMappings.java");
		exclusions.add("ConfigFileApplicationListenerTests.java");
		exclusions.add("SpringBootMockServletContextTests.java");
		exclusions.add("ResourceServerTokenServicesConfigurationTests.java");
		exclusions.add("SampleClient.java");
		exclusions.add("SampleSecureOAuth2Application.java");
		
		List<File> allFiles = FileUtils.findAllFiles(directory, fileExtensions, exclusions);
		int size = allFiles.size();
		List<ValidationReport> failureReports = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			File file = allFiles.get(i);
			System.out.println("Target file: " + file);
			
			Set<WordToken> typos = this.typoFinder.findTypos(file);
			System.out.println("\n= Typos =");
			typos.stream().forEach(typo -> System.out.println(typo.getLowerCaseWord()));
			System.out.println("\nTotal typos: " + typos.size());

			System.out.println("\n= Typos for analysis =");
			typos.stream().forEach(System.out::println);
			
			assertThat(typos).isEmpty();

			if (file.getName().endsWith(".java")) {
				failureReports.addAll(StringFormatValidationUtils.validate(file));
			}

			System.out.printf("%d of %d file(s) succeeded.%n", i + 1, size);
		}

		if (!failureReports.isEmpty()) {
			System.out.println("----- Failure Summary -----");
			failureReports.stream().forEach(System.out::println);
			fail();
		}
	}
	
	@Test
	public void testFindTyposWithText() {
		// FIXME: How to handle this kind of camelCase?
		String text = "localhostIsIPv6";
		Set<WordToken> typos = this.typoFinder.findTypos(text);
		System.out.println(typos);
	}
	
}
