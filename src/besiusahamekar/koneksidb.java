/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package besiusahamekar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Banuajie
 */
public class koneksidb {
    
    private static Connection koneksi;
    //PATH REPORT UNTUK NETBEANS
    public static String PathReport=System.getProperty("user.dir") + "/report/";
    //PATH REPORT UNTUK COMPILE
    //public static String PathReport=System.getProperty("user.dir") + "/Laporan/";
    
    public static Connection getkoneksi() {
        if (koneksi==null) {
            try {
                String url;
                String user;
                String password;
                url="jdbc:mysql://localhost:3306/dbbesiusahamekar";
                user="root";
                password="";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksi=DriverManager.getConnection(url,user,password);
            }catch (SQLException t) {
                System.out.println("Error membuat koneksi");
            }
        }
     return koneksi;
    }
}
