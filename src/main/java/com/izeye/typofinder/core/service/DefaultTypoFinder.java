package com.izeye.typofinder.core.service;

import com.izeye.typofinder.core.domain.TypoFinderProperties;
import com.izeye.typofinder.core.domain.WordToken;
import com.izeye.typofinder.core.repository.Dictionary;
import com.izeye.typofinder.core.util.StringUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by izeye on 15. 7. 2..
 */
@Service
@EnableConfigurationProperties(TypoFinderProperties.class)
public class DefaultTypoFinder implements TypoFinder {
	
	@Autowired
	private TypoFinderProperties properties;
	
	@Resource
	private Dictionary englishWordsDictionary;

	@Resource
	private Dictionary computerTermsDictionary;
	
	@Resource
	private Dictionary miscDictionary;
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public Set<WordToken> findTypos(String text) {
		Map<String, WordToken> typos = new HashMap<>();
		
		WordTokenizer wordTokenizer = new DefaultWordTokenizer(text);
		WordToken token;
		while ((token = wordTokenizer.next()) != null) {
			String lowerCaseWord = token.getLowerCaseWord();
			if (!containsInAnyDictionary(lowerCaseWord)) {
				if (properties.isAllowCamelCase()) {
					List<String> words = StringUtils.camelCase2Words(token.getWord());
					boolean satisfied = true;
					for (String word : words) {
						if (word.length() == 1 && properties.isIgnoreSingleCharacterWords()) {
							log.trace("A single character word in camelCase has been ignored: {}", word);
							continue;
						}
						if (!containsInAnyDictionary(word.toLowerCase())) {
							satisfied = false;
							break;
						}
					}
					if (satisfied) {
						continue;
					}
				}
				typos.put(lowerCaseWord, token);
			}
		}
		
		if (properties.isIgnoreSingleCharacterWords()) {
			Set<String> ignored = new HashSet<>();
			for (Map.Entry<String, WordToken> typo : typos.entrySet()) {
				String key = typo.getKey();
				if (key.length() == 1) {
					log.trace("A single character word has been ignored: {}", typo);
					ignored.add(key);
				}
			}
			ignored.forEach(key -> typos.remove(key));
		}
		
		
		String[] ignoredWordFiles = properties.getIgnoredWordFiles();
		for (String ignoredWordFile : ignoredWordFiles) {
			try {
				List<String> ignoredWords = IOUtils.readLines(new ClassPathResource(ignoredWordFile).getInputStream());
				ignoredWords.forEach(key -> typos.remove(key));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
		return new TreeSet<>(typos.values());
	}

	@Override
	public Set<WordToken> findTypos(File file) {
		try {
			String text = IOUtils.toString(new FileReader(file));
			return findTypos(text);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	private boolean containsInAnyDictionary(String word) {
		return englishWordsDictionary.contains(word)
				|| computerTermsDictionary.contains(word)
				|| miscDictionary.contains(word);
	}
	
}
