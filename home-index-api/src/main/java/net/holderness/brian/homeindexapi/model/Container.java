package net.holderness.brian.homeindexapi.model;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Container {
    private final int id;
    private final String nickname;
    private final String description;
    private final int spaceId;

    public static Container mapContainer(ResultSet rs, int rowNum) throws SQLException {
        return new Container(
            rs.getInt("id"),
            rs.getString("nickname"),
            rs.getString("description"),
            rs.getInt("space_id")
        );
    }
}
