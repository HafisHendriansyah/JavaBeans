/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabeans.model;

/**
 * Kelas Coffee
 * Kelas turunan dari AllMenuItems yang merepresentasikan menu dengan kategori "Coffee".
 * Digunakan untuk membedakan jenis menu kopi dari item lainnya.
 *
 * @author alfar
 */
public class Coffee extends AllMenuItems {

    /**
     * Konstruktor untuk objek Coffee.
     * 
     * @param id ID menu
     * @param nama Nama menu
     * @param harga Harga menu
     * @param gambar Gambar menu dalam bentuk byte array
     */
    public Coffee(int id, String nama, int harga, byte[] gambar) {
        super(id, nama, harga, gambar);
    }

    /**
     * Mengembalikan kategori menu.
     * 
     * @return String "Coffee"
     */
    @Override
    public String getKategori() {
        return "Coffee";
    }
}