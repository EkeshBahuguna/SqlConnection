package oodles.configuration;

import org.springframework.beans.factory.annotation.Value;

public class StagEnv implements EnvConfiguration {
    @Value("${spring.datasource.url.stage}")
    private String mySqlDBUrl;

    @Value("${spring.datasource.user.stage}")
    private String mySqlDBUser;

    @Value("${spring.datasource.password.stage}")
    private String mySqlDBPassword;

    @Value("${spring.datasource.db.driver}")
    private String mySqlDBDriver;

	public String getMySqlDBUrl() {
		return mySqlDBUrl;
	}

	public void setMySqlDBUrl(String mySqlDBUrl) {
		this.mySqlDBUrl = mySqlDBUrl;
	}

	public String getMySqlDBUser() {
		return mySqlDBUser;
	}

	public void setMySqlDBUser(String mySqlDBUser) {
		this.mySqlDBUser = mySqlDBUser;
	}

	public String getMySqlDBPassword() {
		return mySqlDBPassword;
	}

	public void setMySqlDBPassword(String mySqlDBPassword) {
		this.mySqlDBPassword = mySqlDBPassword;
	}

	public String getMySqlDBDriver() {
		return mySqlDBDriver;
	}

	public void setMySqlDBDriver(String mySqlDBDriver) {
		this.mySqlDBDriver = mySqlDBDriver;
	}

	@Override
	public String getEnvironment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getControllerPackages() {
		// TODO Auto-generated method stub
		return null;
	}
}