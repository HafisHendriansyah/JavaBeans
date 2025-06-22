/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabeans.model;

/**
 * Kelas NonCoffee
 * Kelas turunan dari AllMenuItems yang merepresentasikan menu dengan kategori "Non-Coffee".
 * Digunakan untuk membedakan jenis menu non-kopi dari item lainnya.
 *
 * Contoh menu: teh, susu, jus, dll.
 *
 * @author alfar
 */
public class NonCoffee extends AllMenuItems{
        
    /**
     * Konstruktor untuk objek NonCoffee.
     * 
     * @param id ID menu
     * @param nama Nama menu
     * @param harga Harga menu
     * @param gambar Gambar menu dalam bentuk byte array
     */
    public NonCoffee(int id, String nama, int harga, byte[] gambar) {
        super(id, nama, harga, gambar);
    }
    
    /**
     * Mengembalikan kategori menu.
     * 
     * @return String "Non-Coffee"
     */
    @Override
    public String getKategori() {
        return "Non-Coffee";
    }
}
