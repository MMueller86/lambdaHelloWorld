package helloWorld.dao;


import helloWorld.constants.SupportedLanguagesEnum;
import helloWorld.entity.LanguageEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by mimo on 24.04.2017.
 */
public class LanguageDAO extends JdbcDaoSupport implements ILanguageDAO {

    @Override
    public List<LanguageEntity> listLanguages() {
        final String sql = "SELECT \"ID\", \"Language\", \"HelloWorld\" FROM \"TEST\".\"HelloWorld\"";
        return getJdbcTemplate().query(sql, new LanguageRowMapper());
    }

    @Override
    public LanguageEntity getLanguage(final SupportedLanguagesEnum language) {
        final String sql = "SELECT * FROM \"TEST\".\"HelloWorld\" WHERE \"Language\" = ? ";
        return getJdbcTemplate().queryForObject(sql, new LanguageRowMapper(), language.toString());
    }

    private class LanguageRowMapper implements RowMapper<LanguageEntity> {

        @Override
        public LanguageEntity mapRow(final ResultSet resultSet, final int i) throws SQLException {
            final LanguageEntity entity = new LanguageEntity();
            entity.setId(resultSet.getInt("ID"));
            entity.setLanguage(resultSet.getString("Language"));
            entity.setHelloWorld(resultSet.getString("HelloWorld"));

            return entity;
        }
    }


}