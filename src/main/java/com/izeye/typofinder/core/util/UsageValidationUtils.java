package com.izeye.typofinder.core.util;

import java.io.File;
import java.util.regex.Pattern;

import com.izeye.typofinder.core.domain.ValidationReport;

/**
 * Utilities for usage validation.
 *
 * @author Johnny Lim
 */
public final class UsageValidationUtils {

	private static final String THROWN_START = "ExpectedException thrown";
	private static final String THROWN = "thrown";

	private static final String REGEX_WRONG_STRING_UTILS = "import (?!org.springframework.util).+StringUtils;";
	private static final Pattern PATTERN_WRONG_STRING_UTILS = Pattern.compile(REGEX_WRONG_STRING_UTILS);

	private UsageValidationUtils() {
	}

	public static ValidationReport validateThrown(File file) {
		boolean found = false;
		boolean used = false;
		for (String line : FileUtils.readLines(file)) {
			if (line.contains(THROWN_START)) {
				found = true;
				continue;
			}

			if (found) {
				if (line.contains(THROWN)) {
					used = true;
				}
			}
		}
		if (found && !used) {
			return ValidationReport.failure(file, "'thrown' has been found but hasn't been used.");
		}
		return ValidationReport.success(file);
	}

	public static ValidationReport validateStringUtils(File file) {
		for (String line : FileUtils.readLines(file)) {
			if (isWrongStringUtilsImport(line)) {
				return ValidationReport.failure(file, "A wrong 'StringUtils' has been imported: " + line);
			}
		}
		return ValidationReport.success(file);
	}

	static boolean isWrongStringUtilsImport(String line) {
		return PATTERN_WRONG_STRING_UTILS.matcher(line).matches();
	}

}
