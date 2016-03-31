package com.raymondgiorgi.discogs.parsers.utils;

import com.raymondgiorgi.discogs.parsers.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by no-vivisimo on 3/29/2016.
 */
public class DatabaseUtils {
    public static Connection getSlmConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Configuration c = Configuration.Instance();
            String url = "jdbc:mysql://" + c.getDatabaseHost() + "/" + c.getDatabaseName() + "";
            return DriverManager.getConnection(url, c.getDatabaseUsername(), c.getDatabasePassword());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
