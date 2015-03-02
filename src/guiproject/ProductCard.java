/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiproject;

import java.awt.Component;
import se.chalmers.ait.dat215.project.*;

/**
 *
 * @author RebeckaReitmaier
 */
public class ProductCard extends javax.swing.JPanel {
    
    GUIProject gpCon = new GUIProject();
    Product prod;
    
    public ProductCard(Product prod) {
        initComponents();
        this.prod = prod;
        pictureLabel.setIcon(gpCon.getImage(prod));
//        this.productName = searchField.getInputContext(); //hur ska man skriva?
        productCardName.setText(prod.getName());
        priceLabel.setText(""+ prod.getPrice());
       }
    
    public int getSpinnerValue(){
        int value = (Integer) jSpinner1.getValue();
        return value;
    }
    
    public Product getProduct(){
        Product pc = prod;
        return pc;
    }
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Body = new javax.swing.JPanel();
        jSpinner1 = new javax.swing.JSpinner();
        addProductButton = new javax.swing.JButton();
        PriceLabel2 = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        Head = new javax.swing.JPanel();
        pictureLabel = new javax.swing.JLabel();
        productCardName = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        Body.setBackground(new java.awt.Color(204, 255, 204));
        Body.add(jSpinner1);

        addProductButton.setText("OK");
        addProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductButtonActionPerformed(evt);
            }
        });
        Body.add(addProductButton);

        PriceLabel2.setText("Pris:");
        Body.add(PriceLabel2);

        priceLabel.setText("Pris");
        Body.add(priceLabel);

        add(Body, java.awt.BorderLayout.CENTER);

        Head.setBackground(new java.awt.Color(0, 204, 204));
        Head.add(pictureLabel);

        productCardName.setText("jLabel1");
        Head.add(productCardName);

        add(Head, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void addProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductButtonActionPerformed
      gpCon.addToCart(this);
    }//GEN-LAST:event_addProductButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Body;
    private javax.swing.JPanel Head;
    private javax.swing.JLabel PriceLabel2;
    private javax.swing.JButton addProductButton;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel pictureLabel;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JLabel productCardName;
    // End of variables declaration//GEN-END:variables
}