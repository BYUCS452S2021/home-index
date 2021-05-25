package net.holderness.brian.homeindexapi.service;

import net.holderness.brian.homeindexapi.model.Property;
import net.holderness.brian.homeindexapi.model.Space;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import javax.swing.text.html.Option;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PropertyService(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(new JdbcTemplate(dataSource, false));
    }

    public BigInteger create(Property property) {
        // language=SQL
        final String QUERY =
            "INSERT INTO property (nickname, address) " +
            "VALUES (:nickname, :address)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource data = new MapSqlParameterSource();
        data.addValue("nickname", property.getNickname());
        data.addValue("address", property.getAddress());
        jdbcTemplate.update(QUERY, data, keyHolder);
        return keyHolder.getKeyAs(BigInteger.class);
    }

    public Optional<Property> findById(int id) {
        // language=SQL
        final String QUERY =
            "SELECT * FROM property WHERE id = :id";

        try {
            MapSqlParameterSource data = new MapSqlParameterSource();
            data.addValue("id", id);
            return Optional.ofNullable(jdbcTemplate.queryForObject(QUERY, data, Property::mapProperty));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Space> getSpaces(int propertyId) {
        // language=SQL
        final String QUERY =
            "SELECT * FROM space WHERE property_id = :propertyId";

        MapSqlParameterSource data = new MapSqlParameterSource();
        data.addValue("propertyId", propertyId);
        return jdbcTemplate.query(QUERY, data, Space::mapSpace);
    }

    public Property replace(Property property) {
        // language=SQL
        final String QUERY =
            "INSERT INTO property " +
            "VALUES (:id, :nickname, :address) " +
            "ON DUPLICATE KEY UPDATE nickname = :nickname, address = :address";

        MapSqlParameterSource data = new MapSqlParameterSource();
        data.addValue("id", property.getId());
        data.addValue("nickname", property.getNickname());
        data.addValue("address", property.getAddress());
        jdbcTemplate.update(QUERY, data);
        return property;
    }

    public void delete(int id) {
        // language=SQL
        final String QUERY =
            "DELETE FROM property WHERE id = :id";

        MapSqlParameterSource data = new MapSqlParameterSource();
        data.addValue("id", id);
        jdbcTemplate.update(QUERY, data);
    }
}
