package oodles.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")

public class DevelopmentPro {
	@Autowired
	public EnvConfiguration envConfig;
	
	@Bean
	public EnvConfiguration getDevelopmentConfig(){
		return new DevelopmentEnv();
	}
}