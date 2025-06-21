/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabeans.view;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BorderFactory;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;

import javabeans.model.MenuItem;

/**
 *
 * @author alfar
 */
public class ItemPanel extends JPanel {
    public ItemPanel (MenuItem item){
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(150, 100));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        JLabel nameLabel = new JLabel(item.getNama());
        JLabel priceLabel = new JLabel("Rp " + item.getHarga());
        JButton orderButton = new JButton("Pesan");
        
        add(nameLabel, BorderLayout.NORTH);
        add(priceLabel, BorderLayout.CENTER);
        add(orderButton, BorderLayout.SOUTH);
    }
}
