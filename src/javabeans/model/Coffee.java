/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabeans.model;

/**
 *
 * @author alfar
 */
public class Coffee implements MenuItem{
    private String name;
    private double price;
    
    public Coffee (String name, double price){
        this.name = name;
        this.price = price;
    }
    
    public String getName() {
        return name;
        
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getCategory(){
        return "Coffee";
    }
}
