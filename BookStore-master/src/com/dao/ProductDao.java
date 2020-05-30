package com.dao;

import com.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库封装
 */
public class ProductDao {

    public List<Product> getListProduct(int begin, int rows) {
        List<Product> productList = new ArrayList<Product>();
        String sql = "SELECT * FROM product LIMIT " + begin + " , " + rows;
        Connection connection = DBUtil.getConn();
        Statement statement = DBUtil.createStmt(connection);
        ResultSet resultSet = DBUtil.executeQuery(statement,sql);
        try {
            while (resultSet.next()) {
                Product product = new Product();
                product.setBook_id(resultSet.getInt("book_id"));
                product.setBook_name(resultSet.getString("book_name"));
                product.setBook_price(resultSet.getDouble("book_price"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closers(resultSet);
            DBUtil.closestmt(statement);
            DBUtil.closeconn(connection);
        }
        return productList;
    }

    public Product getProductById(String book_id) {
        String sql = "SELECT * FROM product WHERE book_id = " + book_id;
        Connection connection = DBUtil.getConn();
        Statement statement = DBUtil.createStmt(connection);
        ResultSet resultSet = DBUtil.executeQuery(statement,sql);
        Product product = new Product();
        try {
            while (resultSet.next()) {
                product.setBook_name(resultSet.getString("book_name"));
                product.setBook_price(resultSet.getDouble("book_price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closers(resultSet);
            DBUtil.closestmt(statement);
            DBUtil.closeconn(connection);
        }
        return product;
    }

    public List<Product> getProductByName(String book_name) {
        String sql = "SELECT * FROM product WHERE book_name Like '" + "%" + book_name + "%" + "'" ;
        Connection connection = DBUtil.getConn();
        Statement statement = DBUtil.createStmt(connection);
        ResultSet resultSet = DBUtil.executeQuery(statement,sql);
        List<Product> productList = new ArrayList<Product>();
        try {
            while (resultSet.next()) {
                Product product = new Product();
                product.setBook_id(resultSet.getInt("book_id"));
                product.setBook_name(resultSet.getString("book_name"));
                product.setBook_price(resultSet.getDouble("book_price"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closers(resultSet);
            DBUtil.closestmt(statement);
            DBUtil.closeconn(connection);
        }
        return productList;
    }
}
