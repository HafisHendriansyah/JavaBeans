/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javabeans.homepage;

import javabeans.login.FormLogin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javabeans.addmenu.AddMenuFrame;
import javabeans.makeorder.MakeOrderFrame;
import javabeans.seeallmenu.MenuItemsFrame;
import javabeans.seetransaction.OrderFrame;
import javax.swing.JOptionPane;

/**
 * Kelas HomePageFrame
 * Kelas ini merupakan tampilan utama (homepage) setelah pengguna berhasil login.
 * Menampilkan informasi penting seperti:
 * - Pendapatan hari ini
 * - Jumlah pesanan hari ini
 * - Menu terlaris hari ini
 * Selain itu juga menyediakan menu navigasi ke halaman lain:
 * - Buat Pesanan
 * - Tambah Menu
 * - Lihat Transaksi
 * - Lihat Semua Menu
 * - Logout
 * 
 * Kelas ini menggunakan data user login untuk menampilkan informasi personalisasi.
 * 
 * @author alfar
 */
public class HomePageFrame extends javax.swing.JFrame {

    private int idUserLogin; // Menyimpan ID pengguna yang login
    /**
     * Creates new form HomePage
     */
  
    /**
     * Konstruktor utama dari halaman HomePageFrame.
     * Menginisialisasi tampilan dan data statistik dengan memanggil fungsi:
     * tampilkanNamaUser(), tampilkanPendapatanHariIni(), tampilkanJumlahPesananHariIni(), tampilkanMenuTerlaris()
     * 
     * @param idUser ID pengguna yang login.
     */
    public HomePageFrame(int idUser) {
        initComponents(); // Inisialisasi komponen GUI
        setLocationRelativeTo(null); // Letakkan jendela di tengah layar
        setResizable(false); // Nonaktifkan resize
        this.idUserLogin = idUser; // Simpan ID pengguna login
        
        // Tampilkan data dinamis dari database
        tampilkanNamaUser();
        tampilkanPendapatanHariIni();
        tampilkanJumlahPesananHariIni();
        tampilkanMenuTerlaris();
    }
    
    /**
     * Menampilkan nama pengguna yang sedang login di label namaUserLabel.
     * Data diambil dari database tabel javabeans_akun berdasarkan id_user.
     */
    // Menampilkan nama pengguna berdasarkan ID login
    public void tampilkanNamaUser(){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javabeans", "root", "");
            String query = "SELECT name_user FROM javabeans_akun WHERE id_user = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, idUserLogin);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                String nama = rs.getString("name_user");
                namaUserLabel.setText(nama);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
        
    /**
     * Mengambil dan menampilkan total pendapatan hari ini dari database.
     * Data diambil dari kolom total_harga di tabel transaksi yang tanggalnya adalah hari ini.
     */
    public void tampilkanPendapatanHariIni(){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javabeans", "root", "");
            String query = "SELECT SUM(total_harga) AS pendapatan_hari_ini FROM transaksi WHERE tanggal_transaksi = CURDATE()";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                int pendapatan = rs.getInt("pendapatan_hari_ini");
                lblPendapatan.setText("Rp " + pendapatan);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
        
    /**
     * Mengambil dan menampilkan jumlah pesanan (jumlah item) yang terjual hari ini.
     * Data diperoleh dari tabel order_detail yang di-join dengan transaksi berdasarkan tanggal hari ini.
     */
    public void tampilkanJumlahPesananHariIni(){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javabeans", "root", "");
            String query = "SELECT SUM(o.jumlah) AS total_pesanan FROM `order_detail` o " +
               "JOIN transaksi t ON o.id_transaksi = t.id_transaksi " +
               "WHERE t.tanggal_transaksi = CURDATE()";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                int total = rs.getInt("total_pesanan");
                lblJumlahPesanan.setText(String.valueOf(total));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
        
    /**
     * Mengambil dan menampilkan nama menu yang paling banyak dipesan hari ini (menu terlaris).
     * Data diperoleh dari tabel order_detail yang digabung dengan javabeans_menu dan transaksi.
     */
    public void tampilkanMenuTerlaris(){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javabeans", "root", "");
            String query = "SELECT m.nama_menu, SUM(o.jumlah) AS total_terjual FROM `order_detail` o " +
               "JOIN javabeans_menu m ON o.id_menu = m.id " +
               "JOIN transaksi t ON o.id_transaksi = t.id_transaksi " +
               "WHERE t.tanggal_transaksi = CURDATE() " +
               "GROUP BY o.id_menu ORDER BY total_terjual DESC LIMIT 1";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                String namaMenu = rs.getString("nama_menu");
                lblMenuTerlaris.setText(namaMenu);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblPendapatan = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblJumlahPesanan = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblMenuTerlaris = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        namaUserLabel = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home Page");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(78, 52, 46));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 243, 224));
        jLabel1.setText("JavaBeans.");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 57, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 243, 224));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel2.setText("Welcome, ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 53, -1, -1));

        jPanel3.setBackground(new java.awt.Color(78, 52, 46));

        lblPendapatan.setBackground(new java.awt.Color(255, 243, 224));
        lblPendapatan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblPendapatan.setForeground(new java.awt.Color(255, 243, 224));
        lblPendapatan.setText("jLabel3");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 243, 224));
        jLabel4.setText("Today's Income");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel4))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(lblPendapatan)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel4)
                .addGap(48, 48, 48)
                .addComponent(lblPendapatan)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 102, -1, 180));

        jPanel4.setBackground(new java.awt.Color(78, 52, 46));

        lblJumlahPesanan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblJumlahPesanan.setForeground(new java.awt.Color(255, 243, 224));
        lblJumlahPesanan.setText("jLabel3");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 243, 224));
        jLabel5.setText("Orders Sold Today");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblJumlahPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel5)
                .addGap(43, 43, 43)
                .addComponent(lblJumlahPesanan)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(249, 102, -1, 179));

        jPanel6.setBackground(new java.awt.Color(78, 52, 46));

        lblMenuTerlaris.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMenuTerlaris.setForeground(new java.awt.Color(255, 243, 224));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 243, 224));
        jLabel6.setText("Today's Best Seller");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javabeansmanagementsystem/images/005-star.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel14)))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMenuTerlaris, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblMenuTerlaris, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 102, -1, 179));

        jPanel7.setBackground(new java.awt.Color(78, 52, 46));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 243, 224));
        jLabel7.setText("Make Order");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javabeansmanagementsystem/images/001-grocery-store.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(30, 30, 30))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(31, 31, 31))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 299, 270, -1));

        jPanel8.setBackground(new java.awt.Color(78, 52, 46));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 243, 224));
        jLabel8.setText("Add New Menu");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javabeansmanagementsystem/images/002-add.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(25, 25, 25))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(31, 31, 31))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel11)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 300, 280, 90));

        jPanel9.setBackground(new java.awt.Color(78, 52, 46));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 243, 224));
        jLabel9.setText("See Transaction");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javabeansmanagementsystem/images/003-transactional-data.png"))); // NOI18N
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(15, 15, 15))
        );

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 409, 270, -1));

        jPanel10.setBackground(new java.awt.Color(78, 52, 46));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 243, 224));
        jLabel10.setText("See All Menu");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javabeansmanagementsystem/images/004-fast-food.png"))); // NOI18N
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(62, 62, 62))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(17, 17, 17))
        );

        jPanel2.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(341, 409, 280, 141));

        namaUserLabel.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        namaUserLabel.setText("jLabel3");
        jPanel2.add(namaUserLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 53, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javabeansmanagementsystem/images/gambar biji kopi.png"))); // NOI18N
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 280, 290, 360));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javabeansmanagementsystem/images/gambar biji kopi.png"))); // NOI18N
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, -10, 290, 140));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 698, 632));

        jButton4.setBackground(new java.awt.Color(161, 136, 127));
        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("Logout");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 97, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Aksi saat panel "Add New Menu" diklik.
     * Akan membuka AddMenuFrame dan menutup halaman ini.
     * 
     * @param evt Mouse click event pada panel.
     */
    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        // TODO add your handling code here:
        new AddMenuFrame(idUserLogin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel8MouseClicked
   
    /**
     * Aksi saat panel "Make Order" diklik.
     * 
     * @param evt event klik mouse
     */
    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        // TODO add your handling code here:
        new MakeOrderFrame(idUserLogin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel7MouseClicked
    
    /**
     * Aksi ketika tombol Logout ditekan.
     * Akan menutup halaman home dan membuka kembali FormLogin.
     * Juga menampilkan pesan logout sukses.
     * 
     * @param evt Event dari tombol logout.
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        new FormLogin().setVisible(true);
        this.dispose();
        JOptionPane.showMessageDialog(this, "Logout Successfully", "Message",JOptionPane.INFORMATION_MESSAGE );
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * Aksi saat label "See All Menu" diklik.
     * 
     * @param evt event klik mouse
     */
    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        new MenuItemsFrame(idUserLogin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel10MouseClicked
    
    /**
     * Aksi saat label "See Transaction" diklik.
     * 
     * @param evt event klik mouse
     */
    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        new OrderFrame(idUserLogin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel9MouseClicked
    
    /**
     * Aksi saat ikon menu diklik.
     * 
     * @param evt event klik mouse
     */
    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
        new MenuItemsFrame(idUserLogin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked
    
    /**
     * Aksi saat ikon transaksi diklik.
     * 
     * @param evt event klik mouse
     */
    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        new OrderFrame(idUserLogin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel12MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(HomePageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(HomePageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(HomePageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(HomePageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new HomePageFrame(idUserLogin).setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JLabel lblJumlahPesanan;
    private javax.swing.JLabel lblMenuTerlaris;
    private javax.swing.JLabel lblPendapatan;
    private javax.swing.JLabel namaUserLabel;
    // End of variables declaration//GEN-END:variables
}
