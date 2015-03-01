/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiproject;

import java.util.ArrayList;
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
    public List<Product> products = new ArrayList<Product>();
    public List<ProductCard> productCards = new ArrayList<ProductCard>();
    public List <Product> cart = new ArrayList<Product>();
    /**
     * @param args the command line arguments
     */
        public ImageIcon getImage(Product prod){
        
            return iMDH.getImageIcon(prod,100,100);
    }
    
    public void doSearch(String s){
           this.products = iMDH.findProducts(s);
           this.productCards.clear();
           this.productCards.removeAll(productCards);

           for(int i=0; i<products.size(); i++){
               productCards.add(new ProductCard(products.get(i)));
           }
           
    }
    
        
    public void addToCart(ProductCard pc){
        int value = pc.getSpinnerValue();
        
        for(int i=0; i<value; i++){
            cart.add(pc.getProduct());
           //  System.out.print(pc.getProduct().getName());
        }
        for(int i=0; i<cart.size(); i++){
         System.out.print(cart.get(i).getName());   
        }
    }
    
    public static void main(String[] args) {
        IMatFrame app = new IMatFrame();
        app.setVisible(true);
        
        
        // TODO code application logic here
    }
    
}
