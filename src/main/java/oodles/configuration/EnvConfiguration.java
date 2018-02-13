package oodles.configuration;
public interface EnvConfiguration {

    String getEnvironment();

    // for mysql
    String getMySqlDBUrl();

    String getMySqlDBUser();

    String getMySqlDBPassword();

    String getMySqlDBDriver();

	String[] getControllerPackages();

   

}
