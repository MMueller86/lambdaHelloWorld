package helloworld.entity;

import helloworld.constants.SupportedLanguagesEnum;

import javax.persistence.*;

/**
 * Created by mimo on 26.04.2017.
 */

@Entity
@Table(name = "helloworld")
public class LanguageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(allocationSize = 10, name = "id_generator", sequenceName = "serial")
    @Column(name = "ID")
    private Long id;

    @Column(name = "Language")
    @Enumerated(EnumType.STRING)
    private SupportedLanguagesEnum language;

    @Column(name = "HelloWorld")
    private String helloWorld;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public SupportedLanguagesEnum getLanguage() {
        return language;
    }

    public void setLanguage(final SupportedLanguagesEnum language) {
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
