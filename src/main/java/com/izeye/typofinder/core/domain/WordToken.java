package com.izeye.typofinder.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by izeye on 15. 10. 25..
 */
@Data
@AllArgsConstructor
public class WordToken implements Comparable<WordToken> {
	
	private final String word;
	private final String lowerCaseWord;
	private final String context;

	@Override
	public int compareTo(WordToken o) {
		return this.lowerCaseWord.compareTo(o.lowerCaseWord);
	}
	
}
