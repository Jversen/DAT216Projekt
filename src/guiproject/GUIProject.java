/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiproject;

import java.util.Iterator;
import java.util.List;
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
//           Iterator<Product> iterator = products.iterator();
//           int i=0;
//           while(iterator.hasNext()){
//               methodToMakeCard(iterator.next());
//               i++;
           for(int i=0; i<products.size(); i++){
               methodToMakeCard(products.get(i);
           
           }
    }
    
    public static void main(String[] args) {
        IMatFrame app = new IMatFrame();
        app.setVisible(true);
        
        
        // TODO code application logic here
    }
    
}
