package javabeans.model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Kelas Koneksi
 * Utility class untuk mengelola koneksi ke database MySQL.
 * Menggunakan pola singleton untuk memastikan hanya satu instance koneksi yang digunakan.
 *
 * Database yang digunakan:
 * URL: jdbc:mysql://localhost:3306/javabeans
 * User: root
 * Password: (kosong)
 *
 * Jika koneksi gagal, error akan dicatat dalam log menggunakan java.util.logging.
 *
 * @author HAFIS HENDRIANSYAH
 */
public class Koneksi {
    private static Connection conn;
    
    /**
     * Mengembalikan objek Connection tunggal ke database.
     * Jika koneksi belum dibuat, maka koneksi baru akan dibuat menggunakan DriverManager.
     * 
     * @return objek Connection ke database
     */
    public static Connection getConnection(){
        if(conn == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/javabeans";
                String user = "root";
                String pass = "";
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                conn = (Connection) DriverManager.getConnection(url, user, pass);
            } catch (SQLException e) {
                Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return conn;
    }
}
