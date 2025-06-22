/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabeans.model;

/**
 * Kelas Food
 * <p>
 * Kelas turunan dari AllMenuItems yang merepresentasikan menu dengan kategori "Food".
 * Digunakan untuk membedakan jenis menu makanan dari item lainnya.
 * </p>
 *
 * @author alfar
 */
public class Food extends AllMenuItems {

    /**
     * Konstruktor untuk objek Food.
     * 
     * @param id ID menu
     * @param nama Nama menu
     * @param harga Harga menu
     * @param gambar Gambar menu dalam bentuk byte array
     */
    public Food(int id, String nama, int harga, byte[] gambar) {
        super(id, nama, harga, gambar);
    }

    /**
     * Mengembalikan kategori menu.
     * 
     * @return String "Food"
     */
    @Override
    public String getKategori() {
        return "Food";
    }
}

