package com.izeye.typofinder.core.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by izeye on 15. 10. 23..
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DictionaryTests {
	
	@Resource(name = "englishWordsDictionary")
	private Dictionary dictionary;
	
	@Test
	public void testContains() {
		assertThat(this.dictionary.contains("this")).isTrue();
		assertThat(this.dictionary.contains("izeye")).isFalse();
	}
	
}
