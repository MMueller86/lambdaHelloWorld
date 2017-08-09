package helloworld.dynamo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * Factory to return a valid connection to the AWS Dynamo DB.
 * Created by mmueller on 29.07.2017.
 */
public class DynamaoDbFactoroy extends AbstractFactoryBean<AmazonDynamoDB> {

    // Not Used... It is a factory...
    private DynamaoDbFactoroy() {
    }

    @Override
    public Class<?> getObjectType() {
        return AmazonDynamoDB.class;
    }

    @Override
    protected AmazonDynamoDB createInstance() throws Exception {
        return AmazonDynamoDBClientBuilder.defaultClient();
    }
}
