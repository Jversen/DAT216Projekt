package guiproject;

import java.awt.GridLayout;
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
    
    
    public ImageIcon getImageCustom(Product prod, int x, int y){
        
            return iMDH.getImageIcon(prod,x,y);
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
        
        public void getFavorites(IMatFrame imf){
        
            this.productCards.clear();
            for (int i = 0; i<imf.allProductCards.size(); i++){
                   if (iMDH.isFavorite(imf.allProductCards.get(i).getProduct())){
                       productCards.add(imf.allProductCards.get(i));
                   }
            }
    }
        
        public void getHistory(IMatFrame imf, int orderIndex){
        boolean pinkBG = true;

           Order order = iMDH.getOrders().get(orderIndex);
           
           products.clear();
           productCards.clear();
           historyCards.clear();


           
           for (int i = 0; i<order.getItems().size(); i++){
               historyCards.add(new HistoryObjectCard(order.getItems().get(i),imf, pinkBG));
               if (pinkBG == true){
                pinkBG = false;
            } else if (pinkBG == false){
                pinkBG = true;
            }
           }
           

    }
    
    public void addToCart(Product p, int amount, IMatFrame imf){
        boolean pinkBG = true;
        cpo.clear();
        if(p!=null){
          sc.addProduct(p, amount);
        }  
        for(ShoppingItem si: sc.getItems()){
            cpo.add(new CartProdObject(si, imf, pinkBG));
            if (pinkBG == true){
                pinkBG = false;
            } else if (pinkBG == false){
                pinkBG = true;
            }
        }  
        imf.displayCartPanel(cpo);    
        addToCheckoutCart(cpo, imf);
        imf.setLabelCartNbr(""+sc.getItems().size());
        imf.setLabelCartCost(""+sc.getTotal());
        imf.setCashierPriceLabel(""+sc.getTotal());
        
        if (cpo.size()<5){
        GridLayout layout = new GridLayout(cpo.size()+4,1, 0,0);
        imf.setCartLayout(layout);
        } else {
            GridLayout layout = new GridLayout(cpo.size(),1, 0,0);
            imf.setCartLayout(layout);
        }
    }

    public void addToCheckoutCart(ArrayList<CartProdObject> cpo, IMatFrame imf){
        cpoCheckout.clear();
        boolean pinkBG = true;
        
        for(CartProdObject cp: cpo){
            
            cpoCheckout.add(new CheckoutProductCard(cp.getShoppingItem(), imf, pinkBG));
            if (pinkBG == true){
                pinkBG = false;
            } else if (pinkBG == false){
                pinkBG = true;
            }
        }
        imf.displayCheckoutCart(cpoCheckout);
    }    
    
    public void addHistoryToCart(IMatFrame imf){
            for (int i = 0; i<historyCards.size(); i++){
                addToCart(historyCards.get(i).getShoppingItem().getProduct(), (int) historyCards.get(i).getShoppingItem().getAmount(), imf);
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
