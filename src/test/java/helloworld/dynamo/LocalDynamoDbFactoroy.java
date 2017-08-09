package helloworld.dynamo;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Bean;

/**
 * Created by mmueller on 29.07.2017.
 */

public class LocalDynamoDbFactoroy extends AbstractFactoryBean<AmazonDynamoDB> {

    private String localDynamoDb;

    @Override
    public Class<AmazonDynamoDB> getObjectType() {
        return AmazonDynamoDB.class;
    }

    @Override
    public AmazonDynamoDB createInstance() throws Exception {

        BasicAWSCredentials awsCreds = new BasicAWSCredentials("access_key_id", "secret_key_id");
        AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
                // we can use any region here
                new AwsClientBuilder.EndpointConfiguration(localDynamoDb, "us-west-2")).
                withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
        return ddb;
    }

    public void setLocalDynamoDb(String localDynamoDb) {
        this.localDynamoDb = localDynamoDb;
    }
}
