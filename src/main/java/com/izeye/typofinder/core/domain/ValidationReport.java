package com.izeye.typofinder.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Validation report.
 *
 * @author Johnny Lim
 */
@Data
@AllArgsConstructor
public class ValidationReport {

	private final Object target;
	private final String failureMessage;

	public boolean isValid() {
		return this.failureMessage == null;
	}

	public static ValidationReport success(Object target) {
		return new ValidationReport(target, null);
	}

	public static ValidationReport failure(Object target, String failure) {
		return new ValidationReport(target, failure);
	}

}
