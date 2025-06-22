/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabeans.model;

/**
 * Kelas Snack
 * Kelas turunan dari AllMenuItems yang merepresentasikan menu dengan kategori "Snack".
 * Digunakan untuk membedakan jenis menu camilan dari item lainnya.
 *
 * Contoh menu: kentang goreng, donat, kue, dll.
 *
 * @author alfar
 */
public class Snack extends AllMenuItems{
    /**
     * Konstruktor untuk objek Snack.
     * 
     * @param id ID menu
     * @param nama Nama menu
     * @param harga Harga menu
     * @param gambar Gambar menu dalam bentuk byte array
     */
     public Snack(int id, String nama, int harga, byte[] gambar) {
        super(id, nama, harga, gambar);
    }
    
     /**
     * Mengembalikan kategori menu.
     * 
     * @return String "Snack"
     */
    @Override
    public String getKategori() {
        return "Snack";
    }
}
