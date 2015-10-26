package com.izeye.typofinder.core.config;

import com.izeye.typofinder.core.repository.DefaultDictionary;
import com.izeye.typofinder.core.repository.Dictionary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by izeye on 15. 10. 23..
 */
@Configuration
public class DictionaryConfig {

	private static final String RESOURCE_LOCATION_ENGLISH_WORDS = "classpath:dictionary/english_words.txt";
	
	private static final String RESOURCE_LOCATION_COMPUTER_TERMS = "classpath:dictionary/computer_terms.txt";
	private static final String RESOURCE_LOCATION_MAVEN_TERMS = "classpath:dictionary/maven_terms.txt";
	private static final String RESOURCE_LOCATION_GRADLE_TERMS = "classpath:dictionary/gradle_terms.txt";
	private static final String RESOURCE_LOCATION_ANT_TERMS = "classpath:dictionary/ant_terms.txt";
	private static final String RESOURCE_LOCATION_ASCIIDOCTOR_TERMS = "classpath:dictionary/asciidoctor_terms.txt";
	private static final String RESOURCE_LOCATION_JAVA_TERMS = "classpath:dictionary/java_terms.txt";
	private static final String RESOURCE_LOCATION_SPRING_TERMS = "classpath:dictionary/spring_terms.txt";
	private static final String RESOURCE_LOCATION_SPRING_BOOT_TERMS = "classpath:dictionary/spring_boot_terms.txt";
	
	@Bean
	public Dictionary englishWordsDictionary() {
		return new DefaultDictionary(RESOURCE_LOCATION_ENGLISH_WORDS);
	}
	
	@Bean
	public Dictionary computerTermsDictionary() {
		return new DefaultDictionary(RESOURCE_LOCATION_COMPUTER_TERMS,
				RESOURCE_LOCATION_MAVEN_TERMS,
				RESOURCE_LOCATION_GRADLE_TERMS,
				RESOURCE_LOCATION_ANT_TERMS,
				RESOURCE_LOCATION_ASCIIDOCTOR_TERMS,
				RESOURCE_LOCATION_JAVA_TERMS,
				RESOURCE_LOCATION_SPRING_TERMS,
				RESOURCE_LOCATION_SPRING_BOOT_TERMS);
	}
	
}
