/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiproject;

import java.util.ArrayList;
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
    ShoppingCart sc = iMDH.getShoppingCart();
    
    public List<Product> products = new ArrayList<Product>();
    public List<ProductCard> productCards = new ArrayList<ProductCard>();
    public List <ShoppingItem> cartContents = new ArrayList<ShoppingItem>();
        
    
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
    
    /**
     * Work in progress... 
     */
    public void addToCart(ProductCard pc){
        double amount = pc.getSpinnerValue();
        
//        for(int i=0; i<amount; i++){
//            cart.add(pc.getProduct());
            System.out.println("amount: " + amount + ", produkt: " + pc.getProduct());
            sc.addProduct(pc.getProduct());
           //  System.out.print(pc.getProduct().getName());
//        }
            cartContents.clear();
            cartContents.addAll(sc.getItems());
            
            
            
//        for(int i=0; i<cartContents.size(); i++){
//         System.out.print(cartContents.get(i).getProduct());  
//         System.out.print(cartContents.get(i).getAmount() + " amount");
//         System.out.print(cartContents.get(i).getTotal() + " total");  
//        }
    }
    
    public void listCatProds(String s){
        this.products = iMDH.getProducts(ProductCategory.valueOf(s));
         this.productCards.clear();
           this.productCards.removeAll(productCards);

           for(int i=0; i<products.size(); i++){
               productCards.add(new ProductCard(products.get(i)));
           }
           
        System.out.println(iMDH.getProducts(ProductCategory.valueOf(s)));
        
    }
    
    public static void main(String[] args) {
        
        
        IMatFrame app = new IMatFrame();
        app.setVisible(true);
        
        
        
        
 
        
        
        // TODO code application logic here
    }
    
}
