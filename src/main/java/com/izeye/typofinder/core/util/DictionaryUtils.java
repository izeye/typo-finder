package com.izeye.typofinder.core.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by izeye on 15. 10. 26..
 */
public abstract class DictionaryUtils {
	
	public static Set<String> mergeDictionaryFilesToWordSet(Resource... dictionaryResources) {
		Set<String> merged = new TreeSet<>();
		for (Resource dictionaryResource : dictionaryResources) {
			try {
				List<String> words = IOUtils.readLines(dictionaryResource.getInputStream());
				merged.addAll(words);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
		return merged;
	}
	
}
