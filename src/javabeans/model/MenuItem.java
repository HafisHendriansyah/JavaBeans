/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package javabeans.model;

/**
 * Interface MenuItem
 * Antarmuka untuk mewakili objek menu umum dalam sistem. Digunakan untuk menyatukan
 * perilaku berbagai jenis item menu seperti Coffee, Food, Snack, NonCoffee, dll.
 *
 * Interface ini menjamin bahwa setiap implementasi akan memiliki:
 *   ID unik
 *   Kategori
 *   Nama item
 *   Gambar dalam bentuk byte[]
 * 
 * Interface ini penting untuk keperluan polimorfisme saat menampilkan atau mengelola daftar menu.
 *
 * @author alfar
 */
public interface MenuItem {
    
    /**
     * Mengembalikan ID unik item menu.
     * @return ID item
     */
    int getId();
        
    /**
     * Mengembalikan kategori dari item menu (misal: Coffee, Snack, dll).
     * @return kategori item
     */
    String getKategori();
        
    /**
     * Mengembalikan nama item menu.
     * @return nama item
     */
    String getNama();
        
    /**
     * Mengembalikan harga item menu.
     * @return harga item
     */
    int getHarga();
        
    /**
     * Mengembalikan gambar item menu dalam bentuk array byte.
     * @return byte array gambar
     */
    byte[] getGambar();
}
