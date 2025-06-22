/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javabeans.makeorder;

import javabeans.homepage.HomePageFrame;
import java.awt.GridLayout;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javabeans.model.MenuItem;
import javabeans.model.MenuItemImpl;
import java.util.ArrayList;
import java.util.List;
import javabeans.makeorder.KeranjangCard.KeranjangListener;
import javax.swing.JOptionPane;
import javabeans.model.MenuItem;
import javabeans.model.AllMenuItems;
import javabeans.model.Coffee;
import javabeans.model.NonCoffee;
import javabeans.model.Snack;
import javabeans.model.Food;


/**
 * Kelas MakeOrderFrame
 * JFrame yang digunakan untuk membuat pesanan. Menampilkan daftar menu dari database,
 * memungkinkan pengguna memilih menu, memasukkan ke keranjang, menghitung total harga,
 * dan menyimpan transaksi ke dalam database.
 * Fitur utama:
 * - Menampilkan menu berdasarkan kategori (coffee, non-coffee, snack, food)
 * - Keranjang belanja dengan item yang dapat dihapus
 * - Perhitungan total harga
 * - Simpan transaksi ke tabel transaksi dan order_detail
 * - Kembali ke HomePageFrame
 * @author alfar
 */
public class MakeOrderFrame extends javax.swing.JFrame {
    private List<KeranjangCard> daftarKeranjang = new ArrayList<>();
    private int idUserLogin;
    
    /**
     * Creates new form TambahMenu
     * Konstruktor
     * @param idUser ID user yang sedang login
     */
    public MakeOrderFrame(int idUser){
        initComponents();
        this.idUserLogin = idUser;
        displayMenuDatabase();
    }
    
    /**
     * Menampilkan semua menu dari database ke panel menu.
     */
    private void displayMenuDatabase(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javabeans", "root", "");
            String query = "SELECT id, nama_menu, category_menu, harga_menu, gambar_menu FROM javabeans_menu";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery(query);
            
            jPanel1.removeAll();
            jPanel1.setLayout(new GridLayout(0, 2, 20, 20));
            
            while (rs.next()){
                int id = rs.getInt("id");
                String nama = rs.getString("nama_menu");
                String kategori = rs.getString("category_menu");
                int harga = rs.getInt("harga_menu");
                byte[] imgBytes = rs.getBytes("gambar_menu");
                
            MenuItem item;

            switch (kategori.toLowerCase()) {
                case "coffee":
                    item = new Coffee(id, nama, harga, imgBytes);
                    break;
                case "non-coffee":
                case "noncoffee":
                    item = new NonCoffee(id, nama, harga, imgBytes);
                    break;
                case "snack":
                    item = new Snack(id, nama, harga, imgBytes);
                    break;
                case "food":
                    item = new Food(id, nama, harga, imgBytes);
                    break;
                default:
                    item = new AllMenuItems(id, nama, harga, imgBytes); // fallback
                    break;
                }                
                MenuCard card = new MenuCard(item);
                
                card.setCartListener((itemNama, itemHarga, itemJumlah) -> {
                    boolean found = false;
                    
                    for (KeranjangCard k : daftarKeranjang){
                        if(k.getNama().equals(itemNama)){
                            int jumlahBaru = k.getJumlah() + itemJumlah;
                            k.setJumlah(jumlahBaru);
                            k.updateView();
                            found = true;
                            break;
                        }
                    }
                    
                    if (!found) {
                        KeranjangCard keranjang = new KeranjangCard(itemNama, itemHarga, itemJumlah);
                        
                        keranjang.setKeranjangListener(new KeranjangListener(){
                            @Override
                            public void onItemRemoved(KeranjangCard cardYangDihapus) {
                                daftarKeranjang.remove(cardYangDihapus);
                                panelKeranjang.remove(cardYangDihapus);
                                panelKeranjang.revalidate();
                                panelKeranjang.repaint();
                                hitungTotalHarga();
                            }
                        });
                        daftarKeranjang.add(keranjang);
                        panelKeranjang.add(keranjang);
                        
                    }
                   
                panelKeranjang.revalidate();
                panelKeranjang.repaint();
                
                hitungTotalHarga();
            });
                jPanel1.add(card);
            }
            
            rs.close();
            pst.close();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
        
    /**
     * Menghitung total harga dari seluruh item dalam keranjang.
     * Menampilkan total di label totalHargaLabel.
     */
    private void hitungTotalHarga() {
        int total = 0;
        for (KeranjangCard k : daftarKeranjang){
             total += k.getSubtotal();
        }
        totalHargaLabel.setText("Total: Rp " + total);
    }
        
    /**
     * Menampilkan menu berdasarkan kategori tertentu.
     *
     * @param kategoriFilter kategori menu yang ingin difilter
     */
    private void filterMenuByCategory(String kategoriFilter){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javabeans", "root", "");
            String query = "SELECT id, nama_menu, category_menu, harga_menu, gambar_menu FROM javabeans_menu WHERE category_menu = ? ";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, kategoriFilter);
            ResultSet rs = pst.executeQuery();
            
            jPanel1.removeAll();
            jPanel1.setLayout(new GridLayout(0, 2, 20, 20));
            
            while (rs.next()){
                int id = rs.getInt("id");
                String nama = rs.getString("nama_menu");
                String kategori = rs.getString("category_menu");
                int harga = rs.getInt("harga_menu");
                byte[] imgBytes = rs.getBytes("gambar_menu");
                
                MenuItem item;

            switch (kategori.toLowerCase()) {
                case "coffee":
                    item = new Coffee(id, nama, harga, imgBytes);
                    break;
                case "non-coffee":
                case "noncoffee":
                    item = new NonCoffee(id, nama, harga, imgBytes);
                    break;
                case "snack":
                    item = new Snack(id, nama, harga, imgBytes);
                    break;
                case "food":
                    item = new Food(id, nama, harga, imgBytes);
                    break;
                default:
                    item = new AllMenuItems(id, nama, harga, imgBytes); // fallback
                    break;
                }
                MenuCard card = new MenuCard(item);
                
                card.setCartListener((itemNama, itemHarga, itemJumlah) -> {
                     KeranjangCard keranjang = new KeranjangCard(itemNama, itemHarga, itemJumlah);
                     daftarKeranjang.add(keranjang);
                     panelKeranjang.add(keranjang);
                     panelKeranjang.revalidate();
                     panelKeranjang.repaint();
                     hitungTotalHarga();
                });
                
                jPanel1.add(card);
            }
            
            rs.close();
            pst.close();
            conn.close();
            
            jPanel1.revalidate();
            jPanel1.repaint();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
        
    /**
     * Menyimpan transaksi tunggal ke tabel transaksi.
     *
     * @param idMenu ID menu yang dipesan
     * @param jumlah jumlah yang dipesan
     * @param hargaSatuan harga per item
     */
    public void simpanTransaksi(int idMenu, int jumlah, double hargaSatuan){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javabeans", "root", "");
            String query = "INSERT INTO transaksi (id_menu, jumlah, total_harga, tanggal_transaksi) VALUES (?, ?, ?, CURDATE())";
            PreparedStatement pst = conn.prepareStatement(query);
            
            double totalHarga = hargaSatuan * jumlah;
            
            pst.setInt(1, idMenu);
            pst.setInt(2, jumlah);
            pst.setDouble(3, totalHarga);
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Success! Your order has been saved");
        }catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unable to save the transaction.");
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

        jPanel8 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        buttonSemua = new javax.swing.JButton();
        buttonKatKopi = new javax.swing.JButton();
        buttonKatNon = new javax.swing.JButton();
        buttonKatSnack = new javax.swing.JButton();
        buttonKatFood = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        panelKeranjang = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        totalHargaLabel = new javax.swing.JLabel();
        btnCheckOut = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add Order");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setBackground(new java.awt.Color(78, 52, 46));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonSemua.setText("All");
        buttonSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSemuaActionPerformed(evt);
            }
        });
        jPanel15.add(buttonSemua, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 87, -1, -1));

        buttonKatKopi.setText("Coffee");
        buttonKatKopi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonKatKopiActionPerformed(evt);
            }
        });
        jPanel15.add(buttonKatKopi, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 87, -1, -1));

        buttonKatNon.setText("Non-Coffee");
        buttonKatNon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonKatNonActionPerformed(evt);
            }
        });
        jPanel15.add(buttonKatNon, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 87, -1, -1));

        buttonKatSnack.setText("Snack");
        buttonKatSnack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonKatSnackActionPerformed(evt);
            }
        });
        jPanel15.add(buttonKatSnack, new org.netbeans.lib.awtextra.AbsoluteConstraints(314, 87, -1, -1));

        buttonKatFood.setText("Food");
        buttonKatFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonKatFoodActionPerformed(evt);
            }
        });
        jPanel15.add(buttonKatFood, new org.netbeans.lib.awtextra.AbsoluteConstraints(404, 87, -1, -1));

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 31, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 243, 224));
        jLabel1.setText("Make Order");
        jPanel15.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(404, 17, -1, -1));

        getContentPane().add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 130));

        jPanel16.setBackground(new java.awt.Color(78, 52, 46));
        jPanel16.setPreferredSize(new java.awt.Dimension(200, 500));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelKeranjang.setBackground(new java.awt.Color(255, 243, 224));
        panelKeranjang.setLayout(new java.awt.GridLayout(0, 1, 10, 10));
        jPanel16.add(panelKeranjang, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 18, 319, 192));

        jPanel2.setBackground(new java.awt.Color(255, 243, 224));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(totalHargaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(totalHargaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jPanel16.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 236, -1, -1));

        btnCheckOut.setText("Checkout");
        btnCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckOutActionPerformed(evt);
            }
        });
        jPanel16.add(btnCheckOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 325, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javabeansmanagementsystem/images/gambar-biji-kopi-putih.png"))); // NOI18N
        jPanel16.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-270, 40, 510, 310));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javabeansmanagementsystem/images/gambar-biji-kopi-putih.png"))); // NOI18N
        jPanel16.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 410, 290));

        getContentPane().add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 96, 550, 504));

        jPanel17.setBackground(new java.awt.Color(255, 243, 224));
        jPanel17.setPreferredSize(new java.awt.Dimension(270, 400));

        jScrollPane1.setBackground(new java.awt.Color(255, 243, 224));

        jPanel1.setBackground(new java.awt.Color(255, 243, 224));
        jPanel1.setLayout(new java.awt.GridBagLayout());
        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(0, 1, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 480, 470));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Event handler tombol "Semua" ditekan. Menampilkan seluruh item menu.
     * 
     * @param evt event klik tombol "Semua"
     */
    private void buttonSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSemuaActionPerformed
        // TODO add your handling code here:
        displayMenuDatabase();
    }//GEN-LAST:event_buttonSemuaActionPerformed
    
    /**
     * Event handler tombol "Coffee" ditekan. Menampilkan item kategori coffee.
     * 
     * @param evt event klik tombol "Coffee"
     */
    private void buttonKatKopiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonKatKopiActionPerformed
        // TODO add your handling code here:
        filterMenuByCategory("Coffee");
    }//GEN-LAST:event_buttonKatKopiActionPerformed
    
    /**
     * Event handler tombol "Non-Coffee" ditekan. Menampilkan item kategori non-coffee.
     * 
     * @param evt event klik tombol "Non-Coffee"
     */
    private void buttonKatNonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonKatNonActionPerformed
        // TODO add your handling code here:
        filterMenuByCategory("Non-Coffee");
    }//GEN-LAST:event_buttonKatNonActionPerformed
    
    /**
     * Event handler tombol "Snack" ditekan. Menampilkan item kategori snack.
     * 
     * @param evt event klik tombol "Snack"
     */
    private void buttonKatSnackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonKatSnackActionPerformed
        // TODO add your handling code here:
        filterMenuByCategory("Snack");
    }//GEN-LAST:event_buttonKatSnackActionPerformed
    
    /**
     * Event handler tombol "Food" ditekan. Menampilkan item kategori food.
     * 
     * @param evt event klik tombol "Food"
     */
    private void buttonKatFoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonKatFoodActionPerformed
        // TODO add your handling code here:
        filterMenuByCategory("Food");
    }//GEN-LAST:event_buttonKatFoodActionPerformed
    
    /**
     * Event handler saat tombol "Checkout" ditekan.
     * Memproses dan menyimpan seluruh isi keranjang ke database.
     * Kosongkan keranjang setelah transaksi berhasil.
     */
    private void btnCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckOutActionPerformed
        // TODO add your handling code here:
         if (daftarKeranjang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "You haven't added any items!");
            return;
        }
        try{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javabeans", "root", "");
        
        double totalHargaTransaksi = 0;
        
        for (KeranjangCard item : daftarKeranjang){
            totalHargaTransaksi += item.getHarga() * item.getJumlah();
        }
        
        String insertTransaksi = "INSERT INTO transaksi (total_harga, tanggal_transaksi) VALUES (?, CURDATE())";
        PreparedStatement insertStmt = conn.prepareStatement(insertTransaksi, Statement.RETURN_GENERATED_KEYS);
        insertStmt.setDouble(1, totalHargaTransaksi);
        insertStmt.executeUpdate();
        
        ResultSet generatedKeys = insertStmt.getGeneratedKeys();
        int idTransaksi = 0;
        if (generatedKeys.next()) {
            idTransaksi = generatedKeys.getInt(1);
        }
        
        insertStmt.close();
        generatedKeys.close();
            
        for (KeranjangCard item : daftarKeranjang){
            String namaMenu = item.getNama();
            int jumlah = item.getJumlah();
            double hargaSatuan = item.getHarga();
            double total = hargaSatuan * jumlah;
            
            String getIdQuery = "SELECT id FROM javabeans_menu WHERE nama_menu = ?";
            PreparedStatement getIdStmt = conn.prepareStatement(getIdQuery);
            getIdStmt.setString(1, namaMenu);
            ResultSet rs = getIdStmt.executeQuery();
            
            if (rs.next()) {
                int idMenu = rs.getInt("id");

                String insertOrder = "INSERT INTO order_detail (id_transaksi, id_menu, jumlah, sub_total) VALUES (?, ?, ?, ?)";
                PreparedStatement orderStmt = conn.prepareStatement(insertOrder);
                orderStmt.setInt(1, idTransaksi);
                orderStmt.setInt(2, idMenu);
                orderStmt.setInt(3, jumlah);
                orderStmt.setDouble(4, total);
                orderStmt.executeUpdate();
                orderStmt.close();
            }
            rs.close();
            getIdStmt.close();
        }
            
        conn.close();
        JOptionPane.showMessageDialog(this, "Success! Transaction saved");
        
        daftarKeranjang.clear();
        panelKeranjang.removeAll();
        panelKeranjang.revalidate();
        panelKeranjang.repaint();
        totalHargaLabel.setText("Total: Rp 0");
        
        } catch (Exception e){
             e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to save transaction");
        }
        
    }//GEN-LAST:event_btnCheckOutActionPerformed
    
    /**
     * Event handler untuk tombol "Back".
     * Kembali ke HomePageFrame.
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new HomePageFrame(idUserLogin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(MakeOrderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MakeOrderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MakeOrderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MakeOrderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MakeOrderFrame().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCheckOut;
    private javax.swing.JButton buttonKatFood;
    private javax.swing.JButton buttonKatKopi;
    private javax.swing.JButton buttonKatNon;
    private javax.swing.JButton buttonKatSnack;
    private javax.swing.JButton buttonSemua;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelKeranjang;
    private javax.swing.JLabel totalHargaLabel;
    // End of variables declaration//GEN-END:variables
}