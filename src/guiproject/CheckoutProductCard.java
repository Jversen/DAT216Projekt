/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiproject;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 *
 * @author Jenny
 */
public class CheckoutProductCard extends javax.swing.JPanel {

    private IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    private Product product;

    /**
     * Creates new form CheckoutProductCard
     */
    public CheckoutProductCard() {
        initComponents();
    }

    public CheckoutProductCard(ShoppingItem si, IMatFrame imf){
        this.checkoutProductLabel = new JLabel();
        this.checkoutPriceLabel = new JLabel();
        this.checkoutProductIcon = new JLabel();
        
        this.product = si.getProduct();

        //System.out.print(product.getName() + " hej");
        
        this.checkoutProductLabel.setText(product.getName());
        this.checkoutPriceLabel.setText(product.getPrice() + "");
        this.checkoutProductIcon.setIcon(imf.gpCon.getImage100(product));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        checkoutProductIcon = new javax.swing.JLabel();
        checkoutProductLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        checkoutPriceLabel = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();

        checkoutProductIcon.setBackground(new java.awt.Color(255, 255, 255));
        checkoutProductIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guiKundvagn.png"))); // NOI18N
        checkoutProductIcon.setMaximumSize(new java.awt.Dimension(50, 50));
        checkoutProductIcon.setMinimumSize(new java.awt.Dimension(50, 50));
        checkoutProductIcon.setPreferredSize(new java.awt.Dimension(50, 50));

        checkoutProductLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        checkoutProductLabel.setForeground(new java.awt.Color(51, 51, 51));
        checkoutProductLabel.setText("Produktnamn");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("kr");

        checkoutPriceLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        checkoutPriceLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        checkoutPriceLabel.setText("pris");
        checkoutPriceLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/deleteNormal.png"))); // NOI18N
        deleteButton.setBorder(null);
        deleteButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/deleteHover.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkoutProductIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkoutProductLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 289, Short.MAX_VALUE)
                .addComponent(checkoutPriceLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addComponent(deleteButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(checkoutProductIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(checkoutPriceLabel)
                            .addComponent(checkoutProductLabel)
                            .addComponent(deleteButton))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel checkoutPriceLabel;
    private javax.swing.JLabel checkoutProductIcon;
    private javax.swing.JLabel checkoutProductLabel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}

