package dynamo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.*;
import com.github.klousiaj.junit.DockerRule;
import helloworld.constants.SupportedLanguagesEnum;
import helloworld.entity.LanguageEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmueller on 04.08.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring_test.xml"})
public abstract class DynamoTestSupport {

    // Amount of saved Items durin the initDB.
    protected List<String> savedIds = new ArrayList<>();

    @Autowired
    private AmazonDynamoDB ddb;

    @Before
    public void initDb() {
        createTable("helloworld", "Id");
        saveItem("Hallo Welt", SupportedLanguagesEnum.GERMAN);
        saveItem("Hello World", SupportedLanguagesEnum.ENGLISH);

    }

    private void saveItem(String helloWorld, SupportedLanguagesEnum language) {
        LanguageEntity languageItem = new LanguageEntity();
        languageItem.setHelloWorld(helloWorld);
        languageItem.setLanguage(language.toString());
        DynamoDBMapper mapper = new DynamoDBMapper(ddb);
        mapper.save(languageItem);
        savedIds.add(languageItem.getId());

    }

    @After
    public void cleanDb() {
        deleteTable("helloworld");
    }

    private void createTable(String tableName, String hashKeyName) {
        List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
        attributeDefinitions.add(new AttributeDefinition(hashKeyName, ScalarAttributeType.S));

        List<KeySchemaElement> ks = new ArrayList<KeySchemaElement>();
        ks.add(new KeySchemaElement(hashKeyName, KeyType.HASH));

        ProvisionedThroughput provisionedthroughput = new ProvisionedThroughput(1000L, 1000L);

        CreateTableRequest request =
                new CreateTableRequest()
                        .withTableName(tableName)
                        .withAttributeDefinitions(attributeDefinitions)
                        .withKeySchema(ks)
                        .withProvisionedThroughput(provisionedthroughput);

        ddb.createTable(request);
    }

    private void deleteTable(String tableName) {
        ddb.deleteTable(tableName);
    }

}
