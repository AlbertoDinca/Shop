package com.Link.Shop.controller;


import com.Link.Shop.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SalesController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/sales")
    public ModelAndView sales(){
        ModelAndView salesView = new ModelAndView("sales.html");
        String sqlP = "SELECT * FROM `shop`.`products`";
        List<products> productsList = jdbcTemplate.query(sqlP, new ProductRowMapper());
        String sqlB = "SELECT * FROM `shop`.`buyers`";
        List<buyer> buyerList = jdbcTemplate.query(sqlB, new BuyerRowMapper());
        salesView.addObject("buyer", buyerList);
       salesView.addObject("products", productsList);

       String sqlS= "SELECT * FROM `shop`.`sales`";
       List<sale>saleList=jdbcTemplate.query(sqlS, new SaleRowMapper());
       salesView.addObject("sales", saleList);

        return  salesView;
    }
  @PostMapping(value = "/saleSuccess" , consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView saleSuccess( SalesForm data){
//      String query ="DELETE FROM `shop`.`products`WHERE id ="+ id;
//      int result = jdbcTemplate.update(query);
      String query2 = "INSERT INTO `shop`.`sales` (`buyersId`, `productsId`) VALUES (?,?);";
      int result2 = jdbcTemplate.update(query2,data.getBuyersId(),data.getProductsId());
      String query ="DELETE FROM `shop`.`products`WHERE id ="+ data.getProductsId();
      int result = jdbcTemplate.update(query);


      return new ModelAndView("redirect:/sales");
  }


    }

