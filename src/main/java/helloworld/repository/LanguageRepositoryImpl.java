package helloworld.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.*;
import helloworld.constants.SupportedLanguagesEnum;
import helloworld.entity.LanguageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mimo on 17.05.2017.
 */
@Repository
public class LanguageRepositoryImpl implements LanguageRepository {

    @Autowired
    private AmazonDynamoDB ddb;

    @Override
    public LanguageEntity findByLanguage(final SupportedLanguagesEnum language) {
        DynamoDBMapper mapper = new DynamoDBMapper(ddb);

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":languageToFilter", new AttributeValue().withS(language.toString()));

        DynamoDBScanExpression queryExpression = new DynamoDBScanExpression()
                .withFilterExpression("origanalLanguage = :languageToFilter").withExpressionAttributeValues(expressionAttributeValues);

        List<LanguageEntity> languageEntityList = mapper.scan(LanguageEntity.class, queryExpression);
        if (CollectionUtils.isEmpty(languageEntityList) || languageEntityList.size() > 1) {
            throw new IllegalArgumentException("Found no or more than 1 record for language = " + language.toString());
        }
        return languageEntityList.get(0);
    }

    @Override
    public List<LanguageEntity> listAll() {
        DynamoDBMapper mapper = new DynamoDBMapper(ddb);
        List<LanguageEntity> languageList = mapper.scan(LanguageEntity.class, new DynamoDBScanExpression());
        return languageList;
    }
}
