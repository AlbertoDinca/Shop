package com.Link.Shop.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<products> {
    @Override
    public products mapRow(ResultSet rs, int rowNum) throws SQLException {
       products products = new products();
       products.setId(rs.getInt("id"));
       products.setProductName(rs.getString("productName"));
       products.setProductPrice(rs.getInt("productPrice"));
       products.setProductQuantity(rs.getInt("productQuantity"));
        return products;
    }
}
