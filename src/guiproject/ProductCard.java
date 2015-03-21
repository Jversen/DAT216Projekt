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
    IMatFrame imf;
    
    public ProductCard(Product prod, IMatFrame imf) {
        initComponents();
        this.prod = prod;
        
        this.imf = imf;
        pictureLabel.setIcon(gpCon.getImageCustom(prod, 100, 100));
        productCardName.setText(prod.getName());
        priceLabel.setText(""+ prod.getPrice() + " " + prod.getUnit());
        
        updateHeart();
        if (!gpCon.iMDH.isFavorite(prod)){
        pcFavLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/invisibleHeart.png")));
        }
       }
      
      public void updateHeart(){
          if (gpCon.iMDH.isFavorite(prod)){
            pcFavLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/filledHeart.png")));
        }else{
            pcFavLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyHeart.png")));
        }
      }
      public void toggleFavorite(){

          if (gpCon.iMDH.isFavorite(prod)){
            gpCon.iMDH.removeFavorite(prod);
        } else if (!gpCon.iMDH.isFavorite(prod)){
            gpCon.iMDH.addFavorite(prod);
        }
         

        updateHeart();
        gpCon.getFavorites(imf);
        gpCon.iMDH.shutDown();
      }

    public int getSpinnerValue(){
        int value = (Integer) jSpinner1.getValue();
        return value;
    }
    
    public Product getProduct(){
        Product pc = prod;
        return pc;
    }
  
    public void unHoverHeart(){
        if (!gpCon.iMDH.isFavorite(prod)){
        pcFavLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/invisibleHeart.png")));
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        priceLabel = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        addProductButton = new javax.swing.JButton();
        Body = new javax.swing.JPanel();
        pictureLabel = new javax.swing.JLabel();
        Head = new javax.swing.JPanel();
        productCardName = new javax.swing.JLabel();
        pcFavLabel = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(201, 32, 32), new java.awt.Color(166, 3, 3)));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 248, 248));

        priceLabel.setText("Pris");
        jPanel1.add(priceLabel);

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 0, 100, 1));
        jSpinner1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jSpinner1MouseEntered(evt);
            }
        });
        jPanel1.add(jSpinner1);

        addProductButton.setText("Lägg till");
        addProductButton.setToolTipText("Lägg till i kundvagn");
        addProductButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addProductButtonMouseEntered(evt);
            }
        });
        addProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductButtonActionPerformed(evt);
            }
        });
        jPanel1.add(addProductButton);

        add(jPanel1, java.awt.BorderLayout.PAGE_END);

        Body.setBackground(new java.awt.Color(255, 248, 248));
        Body.add(pictureLabel);

        add(Body, java.awt.BorderLayout.CENTER);

        Head.setBackground(new java.awt.Color(196, 158, 158));

        productCardName.setText("jLabel1");

        pcFavLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/invisibleHeart.png"))); // NOI18N
        pcFavLabel.setToolTipText("Lägg till i favoriter");
        pcFavLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pcFavLabelMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pcFavLabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout HeadLayout = new javax.swing.GroupLayout(Head);
        Head.setLayout(HeadLayout);
        HeadLayout.setHorizontalGroup(
            HeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeadLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(productCardName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(pcFavLabel))
        );
        HeadLayout.setVerticalGroup(
            HeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeadLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(productCardName))
            .addComponent(pcFavLabel)
        );

        add(Head, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void addProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductButtonActionPerformed
        gpCon.addToCart(this.prod, this.getSpinnerValue(), imf);
        gpCon.iMDH.shutDown();
    }//GEN-LAST:event_addProductButtonActionPerformed

    private void pcFavLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pcFavLabelMousePressed
        toggleFavorite();
    }//GEN-LAST:event_pcFavLabelMousePressed

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        updateHeart();
        
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        unHoverHeart();
    }//GEN-LAST:event_formMouseExited

    private void jSpinner1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSpinner1MouseEntered
        updateHeart();
    }//GEN-LAST:event_jSpinner1MouseEntered

    private void addProductButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addProductButtonMouseEntered
        updateHeart();
    }//GEN-LAST:event_addProductButtonMouseEntered

    private void pcFavLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pcFavLabelMouseEntered
        updateHeart();
    }//GEN-LAST:event_pcFavLabelMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Body;
    private javax.swing.JPanel Head;
    private javax.swing.JButton addProductButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel pcFavLabel;
    private javax.swing.JLabel pictureLabel;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JLabel productCardName;
    // End of variables declaration//GEN-END:variables
}
