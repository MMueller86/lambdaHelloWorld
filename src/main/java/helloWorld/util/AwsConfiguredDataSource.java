package helloWorld.util;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created by mimo on 25.04.2017.
 */
public class AwsConfiguredDataSource extends DriverManagerDataSource {
    public AwsConfiguredDataSource() {
        final String dbUser = AwsKmsEnvUtil.decryptKey("TEST_GAME_CARD_DB_USER");
        final String dbUrl = AwsKmsEnvUtil.decryptKey("TEST_GAME_CARD_JDBC_PATH");
        final String dbPassword = AwsKmsEnvUtil.decryptKey("TEST_GAME_CARD_PWD");
        super.setUrl(dbUrl);
        super.setUsername(dbUser);
        super.setPassword(dbPassword);
        super.setDriverClassName("org.postgresql.Driver");
    }
}
