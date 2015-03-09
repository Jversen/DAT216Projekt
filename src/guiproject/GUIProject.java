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

public class GUIProject{
    
    
    /**
     * Skapar instans av IMatDataHandler och lista för produkter.
     */
    IMatDataHandler iMDH = IMatDataHandler.getInstance();
    ShoppingCart sc = iMDH.getShoppingCart();
    //IMatFrame app;
    
    
    public List<Product> products = new ArrayList<Product>();
    public List<ProductCard> productCards = new ArrayList<ProductCard>();
    //public List <ShoppingItem> cartContents = new ArrayList<ShoppingItem>();
    public List <CartProdObject> cpo = new ArrayList<CartProdObject>();
    
    public List<Product> allProducts = new ArrayList<Product>();
    
    /**
     * @param args the command line arguments
     */
    
    
    public ImageIcon getImage100(Product prod){
        
            return iMDH.getImageIcon(prod,100,100);
    }
    public ImageIcon getImage50(Product prod){
        
            return iMDH.getImageIcon(prod,50,50);
    }
    
    public void createAllProducts(){
        allProducts = iMDH.getProducts();
        
    }
    
    public void doSearch(String s, IMatFrame imf){
        
           this.products = iMDH.findProducts(s);
           this.productCards.clear();
           
           for (int i = 0; i<products.size(); i++){
            for (int j = 0; j<imf.allProductCards.size(); j++){
//                System.out.println("imf allprdc j : " + imf.allProductCards.get(j).getProduct());
                   if (imf.allProductCards.get(j).getProduct() == products.get(i)){
                       productCards.add(imf.allProductCards.get(j));
                       
//                       System.out.println("productcards: " + imf.allProductCards.get(j).getProduct().getName());
                }
                               
            }
    }System.out.print("This is cpo size: "+cpo.size() +". This is the shoppingcart: "+ sc.getItems().size());
    }
//            this.products = iMDH.findProducts(s);
//           this.productCards.clear();
//           this.productCards.removeAll(productCards);
//
//           
//           for(int i=0; i<products.size(); i++){
//               
//               cpo.add(new CartProdObject(new ShoppingItem(products.get(i), 1.0), imf));
//           }
           
    
    
    /**
     * Work in progress... 
     */
    public void addToCart(Product p, int amount, IMatFrame imf){
        sc.addProduct(p, amount);
        for(ShoppingItem si: sc.getItems()){
             cpo.add(new CartProdObject(si, imf));
        }
        imf.updateCartPanel(cpo);    
    }
    
    public void listCatProds(ArrayList<String> s, IMatFrame imf){
            productCards.clear();
            products.clear();
            
            for (int i = 0; i < s.size(); i++){
                products.addAll((iMDH.getProducts(ProductCategory.valueOf(s.get(i)))));
            }
            
            for (int i = 0; i < products.size(); i++){
                
            
                for (int j = 0; j<imf.allProductCards.size(); j++){
                   if (imf.allProductCards.get(j).getProduct() == products.get(i)){
                       productCards.add(imf.allProductCards.get(j));
                       
                }
            }
        
        }
           /*
//           this.productCards.removeAll(productCards);
           
           for (int i = 0; i<products.size(); i++){
//               System.out.println("prodcardssize: " + imf.allProductCards.size());
            for (int j = 0; j<imf.allProductCards.size(); j++){
//                System.out.println("imf allprdc j : " + imf.allProductCards.get(j).getProduct());
                   if (imf.allProductCards.get(j).getProduct() == products.get(i)){
                       productCards.add(imf.allProductCards.get(j));
                       
//                       System.out.println("productcards: " + imf.allProductCards.get(j).getProduct().getName());
                }
            }
    }
           
//        List<Product> tempProdList;
//       String tempString = s.get(0);
        this.products.clear();
         for (int i = 0; i < s.size(); i++){
//             tempProd = iMDH.getProducts(ProductCategory.valueOf(tempString));
//        tempProdList = (iMDH.getProducts(ProductCategory.valueOf(s.get(i))));
        this.products.addAll((iMDH.getProducts(ProductCategory.valueOf(s.get(i)))));
        }
        
         this.productCards.clear();
           this.productCards.removeAll(productCards);

           for(int i=0; i<products.size(); i++){
               productCards.add(new ProductCard(products.get(i), imf));
           }
           
//        System.out.println(iMDH.getProducts(ProductCategory.valueOf(s)));
        */
    } 

    
}
