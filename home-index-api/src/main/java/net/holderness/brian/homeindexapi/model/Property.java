package net.holderness.brian.homeindexapi.model;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Property {
    private final int id;
    private final String nickname;
    private final String address;

    public static Property mapProperty(ResultSet rs, int rowNum) throws SQLException {
        return new Property(
            rs.getInt("id"),
            rs.getString("nickname"),
            rs.getString("address")
        );
    }
}
