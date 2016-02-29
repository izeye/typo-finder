package com.izeye.typofinder.core.repository;

import com.izeye.typofinder.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by izeye on 15. 10. 23..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class DictionaryTests {
	
	@Resource(name = "englishWordsDictionary")
	Dictionary dictionary;
	
	@Test
	public void testContains() {
		assertTrue(dictionary.contains("this"));
		assertFalse(dictionary.contains("izeye"));
	}
	
}
