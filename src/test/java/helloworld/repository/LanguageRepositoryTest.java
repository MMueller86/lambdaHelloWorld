package helloworld.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.github.klousiaj.junit.DockerRule;
import helloworld.constants.SupportedLanguagesEnum;
import helloworld.dynamo.DynamoTestSupport;
import helloworld.entity.LanguageEntity;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
