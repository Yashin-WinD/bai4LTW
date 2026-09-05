package vn.ute.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
    private final String serverName = "localhost";
    private final String dbName = "shoppingservicemvc"; 
    private final String portNumber = "3306"; 
    private final String userID = "root"; 
    private final String password = "bao123456"; 

    public Connection getConnection() throws Exception {
    
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        String url = "jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName 
                     + "?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        
        return DriverManager.getConnection(url, userID, password);
    }
}