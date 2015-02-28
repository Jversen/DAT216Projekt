/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiproject;

import java.util.List;
import javax.swing.*;
import se.chalmers.ait.dat215.project.*;



/**
 *
 * @author Johan
 */
public class GUIProject{
    
    /**
     * Skapar instans av IMatDataHandler och lista för produkter.
     */
    IMatDataHandler iMDH = IMatDataHandler.getInstance();
    List<Product> products;
    
    
    /**
     * @param args the command line arguments
     */
    
    public void doSearch(String s){
        this.products = iMDH.findProducts(s);
    }
    public void guiAdd(JPanel p, JPanel q){
        p.add(q);
    }
    public void guiAdd(JPanel p, JLabel q){
        p.add(q);
    }
    
    public static void main(String[] args) {
        IMatFrame app = new IMatFrame();
        CategoryPanel catPanel = new CategoryPanel();
        app.add(catPanel);
        app.setVisible(true);
        
        
        
        
 
        
        
        // TODO code application logic here
    }
    
}
