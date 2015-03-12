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
    public ArrayList <CartProdObject> cpo = new ArrayList<CartProdObject>();
    public List<Product> allProducts = new ArrayList<Product>();
    public List<Order> orderHistory = new ArrayList<Order>();
    public ArrayList<CheckoutProductCard> cpoCheckout = new ArrayList<CheckoutProductCard>();
    public List<HistoryObjectCard> historyCards = new ArrayList<HistoryObjectCard>();
    /**
     * @param args the command line arguments
     */
    
    
    //dessa metoder bör tas bort om tid ges (getImage)
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
           
        public void showHistory(IMatFrame imf, int orderIndex){
        
//           orderHistory = iMDH.getOrders();
           System.out.println("orderIndex inuti showHistory/guip: " + orderIndex);
           Order order = iMDH.getOrders().get(orderIndex);
           
           products.clear();
           productCards.clear();
           historyCards.clear();
           System.out.println("Order history size: " + orderHistory.size());

           
           for (int i = 0; i<order.getItems().size(); i++){
               historyCards.add(new HistoryObjectCard(order.getItems().get(i),imf));
           }
           

    }
    
    public void addToCart(Product p, int amount, IMatFrame imf){
        cpo.clear();
        if(p!=null){
          sc.addProduct(p, amount);
        }  
        for(ShoppingItem si: sc.getItems()){
            cpo.add(new CartProdObject(si, imf));
        }  
        imf.displayCartPanel(cpo);    
        addToCheckoutCart(cpo, imf);
        }

    public void addToCheckoutCart(ArrayList<CartProdObject> cpo, IMatFrame imf){
        cpoCheckout.clear();
        System.out.println("cpo size: " + cpo.size());
        for(CartProdObject cp: cpo){
            cpoCheckout.add(new CheckoutProductCard(cp.getShoppingItem(), imf));
        }
        System.out.println("cpoCheckout size: " + cpoCheckout.size());
        //imf.displayCheckoutCart(cpoCheckout);
        imf.displayCheckoutCart(cpoCheckout);
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
