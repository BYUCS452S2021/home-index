package net.holderness.brian.homeindexapi.service;

import net.holderness.brian.homeindexapi.model.Item;
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
public class ItemService {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ItemService(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(new JdbcTemplate(dataSource, false));
    }

    public BigInteger create(Item item) {
        // language=SQL
        final String QUERY =
            "INSERT INTO item (nickname, description, photo, quantity, monetary_value, container_id) " +
            "VALUES (:nickname, :description, :photo, :quantity, :monetaryValue, :containerId)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource data = new MapSqlParameterSource();
        data.addValue("nickname", item.getNickname());
        data.addValue("description", item.getDescription());
        data.addValue("photo", item.getPhoto());
        data.addValue("quantity", item.getQuantity());
        data.addValue("monetaryValue", item.getMonetaryValue());
        data.addValue("containerId", item.getContainerId());
        jdbcTemplate.update(QUERY, data, keyHolder);
        return keyHolder.getKeyAs(BigInteger.class);
    }

    public Optional<Item> findById(int id) {
        // language=SQL
        final String QUERY =
            "SELECT * FROM item WHERE id = :id";

        try {
            MapSqlParameterSource data = new MapSqlParameterSource();
            data.addValue("id", id);
            return Optional.ofNullable(jdbcTemplate.queryForObject(QUERY, data, Item::mapItem));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Item replace(Item item) {
        // language=SQL
        final String QUERY =
            "INSERT INTO item " +
            "VALUES (:id, :nickname, :description, :photo, :quantity, :monetaryValue, :containerId) " +
            "ON DUPLICATE KEY UPDATE nickname = :nickname, description = :description, photo = :photo, quantity = :quantity, monetary_value = :monetaryValue, container_id = :containerId";

        MapSqlParameterSource data = new MapSqlParameterSource();
        data.addValue("id", item.getId());
        data.addValue("nickname", item.getNickname());
        data.addValue("description", item.getDescription());
        data.addValue("photo", item.getPhoto());
        data.addValue("quantity", item.getQuantity());
        data.addValue("monetaryValue", item.getMonetaryValue());
        data.addValue("containerId", item.getContainerId());
        jdbcTemplate.update(QUERY, data);
        return item;
    }

    public void delete(int id) {
        // language=SQL
        final String QUERY =
            "DELETE FROM item WHERE id = :id";

        MapSqlParameterSource data = new MapSqlParameterSource();
        data.addValue("id", id);
        jdbcTemplate.update(QUERY, data);
    }
}
