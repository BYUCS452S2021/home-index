package net.holderness.brian.homeindexapi.service;

import net.holderness.brian.homeindexapi.model.Container;
import net.holderness.brian.homeindexapi.model.Container;
import net.holderness.brian.homeindexapi.model.Item;
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
public class ContainerService {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ContainerService(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(new JdbcTemplate(dataSource, false));
    }

    public BigInteger create(Container container) {
        // language=SQL
        final String QUERY =
            "INSERT INTO container (nickname, description, space_id) " +
            "VALUES (:nickname, :description, :spaceId)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource data = new MapSqlParameterSource();
        data.addValue("nickname", container.getNickname());
        data.addValue("description", container.getDescription());
        data.addValue("spaceId", container.getSpaceId());
        jdbcTemplate.update(QUERY, data, keyHolder);
        return keyHolder.getKeyAs(BigInteger.class);
    }

    public Optional<Container> findById(int id) {
        // language=SQL
        final String QUERY =
            "SELECT * FROM container WHERE id = :id";

        try {
            MapSqlParameterSource data = new MapSqlParameterSource();
            data.addValue("id", id);
            return Optional.ofNullable(jdbcTemplate.queryForObject(QUERY, data, Container::mapContainer));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Item> getItems(int containerId) {
        // language=SQL
        final String QUERY =
            "SELECT * FROM item WHERE container_id = :containerId";

        MapSqlParameterSource data = new MapSqlParameterSource();
        data.addValue("containerId", containerId);
        return jdbcTemplate.query(QUERY, data, Item::mapItem);
    }

    public Container replace(Container container) {
        // language=SQL
        final String QUERY =
            "INSERT INTO container " +
            "VALUES (:id, :nickname, :description, :spacedId) " +
            "ON DUPLICATE KEY UPDATE nickname = :nickname, description = :description, space_id = :spaceId";

        MapSqlParameterSource data = new MapSqlParameterSource();
        data.addValue("id", container.getId());
        data.addValue("nickname", container.getNickname());
        data.addValue("description", container.getDescription());
        data.addValue("spaceId", container.getSpaceId());
        jdbcTemplate.update(QUERY, data);
        return container;
    }

    public void delete(int id) {
        // language=SQL
        final String QUERY =
            "DELETE FROM container WHERE id = :id";

        MapSqlParameterSource data = new MapSqlParameterSource();
        data.addValue("id", id);
        jdbcTemplate.update(QUERY, data);
    }
}
