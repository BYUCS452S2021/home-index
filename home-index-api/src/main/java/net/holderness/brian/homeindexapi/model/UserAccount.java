package net.holderness.brian.homeindexapi.model;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class UserAccount {
    private final String username;
    private final String firstName;
    private final String lastName;

    public static UserAccount mapUserAccount(ResultSet rs, int rowNum) throws SQLException {
        return new UserAccount(
            rs.getString("username"),
            rs.getString("first_name"),
            rs.getString("last_name")
        );
    }
}
