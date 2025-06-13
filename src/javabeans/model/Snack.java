/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabeans.model;

/**
 *
 * @author alfar
 */
public class Snack implements MenuItem {
    private String name;
    private double price;
    
    public Snack(String name, double price){
        this.name = name;
        this.price = price;
    }
    
    @Override
    public String getName(){
        return name;
    }
    
    @Override
    public double getPrice(){
        return price;
    }
    
    @Override
    public String getCategory(){
        return "Snack";
    }
}
