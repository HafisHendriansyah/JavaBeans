/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabeans.model;

/**
 * Kelas MenuItemImpl
 * Implementasi konkret dari interface MenuItem. Kelas ini digunakan untuk
 * membuat objek menu dinamis dengan nilai kategori, nama, harga, dan gambar
 * yang dapat ditentukan langsung saat pembuatan objek.
 *
 * Cocok digunakan saat data menu berasal dari sumber fleksibel (misalnya hasil filter, input user, dsb).
 *
 * @author alfar
 */
public class MenuItemImpl implements MenuItem {
    private int id;
    private String nama, kategori;
    private int harga;
    private byte[] gambar;
    
    /**
     * Konstruktor MenuItemImpl
     * 
     * @param id ID menu
     * @param nama Nama menu
     * @param kategori Kategori menu
     * @param harga Harga menu
     * @param gambar Gambar dalam bentuk byte array
     */
    public MenuItemImpl(int id, String nama, String kategori, int harga, byte[] gambar){
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.gambar = gambar;
    }
    
    /** {@inheritDoc} */
    @Override
    public int getId() {return id;}
    
    /** {@inheritDoc} */
    @Override
    public String getNama() { return nama; }
    
    /** {@inheritDoc} */
    @Override
    public String getKategori() { return kategori; }
    
    /** {@inheritDoc} */
    @Override
    public int getHarga() { return harga; }
    
    /** {@inheritDoc} */
    @Override
    public byte[] getGambar() { return gambar; }
}
