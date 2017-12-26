package com.izeye.typofinder.core.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link UsageValidationUtils}.
 *
 * @author Johnny Lim
 */
public class UsageValidationUtilsTests {

	@Test
	public void testIsWrongStringUtilsImport() {
		assertThat(
				UsageValidationUtils.isWrongStringUtilsImport(
						"import org.flywaydb.core.internal.util.StringUtils;")).isTrue();
		assertThat(
				UsageValidationUtils.isWrongStringUtilsImport(
						"import org.springframework.util.StringUtils;")).isFalse();
	}

}
