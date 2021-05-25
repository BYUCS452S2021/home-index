package net.holderness.brian.homeindexapi.model;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Item {
    private final int id;
    private final String nickname;
    private final String description;
    private final String photo;
    private final int quantity;
    private final double monetaryValue;
    private final int containerId;

    public static Item mapItem(ResultSet rs, int rowNum) throws SQLException {
        return new Item(
            rs.getInt("id"),
            rs.getString("nickname"),
            rs.getString("description"),
            rs.getString("photo"),
            rs.getInt("quantity"),
            rs.getDouble("monetary_value"),
            rs.getInt("container_id")
        );
    }
}
