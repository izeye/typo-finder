package com.izeye.typofinder.core.util;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by izeye on 15. 11. 12..
 */
public abstract class FileUtils {

	public static String extractFileExtension(File file) {
		String filename = file.getName();
		return filename.substring(filename.lastIndexOf('.') + 1);
	}
	
	public static List<File> findAllFiles(File directory, Set<String> fileExtensions, boolean subdirectoryIncluded) {
		List<File> allFiles = new ArrayList<>();
		File[] files = directory.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if (pathname.isDirectory()) {
					if (subdirectoryIncluded) {
						List<File> allFilesInSubdirectory = findAllFiles(pathname, fileExtensions, subdirectoryIncluded);
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

	public static List<File> findAllFiles(File directory, Set<String> fileExtensions) {
		return findAllFiles(directory, fileExtensions, true);
	}
	
}
