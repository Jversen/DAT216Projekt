package guiproject;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import se.chalmers.ait.dat215.project.*;

public class GUIProject{
    
    IMatDataHandler iMDH = IMatDataHandler.getInstance();
    ShoppingCart sc = iMDH.getShoppingCart();
    
    
    public List<Product> products = new ArrayList<Product>();
    public List<ProductCard> productCards = new ArrayList<ProductCard>();
    public List <CartProdObject> cpo = new ArrayList<CartProdObject>();
    public List<Product> allProducts = new ArrayList<Product>();
    public List<Order> orderHistory = new ArrayList<Order>();

    public List<HistoryObjectCard> historyCards = new ArrayList<HistoryObjectCard>();
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
                   if (imf.allProductCards.get(j).getProduct() == products.get(i)){
                       productCards.add(imf.allProductCards.get(j));
                }                  
            }
        }
    }
           
    public void showHistory(IMatFrame imf){
        
           orderHistory = iMDH.getOrders();
           products.clear();
           productCards.clear();
           System.out.println("Order history size: " + orderHistory.size());
//           for (int i = 0; i<orderHistory.size(); i++){
//               for (int j = 0; j<orderHistory.get(i).getItems().size(); j++){
//                    products.add(orderHistory.get(i).getItems().get(j).getProduct());
//               }
//           }
           
           for (int i = 0; i<orderHistory.size();i++){
               for (int j = 0; j<orderHistory.get(i).getItems().size(); j++){
               historyCards.add(new HistoryObjectCard(orderHistory.get(i).getItems().get(j),imf));
           }

           }
//           for (int i = 0; i<products.size(); i++){
//            for (int j = 0; j<imf.allProductCards.size(); j++){
//                   if (imf.allProductCards.get(j).getProduct() == products.get(i)){
//                       productCards.add(imf.allProductCards.get(j));
//                }
//            }
//    }
    }
    
    /**
     * Work in progress... 
     */

    public void addToCart(Product p, int amount, IMatFrame imf){
        if(p==null){
            cpo.clear();
        for(ShoppingItem si: sc.getItems()){
             cpo.add(new CartProdObject(si, imf));
        }
        imf.updateCartPanel(cpo);    
        }else{
             cpo.clear();
        sc.addProduct(p, amount);
        for(ShoppingItem si: sc.getItems()){
             cpo.add(new CartProdObject(si, imf));
        }
        imf.updateCartPanel(cpo);    
        }
       
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
    }  
}
