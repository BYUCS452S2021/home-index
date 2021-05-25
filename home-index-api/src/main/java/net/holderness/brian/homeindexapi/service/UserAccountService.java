package net.holderness.brian.homeindexapi.service;

import net.holderness.brian.homeindexapi.model.Property;
import net.holderness.brian.homeindexapi.model.UserAccount;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccountService {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserAccountService(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(new JdbcTemplate(dataSource, false));
    }

    public Optional<UserAccount> findByUsername(String username) {
        // language=SQL
        final String QUERY =
            "SELECT * FROM user_account WHERE username = :username";

        try {
            MapSqlParameterSource data = new MapSqlParameterSource();
            data.addValue("username", username);
            return Optional.ofNullable(jdbcTemplate.queryForObject(QUERY, data, UserAccount::mapUserAccount));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Property> getUserProperties(String username) {
        // language=SQL
        final String QUERY =
            "SELECT\n" +
                "    property.* " +
                "FROM property " +
                "JOIN user_property up on property.id = up.property_id " +
                "JOIN user_account ua on up.username = ua.username " +
                "WHERE ua.username = :username";


        MapSqlParameterSource data = new MapSqlParameterSource();
        data.addValue("username", username);
        return jdbcTemplate.query(QUERY, data, Property::mapProperty);
    }

    public UserAccount replace(UserAccount userAccount) {
        // language=SQL
        final String QUERY =
            "INSERT INTO user_account " +
            "VALUES (:username, :firstName, :lastName) " +
            "ON DUPLICATE KEY UPDATE first_name = :firstName, last_name = :lastName";

        MapSqlParameterSource data = new MapSqlParameterSource();
        data.addValue("username", userAccount.getUsername());
        data.addValue("firstName", userAccount.getFirstName());
        data.addValue("lastName", userAccount.getLastName());
        jdbcTemplate.update(QUERY, data);
        return userAccount;
    }

    public void delete(String username) {
        // language=SQL
        final String QUERY =
            "DELETE FROM user_account WHERE username = :username";

        MapSqlParameterSource data = new MapSqlParameterSource();
        data.addValue("username", username);
        jdbcTemplate.update(QUERY, data);
    }
}
