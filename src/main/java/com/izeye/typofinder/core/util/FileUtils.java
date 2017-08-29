package com.izeye.typofinder.core.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Utilities for {@link File}.
 *
 * @author Johnny Lim
 */
@Slf4j
public final class FileUtils {

	private FileUtils() {
	}

	public static String extractFileExtension(File file) {
		String filename = file.getName();
		return filename.substring(filename.lastIndexOf('.') + 1);
	}
	
	public static List<File> findAllFiles(
			File directory, Set<String> fileExtensions, Set<String> exclusions, boolean subdirectoryIncluded) {
		List<File> allFiles = new ArrayList<>();
		File[] files = directory.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if (exclusions.contains(pathname.getName())) {
					log.info("Excluded {}", pathname);
					return false;
				}
				if (pathname.isDirectory()) {
					if (subdirectoryIncluded) {
						List<File> allFilesInSubdirectory = findAllFiles(
								pathname, fileExtensions, exclusions, subdirectoryIncluded);
						allFiles.addAll(allFilesInSubdirectory);
					}
					return false;
				}
				return fileExtensions.contains(extractFileExtension(pathname));
			}
		});
		if (files != null) {
			allFiles.addAll(Arrays.asList(files));
		}
		return allFiles;
	}

	public static List<File> findAllFiles(
			File directory, Set<String> fileExtensions, Set<String> exclusions) {
		return findAllFiles(directory, fileExtensions, exclusions, true);
	}

	public static List<String> readLines(File file) {
		try {
			return org.apache.commons.io.FileUtils.readLines(file);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
}
