package javabeans.signup;

import javabeans.login.FormLogin;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javabeans.model.Koneksi;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 * FormSignUp adalah tampilan GUI untuk halaman pendaftaran akun baru.
 * Pengguna dapat mengisi nama, username, email, password dan mengonfirmasi password.
 * Form ini juga melakukan validasi input dan menyimpan data ke database.
 *
 * @author HAFIS HENDRIANSYAH
 */
public class FormSignUp extends javax.swing.JFrame {

    /**
     * Creates new form SignUp
     * Konstruktor utama untuk inisialisasi FormSignUp.
     * Mengatur komponen, placeholder dan aksi default.
     */
    public FormSignUp() {
        initComponents(); // Inisialisasi komponen UI bawaan NetBeans
        setLocationRelativeTo(null); // Menempatkan form di tengah layar
        setResizable(false); // Mencegah form diubah ukurannya
        
    txtPasswordSign.setEchoChar((char) 0); // Supaya "Password" terlihat
    txtPasswordConfirm.setEchoChar((char) 0);

    // Fokus awal ke panel supaya placeholder tidak langsung hilang
    this.addWindowFocusListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowGainedFocus(java.awt.event.WindowEvent e) {
            jPanel1.requestFocusInWindow();
        }
    });

    // Placeholder Nama Pengguna 
    txtNameSign.addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusGained(java.awt.event.FocusEvent evt) {
            if (txtNameSign.getText().equals("Name")) {
                txtNameSign.setText("");
            }
        }
        @Override
        public void focusLost(java.awt.event.FocusEvent evt) {
            if (txtNameSign.getText().isEmpty()) {
                txtNameSign.setText("Name");
            }
        }
    });

    // Placeholder Username
    txtUsernameSign.addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusGained(java.awt.event.FocusEvent evt) {
            if (txtUsernameSign.getText().equals("User Name")) {
                txtUsernameSign.setText("");
            }
        }
        @Override
        public void focusLost(java.awt.event.FocusEvent evt) {
            if (txtUsernameSign.getText().isEmpty()) {
                txtUsernameSign.setText("User Name");
            }
        }
    });

    // Placeholder Email
    txtEmailSign.addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusGained(java.awt.event.FocusEvent evt) {
            if (txtEmailSign.getText().equals("Email")) {
                txtEmailSign.setText("");
            }
        }
        @Override
        public void focusLost(java.awt.event.FocusEvent evt) {
            if (txtEmailSign.getText().isEmpty()) {
                txtEmailSign.setText("Email");
            }
        }
    });

    // Placeholder Password
    txtPasswordSign.addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusGained(java.awt.event.FocusEvent evt) {
            String pass = new String(txtPasswordSign.getPassword());
            if (pass.equals("Password")) {
        txtPasswordSign.setText("");
            if (cbxShowPassword.isSelected()) {
            txtPasswordSign.setEchoChar((char) 0);
        } else {
            txtPasswordSign.setEchoChar('●');
            }
        }
    }
        @Override
        public void focusLost(java.awt.event.FocusEvent evt) {
            String pass = new String(txtPasswordSign.getPassword());
            if (pass.isEmpty()) {
                txtPasswordSign.setText("Password");
                txtPasswordSign.setEchoChar((char) 0);
            }
        }
    });
    
     // Placeholder Confirm Password
    txtPasswordConfirm.addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusGained(java.awt.event.FocusEvent evt) {
            String pass = new String(txtPasswordConfirm.getPassword());
            if (pass.equals("Confirm Password")) {
        txtPasswordConfirm.setText("");
            if (cbxShowPasswordConfirm.isSelected()) {
            txtPasswordConfirm.setEchoChar((char) 0);
        } else {
            txtPasswordConfirm.setEchoChar('●');
            }
        }
    }
        @Override
        public void focusLost(java.awt.event.FocusEvent evt) {
            String pass = new String(txtPasswordConfirm.getPassword());
            if (pass.isEmpty()) {
                txtPasswordConfirm.setText("Confirm Password");
                txtPasswordConfirm.setEchoChar((char) 0);
            }
        }
    });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNameSign = new javax.swing.JTextField();
        txtUsernameSign = new javax.swing.JTextField();
        txtEmailSign = new javax.swing.JTextField();
        txtPasswordSign = new javax.swing.JPasswordField();
        btnCreateAccount = new javax.swing.JButton();
        cbxShowPassword = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        txtPasswordConfirm = new javax.swing.JPasswordField();
        cbxShowPasswordConfirm = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sign Up");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(78, 52, 46));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Create an account");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Already have an account?");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, -1, 20));

        txtNameSign.setBackground(new java.awt.Color(255, 243, 224));
        txtNameSign.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtNameSign.setText("Name");
        jPanel1.add(txtNameSign, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 320, 30));

        txtUsernameSign.setBackground(new java.awt.Color(255, 243, 224));
        txtUsernameSign.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtUsernameSign.setText("User Name");
        txtUsernameSign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameSignActionPerformed(evt);
            }
        });
        jPanel1.add(txtUsernameSign, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, 320, 30));

        txtEmailSign.setBackground(new java.awt.Color(255, 243, 224));
        txtEmailSign.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtEmailSign.setText("Email");
        jPanel1.add(txtEmailSign, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 160, 318, 30));

        txtPasswordSign.setBackground(new java.awt.Color(255, 243, 224));
        txtPasswordSign.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPasswordSign.setText("Password");
        jPanel1.add(txtPasswordSign, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, 318, 30));

        btnCreateAccount.setBackground(new java.awt.Color(255, 242, 196));
        btnCreateAccount.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCreateAccount.setText("Create account");
        btnCreateAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateAccountActionPerformed(evt);
            }
        });
        jPanel1.add(btnCreateAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 340, 250, 30));

        cbxShowPassword.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbxShowPassword.setForeground(new java.awt.Color(255, 255, 255));
        cbxShowPassword.setText("Show Password");
        cbxShowPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxShowPasswordActionPerformed(evt);
            }
        });
        jPanel1.add(cbxShowPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Login");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 50, 20));

        txtPasswordConfirm.setBackground(new java.awt.Color(255, 243, 224));
        txtPasswordConfirm.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPasswordConfirm.setText("Confirm Password");
        jPanel1.add(txtPasswordConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, 320, 30));

        cbxShowPasswordConfirm.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbxShowPasswordConfirm.setForeground(new java.awt.Color(255, 255, 255));
        cbxShowPasswordConfirm.setText("Show Password");
        cbxShowPasswordConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxShowPasswordConfirmActionPerformed(evt);
            }
        });
        jPanel1.add(cbxShowPasswordConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 243, 224));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javabeansmanagementsystem/images/image-signup.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 340, 210));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 330, 320));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 769, 407));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameSignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameSignActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameSignActionPerformed

   /** Event handler untuk checkbox "Show Password".
    * Jika checkbox dicentang, maka isi password akan ditampilkan (tidak disensor).
    * Jika tidak dicentang, isi password disembunyikan kembali menggunakan karakter ●.
    * Password hanya ditampilkan atau disembunyikan jika isinya bukan teks placeholder "Password".
    * 
    * @param evt Event yang dipicu saat checkbox di-klik.
    */
    private void cbxShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxShowPasswordActionPerformed
        // TODO add your handling code here:
        String pass = new String(txtPasswordSign.getPassword());
        if (cbxShowPassword.isSelected()) {
            if (!pass.equals("Password")) {
                txtPasswordSign.setEchoChar((char) 0); // tampilkan isi password
            }
        } else {
            if (!pass.equals("Password")) {
                txtPasswordSign.setEchoChar('●'); // sembunyikan isi password
            }
        }
    }//GEN-LAST:event_cbxShowPasswordActionPerformed
    
// Event handler tombol "Login" diklik
    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        new FormLogin().setVisible(true);// Buka form login
        this.dispose();// Tutup form signup
    }//GEN-LAST:event_jLabel3MouseClicked
    /**
     * Method ini dipanggil saat tombol "Create account" ditekan.
     * Melakukan validasi input pengguna, mengecek ketersediaan username/email,
     * dan menyimpan akun baru ke dalam database jika valid.
     * 
     * @param evt event klik tombol
     */
    private void btnCreateAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateAccountActionPerformed
        String name = txtNameSign.getText();
        String userName = txtUsernameSign.getText();
        String email = txtEmailSign.getText();
        String password = new String(txtPasswordSign.getPassword());
        String confirmPassword = new String(txtPasswordConfirm.getPassword());
        String passwordHash = passwordHash(password);
        
        // Validasi input kosong atau masih default
        if (name.equals("Name") || userName.equals("User Name") || email.equals("Email") || password.equals("Password") || confirmPassword.equals("Confrim Password") ||
            name.isEmpty() || userName.isEmpty() || email.isEmpty() || password.isEmpty()|| confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data can not be empty", "Warning", JOptionPane.ERROR_MESSAGE);
            return;
        }
                
        // Validasi nama hanya huruf
        if (!name.matches("[a-zA-Z\\s]+")) {
            JOptionPane.showMessageDialog(this, "Please enter a name Without numbers or symbols", "Warning", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validasi format email
        if (!email.matches("^[A-Za-z0-9._%+-]+@(gmail\\.com|yahoo\\.com|outlook\\.com)$")) {
            JOptionPane.showMessageDialog(this, "Oops! Please use a Gmail, Yahoo, or Outlook emails are accepted", "Warning", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //Validasi panjang password
        if(password.length() < 8){
            JOptionPane.showMessageDialog(this, "Password atleast 8 characters", "Warning", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validasi kecocokan password dan konfirmasi
        if(!password.equals(confirmPassword)){
            JOptionPane.showMessageDialog(this, "Please make sure the password and confirmation match", "Warning", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Proses koneksi dan simpan data
        try {
            SignUp signUp = new SignUp(name, userName, email, passwordHash);
            Connection conn = Koneksi.getConnection();

            // Cek apakah username sudah digunakan
            String cekUsername = "SELECT * FROM javabeans_akun WHERE username = ?";
            String cekEmail = "SELECT * FROM javabeans_akun WHERE email = ?";

            PreparedStatement psUserName = conn.prepareStatement(cekUsername);
            psUserName.setString(1, signUp.username);
            ResultSet rsUserName = psUserName.executeQuery();

            if (rsUserName.next()) {
                JOptionPane.showMessageDialog(this, "Sorry, that username is already taken. Please choose another one", "Warning", JOptionPane.ERROR_MESSAGE);
            } else {
                // Cek email jika username belum digunakan
                PreparedStatement psEmail = conn.prepareStatement(cekEmail);
                psEmail.setString(1, signUp.email);
                ResultSet rsEmail = psEmail.executeQuery();

            if (rsEmail.next()) {
                JOptionPane.showMessageDialog(this, "Email is already used", "Warning", JOptionPane.ERROR_MESSAGE);
            } else {
                // Simpan data akun baru ke tabel
                String insert = "INSERT INTO javabeans_akun (name_user, username, email, password_user) VALUES (?, ?, ?, ?)";
                PreparedStatement psInsert = conn.prepareStatement(insert);
                psInsert.setString(1, signUp.nameUser);
                psInsert.setString(2, signUp.username);
                psInsert.setString(3, signUp.email);
                psInsert.setString(4, signUp.passwordUser);

                psInsert.executeUpdate();// Eksekusi query
                JOptionPane.showMessageDialog(this, "Your account has been successfully created", "Sign Up", JOptionPane.INFORMATION_MESSAGE);
                
                // Reset input ke default
                txtNameSign.setText("Name");
                txtUsernameSign.setText("User Name");
                txtEmailSign.setText("Email");
                txtPasswordSign.setText("Password");
                txtPasswordConfirm.setText("Confirm Password");
                
                
                }
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
       
    }//GEN-LAST:event_btnCreateAccountActionPerformed
    /**
     * Melakukan hash pada password menggunakan algoritma SHA-256.
     * 
     * @param password Password asli yang ingin di-hash
     * @return Password dalam bentuk hash SHA-256
     */
    public static String passwordHash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found.");
        }
    }
    
   /**
    * Event handler untuk checkbox "Show Password".
    * Jika checkbox dicentang, maka isi password akan ditampilkan (tidak disensor).
    * Jika tidak dicentang, isi password disembunyikan kembali menggunakan karakter ●.
    * Password hanya ditampilkan atau disembunyikan jika isinya bukan teks placeholder "Password".
    * 
    * @param evt Event yang dipicu saat checkbox di-klik.
    */
    private void cbxShowPasswordConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxShowPasswordConfirmActionPerformed
        // TODO add your handling code here:
        String pass = new String(txtPasswordConfirm.getPassword());
        if (cbxShowPasswordConfirm.isSelected()) {
            if (!pass.equals("Confirm Password")) {
                txtPasswordConfirm.setEchoChar((char) 0); // tampilkan isi password
            }
        } else {
            if (!pass.equals("Confirm Password")) {
                txtPasswordConfirm.setEchoChar('●'); // sembunyikan isi password
            }
        }
    }//GEN-LAST:event_cbxShowPasswordConfirmActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormSignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormSignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormSignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormSignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormSignUp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateAccount;
    private javax.swing.JCheckBox cbxShowPassword;
    private javax.swing.JCheckBox cbxShowPasswordConfirm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtEmailSign;
    private javax.swing.JTextField txtNameSign;
    private javax.swing.JPasswordField txtPasswordConfirm;
    private javax.swing.JPasswordField txtPasswordSign;
    private javax.swing.JTextField txtUsernameSign;
    // End of variables declaration//GEN-END:variables
}
