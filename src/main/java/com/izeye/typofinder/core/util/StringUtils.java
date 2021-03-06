package com.izeye.typofinder.core.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by izeye on 15. 10. 25..
 */
public abstract class StringUtils {
	
	public static List<String> camelCase2Words(String camelCase) {
		if (isUpperCaseOnly(camelCase)) {
			return Collections.singletonList(camelCase);
		}
		
		List<String> words = new ArrayList<>();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < camelCase.length(); i++) {
			char c = camelCase.charAt(i);
			if (Character.isUpperCase(c) && sb.length() > 0) {
				words.add(sb.toString());
				sb = new StringBuilder();
			}
			sb.append(c);
		}
		if (sb.length() > 0) {
			words.add(sb.toString());
		}
		return words;
	}
	
	public static boolean isUpperCaseOnly(String string) {
		for (char c : string.toCharArray()) {
			if (!Character.isUpperCase(c)) {
				return false;
			}
		}
		return true;
	}
	
}
