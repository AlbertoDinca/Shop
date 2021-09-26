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
public class BuyersController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/buyers")
    public ModelAndView getProductsPage() {
        return  new ModelAndView("buyers.html");
    }

    @PostMapping(value = "/buyers-result", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView postBuyers(BuyerForm data){
        //adaugam produs in baza de date
        String sqlQuery="INSERT INTO `shop`.`buyers`(`firstName`,`lastName`,`age`) VALUES(?,?,?);";
        int result = jdbcTemplate.update(sqlQuery, data.getFirstName(), data.getLastName(), data.getAge());
        return new ModelAndView("redirect:/buyer-list");
    }

    @GetMapping("/buyer-list")
    public ModelAndView getBuyerList() {
        ModelAndView buyerView = new ModelAndView("buyerList.html");
        String sql = "SELECT * FROM `shop`.`buyers`";
        List<buyer> buyerList = jdbcTemplate.query(sql, new BuyerRowMapper());
        buyerView.addObject("buyer", buyerList);
        return buyerView;
    }

    @GetMapping("/buyerUpdate/{id}")
    public ModelAndView getBuyerById(@PathVariable Integer id){
        String query ="SELECT * FROM `shop`.`buyers`WHERE id ="+ id;
        buyer dbProductElem = jdbcTemplate.queryForObject(query, new BuyerRowMapper());

        ModelAndView updateView = new ModelAndView("buyerUpdate.html");
        updateView.addObject("UpdateElem", dbProductElem);
        return  updateView;
    }
    @GetMapping("/buyerUpdated/{id}")
    public ModelAndView updatedId(@PathVariable Integer id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam Integer age){
        String query = "UPDATE `shop`.`buyers` SET `firstName` = '"+ firstName + "', `lastName` = ' "+ lastName +"',`age` = '"+ age +"' WHERE id ="+ id;
        int result = jdbcTemplate.update(query);
        return new ModelAndView("redirect:/buyer-list");
    }

    @GetMapping("/deletedBuyer/{id}")
    public ModelAndView deletedId(@PathVariable Integer id){
        String query = "DELETE FROM `shop`.`buyers` WHERE id = "+ id;
        int result = jdbcTemplate.update(query);
        return new ModelAndView("redirect:/buyer-list");
    }
}
