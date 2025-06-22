/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabeans.model;

/**
 * Kelas AllMenuItems
 * Kelas implementasi dari interface MenuItem yang mewakili item menu dengan kategori "General".
 * Digunakan sebagai fallback/default ketika kategori menu tidak cocok dengan kategori spesifik lain.
 *
 * Menyimpan data ID, nama menu, harga, dan gambar dalam bentuk byte array.
 *
 * @author alfar
 */
public class AllMenuItems implements MenuItem {
    protected int id;
    protected String nama;
    protected int harga;
    protected byte[] gambar;
    
    /**
     * Konstruktor untuk AllMenuItems.
     * 
     * @param id ID menu
     * @param nama Nama menu
     * @param harga Harga menu
     * @param gambar Gambar menu dalam bentuk byte array
     */
    public AllMenuItems(int id, String nama, int harga, byte[] gambar) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.gambar = gambar;
    }
        
    /**
     * Mengembalikan ID menu.
     * @return ID menu
     */
     @Override
    public int getId() {
        return id;
    }
    
    /**
     * Mengembalikan nama menu.
     * @return nama menu
     */
    @Override
    public String getNama() {
        return nama;
    }
    
    /**
     * Mengembalikan harga menu.
     * @return harga menu
     */
    @Override
    public int getHarga() {
        return harga;
    }
    
    /**
     * Mengembalikan kategori menu, dalam kasus ini selalu "General".
     * @return kategori menu "General"
     */
    @Override
    public String getKategori() {
        return "General";
    }
    
    /**
     * Mengembalikan gambar menu dalam bentuk byte array.
     * @return gambar menu
     */
    @Override
    public byte[] getGambar() {
        return gambar;
    }
}
