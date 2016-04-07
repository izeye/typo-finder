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
	private static final String RESOURCE_LOCATION_AMAZON_TERMS = "classpath:dictionary/amazon_terms.txt";
	private static final String RESOURCE_LOCATION_ANT_TERMS = "classpath:dictionary/ant_terms.txt";
	private static final String RESOURCE_LOCATION_ASCIIDOCTOR_TERMS = "classpath:dictionary/asciidoctor_terms.txt";
	private static final String RESOURCE_LOCATION_BASH_TERMS = "classpath:dictionary/bash_terms.txt";
	private static final String RESOURCE_LOCATION_DOMAIN_TERMS = "classpath:dictionary/domain_terms.txt";
	private static final String RESOURCE_LOCATION_GIT_TERMS = "classpath:dictionary/git_terms.txt";
	private static final String RESOURCE_LOCATION_GRADLE_TERMS = "classpath:dictionary/gradle_terms.txt";
	private static final String RESOURCE_LOCATION_GROOVY_TERMS = "classpath:dictionary/groovy_terms.txt";
	private static final String RESOURCE_LOCATION_HEROKU_TERMS = "classpath:dictionary/heroku_terms.txt";
	private static final String RESOURCE_LOCATION_HIBERNATE_TERMS = "classpath:dictionary/hibernate_terms.txt";
	private static final String RESOURCE_LOCATION_HTML_TERMS = "classpath:dictionary/html_terms.txt";
	private static final String RESOURCE_LOCATION_HTTP_TERMS = "classpath:dictionary/http_terms.txt";
	private static final String RESOURCE_LOCATION_JAVA_TERMS = "classpath:dictionary/java_terms.txt";
	private static final String RESOURCE_LOCATION_JAVA_PACKAGES = "classpath:dictionary/java_packages.txt";
	private static final String RESOURCE_LOCATION_JWT_TERMS = "classpath:dictionary/jwt_terms.txt";
	private static final String RESOURCE_LOCATION_LINUX_TERMS = "classpath:dictionary/linux_terms.txt";
	private static final String RESOURCE_LOCATION_MAVEN_TERMS = "classpath:dictionary/maven_terms.txt";
	private static final String RESOURCE_LOCATION_REDIS_TERMS = "classpath:dictionary/redis_terms.txt";
	private static final String RESOURCE_LOCATION_SPRING_TERMS = "classpath:dictionary/spring_terms.txt";
	private static final String RESOURCE_LOCATION_SPRING_BOOT_TERMS = "classpath:dictionary/spring_boot_terms.txt";
	private static final String RESOURCE_LOCATION_TOMCAT_TERMS = "classpath:dictionary/tomcat_terms.txt";
	private static final String RESOURCE_LOCATION_UNDERTOW_TERMS = "classpath:dictionary/undertow_terms.txt";

	private static final String RESOURCE_LOCATION_ORGANIZATION_TERMS = "classpath:dictionary/organization_terms.txt";
	private static final String RESOURCE_LOCATION_PERSON_NAMES = "classpath:dictionary/person_names.txt";
	private static final String RESOURCE_LOCATION_PRODUCT_NAMES = "classpath:dictionary/product_names.txt";
	private static final String RESOURCE_LOCATION_SPRING_BOOT_TEST_DATA = "classpath:dictionary/spring_boot_test_data.txt";
	
	@Bean
	public Dictionary englishWordsDictionary() {
		return new DefaultDictionary(RESOURCE_LOCATION_ENGLISH_WORDS);
	}
	
	@Bean
	public Dictionary computerTermsDictionary() {
		return new DefaultDictionary(RESOURCE_LOCATION_COMPUTER_TERMS,
				RESOURCE_LOCATION_AMAZON_TERMS,
				RESOURCE_LOCATION_ANT_TERMS,
				RESOURCE_LOCATION_ASCIIDOCTOR_TERMS,
				RESOURCE_LOCATION_BASH_TERMS,
				RESOURCE_LOCATION_DOMAIN_TERMS,
				RESOURCE_LOCATION_GIT_TERMS,
				RESOURCE_LOCATION_GRADLE_TERMS,
				RESOURCE_LOCATION_GROOVY_TERMS,
				RESOURCE_LOCATION_HEROKU_TERMS,
				RESOURCE_LOCATION_HIBERNATE_TERMS,
				RESOURCE_LOCATION_HTML_TERMS,
				RESOURCE_LOCATION_HTTP_TERMS,
				RESOURCE_LOCATION_JAVA_TERMS,
				RESOURCE_LOCATION_JAVA_PACKAGES,
				RESOURCE_LOCATION_JWT_TERMS,
				RESOURCE_LOCATION_LINUX_TERMS,
				RESOURCE_LOCATION_MAVEN_TERMS,
				RESOURCE_LOCATION_REDIS_TERMS,
				RESOURCE_LOCATION_SPRING_TERMS,
				RESOURCE_LOCATION_SPRING_BOOT_TERMS,
				RESOURCE_LOCATION_TOMCAT_TERMS,
				RESOURCE_LOCATION_UNDERTOW_TERMS);
	}
	
	@Bean
	 public Dictionary miscDictionary() {
		return new DefaultDictionary(RESOURCE_LOCATION_ORGANIZATION_TERMS,
				RESOURCE_LOCATION_PERSON_NAMES,
				RESOURCE_LOCATION_PRODUCT_NAMES,
				RESOURCE_LOCATION_SPRING_BOOT_TEST_DATA);
	}
	
}
