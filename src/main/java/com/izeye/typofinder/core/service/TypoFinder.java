package com.izeye.typofinder.core.service;

import com.izeye.typofinder.core.domain.WordToken;

import java.util.Set;

/**
 * Created by izeye on 15. 7. 2..
 */
public interface TypoFinder {
	
	Set<WordToken> findTypos(String text);
	
}
