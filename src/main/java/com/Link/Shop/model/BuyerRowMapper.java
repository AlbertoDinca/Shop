package com.Link.Shop.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuyerRowMapper implements RowMapper<buyer> {
    @Override
    public buyer mapRow(ResultSet rs, int rowNum) throws SQLException {
       buyer buyer = new buyer();
       buyer.setId(rs.getInt("id"));
       buyer.setFirstName(rs.getString("firstName"));
       buyer.setLastName(rs.getString("lastName"));
       buyer.setAge(rs.getInt("age"));
        return buyer;
    }
}
