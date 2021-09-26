package com.Link.Shop.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleRowMapper implements RowMapper<sale> {
    @Override
    public sale mapRow(ResultSet rs, int rowNum) throws SQLException {
        sale sale = new sale();
        sale.setId(rs.getInt("id"));
        sale.setBuyersId(rs.getInt("buyersId"));
        sale.setProductsId(rs.getInt("productsId"));
        return sale;
    }
}
