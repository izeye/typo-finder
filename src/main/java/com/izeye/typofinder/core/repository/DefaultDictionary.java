package com.izeye.typofinder.core.repository;

import org.apache.commons.io.IOUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by izeye on 15. 10. 23..
 */
public class DefaultDictionary implements Dictionary {
	
	private final Set<String> words = new HashSet<>();
	
	public DefaultDictionary(String... resourceLocations) {
		for (String resourceLocation : resourceLocations) {
			try {
				File file = ResourceUtils.getFile(resourceLocation);
				this.words.addAll(IOUtils.readLines(new FileReader(file)));
			} catch (FileNotFoundException ex) {
				throw new RuntimeException(ex);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
	}
	
	@Override
	public boolean contains(String word) {
		return words.contains(word);
	}
	
}
