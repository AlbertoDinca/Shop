package com.Link.Shop.controller;

import com.Link.Shop.model.ProductForm;
import com.Link.Shop.model.ProductRowMapper;
import com.Link.Shop.model.products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    //endpoint care intoarce product.html -> GET
    @GetMapping("/products")
    public ModelAndView getProductsPage() {
        return  new ModelAndView("products.html");
    }
    //endpoint care proceseaza cererea de adaugare produs -> POST
    @PostMapping(value = "/products-result", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView postProduct(ProductForm data){
        //adaugam produs in baza de date
        String sqlQuery="INSERT INTO `shop`.`products`(`productName`,`productPrice`,`productQuantity`) VALUES(?,?,?);";
        int result = jdbcTemplate.update(sqlQuery, data.getProductName(), data.getProductPrice(), data.getProductQuantity());
        return new ModelAndView("redirect:/");
    }
    @GetMapping("/")
    public ModelAndView getIndexPage() {
        ModelAndView productsView = new ModelAndView("index.html");
        String sql = "SELECT * FROM `shop`.`products`";
        List<products> productsList = jdbcTemplate.query(sql, new ProductRowMapper());
        productsView.addObject("products", productsList);
        return productsView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView getProductById(@PathVariable Integer id){
        String query ="SELECT * FROM `shop`.`products`WHERE id ="+ id;
        products dbProductElem = jdbcTemplate.queryForObject(query, new ProductRowMapper());

        ModelAndView updateView = new ModelAndView("update.html");
        updateView.addObject("UpdateElem", dbProductElem);
        return  updateView;
    }
    @GetMapping("/updated/{id}")
    public ModelAndView updated(@PathVariable Integer id, @RequestParam String productName, @RequestParam Integer productPrice, @RequestParam Integer productQuantity) {
        String query = "UPDATE `shop`.`products` SET `productName` = ' " + productName + "' , `productPrice` = ' "+ productPrice +"', `productQuantity` = '"+ productQuantity +"' WHERE `id`= " + id ;
       int result = jdbcTemplate.update(query);
       return  new ModelAndView("redirect:/");
    }
    @GetMapping("/delete/{id}")
    public ModelAndView getProductByIdDel(@PathVariable Integer id){
        String query ="DELETE FROM `shop`.`products`WHERE id ="+ id;
        int result = jdbcTemplate.update(query);
        return  new ModelAndView("redirect:/");
    }

}
