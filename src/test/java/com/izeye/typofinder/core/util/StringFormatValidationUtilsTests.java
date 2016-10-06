package com.izeye.typofinder.core.util;

import java.io.File;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link StringFormatValidationUtils}.
 *
 * @author Johnny Lim
 */
public class StringFormatValidationUtilsTests {

	@Test
	public void testValidateExpression() {
		String expression = "String.format(\"No format specifier\")";
		assertThat(StringFormatValidationUtils.validate(expression).isValid()).isFalse();

		expression = "String.format(\"No format specifier but one parameter.\", 1)";
		assertThat(StringFormatValidationUtils.validate(expression).isValid()).isFalse();

		expression = "String.format(\"One format specifier but no parameter: %d\")";
		assertThat(StringFormatValidationUtils.validate(expression).isValid()).isFalse();

		expression = "String.format(\"One format specifier but two parameters: %d\", 1, \"Hello, world!\")";
		assertThat(StringFormatValidationUtils.validate(expression).isValid()).isFalse();

		expression = "String.format(\"One parameter: %d\", 1);";
		assertThat(StringFormatValidationUtils.validate(expression).isValid()).isTrue();

		expression = "String.format(\"One parameter: %s\", \"Hello, world!\");";
		assertThat(StringFormatValidationUtils.validate(expression).isValid()).isTrue();

		expression = "String.format(\"Two parameters: %d, %s\", 1, \"Hello, world!\");";
		assertThat(StringFormatValidationUtils.validate(expression).isValid()).isTrue();

		// FIXME: Can't judge with as-is.
		expression = "String.format(formatString, 1);";
		assertThat(StringFormatValidationUtils.validate(expression).isValid()).isTrue();

		expression = "String.format(this.formatString, 1);";
		assertThat(StringFormatValidationUtils.validate(expression).isValid()).isTrue();

		expression = "String.format(getFormatString(), 1);";
		assertThat(StringFormatValidationUtils.validate(expression).isValid()).isTrue();

		expression = "String.format(condition ? \"True: %d\" : \"False: %d\", 1);";
		assertThat(StringFormatValidationUtils.validate(expression).isValid()).isTrue();
	}

	@Test
	public void testValidate() {
		String directory = "src/test/java/com/izeye/typofinder/core/util/";

		String filename = directory + "StringFormatUsageExample.java";
		assertThat(StringFormatValidationUtils.validate(new File(filename))).isEmpty();

		filename = directory + "StringFormatWrongUsageExample.java";
		assertThat(StringFormatValidationUtils.validate(new File(filename))).hasSize(4);
	}

}
