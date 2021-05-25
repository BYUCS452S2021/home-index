package net.holderness.brian.homeindexapi.service;

import net.holderness.brian.homeindexapi.model.Container;
import net.holderness.brian.homeindexapi.model.Space;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class SpaceService {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public SpaceService(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(new JdbcTemplate(dataSource, false));
    }

    public BigInteger create(Space space) {
        // language=SQL
        final String QUERY =
            "INSERT INTO space (nickname, description, property_id) " +
            "VALUES (:nickname, :description, :propertyId)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource data = new MapSqlParameterSource();
        data.addValue("nickname", space.getNickname());
        data.addValue("description", space.getDescription());
        data.addValue("propertyId", space.getPropertyId());
        jdbcTemplate.update(QUERY, data, keyHolder);
        return keyHolder.getKeyAs(BigInteger.class);
    }

    public Optional<Space> findById(int id) {
        // language=SQL
        final String QUERY =
            "SELECT * FROM space WHERE id = :id";

        try {
            MapSqlParameterSource data = new MapSqlParameterSource();
            data.addValue("id", id);
            return Optional.ofNullable(jdbcTemplate.queryForObject(QUERY, data, Space::mapSpace));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Container> getContainers(int spaceId) {
        // language=SQL
        final String QUERY =
            "SELECT * FROM container WHERE space_id = :spaceId";

        MapSqlParameterSource data = new MapSqlParameterSource();
        data.addValue("spaceId", spaceId);
        return jdbcTemplate.query(QUERY, data, Container::mapContainer);
    }

    public Space replace(Space space) {
        // language=SQL
        final String QUERY =
            "INSERT INTO space " +
            "VALUES (:id, :nickname, :description, :propertyId) " +
            "ON DUPLICATE KEY UPDATE nickname = :nickname, description = :description, property_id = :propertyId";

        MapSqlParameterSource data = new MapSqlParameterSource();
        data.addValue("id", space.getId());
        data.addValue("nickname", space.getNickname());
        data.addValue("description", space.getDescription());
        data.addValue("propertyId", space.getPropertyId());
        jdbcTemplate.update(QUERY, data);
        return space;
    }

    public void delete(int id) {
        // language=SQL
        final String QUERY =
            "DELETE FROM space WHERE id = :id";

        MapSqlParameterSource data = new MapSqlParameterSource();
        data.addValue("id", id);
        jdbcTemplate.update(QUERY, data);
    }
}
