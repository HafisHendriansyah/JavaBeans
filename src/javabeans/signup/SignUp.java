package javabeans.signup;

/**
 * Kelas ini merepresentasikan data akun pengguna baru 
 * yang akan didaftarkan ke dalam sistem.
 * Berisi informasi nama, username, email, dan password.
 * Kelas ini merepresentasikan data akun pengguna baru 
 * yang akan didaftarkan ke dalam sistem.
 * Berisi informasi nama, username, email, dan password.

 * @author HAFIS HENDRIANSYAH
 */
public class SignUp {
    /** Nama lengkap pengguna */
    public String nameUser;
    
    /** Username yang akan digunakan untuk login */
    public String username;
    
    /** Alamat email pengguna */
    public String email;
    
    /** Password pengguna (sudah dalam bentuk hash saat disimpan) */
    public String passwordUser;
    
    /**
     * Konstruktor untuk membuat objek SignUp baru
     * 
     * @param nameUser Nama lengkap pengguna
     * @param username Username pengguna
     * @param email Email pengguna
     * @param passwordUser Password pengguna (dalam bentuk hash)
     */
    public SignUp(String nameUser, String username, String email, String passwordUser){
        this.nameUser = nameUser;
        this.username = username;
        this.email = email;
        this.passwordUser = passwordUser;
    }
}
