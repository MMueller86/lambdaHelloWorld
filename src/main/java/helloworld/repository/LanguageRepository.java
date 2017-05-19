package helloworld.repository;

import helloworld.constants.SupportedLanguagesEnum;
import helloworld.entity.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mimo on 17.05.2017.
 */
@Repository
@Transactional(readOnly = true)
public interface LanguageRepository extends JpaRepository<LanguageEntity, Integer> {

    LanguageEntity findByLanguage(SupportedLanguagesEnum language);
}
