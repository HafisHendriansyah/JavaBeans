package javabeans.login;

/**
 *
 * @author HAFIS HENDRIANSYAH
 * 
 * Login.java
 * 
 * Kelas ini menangani proses autentikasi login dengan mencocokkan input username dan password
 * terhadap data yang tersimpan dalam array atau basis data (jika tersedia).
 */
public class Login {
    /**
     * Menyimpan nama pengguna (username) yang diinputkan oleh pengguna.
     */
    public String username;
    
    /**
     * Menyimpan kata sandi (password) yang diinputkan oleh pengguna.
     */
    public String password;
    
    /**
     * Konstruktor untuk kelas Login.
     * Digunakan untuk menginisialisasi data username dan password dari input pengguna.
     * 
     * @param username nama pengguna yang diinput
     * @param password kata sandi yang diinput
     */
    public Login(String username, String password){
        this.username = username;
        this.password = password;
    }
}
