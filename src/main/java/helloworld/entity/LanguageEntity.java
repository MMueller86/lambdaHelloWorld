package helloworld.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


/**
 * Entity to containing the Hello Wold information.
 * Due to the used annotations this entity can be used by the
 * {@link com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper}.
 *
 * Created by mimo on 26.04.2017.
 */

@DynamoDBTable(tableName = "helloworld")
public class LanguageEntity {

    @DynamoDBHashKey(attributeName = "Id")
    @DynamoDBAutoGeneratedKey
    private String id;

    @DynamoDBAttribute(attributeName="origanalLanguage")
    private String language;

    @DynamoDBAttribute(attributeName="helloworld")
    private String helloWorld;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    public String getHelloWorld() {
        return helloWorld;
    }

    public void setHelloWorld(final String helloWorld) {
        this.helloWorld = helloWorld;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof LanguageEntity)) return false;

        final LanguageEntity that = (LanguageEntity) o;

        return getId().equals(that.getId());
    }

    @Override
    public String toString() {
        return "LanguageEntity{" +
                "language='" + language + '\'' +
                ", HelloWorld='" + helloWorld + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getLanguage().hashCode();
        result = 31 * result + getHelloWorld().hashCode();
        return result;
    }
}
