/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabeans.view;

import javax.swing.*;
import javabeans.model.MenuItem;
import java.util.List;

/**
 *
 * @author alfar
 */
public class MenuPanel extends JPanel{
    public MenuPanel(List<MenuItem> items, String category) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder(category));
        
        for (MenuItem item : items){
            if (item.getCategory().equalsIgnoreCase(category)) {
                JLabel label = new JLabel(item.getName() + " - Rp" + item.getPrice());
                this.add(label);
            }
        }   
    }
}
