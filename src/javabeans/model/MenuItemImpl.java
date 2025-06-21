/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabeans.model;

/**
 *
 * @author alfar
 */
public class MenuItemImpl implements MenuItem {
    private int id;
    private String nama, kategori;
    private int harga;
    private byte[] gambar;
    
    public MenuItemImpl(int id, String nama, String kategori, int harga, byte[] gambar){
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.gambar = gambar;
    }
    
    @Override
    public int getId() {return id;}
    
    @Override
    public String getNama() { return nama; }
    
    @Override
    public String getKategori() { return kategori; }
    
    @Override
    public int getHarga() { return harga; }
    
    @Override
    public byte[] getGambar() { return gambar; }
}
