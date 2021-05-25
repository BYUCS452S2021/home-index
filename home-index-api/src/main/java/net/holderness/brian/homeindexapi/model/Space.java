package net.holderness.brian.homeindexapi.model;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Space {
    private final int id;
    private final String nickname;
    private final String description;
    private final int propertyId;

    public static Space mapSpace(ResultSet rs, int rowNum) throws SQLException {
        return new Space(
            rs.getInt("id"),
            rs.getString("nickname"),
            rs.getString("description"),
            rs.getInt("property_id")
        );
    }
}
