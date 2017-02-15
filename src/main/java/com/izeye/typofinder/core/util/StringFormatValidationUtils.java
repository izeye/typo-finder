package com.izeye.typofinder.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.apache.commons.lang3.StringUtils;

import com.izeye.typofinder.core.domain.ValidationReport;

/**
 * Utilities for validating on {@link String#format} like matching varargs.
 *
 * @author Johnny Lim
 */
public abstract class StringFormatValidationUtils {

	private StringFormatValidationUtils() {
	}

	public static ValidationReport validate(String expressionString) {
		try {
			Expression expression = JavaParser.parseExpression(expressionString);
			List<Node> childrenNodes = expression.getChildrenNodes();
			Node formatStringNode = childrenNodes.get(2);

			// FIXME: Can't judge with as-is.
			if (formatStringNode instanceof ConditionalExpr ||
					formatStringNode instanceof FieldAccessExpr ||
					formatStringNode instanceof MethodCallExpr ||
					formatStringNode instanceof NameExpr) {
				return ValidationReport.success(expressionString);
			}

			String formatString = formatStringNode.toString();
			int countBeforeCleanUp = StringUtils.countMatches(formatString, "%");
			if (countBeforeCleanUp == 0) {
				String failureMessage = String.format(
						"%s: wrong usage: no format specifier", expressionString);
				return ValidationReport.failure(expressionString, failureMessage);
			}
			formatString = formatString.replace("%n", "");
			int formatSpecifierCount = StringUtils.countMatches(formatString, "%");
			List<Node> parameters = childrenNodes.subList(3, childrenNodes.size() - 1);
			int parameterCount = parameters.size();
			boolean matched = formatSpecifierCount == parameterCount;
			if (!matched) {
				String failureMessage = String.format(
						"%s: wrong usage: expected parameter count was %d but %d",
						expressionString, formatSpecifierCount, parameterCount);
				return ValidationReport.failure(expressionString, failureMessage);
			}
			return ValidationReport.success(expressionString);
		} catch (ParseException ex) {
			throw new RuntimeException(ex);
		}
	}

	public static List<ValidationReport> validate(File file) {
		try {
			CompilationUnit compilationUnit = JavaParser.parse(new FileInputStream(file));
			MethodCallExpressionVisitor visitor = new MethodCallExpressionVisitor();
			visitor.visit(compilationUnit, null);
			return visitor.getFailureReports();
		} catch (ParseException ex) {
			throw new RuntimeException(ex);
		} catch (FileNotFoundException ex) {
			throw new RuntimeException(ex);
		}
	}

	private static class MethodCallExpressionVisitor extends VoidVisitorAdapter<Object> {

		private List<ValidationReport> failureReports = new ArrayList<>();

		@Override
		public void visit(final MethodCallExpr n, final Object arg) {
			super.visit(n, arg);

			String expressionString = n.toString();
			if (expressionString.startsWith("String.format(")) {
				ValidationReport report = validate(expressionString);
				if (!report.isValid()) {
					this.failureReports.add(report);
				}
			}
		}

		public List<ValidationReport> getFailureReports() {
			return this.failureReports;
		}

	}

}
