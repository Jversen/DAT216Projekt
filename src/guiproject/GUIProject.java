/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiproject;

import java.awt.Image;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;
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
    public List<Product> products;
    public List<ProductCard> productCards;
    /**
     * @param args the command line arguments
     */
        public ImageIcon getImage(Product prod){
        
            return iMDH.getImageIcon(prod);
    }
    
    public void doSearch(String s){
           this.products = iMDH.findProducts(s);
           
           for(int i=0; i<products.size(); i++){
               productCards.add(new ProductCard(products.get(i)));
           }
    }
    
    public static void main(String[] args) {
        IMatFrame app = new IMatFrame();
        app.setVisible(true);
        
        
        // TODO code application logic here
    }
    
}
