package helloworld.entity;

/**
 * Created by mimo on 26.04.2017.
 */

public class LanguageEntity {

    private Integer id;
    private String language;
    private String helloWorld;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
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
