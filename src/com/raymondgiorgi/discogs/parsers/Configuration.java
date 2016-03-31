package com.raymondgiorgi.discogs.parsers;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

/**
 * Created by no-vivisimo on 3/31/2016.
 */
public class Configuration {
    private String databaseHost;
    private String databaseName;
    private String databaseUsername;
    private String databasePassword;
    public Configuration() {

    }

    public void setDatabaseUsername(String database_username) {
        this.databaseUsername = database_username;
    }

    public void setDatabasePassword(String database_password) {
        this.databasePassword = database_password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseHame(String database_name) {
        this.databaseName = database_name;
    }

    public String getDatabaseHost() {
        return databaseHost;
    }

    public void setDatabaseHost(String database_host) {
        this.databaseHost = database_host;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public static Configuration _instance;

    public static Configuration Instance() {
        if (_instance == null) {
            throw new NullPointerException();
        }
        return _instance;
    }

    public static Configuration Build(String configuration) throws FileNotFoundException {
        String conf = configuration;
        if (conf == null) {
            conf = "c://configuration.json";
        }
        Reader reader = new BufferedReader(new FileReader(conf));
        Gson gson = new Gson();
        _instance = (Configuration) gson.fromJson(reader, Configuration.class);
        return _instance;
    }
}
