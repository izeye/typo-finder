package com.izeye.typofinder.core.service;

import com.izeye.typofinder.core.domain.WordToken;

/**
 * Created by izeye on 15. 10. 23..
 */
public class DefaultWordTokenizer implements WordTokenizer {
	
	private static final String[] CONTRACTED_FORMS = {
		"'ll", // For contracted form with `will` like `we'll`
		"'re", // For contracted form with `are` like `you're`
		"'t", // For contracted form with `not` like `don't`
		"'ve" // For contracted form with `have` like `you've`
	};
	
	private static final int CONTEXT_OFFSET = 10;
	
	private final String text;
	
	private int index = 0;
	
	public DefaultWordTokenizer(String text) {
		this.text = text;
	}
	
	@Override
	public WordToken next() {
		StringBuilder sb = new StringBuilder();
		for (; index < text.length(); index++) {
			char c = text.charAt(index);
			if (Character.isAlphabetic(c)) {
				sb.append(c);
			}
			else {
				if (sb.length() > 0 && c == '\'') {
					boolean contractedFormFound = false;
					for (String contractedForm : CONTRACTED_FORMS) {
						if (isContractedForm(contractedForm)) {
							sb.append(contractedForm);
							index += contractedForm.length() - 1;
							contractedFormFound = true;
							break;
						}
					}
					if (contractedFormFound) {
						continue;
					}
				}
				
				if (sb.length() > 0) {
					return createWordToken(sb.toString());
				}
			}
		}
		if (sb.length() > 0) {
			return createWordToken(sb.toString());
		}
		return null;
	}

	private WordToken createWordToken(String word) {
		int beginIndex = index - word.length() - CONTEXT_OFFSET;
		int endIndex = index + CONTEXT_OFFSET;
		String context = text.substring(
				beginIndex < 0 ? 0 : beginIndex,
				endIndex > text.length() ? text.length() : endIndex);
		return new WordToken(word, word.toLowerCase(), context);
	}
	
	private boolean isContractedForm(String expected) {
		int expectedLength = expected.length();
		if (index + expectedLength >= text.length()) {
			return false;
		}
		
		if (Character.isAlphabetic(text.charAt(index + expectedLength))) {
			return false;
		}
		
		for (int i = 0; i < expectedLength; i++) {
			if (text.charAt(index + i) != expected.charAt(i)) {
				return false;
			}
		}
		return true;
	}

}
