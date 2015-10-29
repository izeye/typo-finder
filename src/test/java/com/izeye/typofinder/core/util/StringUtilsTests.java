package com.izeye.typofinder.core.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	
	@Test
	public void testIsUpperCaseOnly() {
		assertFalse(StringUtils.isUpperCaseOnly("a"));
		assertFalse(StringUtils.isUpperCaseOnly("aa"));
		assertFalse(StringUtils.isUpperCaseOnly("Aa"));
		assertFalse(StringUtils.isUpperCaseOnly("aA"));
		assertFalse(StringUtils.isUpperCaseOnly("aAa"));
		
		assertTrue(StringUtils.isUpperCaseOnly("A"));
		assertTrue(StringUtils.isUpperCaseOnly("AA"));
	}
	
}
