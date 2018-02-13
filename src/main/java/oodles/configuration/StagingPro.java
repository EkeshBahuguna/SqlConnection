package oodles.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("stage")

public class StagingPro {
	@Bean
	public EnvConfiguration getTestingConfig(){
		return new StagEnv();
	}

}
