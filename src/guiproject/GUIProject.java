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
    /**
     * @param args the command line arguments
     */
        public ImageIcon getImage(Product prod){
        
            return iMDH.getImageIcon(prod,70,70);
    }
    
    public void doSearch(String s){
           this.products = iMDH.findProducts(s);
           productCards.clear();
           
//           for (int i = 0; i<products.size(); i++){
//               System.out.println(products.get(i));
//           }
           
           for(int i=0; i<products.size(); i++){
               productCards.add(new ProductCard(products.get(i)));
           }
//            for(int i=0; i<gpCon.productCards.size(); i++ ){
//                    searchResultPanel.add(gpCon.productCards.get(i));
//        } // TODO add your handling code here:
           
    }
    
    public static void main(String[] args) {
        IMatFrame app = new IMatFrame();
        app.setVisible(true);
        
        
        // TODO code application logic here
    }
    
}
