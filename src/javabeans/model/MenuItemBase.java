/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabeans.model;

/**
 *
 * @author alfar
 */
public abstract class MenuItemBase implements MenuItem {
    protected int id;
    protected String nama;
    protected int harga;
    protected String kategori;
    protected byte[] gambar;
    
    public MenuItemBase (int id, String nama, String kategori, int harga, byte[] gambar){
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.gambar = gambar;
        
    }
    
    @Override
    public int getId(){
        return id;
    }
    
    @Override
    public byte[] getGambar(){
        return gambar;
    }
    
    @Override
    public String getNama(){
        return nama;
    }
    
    @Override
    public String getKategori(){
        return kategori;
    }
    
    @Override
    public int getHarga(){
        return harga;
    }
    
    public String formatHarga(){
        return "Rp + " + harga;
    }
    
    
}
