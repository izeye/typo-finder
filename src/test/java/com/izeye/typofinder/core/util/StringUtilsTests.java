package com.izeye.typofinder.core.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by izeye on 15. 10. 25..
 */
public class StringUtilsTests {
	
	@Test
	public void testCamelCase2Words() {
		List<String> expected = Arrays.asList("This", "Will", "Actually", "Run");
		
		String camelCase = "ThisWillActuallyRun";
		assertThat(StringUtils.camelCase2Words(camelCase), is(expected));
		
		expected = Arrays.asList("this", "Will", "Actually", "Run");

		camelCase = "thisWillActuallyRun";
		assertThat(StringUtils.camelCase2Words(camelCase), is(expected));
	}
	
}
