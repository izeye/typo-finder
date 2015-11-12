package com.izeye.typofinder.core.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by izeye on 15. 10. 25..
 */
@ConfigurationProperties(prefix = "typo-finder")
@Data
public class TypoFinderProperties {
	
	private boolean ignoreSingleCharacterWords;
	private String[] ignoredWordFiles;
	private boolean allowCamelCase;
	
}
