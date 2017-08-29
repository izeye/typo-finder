package com.izeye.typofinder.core.util;

import java.io.File;

import com.izeye.typofinder.core.domain.ValidationReport;

/**
 * Utilities for usage validation.
 *
 * @author Johnny Lim
 */
public final class UsageValidationUtils {

	private static final String THROWN_START = "ExpectedException thrown";
	private static final String THROWN = "thrown";

	private UsageValidationUtils() {
	}

	public static ValidationReport validateThrown(File file) {
		boolean found = false;
		boolean used = false;
		for (String string : FileUtils.readLines(file)) {
			if (string.contains(THROWN_START)) {
				found = true;
				continue;
			}

			if (found) {
				if (string.contains(THROWN)) {
					used = true;
				}
			}
		}
		if (found && !used) {
			return ValidationReport.failure(file, "'thrown' has been found but hasn't been used.");
		}
		return ValidationReport.success(file);
	}

}
