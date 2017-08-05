package helloworld.dynamo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * Created by mmueller on 29.07.2017.
 */
public class DynamaoDbFactoroy extends AbstractFactoryBean<AmazonDynamoDB> {

    @Override
    public Class<?> getObjectType() {
        return AmazonDynamoDB.class;
    }

    @Override
    protected AmazonDynamoDB createInstance() throws Exception {
        AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();
        return ddb;
    }
}
