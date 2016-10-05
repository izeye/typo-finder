package com.izeye.typofinder.core.repository;

import org.apache.commons.io.IOUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by izeye on 15. 10. 23..
 */
public class DefaultDictionary implements Dictionary {
	
	private final Set<String> words = new HashSet<>();
	
	public DefaultDictionary(String... resourceLocations) {
		for (String resourceLocation : resourceLocations) {
			try {
				File file = ResourceUtils.getFile(resourceLocation);
				List<String> lines = IOUtils.readLines(new FileReader(file));
				List<String> filtered = lines.stream().filter(line -> !line.startsWith("#"))
						.collect(Collectors.toList());
				this.words.addAll(filtered);
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
