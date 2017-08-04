package repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.Expected;
import com.amazonaws.services.dynamodbv2.model.*;
import com.github.klousiaj.junit.DockerRule;
import dynamo.DynamoTestSupport;
import helloworld.constants.SupportedLanguagesEnum;
import helloworld.entity.LanguageEntity;
import helloworld.repository.LanguageRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by mimo on 17.05.2017.
 */
public class LanguageRepositoryTest extends DynamoTestSupport {

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private AmazonDynamoDB ddb;

    @ClassRule
    public static DockerRule rabbitRule =
            DockerRule.builder()
                    .image("ryanratcliff/dynamodb")
                    .ports("5672", ":32779", "8000:8000")
                    .build();

    @Test
    public void findByLanguage_Success() {
        LanguageEntity entity = languageRepository.findByLanguage(SupportedLanguagesEnum.ENGLISH);
        Assert.assertNotNull(entity);
        Assert.assertEquals("Hello World", entity.getHelloWorld());
        Assert.assertEquals(SupportedLanguagesEnum.ENGLISH.toString(),entity.getLanguage());
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByLanguage_NoRecord() {
        LanguageEntity entity = languageRepository.findByLanguage(SupportedLanguagesEnum.UNKNOWN);
    }

    @Test
    public void listAllLanguages() {
        List<LanguageEntity> languageEntityList = languageRepository.listAll();
        Assert.assertNotNull(languageEntityList);
        Assert.assertEquals(savedIds.size(), languageEntityList.size());
    }
}
