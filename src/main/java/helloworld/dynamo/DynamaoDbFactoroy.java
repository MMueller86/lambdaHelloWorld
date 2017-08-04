package helloworld.dynamo;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * Created by mmueller on 29.07.2017.
 */
public class DynamaoDbFactoroy extends AbstractFactoryBean<AmazonDynamoDB> {
    private String localDynamoDb;

    @Override
    public Class<?> getObjectType() {
        return AmazonDynamoDB.class;
    }

    @Override
    protected AmazonDynamoDB createInstance() throws Exception {
        AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
                // we can use any region here
                new AwsClientBuilder.EndpointConfiguration(localDynamoDb, "us-west-2"))
                .build();
        return ddb;
    }

    public void setLocalDynamoDb(String localDynamoDb) {
        this.localDynamoDb = localDynamoDb;
    }
}
