package com.raghav.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static Connection connection;
    private static  Connection createConnection() throws ClassNotFoundException, IOException, SQLException {
        final Properties properties = loadProp();
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
    }
    public static Properties loadProp() throws IOException {
        final Properties prop = new Properties();
        prop.load(ConnectionFactory.class.getClassLoader().getResourceAsStream("db.properties"));
        return prop;
    }


    public synchronized static Connection getConnection() throws Exception{
            if(connection == null){
                connection = createConnection();
            }else if(connection.isClosed()){
                connection  = null;
                return getConnection();
            }
            return connection;
    }
    public synchronized static void close() throws Exception{
            if(connection != null){
                connection.close();
                connection = null;
            }
    }
}
